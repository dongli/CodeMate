package codemate;

public class ProjectBuilder {
	public static void prepare(Project project) {
		UI.notice("ProjectBuilder", "Prepare build system for project.");
		System.out.println("Default compiler vendor: "+
				Config.getDefaultCompilerVendor("Fortran"));
	}
}
