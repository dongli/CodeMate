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

public class NetcdfLibraryMate implements LibraryMate {
	private String root = null;
	
	@Override
	public String getLibraryName() {
		return "netcdf";
	}

	@Override
	public boolean isDepended(String language, String headerOrModule) {
		if (language.equals("Fortran")) {
			if (headerOrModule.equals("netcdf") ||
				headerOrModule.equals("netcdf.inc"))
				return true;
		}
		return false;
	}

	@Override
	public boolean provideCompilerWrapper() {
		return false;
	}

	@Override
	public String getIncludeOptions() {
		return "-I$(NETCDF_ROOT)/include";
	}

	@Override
	public String getLibraryOptions(String language) {
		if (language.equals("Fortran")) {
			return "-L$(NETCDF_ROOT)/lib -lnetcdf -lnetcdff";
		}
		return null;
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
	public void setWrapper(String language, String wrapper) { }

	@Override
	public String getWrapper(String language) {
		return null;
	}

}
