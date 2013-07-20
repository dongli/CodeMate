program main

    type, extends(list_elem_t<foo_t>) :: foo_t
        integer data
    end type foo_t

    type, extends(list_elem_t<bar_t>) :: bar_t
        type(list_t<foo_t>) foos
    end type bar_t
    

end program main
