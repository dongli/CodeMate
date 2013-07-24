package codemate.Fortran;

/**
 * FortranDepend
 * 
 * This class extracts the internal and external dependencies of Fortran code,
 * and also conduct some other small operations like discern the code type.
 * 
 * @author Li Dong <dongli@lasg.iap.ac.cn>
 */

import java.util.*;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import codemate.Fortran.FortranParser.*;
import codemate.librarymate.*;
import codemate.project.*;
import codemate.ui.*;

public class FortranDepend extends FortranBaseListener {
	private List<String> usedModules = new ArrayList<String>();
    private List<String> definedMacros = new LinkedList<String>();
    private List<String> undefinedMacros = new LinkedList<String>();
    private List<String> addedMacros = new LinkedList<String>();
	private List<String> procedureTypes = new ArrayList<String>();
    private Stack<String> activeStack = new Stack<String>();
	
	/**
	 * extract
	 * 
	 * This method extracts the dependencies of given Fortran code in a project.
	 * 
	 * @param entity
	 * @param project
	 */
	public void extract(CodeEntity entity, Project project) {
		usedModules.clear();
		procedureTypes.clear();
        activeStack.clear();
		ParseTreeWalker walker = new ParseTreeWalker();
		walker.walk(this, entity.getParseTree());
		for (String moduleName : usedModules) {
			CodeEntity depend = project.searchCodeEntity(moduleName);
			if (depend != null) {
				entity.addInternalDepend(depend);
			} else {
				LibraryMate libraryMate =
						LibraryMates.searchLibrary("Fortran", moduleName);
				if (libraryMate != null) {
					entity.addExternalDepend(libraryMate);
				} else {
					UI.warning("codemate",
							"Can't find \""+moduleName+"\" in project!");
				}
			}
		}
		// discern code type
		if (procedureTypes.contains("program"))
			entity.setType(CodeEntity.Type.EXECUTABLE);
		else
			entity.setType(CodeEntity.Type.OBJECT);
		project.addedMacros.addAll(addedMacros);
	}
	
	public void enterFile(FileContext ctx) {
		for (ProcedureContext procedure : ctx.procedure())
			procedureTypes.add(procedure.PROCEDURE_TYPE(0).getText());
	}
	
	public void enterUseStatement(UseStatementContext ctx) {
		if (!activeStack.contains("f"))
			usedModules.add(ctx.id().getText());
	}

	public void enterDefineDirective(DefineDirectiveContext ctx) {
		definedMacros.add(ctx.id().getText());
	}
	
	public void enterUndefDirective(UndefDirectiveContext ctx) {
		undefinedMacros.add(ctx.id().getText());
	}
	
    public void enterIfdefDirective(IfdefDirectiveContext ctx) {
    	String macro = ctx.id().getText();
        if (definedMacros.contains(macro))
            activeStack.push("t");
        else if (undefinedMacros.contains(macro) || !isDependMacro(ctx)) {
        	activeStack.push("f");
        } else {
        	UI.warning("codemate", "Encounter dependency macro \""+macro+
        			"\" in ifdef directive, do you want to define it (y/n)?");
        	String[] ans = UI.getAnswer(null);
        	if (ans[0].equals("y")) {
        		definedMacros.add(macro);
        		addedMacros.add(macro);
                activeStack.push("t");
        	} else {
        		undefinedMacros.add(macro);
        		activeStack.push("f");
        	}
        }
    }

    public void enterIfndefDirective(IfndefDirectiveContext ctx) {
    	String macro = ctx.id().getText();
        if (definedMacros.contains(ctx.id().getText())) {
            activeStack.push("f");
        } else if (undefinedMacros.contains(macro) || !isDependMacro(ctx)) {
        	activeStack.push("t");
        } else {
        	UI.warning("codemate", "Encounter dependency macro \""+macro+
        			"\" in ifndef directive, do you want to define it (y/n)?");
        	String[] ans = UI.getAnswer(null);
        	if (ans[0].equals("y")) {
        		definedMacros.add(macro);
        		addedMacros.add(macro);
                activeStack.push("f");
        	} else {
        		undefinedMacros.add(macro);
        		activeStack.push("t");
        	}
        }
    }

    public void enterElseDirective(ElseDirectiveContext ctx) {
        String tmp = activeStack.lastElement();
        activeStack.pop();
        if (tmp.equals("t"))
        	activeStack.push("f");
        else
        	activeStack.push("t");
    }

    public void enterEndifDirective(EndifDirectiveContext ctx) {
        activeStack.pop();
    }
    
    private boolean isDependMacro(ParserRuleContext ctx) {
		if (ctx.getParent() instanceof CppDirectiveContext) {
			ParserRuleContext mark = ctx.getParent();
			ParserRuleContext parent = mark.getParent();
			if (parent instanceof UseStatementsContext) {
				if (parent.children.get(parent.children.size()-1) != mark)
					return true;
				else
					return false;
			}
		}
		return false;
    }
}
