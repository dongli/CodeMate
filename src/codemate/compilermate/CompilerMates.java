package codemate.compilermate;

import java.util.*;

import codemate.librarymate.*;
import codemate.ui.*;

public class CompilerMates {
	private static List<CompilerMate> mates = new ArrayList<CompilerMate>();
	
	public static void load() {
		mates.add(new IntelCompilerMate());
		mates.add(new GNUCompilerMate());
	}
	
	public static List<CompilerMate> getMates() {
		return mates;
	}
	
	public static CompilerMate getMate(String vendor) {
		for (CompilerMate mate : mates)
			if (mate.getVendorName().equals(vendor))
				return mate;
		return null;	
	}
	
	public static ArrayList<String> getVendorNames() {
		ArrayList<String> vendorNames = new ArrayList<String>();
		for (CompilerMate mate : mates)
			vendorNames.add(mate.getVendorName());
		return vendorNames;
	}

	public static String getDefaultCommandName(String language) {
		CompilerMate mate = getMate(Config.getDefaultCompilerVendor(language));
		String wrapper = LibraryMates.getCompilerWrapper(language);
		if (wrapper == null)
			return mate.getCommandName(language);
		else
			return wrapper;
	}
	
	public static String getDefaultDebugOptions(String language) {
		CompilerMate mate = getMate(Config.getDefaultCompilerVendor(language));
		return mate.getDebugOptions();
	}
	
	public static String getDefaultReleaseOptions(String language) {
		CompilerMate mate = getMate(Config.getDefaultCompilerVendor(language));
		return mate.getReleaseOptions();
	}
}
