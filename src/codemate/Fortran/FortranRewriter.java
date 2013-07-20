package codemate.Fortran;

/**
 * FortranRewriter
 *
 * This class will rewrite the Fortran code that has been parsed according to
 * some standand style.
 *
 * @author      Li Dong <dongli@lasg.iap.ac.cn>
 */

import org.antlr.v4.runtime.tree.*;

import codemate.Fortran.FortranParser.*;

public class FortranRewriter extends FortranBaseVisitor<Void> {
    private int currentIndentLevel;
    private int indentWidth;
    private String newCode;

    public FortranRewriter() {
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
    public Void visitId(IdContext ctx) {
        newCode += ctx.getText();
        return null;
    }
    
    public Void visitNumerics(NumericsContext ctx) {
    	newCode += ctx.getText();
    	return null;
    }
    
    public Void visitLiteralArray(LiteralArrayContext ctx) {
    	newCode += ctx.ARRAY_START().getText();
    	visitExpression(ctx.expression(0));
    	for (int i = 1; i < ctx.expression().size(); ++i) {
    		newCode += ",";
    		visitExpression(ctx.expression(i));
    	}
    	newCode += ctx.ARRAY_END().getText();
    	return null;
    }

    public Void visitExpression(ExpressionContext ctx) {
        if (ctx.LEFT_PAREN() != null)
            newCode += "(";
        switch (ctx.expression().size()) {
        case 0:
            if (ctx.id() != null)
            	visitId(ctx.id());
            else if (ctx.numerics() != null)
            	visitNumerics(ctx.numerics());
            else if (ctx.idWithArgs() != null)
            	visitIdWithArgs(ctx.idWithArgs());
            else if (ctx.derivedDataMember() != null)
            	visitDerivedDataMember(ctx.derivedDataMember());
            else if (ctx.templateInstance() != null)
            	visitTemplateInstance(ctx.templateInstance());
            else if (ctx.literalArray() != null)
            	visitLiteralArray(ctx.literalArray());
            else
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

    public Void visitRangeTail(RangeTailContext ctx) {
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

    public Void visitSubscriptRange(SubscriptRangeContext ctx) {
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

    public Void visitSubscriptRanges(SubscriptRangesContext ctx) {
        visitSubscriptRange(ctx.subscriptRange(0));
        for (int i = 1; i < ctx.subscriptRange().size(); ++i) {
            newCode += ", ";
            visitSubscriptRange(ctx.subscriptRange(i));
        }
        return null;
    }

    public Void visitIdWithArgs(IdWithArgsContext ctx) {
        visitId(ctx.id());
        newCode += "(";
        if (ctx.actualArguments() != null)
            visitActualArguments(ctx.actualArguments());
        else if (ctx.subscriptRanges() != null)
            visitSubscriptRanges(ctx.subscriptRanges());
        newCode += ")";
        return null;
    }

    public Void visitKeywordStatementParameters(KeywordStatementParametersContext ctx) {
        newCode += "(";
        visitActualArguments(ctx.actualArguments());
        newCode += ")";
        return null;
    }

    public Void visitKeywordStatement1(KeywordStatement1Context ctx) {
        newCode += ctx.EXECUTABLE_KEYWORD_1().getText();
        visitKeywordStatementParameters(ctx.keywordStatementParameters());
        return null;
    }
    
    public Void visitKeywordStatement2(KeywordStatement2Context ctx) {
        newCode += ctx.EXECUTABLE_KEYWORD_2().getText();
        if (ctx.keywordStatementParameters() != null)
            visitKeywordStatementParameters(ctx.keywordStatementParameters());
        if (ctx.actualArguments() != null) {
        	newCode += " ";
        	visitActualArguments(ctx.actualArguments());
        }
        return null;
    }
    
    public Void visitKeywordStatement3(KeywordStatement3Context ctx) {
        newCode += ctx.EXECUTABLE_KEYWORD_3().getText();
        newCode += " ";
        if (ctx.id() != null)
        	visitId(ctx.id());
        else if (ctx.numerics() != null)
        	visitNumerics(ctx.numerics());
        return null;
    }
    
    public Void visitKeywordStatement(KeywordStatementContext ctx) {
        if (ctx.keywordStatement1() != null)
        	visitKeywordStatement1(ctx.keywordStatement1());
        else if (ctx.keywordStatement2() != null)
        	visitKeywordStatement2(ctx.keywordStatement2());
        else if (ctx.keywordStatement3() != null)
        	visitKeywordStatement3(ctx.keywordStatement3());
        return null;
    }

    public Void visitAssignmentStatement(AssignmentStatementContext ctx) {
        if (ctx.idWithArgs() != null)
            visitIdWithArgs(ctx.idWithArgs());
        else if (ctx.templateInstance() != null)
            visitTemplateInstance(ctx.templateInstance());
        else if (ctx.derivedDataMember() != null)
            visitDerivedDataMember(ctx.derivedDataMember());
        else if (ctx.id() != null)
            visitId(ctx.id());
        if (ctx.EQUAL() != null)
        	newCode += " = ";
        else if (ctx.POINT() != null)
        	newCode += " => ";
        visitExpression(ctx.expression());
        return null;
    }

    public Void visitDoRangeStatement(DoRangeStatementContext ctx) {
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

    public Void visitDoWhileStatement(DoWhileStatementContext ctx) {
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

    public Void visitDoAnonyStatement(DoAnonyStatementContext ctx) {
        newCode += "do ";
        newCode += "\n";
        increaseIndentLevel();
        visitExecutableStatements(ctx.executableStatements());
        decreaseIndentLevel();
        indent();
        newCode += "end do";
        return null;
    }

    public Void visitDoStatement(DoStatementContext ctx) {
        if (ctx instanceof DoRangeStatementContext)
            visitDoRangeStatement((DoRangeStatementContext) ctx);
        else if (ctx instanceof DoWhileStatementContext)
            visitDoWhileStatement((DoWhileStatementContext) ctx);
        else if (ctx instanceof DoAnonyStatementContext)
            visitDoAnonyStatement((DoAnonyStatementContext) ctx);
        return null;
    }

    public Void visitExecutableStatements(ExecutableStatementsContext ctx) {
        for (ExecutableStatementContext exeStmt : ctx.executableStatement()) {
            indent();
            visitExecutableStatement(exeStmt);
        }
        return null;
    }

    public Void visitExecutableStatement(ExecutableStatementContext ctx) {
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

    public Void visitElseIfStatement(ElseIfStatementContext ctx) {
        indent();
        newCode += "else if ";
        visitExpression(ctx.expression());
        newCode += " then\n";
        increaseIndentLevel();
        visitExecutableStatements(ctx.executableStatements());
        decreaseIndentLevel();
        return null;
    }

    public Void visitElseStatement(ElseStatementContext ctx) {
        indent();
        newCode += "else\n";
        increaseIndentLevel();
        visitExecutableStatements(ctx.executableStatements());
        decreaseIndentLevel();
        return null;
    }

    public Void visitIfMultipleStatements(IfMultipleStatementsContext ctx) {
        newCode += "if ";
        visitExpression(ctx.expression());
        newCode += " then\n";
        increaseIndentLevel();
        visitExecutableStatements(ctx.executableStatements());
        decreaseIndentLevel();
        for (ElseIfStatementContext elseIf : ctx.elseIfStatement())
            visitElseIfStatement(elseIf);
        if (ctx.elseStatement() != null)
            visitElseStatement(ctx.elseStatement());
        indent();
        newCode += "end if";
        return null;
    }

    public Void visitIfSingleStatement(IfSingleStatementContext ctx) {
    	newCode += "if ";
    	visitExpression(ctx.expression());
    	newCode += " ";
    	visitExecutableStatement(ctx.executableStatement());
    	return null;
    }
    
    public Void visitIfStatement(IfStatementContext ctx) {
        if (ctx instanceof IfMultipleStatementsContext)
            visitIfMultipleStatements((IfMultipleStatementsContext) ctx);
        else if (ctx instanceof IfSingleStatementContext)
            visitIfSingleStatement((IfSingleStatementContext) ctx);
        return null;
    }

    public Void visitActualArgument(ActualArgumentContext ctx) {
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

    public Void visitActualArguments(ActualArgumentsContext ctx) {
        visitActualArgument(ctx.actualArgument(0));
        for (int i = 1; i < ctx.actualArgument().size(); ++i) {
            newCode += ", ";
            visitActualArgument(ctx.actualArgument(i));
        }
        return null;
    }

    public Void visitMember(MemberContext ctx) {
        if (ctx.id() != null)
            visitId(ctx.id());
        else if (ctx.idWithArgs() != null)
            visitIdWithArgs(ctx.idWithArgs());
        return null;
    }

    public Void visitDerivedDataMember(DerivedDataMemberContext ctx) {
        visitId(ctx.id());
        for (int i = 0; i < ctx.member().size(); ++i) {
            newCode += "%";
            visitMember(ctx.member(i));
        }
        return null;
    }

    public Void visitCaseLabels(CaseLabelsContext ctx) {
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

    public Void visitCaseStatement(CaseStatementContext ctx) {
        visitCaseLabels(ctx.caseLabels());
        increaseIndentLevel();
        visitExecutableStatements(ctx.executableStatements());
        decreaseIndentLevel();
        return null;
    }

    public Void visitCaseDefaultStatement(CaseDefaultStatementContext ctx) {
        indent();
        newCode += "case default\n";
        increaseIndentLevel();
        visitExecutableStatements(ctx.executableStatements());
        decreaseIndentLevel();
        return null;
    }

    public Void visitSelectStatement(SelectStatementContext ctx) {
        newCode += "select case (";
        if (ctx.templateInstance() != null)
            visitTemplateInstance(ctx.templateInstance());
        else if (ctx.derivedDataMember() != null)
            visitDerivedDataMember(ctx.derivedDataMember());
        else if (ctx.id() != null)
            visitId(ctx.id());
        newCode += ")\n";
        for (CaseStatementContext caseStmt : ctx.caseStatement())
            visitCaseStatement(caseStmt);
        if (ctx.caseDefaultStatement() != null)
            visitCaseDefaultStatement(ctx.caseDefaultStatement());
        indent();
        newCode += "end select\n";
        return null;
    }

    public Void visitDummyArguments(DummyArgumentsContext ctx) {
        visitId(ctx.id(0));
        for (int i = 1; i < ctx.id().size(); ++i) {
            newCode += ", ";
            visitId(ctx.id(i));
        }
        return null;
    }

    public Void visitIntrinsicTypeParameter(IntrinsicTypeParameterContext ctx) {
    	newCode += "(";
    	if (ctx.EQUAL() != null) {
    		visitId(ctx.id(0));
    		newCode += "=";
    	}
    	if (ctx.numerics() != null)
    		visitNumerics(ctx.numerics());
    	else if (ctx.id().size() > 0) {
    		if (ctx.id().size() == 1)
    			visitId(ctx.id(0));
    		else if (ctx.id().size() == 2)
    			visitId(ctx.id(1));
    	} else if (ctx.STAR() != null)
    		newCode += "*";
    	newCode += ")";
    	return null;
    }
    
    public Void visitIntrinsicType(IntrinsicTypeContext ctx) {
        newCode += ctx.INTRINSIC_TYPE_KEYWORD().getText();
        if (ctx.intrinsicTypeParameter() != null)
        	visitIntrinsicTypeParameter(ctx.intrinsicTypeParameter());
        return null;
    }

    public Void visitDerivedTypeName(DerivedTypeNameContext ctx) {
    	if (ctx.id() != null)
            visitId(ctx.id());
        else if (ctx.templateInstance() != null)
            visitTemplateInstance(ctx.templateInstance());
    	return null;
    }
    
    public Void visitDerivedType(DerivedTypeContext ctx) {
        if (ctx.TYPE_KEYWORD() != null)
    		newCode += "type(";
        else if (ctx.CLASS_KEYWORD() != null)
        	newCode += "class(";
        visitDerivedTypeName(ctx.derivedTypeName());
        newCode += ")";
        return null;
    }

    public Void visitDataAttribute(DataAttributeContext ctx) {
        newCode += ctx.getText();
        return null;
    }

    public Void visitDataAttributes(DataAttributesContext ctx) {
        for (DataAttributeContext dataAttr : ctx.dataAttribute()) {
            newCode += ", ";
            visitDataAttribute(dataAttr);
        }
        newCode += " ::";
        return null;
    }

    public Void visitData(DataContext ctx) {
        if (ctx.assignmentStatement() != null)
            visitAssignmentStatement(ctx.assignmentStatement());
        else if (ctx.idWithArgs() != null)
            visitIdWithArgs(ctx.idWithArgs());
        else if (ctx.id() != null)
            visitId(ctx.id());
        return null;
    }

    public Void visitDataList(DataListContext ctx) {
        visitData(ctx.data(0));
        for (int i = 1; i < ctx.data().size(); ++i) {
            newCode += ", ";
            visitData(ctx.data(i));
        }
        return null;
    }

    public Void visitDataDeclarationStatement(DataDeclarationStatementContext ctx) {
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
    
    public Void visitDataDeclarationStatements(DataDeclarationStatementsContext ctx) {
    	for (int i = 0; i < ctx.dataDeclarationStatement().size(); ++i) {
    		indent();
    		visitDataDeclarationStatement(ctx.dataDeclarationStatement(i));
    		newCode += "\n";
    	}
    	return null;
    }
    
    public Void visitExtendsAttribute(ExtendsAttributeContext ctx) {
    	newCode += "extends(";
		if (ctx.templateInstance() != null)
			visitTemplateInstance(ctx.templateInstance());
		else if (ctx.id() != null)
			visitId(ctx.id());
		newCode += ")";
		return null;
    }
    
    public Void visitTypeAttribute(TypeAttributeContext ctx) {
    	if (ctx.extendsAttribute() != null) {
    		visitExtendsAttribute(ctx.extendsAttribute());
    	} else if (ctx.PUBLIC_KEYWORD() != null)
    		newCode += "public";
    	else if (ctx.PRIVATE_KEYWORD() != null)
    		newCode += "private";
    	else if (ctx.ABSTRACT_KEYWORD() != null)
    		newCode += "abstract";
    	return null;
    }
    
    public Void visitTypeAttributes(TypeAttributesContext ctx) {
    	for (TypeAttributeContext typeAttr : ctx.typeAttribute()) {
    		newCode += ", ";
    		visitTypeAttribute(typeAttr);
    	}
    	newCode += " ::";
    	return null;
    }
    
    public Void visitBindingAttribute(BindingAttributeContext ctx) {
    	newCode += ctx.getText();
    	return null;
    }
    
    public Void visitBindingAttributes(BindingAttributesContext ctx) {
    	for (int i = 0; i < ctx.bindingAttribute().size(); ++i) {
    		newCode += ", ";
    		visitBindingAttribute(ctx.bindingAttribute(i));
    	}
    	newCode += " :: ";
    	return null;
    }
    
    public Void visitTypeBoundProcedureStatement(TypeBoundProcedureStatementContext ctx) {
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
    
    public Void visitTypeBoundProcedureStatements(TypeBoundProcedureStatementsContext ctx) {
    	increaseIndentLevel();
    	for (int i = 0; i < ctx.typeBoundProcedureStatement().size(); ++i)
    		visitTypeBoundProcedureStatement(ctx.typeBoundProcedureStatement(i));
    	decreaseIndentLevel();
    	return null;
    }
    
    public Void visitContainedTypeBoundProcedures(ContainedTypeBoundProceduresContext ctx) {
    	indent();
    	newCode += "contains\n";
    	visitTypeBoundProcedureStatements(ctx.typeBoundProcedureStatements());
    	return null;
    }
    
    public Void visitTypeDeclarationStatement(TypeDeclarationStatementContext ctx) {
    	newCode += "type";
    	if (ctx.typeAttributes() != null)
    		visitTypeAttributes(ctx.typeAttributes());
    	newCode += " ";
    	visitId(ctx.id(0));
    	newCode += "\n";
    	increaseIndentLevel();
    	visitDataDeclarationStatements(ctx.dataDeclarationStatements());
    	decreaseIndentLevel();
    	if (ctx.containedTypeBoundProcedures() != null)
    		visitContainedTypeBoundProcedures(ctx.containedTypeBoundProcedures());
    	indent();
    	newCode += "end type ";
    	visitId(ctx.id(0));
    	return null;
    }
    
    public Void visitNamelistParameters(NamelistParametersContext ctx) {
    	visitId(ctx.id(0));
    	for (int i = 1; i < ctx.id().size(); ++i) {
    		newCode += ", ";
    		visitId(ctx.id(i));
    	}
    	return null;
    }
    
    public Void visitNamelistStatement(NamelistStatementContext ctx) {
    	newCode += "namelist /";
    	visitId(ctx.id());
    	newCode += "/ ";
    	visitNamelistParameters(ctx.namelistParameters());
    	newCode += "\n";
    	return null;
    }
    
    public Void visitModuleProcedure(ModuleProcedureContext ctx) {
    	indent();
    	newCode += "module procedure ";
    	visitId(ctx.id());
    	newCode += "\n";
    	return null;
    }
    
    public Void visitInterfaceStatement(InterfaceStatementContext ctx) {
    	newCode += "interface";
    	if (ctx.id().size() > 0) {
    		newCode += " ";
    		visitId(ctx.id(0));
    	}
    	newCode += "\n";
    	increaseIndentLevel();
    	for (ModuleProcedureContext mp : ctx.moduleProcedure())
    		visitModuleProcedure(mp);
    	decreaseIndentLevel();
    	indent();
    	newCode += "end interface";
    	if (ctx.id().size() > 0) {
    		newCode += " ";
    		visitId(ctx.id(0));
    	}
    	newCode += "\n";
    	return null;
    }

    public Void visitDeclarationStatement(DeclarationStatementContext ctx) {
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

    public Void visitDeclarationStatements(DeclarationStatementsContext ctx) {
        for (DeclarationStatementContext declStmt : ctx.declarationStatement())
            visitDeclarationStatement(declStmt);
        if (ctx.declarationStatement().size() != 0)
        	newCode += "\n";
        return null;
    }

    public Void visitUsedItem(UsedItemContext ctx) {
        visitId(ctx.id(0));
        if (ctx.id().size() > 1) {
            newCode += " => ";
            visitId(ctx.id(1));
        }
        return null;
    }

    public Void visitUsedItemList(UsedItemListContext ctx) {
        visitUsedItem(ctx.usedItem(0));
        for (int i = 1; i < ctx.usedItem().size(); ++i) {
            newCode += ", ";
            visitUsedItem(ctx.usedItem(i));
        }
        return null;
    }

    public Void visitUseStatement(UseStatementContext ctx) {
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

    public Void visitUseStatements(UseStatementsContext ctx) {
        for (UseStatementContext useStmt : ctx.useStatement())
            visitUseStatement(useStmt);
        if (ctx.useStatement().size() != 0)
        	newCode += "\n";
        return null;
    }

    public Void visitImplicitNoneStatement(ImplicitNoneStatementContext ctx) {
        indent();
        newCode += "implicit none\n\n";
        return null;
    }

    public Void visitAccessibilityStatement(AccessibilityStatementContext ctx) {
        indent();
        if (ctx.PUBLIC_KEYWORD() != null)
            newCode += "public ";
        else if (ctx.PRIVATE_KEYWORD() != null)
            newCode += "private ";
        if (ctx.id().size() > 0) {
        	visitId(ctx.id(0));
        	for (int i = 1; i < ctx.id().size(); ++i) {
        		newCode += ", ";
        		visitId(ctx.id(i));
        	}
        }
        newCode += "\n";
        return null;
    }

    public Void visitAccessibilityStatements(AccessibilityStatementsContext ctx) {
        for (AccessibilityStatementContext accStmt : ctx.accessibilityStatement())
            visitAccessibilityStatement(accStmt);
        if (ctx.accessibilityStatement().size() != 0)
        	newCode += "\n";
        return null;
    }

    public Void visitContainedProcedures(ContainedProceduresContext ctx) {
    	increaseIndentLevel();
    	for (int i = 0; i < ctx.procedure().size(); ++i)
    		visitProcedure(ctx.procedure(i));
    	decreaseIndentLevel();
    	return null;
    }
    
    public Void visitProcedure(ProcedureContext ctx) {
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
        if (ctx.containedProcedures().getChildCount() > 0) {
        	indent();
        	// When instantiating template, new contained procedures may be
        	// added, but there may not be any contained procedure, so does the
        	// 'contains' keyword. To handle this case, I just write 'contains'
        	// if there is any contained procedure.
        	newCode += "contains\n\n";
        	visitContainedProcedures(ctx.containedProcedures());
        }
        indent();
        newCode += "end "+ctx.PROCEDURE_TYPE(0).getText()+" ";
        visitId(ctx.id(0));
        newCode += "\n";
        return null;
    }

    // *************************************************************************
    // visit methods for template
    public Void visitTemplateArgument(TemplateArgumentContext ctx) {
        if (ctx.expression() != null)
            visitExpression(ctx.expression());
        else if (ctx.subscriptRange() != null)
            visitSubscriptRange(ctx.subscriptRange());
        return null;
    }

    public Void visitTemplateArguments(TemplateArgumentsContext ctx) {
        visitTemplateArgument(ctx.templateArgument(0));
        for (int i = 1; i < ctx.templateArgument().size(); ++i) {
            newCode += ", ";
            visitTemplateArgument(ctx.templateArgument(i));
        }
        return null;
    }

    public Void visitTemplateBlock(TemplateBlockContext ctx) {
        visitExecutableStatements(ctx.executableStatements());
        return null;
    }

    public Void visitTemplateInstance(TemplateInstanceContext ctx) {
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