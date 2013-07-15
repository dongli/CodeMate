<#assign typeName = args[0]>
function ${typeName}_get_elem_id(this) result(res)

    class(${typeName}), intent(in) :: this
    integer res

    res = this%elem_id

end function ${typeName}_get_elem_id

subroutine ${typeName}_get_prev(this, res)

    class(${typeName}), intent(in) :: this
    type(${typeName}), intent(out), pointer :: res

    res => this%prev

end subroutine ${typeName}_get_prev

subroutine ${typeName}_get_next(this, res)

    class(${typeName}), intent(in) :: this
    type(${typeName}), intent(out), pointer :: res

    res => this%next

end subroutine ${typeName}_get_next