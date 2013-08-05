/*
 * Description:
 *
 *   This is a Fortran grammar written in ANTLR v4.
 *
 * Author:
 *
 *   Li Dong <dongli@lasg.iap.ac.cn>
 */

grammar Fortran;

@header {
    package codemate.Fortran;
}

// -----------------------------------------------------------------------------
//                                  rules

// *****************************************************************************
//                                file rule
file
    : ( procedure | cppDirective )*
    ;

// *****************************************************************************
//                              procedure rule
procedure
    : ( intrinsicType | derivedType )?
      PROCEDURE_TYPE_KEYWORD id ( LEFT_PAREN dummyArguments? RIGHT_PAREN )?
      ( RESULT_KEYWORD LEFT_PAREN id RIGHT_PAREN )?
      useStatements
      implicitNoneStatement?
      accessibilityStatements
      declarationStatements
      executableStatements
      CONTAINS_KEYWORD? containedProcedures
      END_KEYWORD PROCEDURE_TYPE_KEYWORD id?
    ;

// *****************************************************************************
//                          use statement rules
useStatements: ( useStatement | cppDirective )*;

useStatement : USE_KEYWORD id ( COMMA ( ONLY_KEYWORD ':' )? usedItemList )?;

usedItemList: usedItem ( COMMA usedItem )*;

usedItem: id ( POINT id )?;

// *****************************************************************************
//                            implicit none rule
implicitNoneStatement: IMPLICIT_KEYWORD NONE_KEYWORD;

// *****************************************************************************
//                       accessibility statement rules
accessibilityStatements: ( accessibilityStatement | cppDirective )*;

accessibilityStatement: (PUBLIC_KEYWORD | PRIVATE_KEYWORD) (id (COMMA id)*)?;

// *****************************************************************************
//                      declaration statement rules
declarationStatements: ( declarationStatement | cppDirective )*;

declarationStatement
    : dataDeclarationStatement
    | typeDeclarationStatement
    | interfaceStatement
    | namelistStatement
    ;

dataDeclarationStatements: dataDeclarationStatement*;

dataDeclarationStatement
    : ( intrinsicType | derivedType ) dataAttributes? dataList
    ;

intrinsicTypeParameter
    : LEFT_PAREN
      ( id EQUAL )?
      ( id | numerics | STAR )
      RIGHT_PAREN
    ;
intrinsicType: INTRINSIC_TYPE_KEYWORD intrinsicTypeParameter?;

derivedTypeName: id | templateInstance;
derivedType
    : ( TYPE_KEYWORD | CLASS_KEYWORD )
      LEFT_PAREN derivedTypeName RIGHT_PAREN
    ;

dataAttributes: ( COMMA dataAttribute )* DOUBLE_COLONS;

dataAttribute
    : ALLOCATABLE_KEYWORD
    | PARAMETER_KEYWORD
    | INTENT_KEYWORD LEFT_PAREN ( INOUT_KEYWORD | IN_KEYWORD | OUT_KEYWORD ) RIGHT_PAREN
    | POINTER_KEYWORD
    | TARGET_KEYWORD
    | OPTIONAL_KEYWORD
    | PUBLIC_KEYWORD
    | PRIVATE_KEYWORD
    | SAVE_KEYWORD
    ;

dataList: data ( COMMA data )*;

data: assignmentStatement | idWithArgs | id;

typeDeclarationStatement
    : TYPE_KEYWORD typeAttributes? id
      dataDeclarationStatements
      containedTypeBoundProcedures?
      END_KEYWORD TYPE_KEYWORD id?
    ;

typeAttributes: ( COMMA typeAttribute )+ DOUBLE_COLONS;

extendsAttribute
    : EXTENDS_KEYWORD LEFT_PAREN ( templateInstance | id ) RIGHT_PAREN
    ;

typeAttribute
    : extendsAttribute
    | PUBLIC_KEYWORD
    | PRIVATE_KEYWORD
    | ABSTRACT_KEYWORD
    ;

containedTypeBoundProcedures: CONTAINS_KEYWORD typeBoundProcedureStatements;

typeBoundProcedureStatements: typeBoundProcedureStatement*;

typeBoundProcedureStatement
    : PROCEDURE_KEYWORD
      bindingAttributes
      id ( POINT id ( COMMA id )* )?
    ;

bindingAttributes: ( COMMA bindingAttribute )* DOUBLE_COLONS;

bindingAttribute
    : PASS_KEYWORD
    | NOPASS_KEYWORD
    | 'non_overridable'
    | DEFFERED_KEYWORD
    | PUBLIC_KEYWORD
    | PRIVATE_KEYWORD
    ;

interfaceStatement
    : INTERFACE_KEYWORD id?
        moduleProcedure*
      END_KEYWORD INTERFACE_KEYWORD id?
    ;

// TODO: Replace 'PROCEDURE_TYPE_KEYWORD' to 'module' without problems.
moduleProcedure: PROCEDURE_TYPE_KEYWORD 'procedure' id;

namelistParameters: id ( COMMA id )*;

namelistStatement
    : NAMELIST_KEYWORD SLASH id SLASH namelistParameters
    ;

// *****************************************************************************
//                         executable statement rules
executableStatements: ( executableStatement | cppDirective )*;

executableStatement
    : ( assignmentStatement
      | doStatement
      | ifStatement
      | selectStatement
      | whereStatement
      | keywordStatement
      | templateInstance ) SEMICOMMA?
    ;

assignmentStatement
    : ( idWithArgs | templateInstance | derivedDataMember | id )
      ( POINT | EQUAL ) expression
    ;

// =============================================================================
//                                do statement
doRange: id EQUAL expression COMMA expression ( COMMA expression )?;
doStatement
    : (id COLON )? DO_KEYWORD doRange
        executableStatements END_KEYWORD DO_KEYWORD id?    # doRangeStatement
    | (id COLON )? DO_KEYWORD WHILE_KEYWORD expression
        executableStatements END_KEYWORD DO_KEYWORD id?    # doWhileStatement
    | (id COLON )? DO_KEYWORD
        executableStatements END_KEYWORD DO_KEYWORD id?    # doAnonyStatement
    ;

// =============================================================================
//                              select statement
caseLabels
    :  CASE_KEYWORD LEFT_PAREN
       expression ( COMMA expression )*
       RIGHT_PAREN
    ;
caseStatement: caseLabels executableStatements;
caseDefaultStatement: CASE_KEYWORD DEFAULT_KEYWORD executableStatements;
selectStatement
    : SELECT_KEYWORD CASE_KEYWORD
      LEFT_PAREN ( expression ) RIGHT_PAREN
      caseStatement*
      caseDefaultStatement?
      END_KEYWORD SELECT_KEYWORD
    ;

// =============================================================================
//                              if statement
elseIfStatement
    : ELSE_KEYWORD IF_KEYWORD expression THEN_KEYWORD executableStatements
    ;

elseStatement
    : ELSE_KEYWORD executableStatements
    ;

ifStatement
    : IF_KEYWORD expression THEN_KEYWORD
        executableStatements
        elseIfStatement*
        elseStatement?
      END_KEYWORD IF_KEYWORD                    # ifMultipleStatements
    | IF_KEYWORD expression executableStatement # ifSingleStatement
    ;

// =============================================================================
//                             where statement
elseWhereStatement
    : ELSE_KEYWORD WHERE_KEYWORD expression id?
    ;

whereStatement
    : ( id COLON )?
      WHERE_KEYWORD expression
        executableStatements
        elseWhereStatement*
      END_KEYWORD WHERE_KEYWORD id?                 # whereMultipleStatements
    | WHERE_KEYWORD expression executableStatement  # whereSingleStatement
    ;

// =============================================================================
//                          keyword statements
// Note: There are three types of keyword statements.
keywordStatementParameters: LEFT_PAREN actualArguments RIGHT_PAREN;
keywordStatement1
    : EXECUTABLE_KEYWORD_1
      keywordStatementParameters
    ;
keywordStatement2
    : EXECUTABLE_KEYWORD_2
      keywordStatementParameters?
      actualArguments?
    ;
keywordStatement3
    : EXECUTABLE_KEYWORD_3
      ( id | numerics )?
    ;
keywordStatement
    : keywordStatement1
    | keywordStatement2
    | keywordStatement3
    ;

// *****************************************************************************
//                      contained procedure rules
containedProcedures: ( procedure | cppDirective )*;

// *****************************************************************************
//                              template rules
templateInstance
    : id LEFT_ANGLE templateArguments? RIGHT_ANGLE
      ( LEFT_BRACE templateBlock RIGHT_BRACE )?;

templateArgument: expression | subscriptRange;
templateArguments: templateArgument ( COMMA templateArgument )*;

templateBlock
    : executableStatements
    ;

// *****************************************************************************
//                                basic rules
id
    : ID | INTRINSIC_TYPE_KEYWORD | RESULT_KEYWORD | TYPE_KEYWORD
    | CASE_KEYWORD | DEFAULT_KEYWORD | PASS_KEYWORD | NOPASS_KEYWORD
    | DO_KEYWORD | ONLY_KEYWORD | END_KEYWORD | TARGET_KEYWORD
    | IN_KEYWORD | OUT_KEYWORD
    ;

subscriptRange: ( expression rangeTail? | rangeTail ) | STAR;
rangeTail
    : COLON expression? ( COLON expression )?
    | DOUBLE_COLONS expression?
    ;
subscriptRanges: subscriptRange ( COMMA subscriptRange )*;

// operators
expOperator: STAR STAR <assoc=right>;
catOperator: SLASH SLASH;
mulOperator: STAR;
divOperator: SLASH;
addOperator: PLUS;
subOperator: MINUS;
eqOperator: EQUAL EQUAL | '.eq.';
neOperator: SLASH EQUAL | '.ne.';
ltOperator: LEFT_ANGLE | '.lt.';
leOperator: LEFT_ANGLE EQUAL | '.le.';
gtOperator: RIGHT_ANGLE | '.gt.';
geOperator: RIGHT_ANGLE EQUAL | '.ge.';
andOperator: '.and.';
orOperator: '.or.';
notOperator: '.not.';

arithmeticOperator1: mulOperator | divOperator;
arithmeticOperator2: addOperator | subOperator;
compareOperator
    : eqOperator | neOperator | ltOperator |
      leOperator | gtOperator | geOperator
    ;
andOrOperator: andOperator | orOperator;

expression
    : expression expOperator expression
    | expression catOperator expression
    | expression arithmeticOperator1 expression
    | expression arithmeticOperator2 expression
    | MINUS<assoc=right> expression
    | expression compareOperator expression
    | expression andOrOperator expression
    | numerics
    | idWithArgs
    | derivedDataMember
    | id
    | templateInstance
    | STRING
    | LOGICAL_STATUS
    | literalArray
    | LEFT_PAREN expression RIGHT_PAREN
    | notOperator expression
    ;

expression_: expression;

idWithArgs: id LEFT_PAREN ( actualArguments | subscriptRanges )? RIGHT_PAREN;

numerics: NUMERICS;

member: id | idWithArgs;
derivedDataMember: member ( PERCENT member )+;

dummyArguments: id ( COMMA id )*;

literalArray: ARRAY_START expression ( COMMA expression )* ARRAY_END;

actualArgument: expression | id EQUAL actualArgument | STAR;

actualArguments: actualArgument ( COMMA actualArgument)*;

// *****************************************************************************
//                           C preprocessor rules
internalFile: STRING;

externalFile: LEFT_ANGLE ~RIGHT_ANGLE* RIGHT_ANGLE;

cppDirective
    : includeDirective
    | defineDirective
    | undefDirective
    | ifdefDirective
    | ifndefDirective
    | ifDirective
    | elseDirective
    | elifDirective
    | endifDirective
    ;

includeDirective
    : DIRECTIVE_START INCLUDE_KEYWORD
      ( internalFile | externalFile )
    ;

defineDirective: DIRECTIVE_START DEFINE_KEYWORD id expression?;

undefDirective: DIRECTIVE_START UNDEF_KEYWORD id;

ifdefDirective: DIRECTIVE_START IFDEF_KEYWORD id;

ifndefDirective: DIRECTIVE_START IFNDEF_KEYWORD id;

ifDirective: DIRECTIVE_START IF_KEYWORD conditionDirective;

elseDirective: DIRECTIVE_START ELSE_KEYWORD;

elifDirective: DIRECTIVE_START ELIF_KEYWORD conditionDirective;

endifDirective: DIRECTIVE_START ENDIF_KEYWORD;

conditionDirective
    : conditionDirective ( CPP_AND | CPP_OR ) conditionDirective
    | definedCondition
    | LEFT_PAREN conditionDirective RIGHT_PAREN
    | EXCAL conditionDirective
    ;

conditionDirective_: conditionDirective;

definedCondition: DEFINED_KEYWORD id;

// -----------------------------------------------------------------------------
//                                  tokens
// Note: The order of tokens matters! The first one will be matched first!

// *****************************************************************************
//                              keyword tokens
PROCEDURE_TYPE_KEYWORD: 'program' | 'module' | 'subroutine' | 'function';
INTRINSIC_TYPE_KEYWORD
    : 'integer' | 'real' | 'character' | 'logical' | 'complex'
    ;
EXECUTABLE_KEYWORD_1
    : 'allocate' | 'deallocate' | 'nullify'
    | 'open' | 'close' | 'inquire' | 'rewind'
    ;
EXECUTABLE_KEYWORD_2
    : 'write' | 'read' | 'print' | 'call'
    ;
EXECUTABLE_KEYWORD_3
    : 'return' | 'cycle' | 'exit' | 'stop'
    ;
CONTAINS_KEYWORD: 'contains';
RESULT_KEYWORD: 'result';
TYPE_KEYWORD: 'type';
CLASS_KEYWORD: 'class';
IF_KEYWORD: 'if';
ELSE_KEYWORD: 'else';
THEN_KEYWORD: 'then';
DO_KEYWORD: 'do';
SELECT_KEYWORD: 'select';
CASE_KEYWORD: 'case';
DEFAULT_KEYWORD: 'default';
END_KEYWORD: 'end';
INTERFACE_KEYWORD: 'interface';
NAMELIST_KEYWORD: 'namelist';
PROCEDURE_KEYWORD: 'procedure';
WHILE_KEYWORD: 'while';
WHERE_KEYWORD: 'where';
CALL_KEYWORD: 'call';
USE_KEYWORD: 'use';
ONLY_KEYWORD: 'only';
IMPLICIT_KEYWORD: 'implicit';
NONE_KEYWORD: 'none';
PUBLIC_KEYWORD: 'public';
PRIVATE_KEYWORD: 'private';
EXTENDS_KEYWORD: 'extends';
ABSTRACT_KEYWORD: 'abstract';
PASS_KEYWORD: 'pass';
NOPASS_KEYWORD: 'nopass';
DEFFERED_KEYWORD: 'deffered';
POINTER_KEYWORD: 'pointer';
TARGET_KEYWORD: 'target';
SAVE_KEYWORD: 'save';
OPTIONAL_KEYWORD: 'optional';
ALLOCATABLE_KEYWORD: 'allocatable';
PARAMETER_KEYWORD: 'parameter';
INTENT_KEYWORD: 'intent';
IN_KEYWORD: 'in';
OUT_KEYWORD: 'out';
INOUT_KEYWORD: 'inout';

// *****************************************************************************
//                          C preprocessor tokens
DIRECTIVE_START: '#';
INCLUDE_KEYWORD: 'include';
DEFINE_KEYWORD: 'define';
UNDEF_KEYWORD: 'undef';
IFDEF_KEYWORD: 'ifdef';
IFNDEF_KEYWORD: 'ifndef';
ELIF_KEYWORD: 'elif';
ENDIF_KEYWORD: 'endif';
DEFINED_KEYWORD: 'defined';
CPP_AND: '&&';
CPP_OR: '||';

// *****************************************************************************
//                        comment and string tokens
COMMENT: EXCAL .*? NEW_LINE+ -> skip;
STRING
    : '"' ('""'|~'"')* '"'
    | '\'' ('\'\''|~'\'')* '\''
    ;

// *****************************************************************************
//                            character tokens
fragment NEW_LINE: '\r'? '\n';
NEW_LINES: NEW_LINE+ -> skip;
BREAK_LINE: '&' WS? ( NEW_LINES | COMMENT ) -> skip;
EXCAL: '!';
COMMA: ',';
SEMICOMMA: ';';
COLON: ':';
DOUBLE_COLONS: '::';
WS: [ \t]+ -> skip;
ID: [a-zA-Z_] [a-zA-Z_0-9]*;
NUMERICS: INT | FLOAT;
fragment INT: [0-9]+;
fragment FLOAT: [0-9]* '.' [0-9]* ([EeDd] [+-]? [0-9]+)?;
LOGICAL_STATUS: '.true.' | '.false.';
STAR: '*';
SLASH: '/';
EQUAL: '=';
PLUS: '+';
MINUS: '-';
LEFT_ANGLE: '<';
RIGHT_ANGLE: '>';
LEFT_PAREN: '(';
RIGHT_PAREN: ')';
LEFT_BRACE: '{';
RIGHT_BRACE: '}';
PERCENT: '%';
ARRAY_START: '[' | '(/';
ARRAY_END: ']' | '/)';
POINT: '=>';
