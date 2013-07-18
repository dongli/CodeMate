package codemate;

public class GNUCompilerMate implements CompilerMate {

	@Override
	public String getVendorName() {
		return "GNU";
	}

	@Override
	public String getCommandName(String language) {
		if (language.equals("Fortran"))
			return "gfortran";
		return null;
	}

	@Override
	public String getDebugOptions() {
		return "-O0 -g -fcheck=all";
	}

	@Override
	public String getReleaseOptions() {
		return "-O2";
	}

	@Override
	public boolean isAvailable() {
		// TODO Auto-generated method stub
		return false;
	}

}
