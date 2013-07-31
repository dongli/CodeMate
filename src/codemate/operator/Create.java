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

import java.io.*;

public class Create {
	public static void operator() {
		String thing = CommandLine.getOperandValue();
		
		if (thing.equals("template")) {
			String className = CommandLine.getOptionValue("-n");
			createTemplateClass(className);
		} else {
			UI.error("codemate", "Unknown operand "+thing+"!");
		}
	}
	
	private static void createTemplateClass(String className) {
		if (!className.endsWith("Template")) {
			UI.warning("codemate",
					"The template class name should end with \"Template\"!");
			className += "Template";
		}
		File file = new File(className+".java");
		if (file.isFile() || file.isDirectory()) {
			UI.error("codemate", "File "+file.getPath()+" already exists!");
		}
		String content = "";
		content +=
				"/***\n"+
				" * "+className+"\n"+
				" *\n" + 
				" * describe this class here\n" + 
				" *\n" + 
				" * @author      write your name here\n" + 
				" */\n" + 
				"\n" + 
				"import codemate.Fortran.*;\n" + 
				"\n" + 
				"import java.util.*;\n" + 
				"\n" + 
				"public class "+className+" implements TemplateBundle {\n" + 
				"    public boolean containsTemplate(String templateName) {\n" + 
				"        // insert judgement codes\n" + 
				"    }\n" + 
				"\n" + 
				"    public Map<Template, String> instantiate(String templateName,\n" + 
				"            List<String> args, String block) {\n" + 
				"        // insert instatiation codes below\n" + 
				"    }\n" + 
				"}";
		try {
			UI.notice("codemate", "Create derived template class "+file.getPath()+".");
			PrintWriter writer = new PrintWriter(file);
			writer.println(content);
			writer.flush();
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
