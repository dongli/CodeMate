package codemate.operator;

import java.util.*;

public class Master {
	public static void main(String[] args) {
		List<String> arguments = new ArrayList<String>(Arrays.asList(args));
		String operator = args[0];
		if (operator.equals("scan")) {
			arguments.remove(0);
			Scan.operate(arguments);
		}
	}
}
