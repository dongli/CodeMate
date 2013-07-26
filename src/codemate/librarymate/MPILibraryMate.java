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
