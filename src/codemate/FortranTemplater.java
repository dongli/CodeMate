package codemate;

/**
 * FortranTemplater
 *
 * This class instantiates the template instances in the Fortran codes that have
 * been parsed.
 *
 * @author      Li Dong <dongli@lasg.iap.ac.cn>
 */

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import freemarker.template.*;

import java.io.*;
import java.util.*;

public class FortranTemplater extends FortranBaseVisitor<Void> {
    private static ArrayList<TemplateBundle> templateBundles =
    		new ArrayList<TemplateBundle>();

    FortranTemplater() { }
    
    /**
     * readTemplates
     * 
     * This method reads templates written in FreeMarker from the files with
     * ".ftl" suffix under the given directory.
     * 
     * @author Li Dong <dongli@lasg.iap.ac.cn>
     */
    public static void readTemplates(File dir) {
        Configuration cfg = new Configuration();
        try {
        	cfg.setDirectoryForTemplateLoading(dir);
        } catch (IOException e) {
        	UI.error("FortranTemplater",
        			"The directory \""+dir+"\""+
        			"for reading template definitions does not exist!");
        }
        cfg.setObjectWrapper(new DefaultObjectWrapper());
    	for (String fileName : dir.list()) {
            if (fileName.endsWith(".ftl")) {
            	UI.notice("FortranTemplater",
            			"Read template from "+fileName+".");
            	String ruleAndTask = fileName.split("\\.")[0];
            	String bundleName = fileName.split("\\.")[1];
            	TemplateBundle templateBundle =
            			searchTemplateBundle(bundleName);
            	if (templateBundle == null) {
            		templateBundle = new TemplateBundle(bundleName);
            		templateBundles.add(templateBundle);
            	}
            	try {
            		templateBundle.addTemplate(ruleAndTask,
            				cfg.getTemplate(fileName));
            	} catch (IOException e) {
            		UI.error("FortranTemplater",
            				"The template file \""+fileName+"\""+
            				"does not exist!");
            	}
            }
        }
    }
    
    /**
     * searchTemplateBundle
     * 
     * This method searches the template bundle with the given name.
     * 
     * @param bundleName
     */
    private static TemplateBundle searchTemplateBundle(String bundleName) {
    	for (TemplateBundle templateBundle : templateBundles)
    		if (templateBundle.getBundleName().equals(bundleName))
    			return templateBundle;
    	return null;
    }

    /**
     * instantiate
     *
     * This method visits the tree to find out template instances and
     * instantiate them if there is a template matched.
     *
     * @param       tree        The parse tree
     *
     * @author      Li Dong <dongli@lasg.iap.ac.cn>
     */
    public void instantiate(ParseTree tree) {
        visit(tree);
    }

    /**
     * searchNodeUpward
     *
     * This method searches the sibling and parent nodes of a given node for
     * matching specified rule name.
     *
     * @param       currNode    The starting node.
     * @param       ruleName    The rule name of the parent node looked for.
     * @return                  The matched node or null if nothing found.
     *
     * @author      Li Dong <dongli@lasg.iap.ac.cn>
     * @since       2013-07-12
     */ 
    private ParserRuleContext searchNodeUpward(
    		ParserRuleContext currNode, String ruleName) {
        ParserRuleContext node = currNode;
        boolean isFound = false;
        while (!isFound && node != null) {
            if (FortranParser.ruleNames[node.getRuleIndex()].equals(ruleName))
                isFound = true;
            else
                for (ParseTree child : node.children)
                    if (child instanceof ParserRuleContext)
                        if (FortranParser.ruleNames[((ParserRuleContext) child).
                                                    getRuleIndex()].
                                                    equals(ruleName)) {
                            node = (ParserRuleContext) child;
                            isFound = true;
                            break;
                        }
            if (!isFound)
                node = node.getParent();
        }
        return node;
    }

    /**
     * modifyTree
     *
     * This method modifies the parse tree.
     *
     * @author      Li Dong <dongli@lasg.iap.ac.cn>
     */
    private void modifyTree(String ruleName, String taskName,
                            ParserRuleContext currNode, ParseTree subtree) {
        if (taskName.equals("append")) {
            ParserRuleContext targetNode = searchNodeUpward(currNode, ruleName);
            if (targetNode != null) {
            	for (int i = 0; i < subtree.getChildCount(); ++i)
            		targetNode.addChild(
            				(ParserRuleContext) subtree.getChild(i));
            } else {
            	// When there is no node matches 'ruleName', we should add them
            	// into proper place.
            	if (ruleName.equals("containedProcedures")) {
            		targetNode = searchNodeUpward(currNode, "procedure");
            		// add the 'contains' keyword
            		TerminalNodeImpl containsNode =
            				new TerminalNodeImpl(
            						new CommonToken(
            								FortranParser.CONTAINS_KEYWORD));
            		targetNode.addChild(containsNode);
            		targetNode.addChild((ParserRuleContext) subtree);
            	}
            }
        } else if (taskName.equals("replace")) {
            ParserRuleContext parentNode = currNode.getParent().getParent();
            int idx = parentNode.children.indexOf(currNode.getParent());
            parentNode.children.remove(currNode.getParent());
            for (int i = subtree.getChildCount()-1; i >= 0; --i)
                parentNode.children.add(idx,
                		(ParserRuleContext) subtree.getChild(i));
        }
    }

    public Void visitTemplateInstance(
    		FortranParser.TemplateInstanceContext ctx) {
    	// Note: The node that needs to be modified may not be 'ctx'.
    	ParserRuleContext modifiedNode = ctx;
        String bundleName = ctx.id().getText();
        // find the matched template definition
        TemplateBundle matchedTemplateBundle = searchTemplateBundle(bundleName);
        if (matchedTemplateBundle != null) {
            ArrayList<String> templateArguments = null;
            if (ctx.templateArguments() != null) {
            	// prepare template arguments
                List<FortranParser.TemplateArgumentContext> args;
                args = ctx.templateArguments().templateArgument();
                templateArguments = new ArrayList<String>();
                for (int i = 0; i < args.size(); ++i) {
                    FortranRewriter rewriter = new FortranRewriter();
                    rewriter.visitTemplateArgument(args.get(i));
                    templateArguments.add(rewriter.getNewCode());
                }
            	if (ctx.getParent().getRuleIndex() ==
            			FortranParser.RULE_extendsAttribute) {
            		FortranParser.TypeDeclarationStatementContext typeDecl =
            				(FortranParser.TypeDeclarationStatementContext)
            				searchNodeUpward(ctx, "typeDeclarationStatement");
            		// Note: When template instance occurs in the 'extends' type
            		//       attribute, we should replace the current type
            		//       declaration statement.
            		modifiedNode = typeDecl;
            		if (typeDecl.dataDeclarationStatements() != null) {
            			// add data declaration statements into arguments for
            			// inserting them in the extended type declaration
            			// statement
            			FortranRewriter rewriter = new FortranRewriter();
            			rewriter.visit(typeDecl.dataDeclarationStatements());
            			templateArguments.add(rewriter.getNewCode());
            		}
            		if (typeDecl.containedTypeBoundProcedures() != null) {
            			// add type bound procedures into arguments for
            			// inserting them in the extended type declaration
            			// statement
            			FortranRewriter rewriter = new FortranRewriter();
            			rewriter.visit(typeDecl.containedTypeBoundProcedures().
            					typeBoundProcedureStatements());
            			templateArguments.add(rewriter.getNewCode());
            		}
            	}
            }
            // prepare template block
            String templateBlock = null;
            if (ctx.templateBlock() != null) {
                templateBlock = new String();
                FortranRewriter rewriter = new FortranRewriter();
                rewriter.visitTemplateBlock(ctx.templateBlock());
                templateBlock = rewriter.getNewCode();
//                System.out.println("template block:");
//                System.out.println(templateBlock);
            }
            // instantiate template
            Map<String, String> templateInstances =
                matchedTemplateBundle.instance(templateArguments, templateBlock);
            // parse the new added codes and put them in the right place
            for (Map.Entry<String, String> instance : templateInstances.entrySet()) {
                String[] tokens = instance.getKey().split("_");
                String ruleName = tokens[0];
                String taskName = tokens[1];
//                System.out.println("template instance: "+ruleName);
//                System.out.println(instance.getValue());
                ParseTree subtree = null;
                try {
                    subtree = FortranProcessor.callParser(ruleName, instance.getValue());
                } catch (Exception e) {
                    System.out.println("Encounter error while parsing!");
                    System.exit(1);
                }
//                FortranRewriter rewriter = new FortranRewriter();
//                rewriter.rewrite(subtree);
//                System.out.println(rewriter.getNewCode());
//                System.out.println("================================");
                modifyTree(ruleName, taskName, modifiedNode, subtree);
            }
        }
        return null;
    }
}
