package codemate.librarymate;

public class CMORLibraryMate implements LibraryMate {
	private String root = null;

	@Override
	public String getLibraryName() {
		return "cmor";
	}

	@Override
	public boolean isDepended(String language, String headerOrModule) {
		if (language.equals("Fortran")) {
			if (headerOrModule.equals("cmor_users_functions")) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean provideCompilerWrapper() {
		return false;
	}

	@Override
	public String getIncludeOptions() {
		return "-I$(CMOR_ROOT)/include";
	}

	@Override
	public String getLibraryOptions(String language) {
		if (language.equals("Fortran")) {
			return "-L$(CMOR_ROOT)/lib -lcmor";
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
