package codemate;

/**
 * FortranRewriter
 *
 * This class will rewrite the Fortran code that has been parsed according to
 * some standand style.
 *
 * @author      Li Dong <dongli@lasg.iap.ac.cn>
 */

import org.antlr.v4.runtime.tree.*;

public class FortranRewriter extends FortranBaseVisitor<Void> {
    private int currentIndentLevel;
    private int indentWidth;
    private String newCode;

    FortranRewriter() {
        currentIndentLevel = 0;
        indentWidth = 4;
        newCode = "";
    }

    public String getNewCode() { return newCode; }

    /**
     * rewrite
     *
     * This method call cascade visit methods to rewrite the parse tree.
     *
     * @param       tree        The input parse tree.
     *
     * @author      Li Dong <dongli@lasg.iap.ac.cn>
     */
    public void rewrite(ParseTree tree) { visit(tree); }

    /**
     * indent
     *
     * This method adds indenting spaces into the newCode according to the
     * currentIndentLevel and indentWidth.
     *
     * @author      Li Dong <dongli@lasg.iap.ac.cn>
     */
    private void indent() {
        for (int i = 0; i < currentIndentLevel*indentWidth; ++i)
            newCode += " ";
    }

    private void increaseIndentLevel() { ++currentIndentLevel; }

    private void decreaseIndentLevel() { --currentIndentLevel; }

    // -------------------------------------------------------------------------
    // visit methods
    public Void visitId(FortranParser.IdContext ctx) {
        newCode += ctx.getText();
        return null;
    }

    public Void visitExpression(FortranParser.ExpressionContext ctx) {
        if (ctx.LEFT_PAREN() != null)
            newCode += "(";
        switch (ctx.expression().size()) {
        case 0:
            // TODO: Use visit methods for each alternative.
            newCode += ctx.getText();
            break;
        case 1:
            if (ctx.notOperator() != null)
                newCode += ".not. ";
            else if (ctx.MINUS() != null)
                newCode += "-";
            visitExpression(ctx.expression(0));
            break;
        case 2:
            visitExpression(ctx.expression(0));
            if (ctx.expOperator() != null)
                newCode += "**";
            else if (ctx.catOperator() != null)
                newCode += "//";
            else if (ctx.arithmeticOperator1() != null)
                newCode += ctx.arithmeticOperator1().getText();
            else if (ctx.arithmeticOperator2() != null)
                newCode += ctx.arithmeticOperator2().getText();
            else if (ctx.compareOperator() != null)
                newCode += " "+ctx.compareOperator().getText()+" ";
            else if (ctx.andOrOperator() != null)
                newCode += " "+ctx.andOrOperator().getText()+" ";
            visitExpression(ctx.expression(1));
            break;
        }
        if (ctx.RIGHT_PAREN() != null)
            newCode += ")";
        return null;
    }

    public Void visitRangeTail(FortranParser.RangeTailContext ctx) {
        if (ctx.DOUBLE_COLONS() == null) {
            newCode += ":";
            if (ctx.expression().size() > 0)
                visitExpression(ctx.expression(0));
            if (ctx.expression().size() > 1) {
                newCode += ":";
                visitExpression(ctx.expression(1));
            }
        } else {
            newCode += "::";
            if (ctx.expression().size() > 0)
                visitExpression(ctx.expression(0));
        }
        return null;
    }

    public Void visitSubscriptRange(FortranParser.SubscriptRangeContext ctx) {
        if (ctx.STAR() == null) {
            if (ctx.expression() != null) {
                visitExpression(ctx.expression());
                if (ctx.rangeTail() != null)
                    visitRangeTail(ctx.rangeTail());
            } else
                visitRangeTail(ctx.rangeTail());
        } else
            newCode += "*";
        return null;
    }

    public Void visitSubscriptRanges(FortranParser.SubscriptRangesContext ctx) {
        visitSubscriptRange(ctx.subscriptRange(0));
        for (int i = 1; i < ctx.subscriptRange().size(); ++i) {
            newCode += ", ";
            visitSubscriptRange(ctx.subscriptRange(i));
        }
        return null;
    }

    public Void visitIdWithArgs(FortranParser.IdWithArgsContext ctx) {
        visitId(ctx.id());
        newCode += "(";
        if (ctx.actualArguments() != null)
            visitActualArguments(ctx.actualArguments());
        else if (ctx.subscriptRanges() != null)
            visitSubscriptRanges(ctx.subscriptRanges());
        newCode += ")";
        return null;
    }

    public Void visitKeywordStatementParameters(FortranParser.KeywordStatementParametersContext ctx) {
        newCode += "("+visitActualArguments(ctx.actualArguments())+")";
        return null;
    }

    public Void visitKeywordStatement(FortranParser.KeywordStatementContext ctx) {
        newCode += ctx.EXECUTABLE_KEYWORD().getText();
        if (ctx.keywordStatementParameters() != null)
            visitKeywordStatementParameters(ctx.keywordStatementParameters());
        if (ctx.actualArguments() != null) {
            newCode += " ";
            visitActualArguments(ctx.actualArguments());
        }
        return null;
    }

    public Void visitAssignmentStatement(FortranParser.AssignmentStatementContext ctx) {
        if (ctx.idWithArgs() != null)
            visitIdWithArgs(ctx.idWithArgs());
        else if (ctx.templateInstance() != null)
            visitTemplateInstance(ctx.templateInstance());
        else if (ctx.derivedDataMember() != null)
            visitDerivedDataMember(ctx.derivedDataMember());
        else if (ctx.id() != null)
            visitId(ctx.id());
        newCode += " = ";
        visitExpression(ctx.expression());
        return null;
    }

    public Void visitDoRangeStatement(FortranParser.DoRangeStatementContext ctx) {
        newCode += "do ";
        visitId(ctx.id());
        newCode += " = ";
        visitExpression(ctx.expression(0));
        newCode += ", ";
        visitExpression(ctx.expression(1));
        if (ctx.expression().size() == 3) {
            newCode += ", ";
            visitExpression(ctx.expression(2));
        }
        newCode += "\n";
        increaseIndentLevel();
        visitExecutableStatements(ctx.executableStatements());
        decreaseIndentLevel();
        indent();
        newCode += "end do";
        return null;
    }

    public Void visitDoWhileStatement(FortranParser.DoWhileStatementContext ctx) {
        newCode += "do ";
        newCode += "while ";
        visitExpression(ctx.expression());
        newCode += "\n";
        increaseIndentLevel();
        visitExecutableStatements(ctx.executableStatements());
        decreaseIndentLevel();
        indent();
        newCode += "end do";
        return null;
    }

    public Void visitDoAnonyStatement(FortranParser.DoAnonyStatementContext ctx) {
        newCode += "do ";
        newCode += "\n";
        increaseIndentLevel();
        visitExecutableStatements(ctx.executableStatements());
        decreaseIndentLevel();
        indent();
        newCode += "end do";
        return null;
    }

    public Void visitDoStatement(FortranParser.DoStatementContext ctx) {
        if (ctx instanceof FortranParser.DoRangeStatementContext)
            visitDoRangeStatement((FortranParser.DoRangeStatementContext) ctx);
        else if (ctx instanceof FortranParser.DoWhileStatementContext)
            visitDoWhileStatement((FortranParser.DoWhileStatementContext) ctx);
        else if (ctx instanceof FortranParser.DoAnonyStatementContext)
            visitDoAnonyStatement((FortranParser.DoAnonyStatementContext) ctx);
        return null;
    }

    public Void visitExecutableStatements(FortranParser.ExecutableStatementsContext ctx) {
        for (FortranParser.ExecutableStatementContext exeStmt : ctx.executableStatement())
            visitExecutableStatement(exeStmt);
        return null;
    }

    public Void visitExecutableStatement(FortranParser.ExecutableStatementContext ctx) {
        indent();
        if (ctx.assignmentStatement() != null)
            visitAssignmentStatement(ctx.assignmentStatement());
        else if (ctx.ifStatement() != null)
            visitIfStatement(ctx.ifStatement());
        else if (ctx.doStatement() != null)
            visitDoStatement(ctx.doStatement());
        else if (ctx.selectStatement() != null)
            visitSelectStatement(ctx.selectStatement());
        else if (ctx.keywordStatement() != null)
            visitKeywordStatement(ctx.keywordStatement());
        else if (ctx.templateInstance() != null)
            visitTemplateInstance(ctx.templateInstance());
        newCode += "\n";
        return null;
    }

    public Void visitElseIfStatement(FortranParser.ElseIfStatementContext ctx) {
        indent();
        newCode += "else if ";
        visitExpression(ctx.expression());
        newCode += " then\n";
        increaseIndentLevel();
        visitExecutableStatements(ctx.executableStatements());
        decreaseIndentLevel();
        return null;
    }

    public Void visitElseStatement(FortranParser.ElseStatementContext ctx) {
        indent();
        newCode += "else\n";
        increaseIndentLevel();
        visitExecutableStatements(ctx.executableStatements());
        decreaseIndentLevel();
        return null;
    }

    public Void visitIfMultipleStatements(FortranParser.IfMultipleStatementsContext ctx) {
        newCode += "if ";
        visitExpression(ctx.expression());
        newCode += " then\n";
        increaseIndentLevel();
        visitExecutableStatements(ctx.executableStatements());
        decreaseIndentLevel();
        for (FortranParser.ElseIfStatementContext elseIf : ctx.elseIfStatement())
            visitElseIfStatement(elseIf);
        if (ctx.elseStatement() != null)
            visitElseStatement(ctx.elseStatement());
        indent();
        newCode += "end if";
        return null;
    }

    public Void visitIfStatement(FortranParser.IfStatementContext ctx) {
        if (ctx instanceof FortranParser.IfMultipleStatementsContext)
            visitIfMultipleStatements((FortranParser.IfMultipleStatementsContext) ctx);
        else if (ctx instanceof FortranParser.IfSingleStatementContext)
            visitIfSingleStatement((FortranParser.IfSingleStatementContext) ctx);
        return null;
    }

    public Void visitActualArgument(FortranParser.ActualArgumentContext ctx) {
        if (ctx.expression() != null)
            visitExpression(ctx.expression());
        else if (ctx.id() != null) {
            visitId(ctx.id());
            newCode += " = ";
            visitActualArgument(ctx.actualArgument());
        } else if (ctx.STAR() != null)
            newCode += "*";
        return null;
    }

    public Void visitActualArguments(FortranParser.ActualArgumentsContext ctx) {
        visitActualArgument(ctx.actualArgument(0));
        for (int i = 1; i < ctx.actualArgument().size(); ++i) {
            newCode += ", ";
            visitActualArgument(ctx.actualArgument(i));
        }
        return null;
    }

    public Void visitMember(FortranParser.MemberContext ctx) {
        if (ctx.id() != null)
            visitId(ctx.id());
        else if (ctx.idWithArgs() != null)
            visitIdWithArgs(ctx.idWithArgs());
        return null;
    }

    public Void visitDerivedDataMember(FortranParser.DerivedDataMemberContext ctx) {
        visitId(ctx.id());
        for (int i = 0; i < ctx.member().size(); ++i) {
            newCode += "%";
            visitMember(ctx.member(i));
        }
        return null;
    }

    public Void visitCaseLabels(FortranParser.CaseLabelsContext ctx) {
        indent();
        newCode += "case (";
        visitExpression(ctx.expression(0));
        for (int i = 1; i < ctx.expression().size(); ++i) {
            newCode += ", ";
            visitExpression(ctx.expression(i));
        }
        newCode += ")\n";
        return null;
    }

    public Void visitCaseStatement(FortranParser.CaseStatementContext ctx) {
        visitCaseLabels(ctx.caseLabels());
        increaseIndentLevel();
        visitExecutableStatements(ctx.executableStatements());
        decreaseIndentLevel();
        return null;
    }

    public Void visitCaseDefaultStatement(FortranParser.CaseDefaultStatementContext ctx) {
        indent();
        newCode += "case default\n";
        increaseIndentLevel();
        visitExecutableStatements(ctx.executableStatements());
        decreaseIndentLevel();
        return null;
    }

    public Void visitSelectStatement(FortranParser.SelectStatementContext ctx) {
        newCode += "select case (";
        if (ctx.templateInstance() != null)
            visitTemplateInstance(ctx.templateInstance());
        else if (ctx.derivedDataMember() != null)
            visitDerivedDataMember(ctx.derivedDataMember());
        else if (ctx.id() != null)
            visitId(ctx.id());
        newCode += ")\n";
        for (FortranParser.CaseStatementContext caseStmt : ctx.caseStatement())
            visitCaseStatement(caseStmt);
        if (ctx.caseDefaultStatement() != null)
            visitCaseDefaultStatement(ctx.caseDefaultStatement());
        indent();
        newCode += "end select\n";
        return null;
    }

    public Void visitDummyArguments(FortranParser.DummyArgumentsContext ctx) {
        visitId(ctx.id(0));
        for (int i = 1; i < ctx.id().size(); ++i) {
            newCode += ", ";
            visitId(ctx.id(i));
        }
        return null;
    }

    public Void visitIntrinsicType(FortranParser.IntrinsicTypeContext ctx) {
        newCode += ctx.INTRINSIC_TYPE().getText();
        if (ctx.intrinsicTypeParameter() != null)
            newCode += ctx.intrinsicTypeParameter().getText();
        return null;
    }

    public Void visitDerivedType(FortranParser.DerivedTypeContext ctx) {
        if (ctx.TYPE_KEYWORD() != null)
    		newCode += "type(";
        else if (ctx.CLASS_KEYWORD() != null)
        	newCode += "class(";
        if (ctx.id() != null)
            visitId(ctx.id());
        else if (ctx.templateInstance() != null)
            visitTemplateInstance(ctx.templateInstance());
        newCode += ")";
        return null;
    }

    public Void visitDataAttribute(FortranParser.DataAttributeContext ctx) {
        newCode += ctx.getText();
        return null;
    }

    public Void visitDataAttributes(FortranParser.DataAttributesContext ctx) {
        for (FortranParser.DataAttributeContext dataAttr : ctx.dataAttribute()) {
            newCode += ", ";
            visitDataAttribute(dataAttr);
        }
        newCode += " ::";
        return null;
    }

    public Void visitData(FortranParser.DataContext ctx) {
        if (ctx.assignmentStatement() != null)
            visitAssignmentStatement(ctx.assignmentStatement());
        else if (ctx.idWithArgs() != null)
            visitIdWithArgs(ctx.idWithArgs());
        else if (ctx.id() != null)
            visitId(ctx.id());
        return null;
    }

    public Void visitDataList(FortranParser.DataListContext ctx) {
        visitData(ctx.data(0));
        for (int i = 1; i < ctx.data().size(); ++i) {
            newCode += ", ";
            visitData(ctx.data(i));
        }
        return null;
    }

    public Void visitDataDeclarationStatement(FortranParser.DataDeclarationStatementContext ctx) {
        if (ctx.intrinsicType() != null)
            visitIntrinsicType(ctx.intrinsicType());
        else if (ctx.derivedType() != null)
            visitDerivedType(ctx.derivedType());
        if (ctx.dataAttributes() != null)
            visitDataAttributes(ctx.dataAttributes());
        newCode += " ";
        visitDataList(ctx.dataList());
        return null;
    }
    
    public Void visitTypeAttribute(FortranParser.TypeAttributeContext ctx) {
    	if (ctx.EXTENDS_KEYWORD() != null) {
    		newCode += "extends(";
    		if (ctx.templateInstance() != null)
    			visitTemplateInstance(ctx.templateInstance());
    		else if (ctx.id() != null)
    			visitId(ctx.id());
    		newCode += ")";
    	} else if (ctx.PUBLIC_KEYWORD() != null)
    		newCode += "public";
    	else if (ctx.PRIVATE_KEYWORD() != null)
    		newCode += "private";
    	else if (ctx.ABSTRACT_KEYWORD() != null)
    		newCode += "abstract";
    	return null;
    }
    
    public Void visitTypeAttributes(FortranParser.TypeAttributesContext ctx) {
    	for (FortranParser.TypeAttributeContext typeAttr : ctx.typeAttribute()) {
    		newCode += ", ";
    		visitTypeAttribute(typeAttr);
    	}
    	newCode += " ::";
    	return null;
    }
    
    public Void visitTypeBoundProcedureStatement(FortranParser.TypeBoundProcedureStatementContext ctx) {
    	indent();
    	newCode += "procedure";
    	if (ctx.bindingAttributes() != null)
    		visitBindingAttributes(ctx.bindingAttributes());
    	visitId(ctx.id(0));
    	if (ctx.POINT() != null) {
    		newCode += " => ";
    		visitId(ctx.id(1));
    	}
    	newCode += "\n";
    	return null;
    }
    
    public Void visitTypeBoundProcedures(FortranParser.TypeBoundProceduresContext ctx) {
    	indent();
    	newCode += "contains\n";
    	increaseIndentLevel();
    	for (int i = 0; i < ctx.typeBoundProcedureStatement().size(); ++i)
    		visitTypeBoundProcedureStatement(ctx.typeBoundProcedureStatement(i));
    	decreaseIndentLevel();
    	return null;
    }
    
    public Void visitTypeDeclarationStatement(FortranParser.TypeDeclarationStatementContext ctx) {
    	newCode += "type";
    	if (ctx.typeAttributes() != null)
    		visitTypeAttributes(ctx.typeAttributes());
    	newCode += " ";
    	visitId(ctx.id(0));
    	newCode += "\n";
    	increaseIndentLevel();
    	for (int i = 0; i < ctx.dataDeclarationStatement().size(); ++i) {
    		indent();
    		visitDataDeclarationStatement(ctx.dataDeclarationStatement(i));
    		newCode += "\n";
    	}
    	decreaseIndentLevel();
    	if (ctx.typeBoundProcedures() != null)
    		visitTypeBoundProcedures(ctx.typeBoundProcedures());
    	indent();
    	newCode += "end type ";
    	visitId(ctx.id(0));
    	return null;
    }

    public Void visitDeclarationStatement(FortranParser.DeclarationStatementContext ctx) {
        indent();
        if (ctx.dataDeclarationStatement() != null)
            visitDataDeclarationStatement(ctx.dataDeclarationStatement());
        else if (ctx.typeDeclarationStatement() != null)
            visitTypeDeclarationStatement(ctx.typeDeclarationStatement());
        else if (ctx.interfaceStatement() != null)
            visitInterfaceStatement(ctx.interfaceStatement());
        else if (ctx.namelistStatement() != null)
            visitNamelistStatement(ctx.namelistStatement());
        newCode += "\n";
        return null;
    }

    public Void visitDeclarationStatements(FortranParser.DeclarationStatementsContext ctx) {
        for (FortranParser.DeclarationStatementContext declStmt : ctx.declarationStatement())
            visitDeclarationStatement(declStmt);
        return null;
    }

    public Void visitUsedItem(FortranParser.UsedItemContext ctx) {
        visitId(ctx.id(0));
        if (ctx.id().size() > 1) {
            newCode += " => ";
            visitId(ctx.id(1));
        }
        return null;
    }

    public Void visitUsedItemList(FortranParser.UsedItemListContext ctx) {
        visitUsedItem(ctx.usedItem(0));
        for (int i = 1; i < ctx.usedItem().size(); ++i) {
            newCode += ", ";
            visitUsedItem(ctx.usedItem(i));
        }
        return null;
    }

    public Void visitUseStatement(FortranParser.UseStatementContext ctx) {
        indent();
        newCode += "use ";
        visitId(ctx.id());
        if (ctx.ONLY_KEYWORD() != null)
            newCode += ", only: ";
        if (ctx.usedItemList() != null)
            visitUsedItemList(ctx.usedItemList());
        newCode += "\n";
        return null;
    }

    public Void visitUseStatements(FortranParser.UseStatementsContext ctx) {
        for (FortranParser.UseStatementContext useStmt : ctx.useStatement())
            visitUseStatement(useStmt);
        return null;
    }

    public Void visitImplicitNoneStatement(FortranParser.ImplicitNoneStatementContext ctx) {
        indent();
        newCode += "implicit none\n";
        return null;
    }

    public Void visitAccessibilityStatement(FortranParser.AccessibilityStatementContext ctx) {
        indent();
        if (ctx.PUBLIC_KEYWORD() != null)
            newCode += "public ";
        else if (ctx.PRIVATE_KEYWORD() != null)
            newCode += "private ";
        visitId(ctx.id(0));
        for (int i = 1; i < ctx.id().size(); ++i) {
            newCode += ", ";
            visitId(ctx.id(i));
        }
        newCode += "\n";
        return null;
    }

    public Void visitAccessibilityStatements(FortranParser.AccessibilityStatementsContext ctx) {
        for (FortranParser.AccessibilityStatementContext accStmt : ctx.accessibilityStatement())
            visitAccessibilityStatement(accStmt);
        return null;
    }

    public Void visitContainedProcedures(FortranParser.ContainedProceduresContext ctx) {
    	indent();
    	newCode += "contains\n\n";
    	increaseIndentLevel();
    	for (int i = 0; i < ctx.procedure().size(); ++i)
    		visitProcedure(ctx.procedure(i));
    	decreaseIndentLevel();
    	return null;
    }
    
    public Void visitProcedure(FortranParser.ProcedureContext ctx) {
        indent();
        newCode += ctx.PROCEDURE_TYPE(0).getText()+" ";
        visitId(ctx.id(0));
        if (!(ctx.PROCEDURE_TYPE(0).getText().equals("program") ||
              ctx.PROCEDURE_TYPE(0).getText().equals("module"))) {
            newCode += " (";
            if (ctx.dummyArguments() != null)
                visitDummyArguments(ctx.dummyArguments());
            newCode += ")";
            if (ctx.RESULT_KEYWORD() != null) {
                newCode += " result(";
                visitId(ctx.id(1));
                newCode += ")";
            }
        }
        newCode += "\n\n";
        increaseIndentLevel();
        visitUseStatements(ctx.useStatements());
        if (ctx.implicitNoneStatement() != null)
            visitImplicitNoneStatement(ctx.implicitNoneStatement());
        visitAccessibilityStatements(ctx.accessibilityStatements());
        visitDeclarationStatements(ctx.declarationStatements());
        visitExecutableStatements(ctx.executableStatements());
        decreaseIndentLevel();
        if (ctx.containedProcedures() != null)
            visitContainedProcedures(ctx.containedProcedures());
        indent();
        newCode += "end "+ctx.PROCEDURE_TYPE(0).getText()+" ";
        visitId(ctx.id(0));
        newCode += "\n";
        return null;
    }

    // *************************************************************************
    // visit methods for template
    public Void visitTemplateArgument(FortranParser.TemplateArgumentContext ctx) {
        if (ctx.expression() != null)
            visitExpression(ctx.expression());
        else if (ctx.subscriptRange() != null)
            visitSubscriptRange(ctx.subscriptRange());
        return null;
    }

    public Void visitTemplateArguments(FortranParser.TemplateArgumentsContext ctx) {
        visitTemplateArgument(ctx.templateArgument(0));
        for (int i = 1; i < ctx.templateArgument().size(); ++i) {
            newCode += ", ";
            visitTemplateArgument(ctx.templateArgument(i));
        }
        return null;
    }

    public Void visitTemplateBlock(FortranParser.TemplateBlockContext ctx) {
        visitExecutableStatements(ctx.executableStatements());
        return null;
    }

    public Void visitTemplateInstance(FortranParser.TemplateInstanceContext ctx) {
        // this method just write out the original template instance without
        // instantiation
        visitId(ctx.id());
        newCode += "<";
        if (ctx.templateArguments() != null)
            visitTemplateArguments(ctx.templateArguments());
        newCode += ">";
        if (ctx.templateBlock() != null) {
            newCode += " {\n";
            increaseIndentLevel();
            visitTemplateBlock(ctx.templateBlock());
            decreaseIndentLevel();
            indent();
            newCode += "}";
        }
        return null;
    }
}
