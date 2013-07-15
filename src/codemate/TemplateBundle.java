package codemate;

/**
 * TemplateBundle
 *
 * This class uses FreeMarker technology to implement template for Fortran. It
 * may contains several Template objects, so it is called a bundle.
 *
 * @author      Li Dong <dongli@lasg.iap.ac.cn>
 */

import freemarker.template.*;

import java.io.*;
import java.util.*;

public class TemplateBundle {
    private String bundleName;
    private Map<String, Template> templates;

    TemplateBundle(String bundleName) {
        this.bundleName = bundleName;
        templates = new HashMap<String, Template>();
    }
    
    /**
     * addTemplate
     * 
     * This method add a template into the template list.
     * 
     * @param	type		The template type
     * @param	template	The template that will be added
     */
    public void addTemplate(String type, Template template) {
    	templates.put(type, template);
    }

    /**
     * instance
     *
     * This method instantiates template with given parameters.
     *
     * @param   args    The template parameters in string
     * @param   block   The template block that will be expanded in string
     * @return          The template instance map
     */
    public Map<String, String> instance(ArrayList<String> args, String block) {
    	Map<String, Object> root = new HashMap<String, Object>();
    	String[] args_ = new String[args.size()];
    	args_ = args.toArray(args_);
    	root.put("args", args_);
    	root.put("block", block);
        Map<String, String> res = new HashMap<String, String>();
        for (Map.Entry<String, Template> template : templates.entrySet()) {
            // call template process
        	StringWriter writer = new StringWriter();
        	try {
        		template.getValue().process(root, writer);
        	} catch (Exception e) {
        		UI.error("TemplateBundle", "Encounter error while processing!");
        	}
        	res.put(template.getKey(), writer.toString());
        }
        return res;
    }

    public String getBundleName() {
        return bundleName;
    }
}
