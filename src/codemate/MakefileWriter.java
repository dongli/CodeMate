package codemate;

/**
 * MakefileWriter
 * 
 * This class writes a Makefile for building project, and it should be able run
 * standalone.
 * 
 * @author Li Dong <dongli@lasg.iap.ac.cn>
 */

import java.io.*;
import java.util.*;

public class MakefileWriter {
	public static void write(Project project) {
		CompilerMate compilerMate =
				CompilerMates.getMate(
						Config.getDefaultCompilerVendor("Fortran"));
		// ---------------------------------------------------------------------
		String content = "";
		content += "PROJECT = "+project.getName()+"\n";
		content += "PROJECT_ROOT = "+project.getRoot().getAbsolutePath()+"\n\n";
		content += "VPATH =";
		for (File dir : project.getDirectories())
			content += " \\\n    $(PROJECT_ROOT)/"+dir.getName();
		content += "\n\n";
		content += "FC = "+compilerMate.getCommandName("Fortran")+"\n";
		if (project.getBuildScheme().equals("debug"))
			content += "FFLAGS = "+compilerMate.getDebugOptions()+"\n";
		else if (project.getBuildScheme().equals("release"))
			content += "FFLAGS = "+compilerMate.getReleaseOptions()+"\n";
		content += "\n";
		Set<LibraryMate> libraries = new HashSet<LibraryMate>();
		for (CodeEntity entity : project.entities) {
			if (entity.getType().equals("executable"))
				libraries.addAll(getAllExternalDepends(entity));
		}
		for (LibraryMate library : libraries) {
			content += library.getLibraryName().toUpperCase()+"_ROOT = "+
					library.getRoot()+"\n";
		}
		content += "\n";
		content +=
			"# ------------------------------------------------------------------------------\n" + 
			"# objects, targets and libraries\n";
		content += "OBJECTS =";
		for (CodeEntity entity : project.entities)
			content += append(content, " "+entity.getName()+".o");
		content += "\n\n";
		content += "EXE_TARGETS =";
		for (CodeEntity entity : project.entities)
			if (entity.getType().equals("executable"))
				content += append(content, " "+entity.getName());
		content += "\n\n";
		content += "all: start $(EXE_TARGETS) end\n\n";
		for (CodeEntity entity : project.entities) {
			content += entity.getName()+".o:";
			for (CodeEntity depend : entity.internalDepends)
				content += append(content, " "+depend.getName()+".o");
			content += "\n";
		}
		content += "\n.SECONDEXPANSION:\n\n";
		for (CodeEntity entity : project.entities) {
			if (entity.getType().equals("executable")) {
				content += "OBJECTS_"+entity.getName()+" =";
				for (String depend : getAllInternalDepends(entity))
					content += " "+depend;
				content += " "+entity.getName()+".o";
				content += "\n";
			}
		}
		content += "\n";
		content += "INCLUDES =";
		for (LibraryMate libraryMate : libraries) {
			content += "\\\n    "+libraryMate.getIncludeOptions();
		}
		content += "\n";
		content += "LIBRARIES =";
		for (LibraryMate libraryMate : libraries) {
			content += "\\\n    "+
					libraryMate.getLibraryOptions("Fortran");
		}
		content += "\n";
		content +=
			"# ------------------------------------------------------------------------------\n" +
			"# implicit building rules\n" + 
			"define fortran_implicit_rules\n" + 
			"%.o: %.$(1)\n" + 
			"\t@echo \" Creating dependency $$@\"\n" + 
			"\t@echo $$(seperator)\n" + 
			"\t@$(FC) -c $$< $(OPTIONS) $(FFLAGS) $(INCLUDES)\n" + 
			"endef\n" + 
			"fortran_suffices := f90 F90\n" + 
			"$(foreach suffix, $(fortran_suffices), \\\n" + 
			"    $(eval $(call fortran_implicit_rules,$(suffix))))\n";
		content += "\n";
		content +=
			"# ------------------------------------------------------------------------------\n" + 
			"# target building rules\n" + 
			"define target_build_rules\n" + 
			"$(1): $$$$(OBJECTS_$(1))\n" + 
			"\t@echo \" Creating target '$(1)'\"\n" + 
			"\t\t@if [ \"$(OPTIONS)\" != \"\" ]; then \\\n" + 
			"\t\t\techo \" Configurations:\"; \\\n" + 
			"\t\t\techo \"   $(OPTIONS)\"; \\\n" + 
			"\t\tfi\n" + 
			"\t@$$(FC) -o $(1) $$(OBJECTS_$(1)) \\\n" + 
			"\t\t$(OPTIONS) $$(FFLAGS) $(INCLUDES) $(LIBRARIES) && \\\n" + 
			"\t\techo \" ---> $(1) is created.\"\n" + 
			"\t@echo $$(seperator)\n" + 
			"endef\n" + 
			"$(foreach target, $(EXE_TARGETS), \\\n" + 
			"    $(eval $(call target_build_rules,$(target))))\n";
		content += "\n";
		content +=
			"seperator = \"-------------------------------------------------------------\"\n" + 
			"\n" + 
			".PHONY: clean start end\n" + 
			"clean:\n" + 
			"\t@echo $(seperator)\n" + 
			"\t@if ls *.o > /dev/null 2>&1; then rm *.o; fi\n" + 
			"\t@if ls *.mod > /dev/null 2>&1; then rm *.mod; fi\n" + 
			"\t@if ls *.i90 > /dev/null 2>&1; then rm *.i90; fi\n" + 
			"\t@if ls *.cmm > /dev/null 2>&1; then rm *.cmm; fi\n" + 
			"\t@for target in $(EXE_TARGETS); do \\\n" + 
			"\t\tif ls $$target > /dev/null 2>&1; then \\\n" + 
			"\t\t\trm $$target; \\\n" + 
			"\t\tfi; \\\n" + 
			"\tdone\n" + 
			"\t@echo \" Project has been cleaned.\"\n" + 
			"\t@echo $(seperator)\n" + 
			"\n" + 
			"start:\n" + 
			"\t@echo $(seperator)\n" + 
			"\t@if test \"$(PROJECT)\" = \"\"; then \\\n" + 
			"\t\techo \" Project powered by CodeMate!\"; \\\n" + 
			"\telse \\\n" + 
			"\t\techo \" Project: >>> $(PROJECT) <<<\"; \\\n" + 
			"\tfi\n" + 
			"\t@echo $(seperator)\n" + 
			"\n" + 
			"end:\n" + 
			"\t@echo \" Finished\"\n" + 
			"\t@echo $(seperator)";
		// ---------------------------------------------------------------------
		UI.notice("MakefileWriter", "Generate Makefile.");
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("Makefile");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		writer.write(content);
		writer.flush();
	}
	
	private static Set<String> getAllInternalDepends(CodeEntity entity) {
		Set<String> internalDepends = new HashSet<String>();
		for (CodeEntity internalDepend : entity.internalDepends) {
			String tmp = internalDepend.getName()+".o";
			if (!internalDepends.contains(tmp))
				internalDepends.add(tmp);
			internalDepends.addAll(getAllInternalDepends(internalDepend));
		}
		return internalDepends;
	}
	
	private static Set<LibraryMate> getAllExternalDepends(CodeEntity entity) {
		Set<LibraryMate> externalDepends = new HashSet<LibraryMate>();
		for (CodeEntity internalDepend : entity.internalDepends) {
			for (LibraryMate externalDepend : internalDepend.externalDepends) {
				if (!externalDepends.contains(externalDepend))
					externalDepends.add(externalDepend);
			}
			externalDepends.addAll(getAllExternalDepends(internalDepend));
		}
		return externalDepends;
	}
	
	private static String append(String a, String b) {
		int loc = a.lastIndexOf("\n");
		int lineWidth = loc == -1 ? a.length() : a.length()-loc;
		if (lineWidth+b.length() < 80) {
			return b;
		} else {
			return " \\\n    "+b;
		}
	}
}
