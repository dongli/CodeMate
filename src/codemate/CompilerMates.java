package codemate;

import java.util.*;

public class CompilerMates {
	private static List<CompilerMate> mates = new ArrayList<CompilerMate>();
	
	public static void load() {
		mates.add(new IntelCompilerMate());
	}
	
	public static List<CompilerMate> getMates() {
		return mates;
	}
	
	public static ArrayList<String> getVendorNames() {
		ArrayList<String> vendorNames = new ArrayList<String>();
		for (CompilerMate mate : mates)
			vendorNames.add(mate.getVendorName());
		return vendorNames;
	}
}
