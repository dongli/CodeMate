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
