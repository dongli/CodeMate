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
package codemate.builder;

import codemate.project.*;
import codemate.ui.*;

public class ProjectBuilder {
	public static void prepare(Project project) {
		UI.notice("codemate", "Prepare build system for project.");
		MakefileWriter.write(project);
	}
}
