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
import codemate.Fortran.*;

import java.util.*;

public class SubdomLoopTemplate implements TemplateBundle {
	public boolean containsTemplate(String templateName) {
		if (templateName.equals("subdom_loop"))
			return true;
		else
			return false;
	}
	
	 public Map<Template, String> instantiate(String templateName,
	            List<String> args, String block) {
	        String instance;
	        Map<Template, String> instances = new HashMap<Template, String>();

	        instance = "";
	        for (String arg : args) {
	        	instance += "type(var_subdom_t), pointer :: __"+arg+"\n";
	        }
	        instance += "type(subdon_t), pointer :: __subdom\n";
	        instance += "integer __i\n";
	        instances.put(new Template(
	        		FortranParser.RULE_declarationStatements,
	        		Template.Action.APPEND), instance);
	        
	        instance = "";
	        for (String arg : args) {
	        	instance += "call "+arg+"%get_start_elem(__"+arg+")\n";
	        }
	        instance += "subdom => __"+args.get(0)+"%subdom\n";
	        instance += "do __i = 1, "+args.get(0)+"%get_num_elem()\n";
	        for (String arg : args) {
	        	block = block.replaceAll("\\b"+arg+"\\b *\\(", "__"+arg+"%array(");
	        }
	        instance += block;
	        for (String arg : args) {
	        	instance += "    call __"+arg+"%get_next(__"+arg+")\n";
	        }
	        instance += "end do\n";
	        instances.put(new Template(
	        		FortranParser.RULE_executableStatements,
	        		Template.Action.REPLACE), instance);
	        
	        return instances;
	 }
}
