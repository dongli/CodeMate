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
	
	public static void loadLibraryMates() {
		mates.add(new NetcdfLibraryMate());
	}

	public static LibraryMate searchLibrary(
			String language, String headerOrModule) {
		for (LibraryMate mate : mates)
			if (mate.isDepended(language, headerOrModule))
				return mate;
		return null;
	}
}
