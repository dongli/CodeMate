package codemate;

/**
 * LibraryMates
 * 
 * This class provides several methods for accessing library mates.
 * 
 * @author Li Dong <dongli@lasg.iap.ac.cn>
 */

import java.util.*;

public class LibraryMates {
	private static List<LibraryMate> mates = new ArrayList<LibraryMate>();
	
	public static void load() {
		mates.add(new NetcdfLibraryMate());
	}

	public static ArrayList<String> getLibraryNames() {
		ArrayList<String> libraryNames = new ArrayList<String>();
		for (LibraryMate mate : mates)
			libraryNames.add(mate.getLibraryName());
		return libraryNames;
	}
	
	public static LibraryMate searchLibrary(
			String language, String headerOrModule) {
		for (LibraryMate mate : mates)
			if (mate.isDepended(language, headerOrModule))
				return mate;
		return null;
	}
	
	public static LibraryMate searchLibrary(String libraryName) {
		for (LibraryMate mate : mates)
			if (mate.getLibraryName().equals(libraryName))
				return mate;
		return null;
	}
}
