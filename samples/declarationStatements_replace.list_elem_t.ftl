<#assign typeName = args[0]>
<#assign extendedDeclarationStatements = args[1]>
<#assign extendedTypeBoundProcedures = args[2]>
type ${typeName}
${extendedDeclarationStatements}
    integer, private :: elem_id
    type(${typeName}), private, pointer :: prev, next
contains
${extendedTypeBoundProcedures}
    procedure :: get_elem_id => ${typeName}_get_elem_id
    procedure :: get_prev => ${typeName}_get_prev
    procedure :: get_next => ${typeName}_get_next
end type ${typeName}
