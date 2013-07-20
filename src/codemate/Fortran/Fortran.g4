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
    : ( procedure | NEW_LINES )*
    ;

// *****************************************************************************
//                              procedure rule
procedure
    : ( intrinsicType | derivedType )?
      PROCEDURE_TYPE id ( LEFT_PAREN dummyArguments? RIGHT_PAREN )?
      ( RESULT_KEYWORD LEFT_PAREN id RIGHT_PAREN )?
      useStatements
      implicitNoneStatement?
      accessibilityStatements
      declarationStatements
      executableStatements
      CONTAINS_KEYWORD? containedProcedures
      END_KEYWORD PROCEDURE_TYPE id?
    ;

// *****************************************************************************
//                          use statement rules
useStatements: useStatement*;

useStatement: USE_KEYWORD id ( COMMA ( ONLY_KEYWORD ':' )? usedItemList )?;

usedItemList: usedItem ( COMMA usedItem )*;

usedItem: id ( POINT id )?;

// *****************************************************************************
//                            implicit none rule
implicitNoneStatement: IMPLICIT_KEYWORD NONE_KEYWORD;

// *****************************************************************************
//                       accessibility statement rules
accessibilityStatements: accessibilityStatement*;

accessibilityStatement: (PUBLIC_KEYWORD | PRIVATE_KEYWORD) (id (COMMA id)*)?;

// *****************************************************************************
//                      declaration statement rules
declarationStatements: declarationStatement*;

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
      id ( POINT id )?
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

// TODO: Replace 'PROCEDURE_TYPE' to 'module' without problems.
moduleProcedure: PROCEDURE_TYPE 'procedure' id;

namelistParameters: id ( COMMA id )*;

namelistStatement
    : NAMELIST_KEYWORD SLASH id SLASH namelistParameters
    ;

// *****************************************************************************
//                         executable statement rules
executableStatements: executableStatement*;

executableStatement
    : ( assignmentStatement
      | doStatement
      | ifStatement
      | selectStatement
      | keywordStatement
      | templateInstance ) SEMICOMMA?
    ;

assignmentStatement
    : ( idWithArgs | templateInstance | derivedDataMember | id )
      ( POINT | EQUAL ) expression
    ;

doStatement
    : DO_KEYWORD id EQUAL expression COMMA expression ( COMMA expression )?
        executableStatements END_KEYWORD DO_KEYWORD     # doRangeStatement
    | DO_KEYWORD WHILE_KEYWORD expression
        executableStatements END_KEYWORD DO_KEYWORD     # doWhileStatement
    | DO_KEYWORD
        executableStatements END_KEYWORD DO_KEYWORD     # doAnonyStatement
    ;

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
containedProcedures: procedure*;

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

// -----------------------------------------------------------------------------
//                                  tokens
// Note: The order of tokens matters! The first one will be matched first!

// *****************************************************************************
//                              keyword tokens
PROCEDURE_TYPE: 'program' | 'module' | 'subroutine' | 'function';
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
//                        comment and string rules
COMMENT: '!' .*? NEW_LINE+ -> skip;
STRING
    : '"' ('""'|~'"')* '"'
    | '\'' ('\'\''|~'\'')* '\''
    ;

// *****************************************************************************
//                            character tokens
fragment NEW_LINE: '\r'? '\n';
NEW_LINES: NEW_LINE+ -> skip;
BREAK_LINE: '&' WS? ( NEW_LINES | COMMENT ) -> skip;
COMMA: ',';
SEMICOMMA: ';';
COLON: ':';
DOUBLE_COLONS: '::';
WS: [ \t]+ -> skip;
ID: [a-zA-Z_] [a-zA-Z_0-9]*;
NUMERICS: FLOAT | INT;
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
