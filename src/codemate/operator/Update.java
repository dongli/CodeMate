package codemate.operator;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.*;

import codemate.ui.UI;

public class Update {
	public static void operate() {
		Pattern hashPattern = Pattern.compile("\"prev_commit_hash\" = \"\\w*\"");
		// get remote hash
		String hashRemote = null;
		BufferedReader reader = null;
		try {
			URL url = new URL("https://raw.github.com/dongli/CodeMate/master/products/installer/payload/install.info");
			reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
			UI.error("codemate",
					"Failed to fetch information from GitHub!");
		}
		try {
			String message = "";
			for (String line; (line = reader.readLine()) != null;)
				message += line;
			Matcher hashMatcher = hashPattern.matcher(message);
			if (hashMatcher.find())
				hashRemote = hashMatcher.group().split("=")[1];
			else
				UI.error("codemate", "Failed to find remote commit hash!");
		} catch (Exception e) {
			
		} finally {
		    if (reader != null) try { reader.close(); } catch (Exception ignore) {}
		}
		// get local hash
		String hashLocal = null;
		File file = new File(System.getenv("HOME")+".codemate/install.info");
		if (!file.isFile()) {
			UI.error("codemate",
					"Sorry, CodeMate can not be updated due to lack of information.");
		}
		try {
			String content = new Scanner(file).useDelimiter("\\Z").next();
			Matcher hashMatcher = hashPattern.matcher(content);
			if (hashMatcher.find())
				hashLocal = hashMatcher.group().split("=")[1];
			else
				UI.error("codemate", "Failed to find local commit hash!");
		} catch (FileNotFoundException e) {
			UI.error("codemate",
					"Failed to read commit information from local installation!");
		}
		// compare
		if (hashRemote.equals(hashLocal)) {
			UI.notice("codemate", "CodeMate is already up-to-date!");
		} else {
			UI.notice("codemate", "Update CodeMate.");
		}
	}
}
