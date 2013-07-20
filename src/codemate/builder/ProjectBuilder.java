package codemate.builder;

import codemate.project.*;
import codemate.ui.*;

public class ProjectBuilder {
	public static void prepare(Project project) {
		UI.notice("ProjectBuilder", "Prepare build system for project.");
		MakefileWriter.write(project);
	}
}