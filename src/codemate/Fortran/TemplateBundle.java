package codemate.Fortran;

import java.util.*;

public interface TemplateBundle {
    public boolean containsTemplate(String templateName);

    public Map<Template, String> instantiate(String templateName,
            List<String> args, String block);
}
