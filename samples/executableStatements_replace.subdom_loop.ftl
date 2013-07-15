<#-- Note: We replace foo<...> to __foo%array(...). -->
<#list args as arg>
    <#assign res = block?matches("\\b${arg}\\b *<([^>]+)>")>
    <#list res as m>
        <#assign block = block?replace("${m}", "__${arg}%array(${m?groups[1]})")>
    </#list>
</#list>
<#assign block = block?replace("idx_start_lon", "__subdom%idx_start_lon")>
<#assign block = block?replace("idx_end_lon", "__subdom%idx_end_lon")>
<#assign block = block?replace("idx_start_lat", "__subdom%idx_start_lat")>
<#assign block = block?replace("idx_end_lat", "__subdom%idx_end_lat")>
<#assign block = block?replace("num_lev", "__subdom%num_lev")>
<#list args as arg>
call ${arg}%get_start_elem(__${arg})
</#list>
do __i = 1, ${args[0]}%get_num_elem()
${block}
<#list args as arg>
    call __${arg}%get_next(__${arg})
</#list>
end do