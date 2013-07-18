package codemate;

import codemate.LibraryMate;

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

}
