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

import codemate.ui.*;

public class Master {
	public static void main(String[] args) {
		initCommandLine();
		CommandLine.parse(args);
		
		if (CommandLine.hasOption("-silence"))
			UI.setVerboseLevel(UI.VerboseLevel.SILENCE);
		
		if (CommandLine.getOperatorName() == null) {
			if (CommandLine.hasOption("-v"))
				printVersion();
		} else if (CommandLine.getOperatorName().equals("scan")) {
			Scan.operate();
		} else if (CommandLine.getOperatorName().equals("create")) {
			Create.operator();
		} else if (CommandLine.getOperatorName().equals("help")) {
			printHelp();
		}
	}
	
	private static void printVersion() {
		String version = "1.0.0-alpha2";
		System.out.println(version);
	}
	
	private static void initCommandLine() {
		CommandLine.setCommand(
				"codemate",
				"@|bold CodeMate|@ is a tool for automating the building process of\n" + 
				"codes (currently only for Fortran). A Makefile will be\n" + 
				"generated with the resolution of internal and external\n" + 
				"dependencies. A practical @|bold template mechanism|@ is also added\n" + 
				"for Fortran, then lots of redundant code typings will be\n" + 
				"saved.\n"
				);
		CommandLine.addOption(null,
				"-silence", "Do not print message.", false, null);
		CommandLine.addOption(null,
				"-v", "Print the version information.", false, null);
		// ---------------------------------------------------------------------
		CommandLine.addOperator("help",
				"Print help information or usage.", true);
		CommandLine.addOperand("help",
				"operator to help",
				true, "all", "all help");
		// ---------------------------------------------------------------------
		CommandLine.addOperator("scan",
				"Scan a project or a single code.", true);
		CommandLine.addOperand("scan",
				"project root directory or code path",
				true, ".", "current directory");
		// ---------------------------------------------------------------------
		CommandLine.addOperator("create",
				"Create something.", true);
		CommandLine.addOperand("create",
				"template",
				false, null, null);
		CommandLine.addOption("create",
				"-n", "set the name of template class",
				true, "template class name");
	}
	
	private static void printHelp() {
		String header =
			"@|green "+
			"   ______          __     __  ___      __     \n"+
			"  / ____/___  ____/ /__  /  |/  /___ _/ /____ \n"+
			" / /   / __ \\/ __  / _ \\/ /|_/ / __ `/ __/ _ \\\n"+
			"/ /___/ /_/ / /_/ /  __/ /  / / /_/ / /_/  __/\n"+
			"\\____/\\____/\\__,_/\\___/_/  /_/\\__,_/\\__/\\___/"+
			"|@\n";

		CommandLine.printUsage(header, CommandLine.getOperandValue());
	}
}
