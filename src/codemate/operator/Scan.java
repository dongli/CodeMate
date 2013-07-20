package codemate.operator;

import codemate.Fortran.*;
import codemate.builder.*;
import codemate.compilermate.*;
import codemate.librarymate.*;
import codemate.project.*;
import codemate.ui.*;

/**
 * Scan
 *
 * This class is a subcommand that scans the codes in a root directories. It
 * will conduct the following operations:
 *
 *   - Collect codes in the given root directory;
 *   - Create a project object for the collected codes;
 *   - Parse all the codes to construct parse tree;
 *   - Extract internal and external dependencies;
 *   - Process codes including rewriting and templating.
 *
 * @author      Li Dong <dongli@lasg.iap.ac.cn>
 */

public class Scan {
	public static void main(String[] args) {
		String root = args[0];

		LibraryMates.load();
		CompilerMates.load();
		Config.load();
		
		UI.notice("Scan", "Scanning project in " + root + ".");

		Project project = new Project(root);
		FortranProcessor.process(project);
		ProjectBuilder.prepare(project);
	}
}
