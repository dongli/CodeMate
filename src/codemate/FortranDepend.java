package codemate;

/**
 * FortranDepend
 * 
 * This class extracts the internal and external dependencies of Fortran code.
 * 
 * @author Li Dong <dongli@lasg.iap.ac.cn>
 */

import java.util.*;

public class FortranDepend extends FortranBaseVisitor<Void> {
	private ArrayList<String> usedModules = new ArrayList<String>();
	
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
		visit(entity.getParseTree());
		for (String moduleName : usedModules) {
			CodeEntity depend = project.searchCodeEntity(moduleName);
			if (depend != null) {
				entity.addInternalDepend(depend);
			} else {
				// TODO: Check external dependencies.
				UI.warning("FortranDepend",
						"Can't find \""+moduleName+"\" in project!");
			}
		}
	}
	
	public Void visitUseStatement(FortranParser.UseStatementContext ctx) {
		usedModules.add(ctx.id().getText());
		return null;
	}
}
