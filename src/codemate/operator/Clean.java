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
package codemate.operator;

/**
 * Clean
 * 
 * This class is a subcommand that cleans the generated file by CodeMate. The
 * generated file is located in the the .codemate directory under the project
 * root.
 * 
 * @author Li Dong <dongli@lasg.iap.ac.cn>
 */

import java.io.*;

import codemate.project.*;
import codemate.ui.*;
import codemate.utils.*;

public class Clean {
	public static void operate() {
    	File projectRoot = Project.findProject();
    	if (projectRoot != null) {
    		UI.notice("codemate", "Clean project at "+projectRoot+".");
    		File stuffs = new File(projectRoot.getAbsolutePath()+"/.codemate");
    		SystemUtils.deleteDirectory(stuffs);
    	}
	}
}
