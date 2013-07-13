package codemate;

/**
 * FortranTemplater
 *
 * This class instantiates the template instances in the Fortran codes.
 *
 * @author      Li Dong <dongli@lasg.iap.ac.cn>
 */

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.*;
import java.util.*;

public class FortranTemplater extends FortranBaseVisitor<Void> {
    private static ArrayList<Template> templates = new ArrayList<Template>();

    FortranTemplater() { }
    
    /**
     * readTemplateDefinitions
     * 
     * This method reads template definitions from the source template group
     * files under the given directory.
     * 
     * @author Li Dong <dongli@lasg.iap.ac.cn>
     */
    public static void readTemplateDefinitions(File dir) {
    	for (String fileName : dir.list()) {
            if (fileName.endsWith(".stg")) {
            	UI.notice("FortranTemplater", "Read template definition from "+dir+".");
                templates.add(new Template(fileName));
            }
        }
    }

    /**
     * instantiate
     *
     * This method visits the tree to find out template instances and
     * instantiate them if there is a template definition matched.
     *
     * @param       tree        The parse tree
     *
     * @author      Li Dong <dongli@lasg.iap.ac.cn>
     */
    public void instantiate(ParseTree tree) {
        visit(tree);
    }

    // -------------------------------------------------------------------------
    /**
     * searchUpward
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
    private ParserRuleContext searchUpward(ParserRuleContext currNode, String ruleName) {
        ParserRuleContext node = currNode.getParent();
        boolean isFound = false;
        while (!isFound) {
            if (FortranParser.ruleNames[node.getRuleIndex()].equals(ruleName))
                isFound = true;
            else {
                for (ParseTree child : node.children)
                    if (child instanceof ParserRuleContext)
                        if (FortranParser.ruleNames[((ParserRuleContext) child).getRuleIndex()].equals(ruleName)) {
                            node = (ParserRuleContext) child;
                            isFound = true;
                            break;
                        }
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
                            FortranParser.TemplateInstanceContext currNode,
                            ParseTree subtree) {
        if (taskName.equals("append")) {
            ParserRuleContext targetNode = searchUpward(currNode, ruleName);
            if (targetNode != null)
                for (int i = 0; i < subtree.getChildCount(); ++i)
                    targetNode.addChild((ParserRuleContext) subtree.getChild(i));
        } else if (taskName.equals("replace")) {
            ParserRuleContext parentNode = currNode.getParent().getParent();
            int idx = parentNode.children.indexOf(currNode.getParent());
            parentNode.children.remove(currNode.getParent());
            for (int i = subtree.getChildCount()-1; i >= 0; --i)
                parentNode.children.add(idx, (ParserRuleContext) subtree.getChild(i));
        }
    }

    public Void visitTemplateInstance(FortranParser.TemplateInstanceContext ctx) {
        String templateName = ctx.id().getText();
        // find the matched template definition
        Template matchedTemplate = null;
        for (Template template : templates)
            if (template.getName().equals(templateName)) {
                matchedTemplate = template;
                break;
            }
        if (matchedTemplate != null) {
            String[] templateArguments = null;
            if (ctx.templateArguments() != null) {
                List<FortranParser.TemplateArgumentContext> args;
                args = ctx.templateArguments().templateArgument();
                templateArguments = new String[args.size()];
                for (int i = 0; i < args.size(); ++i) {
                    FortranRewriter rewriter = new FortranRewriter();
                    rewriter.visitTemplateArgument(args.get(i));
                    templateArguments[i] = rewriter.getNewCode();
                }
            }
            String templateBlock = null;
            if (ctx.templateBlock() != null) {
                templateBlock = new String();
                FortranRewriter rewriter = new FortranRewriter();
                rewriter.visitTemplateBlock(ctx.templateBlock());
                templateBlock = rewriter.getNewCode();
                System.out.println("template block:");
                System.out.println(templateBlock);
            }
            Map<String, String> templateInstances =
                matchedTemplate.instance(templateArguments, templateBlock);
            // parse the new added codes and put them in the right place
            for (Map.Entry<String, String> instance : templateInstances.entrySet()) {
                String[] tokens = instance.getKey().split("_");
                String ruleName = tokens[0];
                String taskName = tokens[1];
                System.out.println("template instance: "+ruleName);
                System.out.println(instance.getValue());
                ParseTree subtree = null;
                try {
                    subtree = FortranProcessor.callParser(ruleName, instance.getValue());
                } catch (Exception e) {
                    System.out.println("Encounter error while parsing!");
                    System.exit(1);
                }
                modifyTree(ruleName, taskName, ctx, subtree);
            }
        }
        return null;
    }
}
