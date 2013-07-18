package codemate;

public class ProjectBuilder {
	public static void prepare(Project project) {
		UI.notice("ProjectBuilder", "Prepare build system for project.");
		MakefileWriter.write(project);
	}
}
