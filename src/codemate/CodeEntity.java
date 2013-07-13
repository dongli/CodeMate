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

public class CodeEntity {
	private String path;
	private ParseTree parseTree;

	CodeEntity(String path) {
		UI.notice("CodeEntity", "Add code entity "+path+".");
		this.setPath(path);
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public ParseTree getParseTree() {
		return parseTree;
	}

	public void setParseTree(ParseTree parseTree) {
		this.parseTree = parseTree;
	}
}