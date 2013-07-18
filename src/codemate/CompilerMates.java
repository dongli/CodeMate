package codemate;

import java.util.*;

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
}
