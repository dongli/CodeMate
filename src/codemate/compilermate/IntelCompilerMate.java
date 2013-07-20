package codemate.compilermate;

public class IntelCompilerMate implements CompilerMate {

	@Override
	public String getVendorName() {
		return "Intel";
	}

	@Override
	public String getCommandName(String language) {
		if (language.equals("Fortran"))
			return "ifort";
		return null;
	}

	@Override
	public boolean isAvailable() {
		
		return false;
	}

	@Override
	public String getDebugOptions() {
		return "-O0 -g -check all";
	}

	@Override
	public String getReleaseOptions() {
		return "-O2";
	}

}
