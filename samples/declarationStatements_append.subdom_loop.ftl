<#list args as arg>
type(var_subdom_t), pointer :: __${arg}
</#list>
type(subdom_t), pointer :: __subdom
integer __i