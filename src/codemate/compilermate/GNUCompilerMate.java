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
