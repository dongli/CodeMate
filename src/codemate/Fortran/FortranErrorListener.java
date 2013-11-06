package codemate.Fortran;

import org.antlr.v4.runtime.*;

import codemate.ui.*;

import java.util.*;

public class FortranErrorListener extends BaseErrorListener {
	@Override
	public void syntaxError(Recognizer<?, ?> recognizer,
							Object offendingSymbol,
							int line, int charPositionInLine, String msg,
							RecognitionException e) {
		if (CommandLine.hasOption("-d")) {
			List<String> stack = ((Parser)recognizer).getRuleInvocationStack();
			Collections.reverse(stack);
			System.err.println("rule stack: "+stack);
			System.err.println("    line "+line+":"+charPositionInLine+" at "+
					offendingSymbol+": "+msg);
		}
	}
}
