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
package codemate.ui;

import java.io.IOException;
import java.util.*;

import codemate.utils.*;
import jline.console.*;
import jline.console.completer.*;

/**
 * UI
 * 
 * This class provides several methods for printing out message.
 * 
 * @author Li Dong <dongli@lasg.iap.ac.cn>
 */

public class UI {
	public static enum VerboseLevel {
		ALL, ONLY_WARNING, SILENCE
	}
	
	private static VerboseLevel verboseLevel = VerboseLevel.ALL;
	
	public static void setVerboseLevel(VerboseLevel level) {
		verboseLevel = level;
	}
	
	public static void notice(String caller, String message) {
		if (!(verboseLevel == VerboseLevel.ALL))
			return;
		SystemUtils.print("[@|bold,green Notice|@]: @|bold,magenta "+caller+
				"|@: "+message+"\n");
	}
	
	public static void warning(String caller, String message) {
		if (!(verboseLevel == VerboseLevel.ALL ||
			  verboseLevel == VerboseLevel.ONLY_WARNING))
			return;
		SystemUtils.print("[@|bold,yellow Warning|@]: @|bold,magenta "+caller+
				"|@: "+message+"\n");
	}
	
	public static void error(String caller, String message) {
		SystemUtils.print("[@|bold,red Error|@]: @|bold,magenta "+caller+
				"|@: "+message+"\n");
		System.exit(1);
	}
	
	public static void printList(ArrayList<String> list) {
		for (String elem : list)
			SystemUtils.print("- @|underline "+elem+"|@\n");
	}
	
	public static String[] getAnswer(
			ArrayList<String> potentialAnswers) {
		if (potentialAnswers != null)
			printList(potentialAnswers);
		String[] answer = null;
		ConsoleReader reader = null;
		try {
			reader = new ConsoleReader();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		reader.setPrompt("> ");
		// add completer
		List<Completer> completers = new LinkedList<Completer>();
		// add file name completer
		completers.add(new FileNameCompleter());
		// add potential answers as completer
		if (potentialAnswers != null) {
			completers.add(new StringsCompleter(potentialAnswers));
		}
		for (Completer completer : completers)
			reader.addCompleter(completer);
		try {
			answer = reader.readLine().split(" ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return answer;
	}
}
