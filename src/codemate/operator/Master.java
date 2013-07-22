package codemate.operator;

import codemate.ui.CommandLine;

public class Master {
	public static void main(String[] args) {
		initCommandLine();
		CommandLine.parse(args);
		if (CommandLine.getOperatorName().equals("scan")) {
			Scan.operate();
		} else if (CommandLine.getOperatorName().equals("help")) {
			help();
		}
	}
	
	private static void initCommandLine() {
		CommandLine.setCommand(
				"codemate",
				"@|bold CodeMate|@ is a tool for automating the building process of codes (currently only\n" + 
				"for Fortran). A Makefile will be generated with the resolution of internal and\n" + 
				"external dependencies. A practical @|bold template mechanism|@ is also added for Fortran,\n" + 
				"then lots of redundant code typings will be saved.\n"
				);
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
		CommandLine.addOption("scan",
				"-t", "set template search paths",
				true, "template searching paths separated by ':'");
	}
	
	private static void help() {
		String header =
			"@|green "+
			"   ______          __     __  ___      __     \n"+
			"  / ____/___  ____/ /__  /  |/  /___ _/ /____ \n"+
			" / /   / __ \\/ __  / _ \\/ /|_/ / __ `/ __/ _ \\\n"+
			"/ /___/ /_/ / /_/ /  __/ /  / / /_/ / /_/  __/\n"+
			"\\____/\\____/\\__,_/\\___/_/  /_/\\__,_/\\__/\\___/"+
			"|@\n";

		CommandLine.printUsage(header);
	}
}
