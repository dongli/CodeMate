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
package codemate.librarymate;

/**
 * LibraryMates
 * 
 * This class provides several methods for accessing library mates.
 * 
 * @author Li Dong <dongli@lasg.iap.ac.cn>
 */

import java.util.*;

import codemate.ui.*;

public class LibraryMates {
	private static List<LibraryMate> mates = new LinkedList<LibraryMate>();
	
	public static void load() {
		mates.add(new NetcdfLibraryMate());
		mates.add(new MPILibraryMate());
		mates.add(new CMORLibraryMate());
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
	
	public static List<LibraryMate> getLibraryMates() {
		return mates;
	}
	
	public static String getCompilerWrapper(String language) {
		String wrapper = null;
		for (LibraryMate mate : mates) {
			if (mate.provideCompilerWrapper()) {
				if (wrapper == null)
					wrapper = mate.getWrapper(language);
				else
					UI.error("codemate",
							"More than one library provides compiler wrapper!");
			}
		}
		return wrapper;
	}
}
