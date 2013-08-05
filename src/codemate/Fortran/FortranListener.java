// Generated from Fortran.g4 by ANTLR 4.1

    package codemate.Fortran;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link FortranParser}.
 */
public interface FortranListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link FortranParser#undefDirective}.
	 * @param ctx the parse tree
	 */
	void enterUndefDirective(@NotNull FortranParser.UndefDirectiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#undefDirective}.
	 * @param ctx the parse tree
	 */
	void exitUndefDirective(@NotNull FortranParser.UndefDirectiveContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#whereMultipleStatements}.
	 * @param ctx the parse tree
	 */
	void enterWhereMultipleStatements(@NotNull FortranParser.WhereMultipleStatementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#whereMultipleStatements}.
	 * @param ctx the parse tree
	 */
	void exitWhereMultipleStatements(@NotNull FortranParser.WhereMultipleStatementsContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#defineDirective}.
	 * @param ctx the parse tree
	 */
	void enterDefineDirective(@NotNull FortranParser.DefineDirectiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#defineDirective}.
	 * @param ctx the parse tree
	 */
	void exitDefineDirective(@NotNull FortranParser.DefineDirectiveContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#includeDirective}.
	 * @param ctx the parse tree
	 */
	void enterIncludeDirective(@NotNull FortranParser.IncludeDirectiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#includeDirective}.
	 * @param ctx the parse tree
	 */
	void exitIncludeDirective(@NotNull FortranParser.IncludeDirectiveContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#doRangeStatement}.
	 * @param ctx the parse tree
	 */
	void enterDoRangeStatement(@NotNull FortranParser.DoRangeStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#doRangeStatement}.
	 * @param ctx the parse tree
	 */
	void exitDoRangeStatement(@NotNull FortranParser.DoRangeStatementContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#gtOperator}.
	 * @param ctx the parse tree
	 */
	void enterGtOperator(@NotNull FortranParser.GtOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#gtOperator}.
	 * @param ctx the parse tree
	 */
	void exitGtOperator(@NotNull FortranParser.GtOperatorContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#internalFile}.
	 * @param ctx the parse tree
	 */
	void enterInternalFile(@NotNull FortranParser.InternalFileContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#internalFile}.
	 * @param ctx the parse tree
	 */
	void exitInternalFile(@NotNull FortranParser.InternalFileContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#bindingAttribute}.
	 * @param ctx the parse tree
	 */
	void enterBindingAttribute(@NotNull FortranParser.BindingAttributeContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#bindingAttribute}.
	 * @param ctx the parse tree
	 */
	void exitBindingAttribute(@NotNull FortranParser.BindingAttributeContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#conditionDirective}.
	 * @param ctx the parse tree
	 */
	void enterConditionDirective(@NotNull FortranParser.ConditionDirectiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#conditionDirective}.
	 * @param ctx the parse tree
	 */
	void exitConditionDirective(@NotNull FortranParser.ConditionDirectiveContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#file}.
	 * @param ctx the parse tree
	 */
	void enterFile(@NotNull FortranParser.FileContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#file}.
	 * @param ctx the parse tree
	 */
	void exitFile(@NotNull FortranParser.FileContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#templateArgument}.
	 * @param ctx the parse tree
	 */
	void enterTemplateArgument(@NotNull FortranParser.TemplateArgumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#templateArgument}.
	 * @param ctx the parse tree
	 */
	void exitTemplateArgument(@NotNull FortranParser.TemplateArgumentContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#compareOperator}.
	 * @param ctx the parse tree
	 */
	void enterCompareOperator(@NotNull FortranParser.CompareOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#compareOperator}.
	 * @param ctx the parse tree
	 */
	void exitCompareOperator(@NotNull FortranParser.CompareOperatorContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#caseDefaultStatement}.
	 * @param ctx the parse tree
	 */
	void enterCaseDefaultStatement(@NotNull FortranParser.CaseDefaultStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#caseDefaultStatement}.
	 * @param ctx the parse tree
	 */
	void exitCaseDefaultStatement(@NotNull FortranParser.CaseDefaultStatementContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#neOperator}.
	 * @param ctx the parse tree
	 */
	void enterNeOperator(@NotNull FortranParser.NeOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#neOperator}.
	 * @param ctx the parse tree
	 */
	void exitNeOperator(@NotNull FortranParser.NeOperatorContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#andOrOperator}.
	 * @param ctx the parse tree
	 */
	void enterAndOrOperator(@NotNull FortranParser.AndOrOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#andOrOperator}.
	 * @param ctx the parse tree
	 */
	void exitAndOrOperator(@NotNull FortranParser.AndOrOperatorContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#actualArguments}.
	 * @param ctx the parse tree
	 */
	void enterActualArguments(@NotNull FortranParser.ActualArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#actualArguments}.
	 * @param ctx the parse tree
	 */
	void exitActualArguments(@NotNull FortranParser.ActualArgumentsContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#numerics}.
	 * @param ctx the parse tree
	 */
	void enterNumerics(@NotNull FortranParser.NumericsContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#numerics}.
	 * @param ctx the parse tree
	 */
	void exitNumerics(@NotNull FortranParser.NumericsContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#catOperator}.
	 * @param ctx the parse tree
	 */
	void enterCatOperator(@NotNull FortranParser.CatOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#catOperator}.
	 * @param ctx the parse tree
	 */
	void exitCatOperator(@NotNull FortranParser.CatOperatorContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#dataList}.
	 * @param ctx the parse tree
	 */
	void enterDataList(@NotNull FortranParser.DataListContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#dataList}.
	 * @param ctx the parse tree
	 */
	void exitDataList(@NotNull FortranParser.DataListContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#subscriptRanges}.
	 * @param ctx the parse tree
	 */
	void enterSubscriptRanges(@NotNull FortranParser.SubscriptRangesContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#subscriptRanges}.
	 * @param ctx the parse tree
	 */
	void exitSubscriptRanges(@NotNull FortranParser.SubscriptRangesContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#extendsAttribute}.
	 * @param ctx the parse tree
	 */
	void enterExtendsAttribute(@NotNull FortranParser.ExtendsAttributeContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#extendsAttribute}.
	 * @param ctx the parse tree
	 */
	void exitExtendsAttribute(@NotNull FortranParser.ExtendsAttributeContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#typeAttribute}.
	 * @param ctx the parse tree
	 */
	void enterTypeAttribute(@NotNull FortranParser.TypeAttributeContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#typeAttribute}.
	 * @param ctx the parse tree
	 */
	void exitTypeAttribute(@NotNull FortranParser.TypeAttributeContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#declarationStatements}.
	 * @param ctx the parse tree
	 */
	void enterDeclarationStatements(@NotNull FortranParser.DeclarationStatementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#declarationStatements}.
	 * @param ctx the parse tree
	 */
	void exitDeclarationStatements(@NotNull FortranParser.DeclarationStatementsContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#executableStatement}.
	 * @param ctx the parse tree
	 */
	void enterExecutableStatement(@NotNull FortranParser.ExecutableStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#executableStatement}.
	 * @param ctx the parse tree
	 */
	void exitExecutableStatement(@NotNull FortranParser.ExecutableStatementContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#keywordStatementParameters}.
	 * @param ctx the parse tree
	 */
	void enterKeywordStatementParameters(@NotNull FortranParser.KeywordStatementParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#keywordStatementParameters}.
	 * @param ctx the parse tree
	 */
	void exitKeywordStatementParameters(@NotNull FortranParser.KeywordStatementParametersContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#derivedType}.
	 * @param ctx the parse tree
	 */
	void enterDerivedType(@NotNull FortranParser.DerivedTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#derivedType}.
	 * @param ctx the parse tree
	 */
	void exitDerivedType(@NotNull FortranParser.DerivedTypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#elseWhereStatement}.
	 * @param ctx the parse tree
	 */
	void enterElseWhereStatement(@NotNull FortranParser.ElseWhereStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#elseWhereStatement}.
	 * @param ctx the parse tree
	 */
	void exitElseWhereStatement(@NotNull FortranParser.ElseWhereStatementContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#literalArray}.
	 * @param ctx the parse tree
	 */
	void enterLiteralArray(@NotNull FortranParser.LiteralArrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#literalArray}.
	 * @param ctx the parse tree
	 */
	void exitLiteralArray(@NotNull FortranParser.LiteralArrayContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#accessibilityStatements}.
	 * @param ctx the parse tree
	 */
	void enterAccessibilityStatements(@NotNull FortranParser.AccessibilityStatementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#accessibilityStatements}.
	 * @param ctx the parse tree
	 */
	void exitAccessibilityStatements(@NotNull FortranParser.AccessibilityStatementsContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#doWhileStatement}.
	 * @param ctx the parse tree
	 */
	void enterDoWhileStatement(@NotNull FortranParser.DoWhileStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#doWhileStatement}.
	 * @param ctx the parse tree
	 */
	void exitDoWhileStatement(@NotNull FortranParser.DoWhileStatementContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#namelistParameters}.
	 * @param ctx the parse tree
	 */
	void enterNamelistParameters(@NotNull FortranParser.NamelistParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#namelistParameters}.
	 * @param ctx the parse tree
	 */
	void exitNamelistParameters(@NotNull FortranParser.NamelistParametersContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#data}.
	 * @param ctx the parse tree
	 */
	void enterData(@NotNull FortranParser.DataContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#data}.
	 * @param ctx the parse tree
	 */
	void exitData(@NotNull FortranParser.DataContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#subOperator}.
	 * @param ctx the parse tree
	 */
	void enterSubOperator(@NotNull FortranParser.SubOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#subOperator}.
	 * @param ctx the parse tree
	 */
	void exitSubOperator(@NotNull FortranParser.SubOperatorContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#intrinsicType}.
	 * @param ctx the parse tree
	 */
	void enterIntrinsicType(@NotNull FortranParser.IntrinsicTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#intrinsicType}.
	 * @param ctx the parse tree
	 */
	void exitIntrinsicType(@NotNull FortranParser.IntrinsicTypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#elseStatement}.
	 * @param ctx the parse tree
	 */
	void enterElseStatement(@NotNull FortranParser.ElseStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#elseStatement}.
	 * @param ctx the parse tree
	 */
	void exitElseStatement(@NotNull FortranParser.ElseStatementContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#ifDirective}.
	 * @param ctx the parse tree
	 */
	void enterIfDirective(@NotNull FortranParser.IfDirectiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#ifDirective}.
	 * @param ctx the parse tree
	 */
	void exitIfDirective(@NotNull FortranParser.IfDirectiveContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#implicitNoneStatement}.
	 * @param ctx the parse tree
	 */
	void enterImplicitNoneStatement(@NotNull FortranParser.ImplicitNoneStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#implicitNoneStatement}.
	 * @param ctx the parse tree
	 */
	void exitImplicitNoneStatement(@NotNull FortranParser.ImplicitNoneStatementContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#accessibilityStatement}.
	 * @param ctx the parse tree
	 */
	void enterAccessibilityStatement(@NotNull FortranParser.AccessibilityStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#accessibilityStatement}.
	 * @param ctx the parse tree
	 */
	void exitAccessibilityStatement(@NotNull FortranParser.AccessibilityStatementContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#declarationStatement}.
	 * @param ctx the parse tree
	 */
	void enterDeclarationStatement(@NotNull FortranParser.DeclarationStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#declarationStatement}.
	 * @param ctx the parse tree
	 */
	void exitDeclarationStatement(@NotNull FortranParser.DeclarationStatementContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#definedCondition}.
	 * @param ctx the parse tree
	 */
	void enterDefinedCondition(@NotNull FortranParser.DefinedConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#definedCondition}.
	 * @param ctx the parse tree
	 */
	void exitDefinedCondition(@NotNull FortranParser.DefinedConditionContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#typeDeclarationStatement}.
	 * @param ctx the parse tree
	 */
	void enterTypeDeclarationStatement(@NotNull FortranParser.TypeDeclarationStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#typeDeclarationStatement}.
	 * @param ctx the parse tree
	 */
	void exitTypeDeclarationStatement(@NotNull FortranParser.TypeDeclarationStatementContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#procedure}.
	 * @param ctx the parse tree
	 */
	void enterProcedure(@NotNull FortranParser.ProcedureContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#procedure}.
	 * @param ctx the parse tree
	 */
	void exitProcedure(@NotNull FortranParser.ProcedureContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#member}.
	 * @param ctx the parse tree
	 */
	void enterMember(@NotNull FortranParser.MemberContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#member}.
	 * @param ctx the parse tree
	 */
	void exitMember(@NotNull FortranParser.MemberContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#keywordStatement1}.
	 * @param ctx the parse tree
	 */
	void enterKeywordStatement1(@NotNull FortranParser.KeywordStatement1Context ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#keywordStatement1}.
	 * @param ctx the parse tree
	 */
	void exitKeywordStatement1(@NotNull FortranParser.KeywordStatement1Context ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#templateInstance}.
	 * @param ctx the parse tree
	 */
	void enterTemplateInstance(@NotNull FortranParser.TemplateInstanceContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#templateInstance}.
	 * @param ctx the parse tree
	 */
	void exitTemplateInstance(@NotNull FortranParser.TemplateInstanceContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#mulOperator}.
	 * @param ctx the parse tree
	 */
	void enterMulOperator(@NotNull FortranParser.MulOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#mulOperator}.
	 * @param ctx the parse tree
	 */
	void exitMulOperator(@NotNull FortranParser.MulOperatorContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#caseStatement}.
	 * @param ctx the parse tree
	 */
	void enterCaseStatement(@NotNull FortranParser.CaseStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#caseStatement}.
	 * @param ctx the parse tree
	 */
	void exitCaseStatement(@NotNull FortranParser.CaseStatementContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#derivedTypeName}.
	 * @param ctx the parse tree
	 */
	void enterDerivedTypeName(@NotNull FortranParser.DerivedTypeNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#derivedTypeName}.
	 * @param ctx the parse tree
	 */
	void exitDerivedTypeName(@NotNull FortranParser.DerivedTypeNameContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#keywordStatement2}.
	 * @param ctx the parse tree
	 */
	void enterKeywordStatement2(@NotNull FortranParser.KeywordStatement2Context ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#keywordStatement2}.
	 * @param ctx the parse tree
	 */
	void exitKeywordStatement2(@NotNull FortranParser.KeywordStatement2Context ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#keywordStatement3}.
	 * @param ctx the parse tree
	 */
	void enterKeywordStatement3(@NotNull FortranParser.KeywordStatement3Context ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#keywordStatement3}.
	 * @param ctx the parse tree
	 */
	void exitKeywordStatement3(@NotNull FortranParser.KeywordStatement3Context ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#moduleProcedure}.
	 * @param ctx the parse tree
	 */
	void enterModuleProcedure(@NotNull FortranParser.ModuleProcedureContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#moduleProcedure}.
	 * @param ctx the parse tree
	 */
	void exitModuleProcedure(@NotNull FortranParser.ModuleProcedureContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#dataAttributes}.
	 * @param ctx the parse tree
	 */
	void enterDataAttributes(@NotNull FortranParser.DataAttributesContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#dataAttributes}.
	 * @param ctx the parse tree
	 */
	void exitDataAttributes(@NotNull FortranParser.DataAttributesContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#selectStatement}.
	 * @param ctx the parse tree
	 */
	void enterSelectStatement(@NotNull FortranParser.SelectStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#selectStatement}.
	 * @param ctx the parse tree
	 */
	void exitSelectStatement(@NotNull FortranParser.SelectStatementContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#elifDirective}.
	 * @param ctx the parse tree
	 */
	void enterElifDirective(@NotNull FortranParser.ElifDirectiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#elifDirective}.
	 * @param ctx the parse tree
	 */
	void exitElifDirective(@NotNull FortranParser.ElifDirectiveContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#containedTypeBoundProcedures}.
	 * @param ctx the parse tree
	 */
	void enterContainedTypeBoundProcedures(@NotNull FortranParser.ContainedTypeBoundProceduresContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#containedTypeBoundProcedures}.
	 * @param ctx the parse tree
	 */
	void exitContainedTypeBoundProcedures(@NotNull FortranParser.ContainedTypeBoundProceduresContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#templateBlock}.
	 * @param ctx the parse tree
	 */
	void enterTemplateBlock(@NotNull FortranParser.TemplateBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#templateBlock}.
	 * @param ctx the parse tree
	 */
	void exitTemplateBlock(@NotNull FortranParser.TemplateBlockContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#ifSingleStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfSingleStatement(@NotNull FortranParser.IfSingleStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#ifSingleStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfSingleStatement(@NotNull FortranParser.IfSingleStatementContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(@NotNull FortranParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(@NotNull FortranParser.ExpressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#externalFile}.
	 * @param ctx the parse tree
	 */
	void enterExternalFile(@NotNull FortranParser.ExternalFileContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#externalFile}.
	 * @param ctx the parse tree
	 */
	void exitExternalFile(@NotNull FortranParser.ExternalFileContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#elseDirective}.
	 * @param ctx the parse tree
	 */
	void enterElseDirective(@NotNull FortranParser.ElseDirectiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#elseDirective}.
	 * @param ctx the parse tree
	 */
	void exitElseDirective(@NotNull FortranParser.ElseDirectiveContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#ltOperator}.
	 * @param ctx the parse tree
	 */
	void enterLtOperator(@NotNull FortranParser.LtOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#ltOperator}.
	 * @param ctx the parse tree
	 */
	void exitLtOperator(@NotNull FortranParser.LtOperatorContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#endifDirective}.
	 * @param ctx the parse tree
	 */
	void enterEndifDirective(@NotNull FortranParser.EndifDirectiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#endifDirective}.
	 * @param ctx the parse tree
	 */
	void exitEndifDirective(@NotNull FortranParser.EndifDirectiveContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#derivedDataMember}.
	 * @param ctx the parse tree
	 */
	void enterDerivedDataMember(@NotNull FortranParser.DerivedDataMemberContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#derivedDataMember}.
	 * @param ctx the parse tree
	 */
	void exitDerivedDataMember(@NotNull FortranParser.DerivedDataMemberContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#containedProcedures}.
	 * @param ctx the parse tree
	 */
	void enterContainedProcedures(@NotNull FortranParser.ContainedProceduresContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#containedProcedures}.
	 * @param ctx the parse tree
	 */
	void exitContainedProcedures(@NotNull FortranParser.ContainedProceduresContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#typeBoundProcedureStatement}.
	 * @param ctx the parse tree
	 */
	void enterTypeBoundProcedureStatement(@NotNull FortranParser.TypeBoundProcedureStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#typeBoundProcedureStatement}.
	 * @param ctx the parse tree
	 */
	void exitTypeBoundProcedureStatement(@NotNull FortranParser.TypeBoundProcedureStatementContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#ifndefDirective}.
	 * @param ctx the parse tree
	 */
	void enterIfndefDirective(@NotNull FortranParser.IfndefDirectiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#ifndefDirective}.
	 * @param ctx the parse tree
	 */
	void exitIfndefDirective(@NotNull FortranParser.IfndefDirectiveContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#dummyArguments}.
	 * @param ctx the parse tree
	 */
	void enterDummyArguments(@NotNull FortranParser.DummyArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#dummyArguments}.
	 * @param ctx the parse tree
	 */
	void exitDummyArguments(@NotNull FortranParser.DummyArgumentsContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#dataAttribute}.
	 * @param ctx the parse tree
	 */
	void enterDataAttribute(@NotNull FortranParser.DataAttributeContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#dataAttribute}.
	 * @param ctx the parse tree
	 */
	void exitDataAttribute(@NotNull FortranParser.DataAttributeContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#arithmeticOperator2}.
	 * @param ctx the parse tree
	 */
	void enterArithmeticOperator2(@NotNull FortranParser.ArithmeticOperator2Context ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#arithmeticOperator2}.
	 * @param ctx the parse tree
	 */
	void exitArithmeticOperator2(@NotNull FortranParser.ArithmeticOperator2Context ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#notOperator}.
	 * @param ctx the parse tree
	 */
	void enterNotOperator(@NotNull FortranParser.NotOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#notOperator}.
	 * @param ctx the parse tree
	 */
	void exitNotOperator(@NotNull FortranParser.NotOperatorContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#arithmeticOperator1}.
	 * @param ctx the parse tree
	 */
	void enterArithmeticOperator1(@NotNull FortranParser.ArithmeticOperator1Context ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#arithmeticOperator1}.
	 * @param ctx the parse tree
	 */
	void exitArithmeticOperator1(@NotNull FortranParser.ArithmeticOperator1Context ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#expOperator}.
	 * @param ctx the parse tree
	 */
	void enterExpOperator(@NotNull FortranParser.ExpOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#expOperator}.
	 * @param ctx the parse tree
	 */
	void exitExpOperator(@NotNull FortranParser.ExpOperatorContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#dataDeclarationStatement}.
	 * @param ctx the parse tree
	 */
	void enterDataDeclarationStatement(@NotNull FortranParser.DataDeclarationStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#dataDeclarationStatement}.
	 * @param ctx the parse tree
	 */
	void exitDataDeclarationStatement(@NotNull FortranParser.DataDeclarationStatementContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#orOperator}.
	 * @param ctx the parse tree
	 */
	void enterOrOperator(@NotNull FortranParser.OrOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#orOperator}.
	 * @param ctx the parse tree
	 */
	void exitOrOperator(@NotNull FortranParser.OrOperatorContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#idWithArgs}.
	 * @param ctx the parse tree
	 */
	void enterIdWithArgs(@NotNull FortranParser.IdWithArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#idWithArgs}.
	 * @param ctx the parse tree
	 */
	void exitIdWithArgs(@NotNull FortranParser.IdWithArgsContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#geOperator}.
	 * @param ctx the parse tree
	 */
	void enterGeOperator(@NotNull FortranParser.GeOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#geOperator}.
	 * @param ctx the parse tree
	 */
	void exitGeOperator(@NotNull FortranParser.GeOperatorContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#expression_}.
	 * @param ctx the parse tree
	 */
	void enterExpression_(@NotNull FortranParser.Expression_Context ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#expression_}.
	 * @param ctx the parse tree
	 */
	void exitExpression_(@NotNull FortranParser.Expression_Context ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#leOperator}.
	 * @param ctx the parse tree
	 */
	void enterLeOperator(@NotNull FortranParser.LeOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#leOperator}.
	 * @param ctx the parse tree
	 */
	void exitLeOperator(@NotNull FortranParser.LeOperatorContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#dataDeclarationStatements}.
	 * @param ctx the parse tree
	 */
	void enterDataDeclarationStatements(@NotNull FortranParser.DataDeclarationStatementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#dataDeclarationStatements}.
	 * @param ctx the parse tree
	 */
	void exitDataDeclarationStatements(@NotNull FortranParser.DataDeclarationStatementsContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#conditionDirective_}.
	 * @param ctx the parse tree
	 */
	void enterConditionDirective_(@NotNull FortranParser.ConditionDirective_Context ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#conditionDirective_}.
	 * @param ctx the parse tree
	 */
	void exitConditionDirective_(@NotNull FortranParser.ConditionDirective_Context ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#whereSingleStatement}.
	 * @param ctx the parse tree
	 */
	void enterWhereSingleStatement(@NotNull FortranParser.WhereSingleStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#whereSingleStatement}.
	 * @param ctx the parse tree
	 */
	void exitWhereSingleStatement(@NotNull FortranParser.WhereSingleStatementContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#divOperator}.
	 * @param ctx the parse tree
	 */
	void enterDivOperator(@NotNull FortranParser.DivOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#divOperator}.
	 * @param ctx the parse tree
	 */
	void exitDivOperator(@NotNull FortranParser.DivOperatorContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#doAnonyStatement}.
	 * @param ctx the parse tree
	 */
	void enterDoAnonyStatement(@NotNull FortranParser.DoAnonyStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#doAnonyStatement}.
	 * @param ctx the parse tree
	 */
	void exitDoAnonyStatement(@NotNull FortranParser.DoAnonyStatementContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#keywordStatement}.
	 * @param ctx the parse tree
	 */
	void enterKeywordStatement(@NotNull FortranParser.KeywordStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#keywordStatement}.
	 * @param ctx the parse tree
	 */
	void exitKeywordStatement(@NotNull FortranParser.KeywordStatementContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#caseLabels}.
	 * @param ctx the parse tree
	 */
	void enterCaseLabels(@NotNull FortranParser.CaseLabelsContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#caseLabels}.
	 * @param ctx the parse tree
	 */
	void exitCaseLabels(@NotNull FortranParser.CaseLabelsContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#useStatements}.
	 * @param ctx the parse tree
	 */
	void enterUseStatements(@NotNull FortranParser.UseStatementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#useStatements}.
	 * @param ctx the parse tree
	 */
	void exitUseStatements(@NotNull FortranParser.UseStatementsContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#ifdefDirective}.
	 * @param ctx the parse tree
	 */
	void enterIfdefDirective(@NotNull FortranParser.IfdefDirectiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#ifdefDirective}.
	 * @param ctx the parse tree
	 */
	void exitIfdefDirective(@NotNull FortranParser.IfdefDirectiveContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#elseIfStatement}.
	 * @param ctx the parse tree
	 */
	void enterElseIfStatement(@NotNull FortranParser.ElseIfStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#elseIfStatement}.
	 * @param ctx the parse tree
	 */
	void exitElseIfStatement(@NotNull FortranParser.ElseIfStatementContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#andOperator}.
	 * @param ctx the parse tree
	 */
	void enterAndOperator(@NotNull FortranParser.AndOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#andOperator}.
	 * @param ctx the parse tree
	 */
	void exitAndOperator(@NotNull FortranParser.AndOperatorContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#typeAttributes}.
	 * @param ctx the parse tree
	 */
	void enterTypeAttributes(@NotNull FortranParser.TypeAttributesContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#typeAttributes}.
	 * @param ctx the parse tree
	 */
	void exitTypeAttributes(@NotNull FortranParser.TypeAttributesContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#bindingAttributes}.
	 * @param ctx the parse tree
	 */
	void enterBindingAttributes(@NotNull FortranParser.BindingAttributesContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#bindingAttributes}.
	 * @param ctx the parse tree
	 */
	void exitBindingAttributes(@NotNull FortranParser.BindingAttributesContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#intrinsicTypeParameter}.
	 * @param ctx the parse tree
	 */
	void enterIntrinsicTypeParameter(@NotNull FortranParser.IntrinsicTypeParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#intrinsicTypeParameter}.
	 * @param ctx the parse tree
	 */
	void exitIntrinsicTypeParameter(@NotNull FortranParser.IntrinsicTypeParameterContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#id}.
	 * @param ctx the parse tree
	 */
	void enterId(@NotNull FortranParser.IdContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#id}.
	 * @param ctx the parse tree
	 */
	void exitId(@NotNull FortranParser.IdContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#executableStatements}.
	 * @param ctx the parse tree
	 */
	void enterExecutableStatements(@NotNull FortranParser.ExecutableStatementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#executableStatements}.
	 * @param ctx the parse tree
	 */
	void exitExecutableStatements(@NotNull FortranParser.ExecutableStatementsContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#usedItem}.
	 * @param ctx the parse tree
	 */
	void enterUsedItem(@NotNull FortranParser.UsedItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#usedItem}.
	 * @param ctx the parse tree
	 */
	void exitUsedItem(@NotNull FortranParser.UsedItemContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#subscriptRange}.
	 * @param ctx the parse tree
	 */
	void enterSubscriptRange(@NotNull FortranParser.SubscriptRangeContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#subscriptRange}.
	 * @param ctx the parse tree
	 */
	void exitSubscriptRange(@NotNull FortranParser.SubscriptRangeContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#addOperator}.
	 * @param ctx the parse tree
	 */
	void enterAddOperator(@NotNull FortranParser.AddOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#addOperator}.
	 * @param ctx the parse tree
	 */
	void exitAddOperator(@NotNull FortranParser.AddOperatorContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#typeBoundProcedureStatements}.
	 * @param ctx the parse tree
	 */
	void enterTypeBoundProcedureStatements(@NotNull FortranParser.TypeBoundProcedureStatementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#typeBoundProcedureStatements}.
	 * @param ctx the parse tree
	 */
	void exitTypeBoundProcedureStatements(@NotNull FortranParser.TypeBoundProcedureStatementsContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#ifMultipleStatements}.
	 * @param ctx the parse tree
	 */
	void enterIfMultipleStatements(@NotNull FortranParser.IfMultipleStatementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#ifMultipleStatements}.
	 * @param ctx the parse tree
	 */
	void exitIfMultipleStatements(@NotNull FortranParser.IfMultipleStatementsContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#eqOperator}.
	 * @param ctx the parse tree
	 */
	void enterEqOperator(@NotNull FortranParser.EqOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#eqOperator}.
	 * @param ctx the parse tree
	 */
	void exitEqOperator(@NotNull FortranParser.EqOperatorContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#namelistStatement}.
	 * @param ctx the parse tree
	 */
	void enterNamelistStatement(@NotNull FortranParser.NamelistStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#namelistStatement}.
	 * @param ctx the parse tree
	 */
	void exitNamelistStatement(@NotNull FortranParser.NamelistStatementContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#usedItemList}.
	 * @param ctx the parse tree
	 */
	void enterUsedItemList(@NotNull FortranParser.UsedItemListContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#usedItemList}.
	 * @param ctx the parse tree
	 */
	void exitUsedItemList(@NotNull FortranParser.UsedItemListContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#rangeTail}.
	 * @param ctx the parse tree
	 */
	void enterRangeTail(@NotNull FortranParser.RangeTailContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#rangeTail}.
	 * @param ctx the parse tree
	 */
	void exitRangeTail(@NotNull FortranParser.RangeTailContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#actualArgument}.
	 * @param ctx the parse tree
	 */
	void enterActualArgument(@NotNull FortranParser.ActualArgumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#actualArgument}.
	 * @param ctx the parse tree
	 */
	void exitActualArgument(@NotNull FortranParser.ActualArgumentContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#cppDirective}.
	 * @param ctx the parse tree
	 */
	void enterCppDirective(@NotNull FortranParser.CppDirectiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#cppDirective}.
	 * @param ctx the parse tree
	 */
	void exitCppDirective(@NotNull FortranParser.CppDirectiveContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#assignmentStatement}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentStatement(@NotNull FortranParser.AssignmentStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#assignmentStatement}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentStatement(@NotNull FortranParser.AssignmentStatementContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#templateArguments}.
	 * @param ctx the parse tree
	 */
	void enterTemplateArguments(@NotNull FortranParser.TemplateArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#templateArguments}.
	 * @param ctx the parse tree
	 */
	void exitTemplateArguments(@NotNull FortranParser.TemplateArgumentsContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#interfaceStatement}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceStatement(@NotNull FortranParser.InterfaceStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#interfaceStatement}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceStatement(@NotNull FortranParser.InterfaceStatementContext ctx);

	/**
	 * Enter a parse tree produced by {@link FortranParser#useStatement}.
	 * @param ctx the parse tree
	 */
	void enterUseStatement(@NotNull FortranParser.UseStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link FortranParser#useStatement}.
	 * @param ctx the parse tree
	 */
	void exitUseStatement(@NotNull FortranParser.UseStatementContext ctx);
}