/***
 * HelloTemplate
 *
 * describe this class here
 *
 * @author      write your name here
 */

import codemate.Fortran.*;

import java.util.*;

public class HelloTemplate implements TemplateBundle {
    public boolean containsTemplate(String templateName) {
        // insert judgement codes
        if (templateName.equals("hello"))
            return true;
        else
            return false;
    }

    public Map<Template, String> instantiate(String templateName,
            List<String> args, String block) {
        Map<Template, String> instances = new HashMap<Template, String>();

        instances.put(new Template(FortranParser.RULE_expression,
            Template.Action.REPLACE), "\"Hello, world!\"");

        return instances;
    }
}
