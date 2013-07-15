module decomp_manager

    implicit none

    type, extends(list_elem_t<subdom_t>) :: subdom_t
        integer idx_start_lon, idx_end_lon
        integer idx_start_lat, idx_end_lat
    contains
        procedure :: init => subdom_init
    end type subdom_t

contains

    subroutine subdom_init(this)

        class(subdom_t), intent(inout) :: this
    
    end subroutine subdom_init

end module decomp_manager