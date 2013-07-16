package codemate;

import codemate.LibraryMate;

public class NetcdfLibraryMate implements LibraryMate {
	
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
	public String getCompileOptions(String language) {
		if (language.equals("Fortran")) {
			// check the version of netcdf
		}
		return null;
	}

}
