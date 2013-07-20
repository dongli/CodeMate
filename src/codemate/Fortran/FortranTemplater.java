package codemate.Fortran;

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

import codemate.Fortran.FortranParser.*;
import codemate.ui.*;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.Map.Entry;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class FortranTemplater extends FortranBaseVisitor<Void> {
    private static List<TemplateBundle> templateBundles =
    		new ArrayList<TemplateBundle>();

    public FortranTemplater() { }
    
    /**
     * readTemplates
     * 
     * This method reads templates written in FreeMarker from the files with
     * ".ftl" suffix under the given directory.
     * 
     * @author Li Dong <dongli@lasg.iap.ac.cn>
     */
    public static void readTemplates(File dir) {
    	List<String> templateClasses = new ArrayList<String>();
    	for (String fileName : dir.list()) {
            if (fileName.endsWith("Template.java")) {
            	UI.notice("FortranTemplater",
            			"Read template from "+fileName+".");
            	String templateClass = fileName.split("\\.")[0];
                JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
                compiler.run(null, null, null, dir.getPath()+"/"+fileName);
                templateClasses.add(templateClass);
            }
        }
        URLClassLoader classLoader = null;
		try {
			classLoader = URLClassLoader.newInstance(
					new URL[] {dir.toURI().toURL()});
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        for (String templateClass : templateClasses) {
        	Class<?> cls = null;
			try {
				cls = classLoader.loadClass(templateClass);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	try {
				templateBundles.add((TemplateBundle) cls.newInstance());
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
    		if (templateBundle.containsTemplate(bundleName))
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
    
    public Void visitTemplateInstance(
    		FortranParser.TemplateInstanceContext ctx) {
        TemplateBundle matchedTemplateBundle =
            searchTemplateBundle(ctx.id().getText());
        if (matchedTemplateBundle != null) {
            if (ctx.getParent().getRuleIndex() ==
            		FortranParser.RULE_extendsAttribute) {
            	// Note: Because the extended type is special, so we handle
            	//       them individually.
                handleTemplateInExtendsAttribute(
                        ctx, matchedTemplateBundle);
            } else {
                handleNormalTemplate(ctx, matchedTemplateBundle);
            }
        }
        return null;
    }

	@SuppressWarnings("unused")
	private Stack<ParserRuleContext> findRoute(
			ParserRuleContext startNode, int endNodeRuleIndex) {
    	Stack<ParserRuleContext> route = new Stack<ParserRuleContext>();
    	ParserRuleContext node = startNode;
        boolean isFound = false;
    	while (!isFound && node != null) {
    		route.push(node);
    		if (node.getRuleIndex() == endNodeRuleIndex)
    			isFound = true;
    		else
    			for (ParseTree child : node.children)
    				if (child instanceof ParserRuleContext) {
                        if (((ParserRuleContext) child).getRuleIndex() ==
                			endNodeRuleIndex) {
                        	route.pop(); // throw the useless parent node away
                        	route.push((ParserRuleContext) child);
                        	// I push a null into the stack to indicate that
                        	// the end node is a sibling node.
                        	route.push(null);
                        	isFound = true;
                        	break;
                        }
    				}
            if (!isFound)
                node = node.getParent();
    	}
    	if (false) {
    		if (route.lastElement() != null)
    			System.out.println("Route (local):");
    		else
    			System.out.println("Route (non-local):");
    		for (ParserRuleContext node_ : route)
    			if (node_ != null)
    				System.out.println("* "+
    					FortranParser.ruleNames[node_.getRuleIndex()]);
    	}
    	return route;
    }
    
	private void applyTemplateInstance(Template template, String instance,
    		ParserRuleContext templateNode) {
    	// parse the new node
    	ParserRuleContext newNode = null;
    	try {
    		newNode = (ParserRuleContext)
    				FortranProcessor.callParser(template.getType(), instance);
    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
    	}
    	// place the new node
    	Stack<ParserRuleContext> route =
    			findRoute(templateNode, template.getType());
    	ParserRuleContext rootNode = null, markNode = null;
    	// ---------------------------------------------------------------------
    	// local modification
    	if (route.lastElement() != null) {
    		rootNode = route.pop();
    		markNode = route.pop();
    		for (ParseTree child : rootNode.children) {
    			if (child == markNode) {
    				switch (template.getAction()) {
    				case REPLACE:
    					rootNode.children.addAll(
        						rootNode.children.indexOf(child),
        						newNode.children);
        				rootNode.children.remove(child);
        				break;
    				case APPEND:
    					rootNode.children.addAll(
        						rootNode.children.indexOf(child)+1,
        						newNode.children);
    					break;
    				case PREPEND:
    					rootNode.children.addAll(
        						rootNode.children.indexOf(child),
        						newNode.children);
    					break;
    				}
    				break;
    			}
    		}
    	}
    	// ---------------------------------------------------------------------
    	// non-local modification
    	else {
    		route.pop(); // throw the indicate element away
    		rootNode = route.pop();
    		if (rootNode.children == null)
    			rootNode.children = new LinkedList<ParseTree>();
    		switch (template.getAction()) {
    		case REPLACE:
    			UI.error("FortranTemplater",
    					"Sorry, CodeMate does not support non-local replacing"+
    					" when instantiating template!");
    			break;
    		case APPEND:
    			rootNode.children.addAll(newNode.children);
    			break;
    		case PREPEND:
    			rootNode.children.addAll(0, newNode.children);
    			break;
    		}
    	}
    }

    private void handleNormalTemplate(
            TemplateInstanceContext templateNode,
            TemplateBundle templateBundle) {
        // prepare template arguments
        List<String> templateArguments = null;
        if (templateNode.templateArguments() != null) {
            templateArguments = new ArrayList<String>();
            for (TemplateArgumentContext arg :
                templateNode.templateArguments().templateArgument()) {
                FortranRewriter rewriter = new FortranRewriter();
                rewriter.visitTemplateArgument(arg);
                templateArguments.add(rewriter.getNewCode());
            }
        }
        // prepare template block
        String templateBlock = null;
        if (templateNode.templateBlock() != null) {
            templateBlock = new String();
            FortranRewriter rewriter = new FortranRewriter();
            rewriter.visitTemplateBlock(templateNode.templateBlock());
            templateBlock = rewriter.getNewCode();
        }
        // instantiate template
        Map<Template, String> templateInstances =
            templateBundle.instantiate(templateNode.id().getText(),
            		templateArguments, templateBlock);
        // apply template instances to the parse tree
        for (Entry<Template, String> instance : templateInstances.entrySet()) {
        	applyTemplateInstance(instance.getKey(), instance.getValue(),
        			templateNode);
        }
    }

    private void handleTemplateInExtendsAttribute(
    		TemplateInstanceContext templateNode,
            TemplateBundle templateBundle) {
        TypeDeclarationStatementContext typeDecl =
        	(TypeDeclarationStatementContext) templateNode.
        		getParent().getParent().getParent().getParent();
        // prepare template arguments
        List<String> templateArguments = new ArrayList<String>();
        for (TemplateArgumentContext arg :
            templateNode.templateArguments().templateArgument()) {
            FortranRewriter rewriter = new FortranRewriter();
            rewriter.visitTemplateArgument(arg);
            templateArguments.add(rewriter.getNewCode());
        }
        if (typeDecl.dataDeclarationStatements() != null) {
        	// add data declaration statements into arguments for
        	// inserting them in the extended type declaration
        	// statement
        	FortranRewriter rewriter = new FortranRewriter();
        	rewriter.visit(typeDecl.dataDeclarationStatements());
        	templateArguments.add(rewriter.getNewCode());
        } else {
        	templateArguments.add("");
        }
        if (typeDecl.containedTypeBoundProcedures() != null) {
        	// add type bound procedures into arguments for
        	// inserting them in the extended type declaration
        	// statement
        	FortranRewriter rewriter = new FortranRewriter();
        	rewriter.visit(typeDecl.containedTypeBoundProcedures().
        			typeBoundProcedureStatements());
        	templateArguments.add(rewriter.getNewCode());
        } else {
        	templateArguments.add("");
        }
        // instantiate template
        Map<Template, String> templateInstances =
        		templateBundle.instantiate(templateNode.id().getText(),
        				templateArguments, null);
        // apply template instances to the parse tree
        for (Entry<Template, String> instance : templateInstances.entrySet()) {
    		applyTemplateInstance(instance.getKey(), instance.getValue(),
    				templateNode);
        }
    }
    
    /**
     * visitChildren
     * 
     * This method overrides the original one which will be interfered when I
     * make changes to the parse tree (e.g. inserting multiple nodes) by
     * changing the loop style.
     */
    public Void visitChildren(RuleNode node) {
        Void result = defaultResult();
        for (int i = 0; i < node.getChildCount(); i++) {
            if (!shouldVisitNextChild(node, result))
                break;
            ParseTree c = node.getChild(i);
            Void childResult = c.accept(this);
            result = aggregateResult(result, childResult);
        }
        return result;
    }
}
