package codemate.utils;

import java.io.*;
import java.util.*;

public class SystemUtils {
	public static List<File> getSubdirectories(File root) {
		List<File> subdirectories = new ArrayList<File>();
		for (String fileName : root.list()) {
			File file = new File(root+"/"+fileName);
			if (file.isDirectory()) {
				subdirectories.add(file);
				subdirectories.addAll(getSubdirectories(file));
			}
		}
		return subdirectories;
	}
}
