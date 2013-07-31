/*******************************************************************************
 * Copyright (c) 2013 Li Dong.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     Li Dong - initial API and implementation
 ******************************************************************************/
package codemate.operator;

import java.io.*;

import codemate.Fortran.*;
import codemate.builder.*;
import codemate.compilermate.*;
import codemate.librarymate.*;
import codemate.project.*;
import codemate.ui.*;
import codemate.utils.SystemUtils;

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
	public static void operate() {
		String root = CommandLine.getOperandValue();
		
		File file = new File(SystemUtils.getAbsolutePath(root));
		if (file.isDirectory()) {
			UI.notice("codemate", "Scanning project in "+root+".");
			LibraryMates.load();
			CompilerMates.load();
			Config.load();
			Project project = new Project(file.getPath());
			FortranProcessor.process(project);
			ProjectBuilder.prepare(project);
		} else if (file.isFile()) {
			UI.notice("codemate", "Scanning single file "+file.getPath()+".");
			FortranProcessor.process(file);
		}
	}
}
