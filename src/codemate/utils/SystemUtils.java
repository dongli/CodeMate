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
package codemate.utils;

import static org.fusesource.jansi.Ansi.ansi;

import java.io.*;
import java.net.*;
import java.nio.channels.*;
import java.util.*;

import javax.net.ssl.*;
import javax.tools.*;

import org.fusesource.jansi.AnsiConsole;

import codemate.ui.*;
import jline.*;

public class SystemUtils {
	/**
	 * getSubdirectories
	 * 
	 * This method gets the subdirectories that under the given root. The hidden
	 * subdirectories are ignored.
	 * 
	 * @param 	root		The root directory
	 * @return	List<File>	The subdirectory list
	 * 
	 * @author Li Dong <dongli@lasg.iap.ac.cn>
	 */
	public static List<File> getSubdirectories(File root) {
		List<File> subdirectories = new ArrayList<File>();
		if (root.isHidden()) return subdirectories;
		for (String fileName : root.list()) {
			File file = new File(root+"/"+fileName);
			if (file.isDirectory()) {
				subdirectories.add(file);
				subdirectories.addAll(getSubdirectories(file));
			}
		}
		return subdirectories;
	}
	
	public static String getAbsolutePath(File file) {
		String res = file.getAbsolutePath();
		if (res.endsWith("/.."))
			res = res.replaceAll("/[^/]*/\\.\\.$", "");
		else if (res.endsWith("/."))
			res = res.replaceAll("/\\.$", "");
		return res;
	}
	
	public static String getAbsolutePath(String fileName) {
		File file = new File(fileName);
		return getAbsolutePath(file);
	}
	
	public static void deleteDirectory(File file) {
		if (file.isDirectory())
			for (File subfile : file.listFiles())
				deleteDirectory(subfile);
		if (!file.delete())
			UI.error("codemate",
					"Failed to delete "+file.getAbsolutePath()+".");
	}
	
	public static int getConsoleWidth() {
		Terminal terminal = TerminalFactory.create();
		return terminal.getWidth();
	}
	
	public static void printSeparateLine() {
		char[] line = new char[60];
		for (int i = 0; i < line.length; ++i)
			line[i] = '-';
		System.out.println(line);
	}
	
	public static void print(String content) {
		AnsiConsole.out.print(ansi().render(content));
	}
	
	public static void compile(File source) {
		List<String> optionList = new ArrayList<String>();
		String paths = System.getProperty("java.class.path");
		if (paths.matches("^[^:]*\\.codemate/codemate")) {
			// When running as a command that is wrapped by a script, we should
			// use the JAR file lies along with that command.
			paths += ".jar";
		}
		optionList.addAll(Arrays.asList("-classpath", paths));
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		StandardJavaFileManager fileManager =
				compiler.getStandardFileManager(null, null, null);
		Iterable<? extends JavaFileObject> compilationUnits =
				fileManager.getJavaFileObjects(source);
		DiagnosticCollector<JavaFileObject> diagnostics =
				new DiagnosticCollector<JavaFileObject>();
		if (!compiler.getTask(null, fileManager, diagnostics, optionList,
				null, compilationUnits).call())
			UI.error("codemate", "There is error in "+source.getPath());
		return;
	}
	
	/**
	 * download
	 * 
	 * This method downloads the file from a remote URL in HTTPS protocol to a
	 * local path.
	 * 
	 * @param remoteUrl
	 * @param localPath
	 */
	public static void download(String remoteUrl, String localPath) {
		URL url = null;
		SecureConnector connector = new SecureConnector("TLS");
		HttpsURLConnection connection = null;
        try {
        	url = new URL(remoteUrl);
            connection = connector.openSecureConnection(url);
        } catch (Exception e) {
            e.printStackTrace();
            UI.error("codemate", "Failed to download "+url.getPath()+"!");
        }
        try {
			ReadableByteChannel channel =
					Channels.newChannel(connection.getInputStream());
		    FileOutputStream fos = new FileOutputStream(localPath);
		    fos.getChannel().transferFrom(channel, 0, Long.MAX_VALUE);
		    fos.close();
		} catch (Exception e) {
            e.printStackTrace();
			UI.error("codemate", "Failed to save "+localPath+"!");
		}
	}
	
	/**
	 * downloadAndRead
	 * 
	 * This method downloads the file from a remote URL in HTTPS protocol and
	 * read the content into a string.
	 * 
	 * @param  remoteUrl
	 * @return String
	 */
	public static String downloadAndRead(String remoteUrl) {
		URL url = null;
		SecureConnector connector = new SecureConnector("TLS");
		HttpsURLConnection connection = null;
		BufferedReader reader = null;
		String content = "";
		try {
			url = new URL(remoteUrl);
            connection = connector.openSecureConnection(url);
			reader = new BufferedReader(
				new InputStreamReader(connection.getInputStream(), "UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
			UI.error("codemate", "Failed to download "+remoteUrl+"!");
		}
		try {
			for (String line; (line = reader.readLine()) != null;)
				content += line;
		} catch (Exception e) {
            e.printStackTrace();
			UI.error("codemate", "Failed to read "+remoteUrl+"!");
		} finally {
		    if (reader != null)
		    	try {
		    		reader.close();
		    	} catch (Exception ignore) {
		    	}
		}
		return content;
	}
}
