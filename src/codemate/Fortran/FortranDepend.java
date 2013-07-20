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

import org.antlr.v4.runtime.tree.*;

import codemate.librarymate.*;
import codemate.project.*;
import codemate.ui.*;

public class FortranDepend extends FortranBaseListener {
	private List<String> usedModules = new ArrayList<String>();
	private List<String> procedureTypes = new ArrayList<String>();
	
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
					UI.warning("FortranDepend",
							"Can't find \""+moduleName+"\" in project!");
				}
			}
		}
		// discern code type
		if (procedureTypes.contains("program"))
			entity.setType("executable");
		else
			entity.setType("object");
	}
	
	public void enterFile(FortranParser.FileContext ctx) {
		for (FortranParser.ProcedureContext procedure : ctx.procedure())
			procedureTypes.add(procedure.PROCEDURE_TYPE(0).getText());
	}
	
	public void enterUseStatement(FortranParser.UseStatementContext ctx) {
		usedModules.add(ctx.id().getText());
	}
}
