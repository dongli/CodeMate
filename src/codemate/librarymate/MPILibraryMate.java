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

import java.util.*;

public class MPILibraryMate implements LibraryMate {
	private String root = null;
	private Map<String, String> wrappers = new HashMap<String, String>();

	@Override
	public String getLibraryName() {
		return "MPI";
	}

	@Override
	public boolean isDepended(String language, String headerOrModule) {
		if (language.equals("Fortran")) {
			if (headerOrModule.equals("mpi"))
				return true;
		}
		return false;
	}

	@Override
	public boolean provideCompilerWrapper() {
		return true;
	}

	@Override
	public String getIncludeOptions() {
		return "";
	}

	@Override
	public String getLibraryOptions(String language) {
		return "";
	}

	@Override
	public void setRoot(String root) {
		this.root = root;
	}

	@Override
	public String getRoot() {
		return root;
	}

	@Override
	public void setWrapper(String language, String wrapper) {
		wrappers.put(language, wrapper);
	}

	@Override
	public String getWrapper(String language) {
		return wrappers.get(language);
	}

}
