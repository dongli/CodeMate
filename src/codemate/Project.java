package codemate;

/**
 * Project
 *
 * This class is used to manage a project.
 *
 * @author      Li Dong <dongli@lasg.iap.ac.cn>
 */

import java.io.*;
import java.util.*;

public class Project {
	private String name;
	private File root;
	public ArrayList<CodeEntity> entities;

	Project(String dirName) {
		setRoot(new File(dirName));
		setName(new File(getRoot().getParent()).getName());
		entities = new ArrayList<CodeEntity>();
		collectCodeEntities(root);
	}
	
	Project(String projectName, String dirName) {
		setName(projectName);
		setRoot(new File(dirName));
		entities = new ArrayList<CodeEntity>();
		collectCodeEntities(root);
	}

	public void collectCodeEntities(File dir) {
		for (String fileName : dir.list()) {
			for (String suffix : FortranTraits.suffix)
				if (fileName.endsWith(suffix))
					entities.add(new CodeEntity(dir+"/"+fileName));
		}
	}

	public File getRoot() {
		return root;
	}

	public void setRoot(File root) {
		this.root = root.getAbsoluteFile();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		UI.notice("Project", "Create project "+name+".");
		this.name = name;
	}
}