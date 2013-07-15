package codemate;

/**
 * FortranProcessor
 *
 * This program is used to process Fortran codes with augmented template
 * mechanism. By using visitor model, the nodes in the parse tree will be
 * visited, during which the code will be rewrited and the templates will be
 * expanded.
 *
 * @author      Li Dong <dongli@lasg.iap.ac.cn>
 */

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.*;

public class FortranProcessor {
    FortranProcessor() { }
    
    /**
     * process
     * 
     * This method processes the given project by calling 'callParser',
     * 'callTemplater' and 'callRewriter'.
     * 
     * @param		project			The project to be processed
     * 
     * @author 		Li Dong <dongli@lasg.iap.ac.cn>
     */
    public void process(Project project) {
    	// first read template definitions under the root of project
    	FortranTemplater.readTemplates(project.getRoot());
    	for (CodeEntity entity : project.entities) {
    		try {
    			callParser(entity);
    		} catch (Exception e) {
    			UI.error("FortranProcessor",
    					"Encounter error while parsing "+entity.getPath()+"!");
    		}
			callTemplater(entity);
			callRewriter(entity);
			callModuleDepend(entity, project);
    	}
    }

    /**
     * callParser
     *
     * This method parses the given input stream from root 'file' rule.
     *
     * @param       entity     		The code entity to be parsed
     *
     * @author      Li Dong <dongli@lasg.iap.ac.cn>
     */
    public void callParser(CodeEntity entity) throws Exception {
    	InputStream inputStream = new FileInputStream(entity.getPath());
        ANTLRInputStream input = new ANTLRInputStream(inputStream);
        FortranLexer lexer = new FortranLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        FortranParser parser = new FortranParser(tokens);
        entity.setParseTree(parser.file());
    }

    /**
     * callParser
     *
     * This method parses pieces of Fortran code. It is called by
     * FortranTemplater.
     *
     * @param       ruleName        The starting parser rule.
     * @param       source          A input string for parsing.
     * @return                      The parse tree.
     *
     * @author      Li Dong <dongli@lasg.iap.ac.cn>
     */
    public static ParseTree callParser(String ruleName, String source) throws Exception {
        ANTLRInputStream input = new ANTLRInputStream(source);
        FortranLexer lexer = new FortranLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        FortranParser parser = new FortranParser(tokens);
        ParseTree tree = null;
        // NOTE: We set the rules that can be used.
        if (ruleName.equals("declarationStatements")) {
            tree = parser.declarationStatements();
        } else if (ruleName.equals("executableStatements")) {
            tree = parser.executableStatements();
        } else if (ruleName.equals("typeDeclarationStatement")) {
        	tree = parser.typeDeclarationStatement();
        } else if (ruleName.equals("containedProcedures")) {
        	tree = parser.containedProcedures();
        } else {
            // TODO: Consider throw an exception.
            UI.error("FortranProcessor",
            		"Unknown parser rule \""+ruleName+"\" "+
            		"when parsing code pieces!");
        }
        return tree;
    }

    /**
     * callTemplater 
     *
     * This method calls FortranTemplater class to process the template
     * instances in the Fortran codes.
     *
     * @author      Li Dong <dongli@lasg.iap.ac.cn>
     */
    public void callTemplater(CodeEntity entity) {
    	FortranTemplater templater = new FortranTemplater();
        templater.instantiate(entity.getParseTree());
    }

    /**
     * callRewriter 
     *
     * This method calls FortranRewriter class to rewrite the Fortran codes.
     *
     * @author      Li Dong <dongli@lasg.iap.ac.cn>
     */
    public void callRewriter(CodeEntity entity) {
        FortranRewriter rewriter = new FortranRewriter();
        rewriter.rewrite(entity.getParseTree());
        System.out.println(rewriter.getNewCode());
    }
    
    public void callModuleDepend(CodeEntity entity, Project project) {
    	FortranDepend moduleDepend = new FortranDepend();
    	moduleDepend.extract(entity, project);
    }
}
