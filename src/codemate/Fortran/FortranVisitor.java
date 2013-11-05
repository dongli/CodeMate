// Generated from Fortran.g4 by ANTLR 4.1

    package codemate.Fortran;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link FortranParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface FortranVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link FortranParser#undefDirective}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUndefDirective(@NotNull FortranParser.UndefDirectiveContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#whereMultipleStatements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhereMultipleStatements(@NotNull FortranParser.WhereMultipleStatementsContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#defineDirective}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefineDirective(@NotNull FortranParser.DefineDirectiveContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#includeDirective}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIncludeDirective(@NotNull FortranParser.IncludeDirectiveContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#internalFile}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInternalFile(@NotNull FortranParser.InternalFileContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#doRangeStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoRangeStatement(@NotNull FortranParser.DoRangeStatementContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#gtOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGtOperator(@NotNull FortranParser.GtOperatorContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#bindingAttribute}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBindingAttribute(@NotNull FortranParser.BindingAttributeContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#conditionDirective}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionDirective(@NotNull FortranParser.ConditionDirectiveContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#file}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFile(@NotNull FortranParser.FileContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#templateArgument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTemplateArgument(@NotNull FortranParser.TemplateArgumentContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#compareOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompareOperator(@NotNull FortranParser.CompareOperatorContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#actualArguments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitActualArguments(@NotNull FortranParser.ActualArgumentsContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#caseDefaultStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseDefaultStatement(@NotNull FortranParser.CaseDefaultStatementContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#neOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNeOperator(@NotNull FortranParser.NeOperatorContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#andOrOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndOrOperator(@NotNull FortranParser.AndOrOperatorContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#numerics}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumerics(@NotNull FortranParser.NumericsContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#catOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCatOperator(@NotNull FortranParser.CatOperatorContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#dataList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDataList(@NotNull FortranParser.DataListContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#subscriptRanges}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubscriptRanges(@NotNull FortranParser.SubscriptRangesContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#typeAttribute}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeAttribute(@NotNull FortranParser.TypeAttributeContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#extendsAttribute}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExtendsAttribute(@NotNull FortranParser.ExtendsAttributeContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#declarationStatements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarationStatements(@NotNull FortranParser.DeclarationStatementsContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#executableStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExecutableStatement(@NotNull FortranParser.ExecutableStatementContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#keywordStatementParameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeywordStatementParameters(@NotNull FortranParser.KeywordStatementParametersContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#derivedType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDerivedType(@NotNull FortranParser.DerivedTypeContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#procedurePointerTypeParameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcedurePointerTypeParameter(@NotNull FortranParser.ProcedurePointerTypeParameterContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#elseWhereStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElseWhereStatement(@NotNull FortranParser.ElseWhereStatementContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#literalArray}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteralArray(@NotNull FortranParser.LiteralArrayContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#accessibilityStatements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAccessibilityStatements(@NotNull FortranParser.AccessibilityStatementsContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#doWhileStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoWhileStatement(@NotNull FortranParser.DoWhileStatementContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#namelistParameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNamelistParameters(@NotNull FortranParser.NamelistParametersContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#data}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitData(@NotNull FortranParser.DataContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#subOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubOperator(@NotNull FortranParser.SubOperatorContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#intrinsicType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntrinsicType(@NotNull FortranParser.IntrinsicTypeContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#elseStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElseStatement(@NotNull FortranParser.ElseStatementContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#ifDirective}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfDirective(@NotNull FortranParser.IfDirectiveContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#implicitNoneStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImplicitNoneStatement(@NotNull FortranParser.ImplicitNoneStatementContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#accessibilityStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAccessibilityStatement(@NotNull FortranParser.AccessibilityStatementContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#declarationStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarationStatement(@NotNull FortranParser.DeclarationStatementContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#definedCondition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefinedCondition(@NotNull FortranParser.DefinedConditionContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#typeDeclarationStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeDeclarationStatement(@NotNull FortranParser.TypeDeclarationStatementContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#procedure}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcedure(@NotNull FortranParser.ProcedureContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#member}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMember(@NotNull FortranParser.MemberContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#keywordStatement1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeywordStatement1(@NotNull FortranParser.KeywordStatement1Context ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#templateInstance}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTemplateInstance(@NotNull FortranParser.TemplateInstanceContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#mulOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulOperator(@NotNull FortranParser.MulOperatorContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#caseStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseStatement(@NotNull FortranParser.CaseStatementContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#derivedTypeName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDerivedTypeName(@NotNull FortranParser.DerivedTypeNameContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#keywordStatement2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeywordStatement2(@NotNull FortranParser.KeywordStatement2Context ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#keywordStatement3}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeywordStatement3(@NotNull FortranParser.KeywordStatement3Context ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#moduleProcedure}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModuleProcedure(@NotNull FortranParser.ModuleProcedureContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#dataAttributes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDataAttributes(@NotNull FortranParser.DataAttributesContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#selectStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectStatement(@NotNull FortranParser.SelectStatementContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#elifDirective}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElifDirective(@NotNull FortranParser.ElifDirectiveContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#containedTypeBoundProcedures}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContainedTypeBoundProcedures(@NotNull FortranParser.ContainedTypeBoundProceduresContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#templateBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTemplateBlock(@NotNull FortranParser.TemplateBlockContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#ifSingleStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfSingleStatement(@NotNull FortranParser.IfSingleStatementContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(@NotNull FortranParser.ExpressionContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#externalFile}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExternalFile(@NotNull FortranParser.ExternalFileContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#elseDirective}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElseDirective(@NotNull FortranParser.ElseDirectiveContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#ltOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLtOperator(@NotNull FortranParser.LtOperatorContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#endifDirective}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEndifDirective(@NotNull FortranParser.EndifDirectiveContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#derivedDataMember}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDerivedDataMember(@NotNull FortranParser.DerivedDataMemberContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#containedProcedures}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContainedProcedures(@NotNull FortranParser.ContainedProceduresContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#typeBoundProcedureStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeBoundProcedureStatement(@NotNull FortranParser.TypeBoundProcedureStatementContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#ifndefDirective}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfndefDirective(@NotNull FortranParser.IfndefDirectiveContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#dummyArguments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDummyArguments(@NotNull FortranParser.DummyArgumentsContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#dataAttribute}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDataAttribute(@NotNull FortranParser.DataAttributeContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#arithmeticOperator2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithmeticOperator2(@NotNull FortranParser.ArithmeticOperator2Context ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#notOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotOperator(@NotNull FortranParser.NotOperatorContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#arithmeticOperator1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithmeticOperator1(@NotNull FortranParser.ArithmeticOperator1Context ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#expOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpOperator(@NotNull FortranParser.ExpOperatorContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#dataDeclarationStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDataDeclarationStatement(@NotNull FortranParser.DataDeclarationStatementContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#orOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrOperator(@NotNull FortranParser.OrOperatorContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#idWithArgs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdWithArgs(@NotNull FortranParser.IdWithArgsContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#geOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGeOperator(@NotNull FortranParser.GeOperatorContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#expression_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression_(@NotNull FortranParser.Expression_Context ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#leOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLeOperator(@NotNull FortranParser.LeOperatorContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#dataDeclarationStatements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDataDeclarationStatements(@NotNull FortranParser.DataDeclarationStatementsContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#conditionDirective_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionDirective_(@NotNull FortranParser.ConditionDirective_Context ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#whereSingleStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhereSingleStatement(@NotNull FortranParser.WhereSingleStatementContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#divOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDivOperator(@NotNull FortranParser.DivOperatorContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#doAnonyStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoAnonyStatement(@NotNull FortranParser.DoAnonyStatementContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#keywordStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeywordStatement(@NotNull FortranParser.KeywordStatementContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#procedurePointerType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcedurePointerType(@NotNull FortranParser.ProcedurePointerTypeContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#caseLabels}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseLabels(@NotNull FortranParser.CaseLabelsContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#useStatements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUseStatements(@NotNull FortranParser.UseStatementsContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#ifdefDirective}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfdefDirective(@NotNull FortranParser.IfdefDirectiveContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#elseIfStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElseIfStatement(@NotNull FortranParser.ElseIfStatementContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#andOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndOperator(@NotNull FortranParser.AndOperatorContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#typeAttributes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeAttributes(@NotNull FortranParser.TypeAttributesContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#bindingAttributes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBindingAttributes(@NotNull FortranParser.BindingAttributesContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#intrinsicTypeParameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntrinsicTypeParameter(@NotNull FortranParser.IntrinsicTypeParameterContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#id}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId(@NotNull FortranParser.IdContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#executableStatements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExecutableStatements(@NotNull FortranParser.ExecutableStatementsContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#procedureInterface}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcedureInterface(@NotNull FortranParser.ProcedureInterfaceContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#usedItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUsedItem(@NotNull FortranParser.UsedItemContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#subscriptRange}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubscriptRange(@NotNull FortranParser.SubscriptRangeContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#addOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddOperator(@NotNull FortranParser.AddOperatorContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#typeBoundProcedureStatements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeBoundProcedureStatements(@NotNull FortranParser.TypeBoundProcedureStatementsContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#ifMultipleStatements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfMultipleStatements(@NotNull FortranParser.IfMultipleStatementsContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#eqOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqOperator(@NotNull FortranParser.EqOperatorContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#namelistStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNamelistStatement(@NotNull FortranParser.NamelistStatementContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#usedItemList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUsedItemList(@NotNull FortranParser.UsedItemListContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#rangeTail}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRangeTail(@NotNull FortranParser.RangeTailContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#cppDirective}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCppDirective(@NotNull FortranParser.CppDirectiveContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#actualArgument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitActualArgument(@NotNull FortranParser.ActualArgumentContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#assignmentStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentStatement(@NotNull FortranParser.AssignmentStatementContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#templateArguments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTemplateArguments(@NotNull FortranParser.TemplateArgumentsContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#interfaceEntry}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterfaceEntry(@NotNull FortranParser.InterfaceEntryContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#interfaceStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterfaceStatement(@NotNull FortranParser.InterfaceStatementContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#doRange}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoRange(@NotNull FortranParser.DoRangeContext ctx);

	/**
	 * Visit a parse tree produced by {@link FortranParser#useStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUseStatement(@NotNull FortranParser.UseStatementContext ctx);
}