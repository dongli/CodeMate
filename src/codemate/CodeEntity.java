package codemate;

/**
 * CodeEntity
 * 
 * This class describes the code entity.
 * 
 * @author Li Dong <dongli@lasg.iap.ac.cn>
 * @since 2013-07-11
 */

import org.antlr.v4.runtime.tree.*;

import java.io.*;
import java.util.*;

public class CodeEntity {
	private File path;
	private ParseTree parseTree;
	private ArrayList<CodeEntity> internalDepends;
	private ArrayList<CodeEntity> externalDepends;

	CodeEntity(String path) {
		UI.notice("CodeEntity", "Add code entity "+path+".");
		this.setPath(path);
		internalDepends = new ArrayList<CodeEntity>();
		externalDepends = new ArrayList<CodeEntity>();
	}

	public void addInternalDepend(CodeEntity entity) {
		if (internalDepends.indexOf(entity) == -1)
			internalDepends.add(entity);
	}
	
	public File getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = new File(path);
	}

	public ParseTree getParseTree() {
		return parseTree;
	}

	public void setParseTree(ParseTree parseTree) {
		this.parseTree = parseTree;
	}
	
	public String getName() {
		return path.getName().substring(0, path.getName().lastIndexOf("."));
	}
}