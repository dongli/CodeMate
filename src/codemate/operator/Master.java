package codemate.operator;

import codemate.ui.CommandLine;

public class Master {
	public static void main(String[] args) {
		initCommandLine();
		CommandLine.parse(args);
		if (CommandLine.getOperatorName().equals("scan")) {
			Scan.operate();
		}
	}
	
	private static void initCommandLine() {
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
}
