package codemate.ui;

import java.util.*;
import java.util.Map.Entry;

import codemate.utils.*;

public class CommandLine {
	private static class Operator {
		String name;
		String description;
		boolean hasOperand;
		Operand operand = new Operand();
		Map<String, Option> options = new HashMap<String, Option>();
	}

	private static class Operand {
		String description;
		boolean hasDefault;
		String defaultValue;
		String defaultDescription;
	}
	
	private static class Option {
		String name;
		String description;
		boolean hasValue;
		String valueDescription;
	}
	
	private static String commandName;
	private static String commandDescription;
	private static String operatorName;
	private static String operandValue;
	private static Map<String, String> commonOptions =
			new HashMap<String, String>();
	private static Map<String, String> options =
			new HashMap<String, String>();

	private static Map<String, Operator> operatorMap =
			new HashMap<String, Operator>();
	private static Map<String, Option> commonOptionMap =
			new HashMap<String, Option>();
	
	public static void setCommand(String name, String description) {
		commandName = name;
		commandDescription = description;
	}
	
	public static void addOperator(String operatorName,
			String operatorDescription, boolean hasOperand) {
		if (!operatorMap.containsKey(operatorName)) {
			Operator operator = new Operator();
			operator.name = operatorName;
			operator.description = operatorDescription;
			operator.hasOperand = hasOperand;
			operatorMap.put(operatorName, operator);
		}
	}
	
	public static void addOperand(String operatorName,
			String operandDescription, boolean hasDefault,
			String defaultOperandValue, String defaultOperandDescription) {
		Operator operator = operatorMap.get(operatorName);
		if (operator != null) {
			operator.operand.description = operandDescription;
			operator.operand.hasDefault = hasDefault;
			operator.operand.defaultValue = defaultOperandValue;
			operator.operand.defaultDescription = defaultOperandDescription;
		}
	}
	
	public static void addOption(String operatorName, String optionName,
			String optionDescription, boolean hasValue,
			String valueDescription) {
		if (operatorName == null) {
			if (!commonOptionMap.containsKey(optionName)) {
				Option option = new Option();
				option.name = optionName;
				option.description = optionDescription;
				option.hasValue = hasValue;
				option.valueDescription = valueDescription;
				commonOptionMap.put(optionName, option);
			}
		} else {
			Operator operator = operatorMap.get(operatorName);
			if (operator != null) {
				if (!operator.options.containsKey(optionName)) {
					Option option = new Option();
					option.name = optionName;
					option.description = optionDescription;
					option.hasValue = hasValue;
					option.valueDescription = valueDescription;
					operator.options.put(optionName, option);
				}
			}
		}
	}
	
	public static void parse(String[] args) {
		operatorName = null;
		operandValue = null;
		commonOptions.clear();
		options.clear();
		Operator operator;
		Option option;
		Map<String, Option> optionMap = null;
		int i = 0;
		while (i < args.length) {
			if (commonOptionMap.containsKey(args[i])) {
				option = commonOptionMap.get(args[i]);
				if (option.hasValue) {
					if (options.containsKey(args[i+1]))
						UI.error("CommandLine",
								"Option "+option.name+" needs argument!");
					options.put(option.name, args[++i]);
				} else
					options.put(option.name, null);
			} else if (operatorMap.containsKey(args[i])) {
				// this is an operatorName
				operatorName = args[i];
				operator = operatorMap.get(operatorName);
				optionMap = operator.options;
				if (operator.hasOperand) {
					if (i == args.length-1 || optionMap.containsKey(args[i+1])) {
						operandValue = operator.operand.defaultValue;
					} else
						operandValue = args[++i];
				}
			} else if (operatorName != null) {
				option = optionMap.get(args[i]);
				if (option != null) {
					if (option.hasValue) {
						if (options.containsKey(args[i+1]))
							UI.error("CommandLine",
									"Option "+option.name+" needs argument!");
						options.put(option.name, args[++i]);
					} else
						options.put(option.name, null);
				} else {
					UI.error("CommandLine", "Unrecognized option "+args[i]+"!");
				}
			}
			i++;
		}
	}
	
	public static void printUsage(String header) {
		SystemUtils.printSeparateLine();
		SystemUtils.print(header);
		SystemUtils.printSeparateLine();
		SystemUtils.print(commandDescription);
		SystemUtils.printSeparateLine();
		SystemUtils.print("Usage:\n\n");
		SystemUtils.print("    @|bold "+commandName+"|@ [<common options>] <operator> [<operand>] [<options>]\n");
		SystemUtils.printSeparateLine();
		if (!commonOptionMap.isEmpty()) {
			SystemUtils.print("Available common options:\n");
			for (Entry<String, Option> iter : commonOptionMap.entrySet()) {
				Option option = iter.getValue();
				SystemUtils.print("\n    @|bold "+option.name+"|@");
				if (option.hasValue)
					SystemUtils.print(" <"+option.valueDescription+">");
				SystemUtils.print("\n\n        @|underline "+option.description+"|@\n");
			}
		}
		SystemUtils.printSeparateLine();
		SystemUtils.print("Available operators:\n");
		for (Entry<String, Operator> iter1 : operatorMap.entrySet()) {
			Operator operator = iter1.getValue();
			SystemUtils.print("\n    @|bold "+operator.name+"|@");
			if (operator.hasOperand) {
				if (operator.operand.hasDefault)
					SystemUtils.print(" [<operand>]");
				else
					SystemUtils.print(" <operand>");
			}
			if (!operator.options.isEmpty())
				SystemUtils.print(" [<options>]");
			SystemUtils.print("\n\n        @|underline "+operator.description+"|@\n");
			if (operator.hasOperand) {
				SystemUtils.print("\n        Operand: "+operator.operand.description);
				if (operator.operand.hasDefault) {
					SystemUtils.print("\n        Default: "+operator.operand.defaultValue);
					SystemUtils.print(" - "+operator.operand.defaultDescription);
				}
			}
			if (!operator.options.isEmpty()) {
				SystemUtils.print("\n\n        Options:\n\n");
				for (Entry<String, Option> iter2 : operator.options.entrySet()) {
					Option option = iter2.getValue();
					SystemUtils.print("             @|bold "+option.name+"|@");
					if (option.hasValue)
						SystemUtils.print(" <"+option.valueDescription+">");
					SystemUtils.print("\n\n                  @|underline "+option.description+"|@\n");
				}
			}
		}
		SystemUtils.printSeparateLine();
	}
	
	public static String getOperatorName() { return operatorName; }
	
	public static String getOperandValue() { return operandValue; }
	
	public static Map<String, String> getOptions() { return options; }
	
	public static String getOptionValue(String optionName) {
		return options.get(optionName);
	}
	
	public static boolean hasOption(String optionName) {
		if (commonOptions.containsKey(optionName) ||
			options.containsKey(optionName))
			return true;
		return false;
	}
}
