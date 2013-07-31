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
package codemate.Fortran;

public class Template {
    public enum Action {
        REPLACE, PREPEND, APPEND
    }

    private int type; // see rule index in FortranParser
    private Action action;
    
    public Template(int type, Action action) {
        this.type = type;
        this.action = action;
    }

    public int getType() { return type; }

    public Action getAction() { return action; }
}
