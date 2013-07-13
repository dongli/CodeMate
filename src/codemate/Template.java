package codemate;

/**
 * Template
 *
 * This class uses StringTemplate technology to implement template for Fortran.
 *
 * @author      Li Dong <dongli@lasg.iap.ac.cn>
 */

import org.stringtemplate.v4.*;

import java.util.*;

public class Template {
    private String name;
    private ArrayList<ST> stList;

    Template(String fileName) {
        name = fileName.split(".stg")[0];
        stList = new ArrayList<ST>();
        STGroup group = new STGroupFile(fileName);
        for (String name : group.getTemplateNames())
            // skip anonymous templates with starting "/_"
            if (!name.startsWith("/_"))
                stList.add(group.getInstanceOf(name));
    }

    /**
     * instance
     *
     * This method instantiates template with given parameters.
     *
     * @param   args    The template parameters in string.
     * @param   block   The template block that will be expanded in string.
     * @return          The template instance map.
     */
    public Map<String, String> instance(String[] args, String block) {
        Map<String, String> res = new HashMap<String, String>();
        for (ST st : stList) {
            if (args != null)
                st.add("args", args);
            if (block != null)
                st.add("block", block);
            res.put(st.getName().substring(1), st.render());
        }
        return res;
    }

    public String getName() {
        return name;
    }
}
