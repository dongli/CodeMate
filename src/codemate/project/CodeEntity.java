package codemate.project;

/**
 * CodeEntity
 * 
 * This class describes the code entity.
 * 
 * @author Li Dong <dongli@lasg.iap.ac.cn>
 * @since 2013-07-11
 */

import org.antlr.v4.runtime.tree.*;

import codemate.librarymate.*;
import codemate.ui.*;

import java.io.*;
import java.util.*;

public class CodeEntity {
	private File path;
	private String type;
	private ParseTree parseTree;
	public List<CodeEntity> internalDepends = new ArrayList<CodeEntity>();
	public List<LibraryMate> externalDepends = new ArrayList<LibraryMate>();

	public CodeEntity(String path) {
		UI.notice("CodeEntity", "Add code entity "+path+".");
		this.setPath(path);
	}

	public void addInternalDepend(CodeEntity entity) {
		if (internalDepends.indexOf(entity) == -1)
			internalDepends.add(entity);
	}
	
	public void addExternalDepend(LibraryMate mate) {
		if (externalDepends.indexOf(mate) == -1) {
			UI.notice("CodeEntity",
					"Add library "+mate.getLibraryName()+
					" dependency for "+getName()+".");
			externalDepends.add(mate);
		}
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}