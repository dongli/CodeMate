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
