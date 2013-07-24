#define A

subroutine foo(u, v)

#ifdef A
    use netcdf
#ifndef B
    use hdf5
#else
    use mpi
#endif
#endif

#ifdef DEBUG
    private
#else
    public
    #endif

    
#ifdef DEBUG
    integer debug
#endif

    type(var_t), intent(inout), pointer :: u
    type(var_t), intent(inout), pointer :: v

    integer i, j, k

#ifdef DEBUG
    print *, "check"
#endif

    subdom_loop<u, v> {
        do k = 1, num_lev
        do j = idx_start_lat, idx_end_lat
        do i = idx_start_lon, idx_end_lon
            u(i,j,k) = 0.0d0
            v(i,j,k) = 0.0d0
        end do
        end do
        end do
    }

contains

#ifdef DEBUG
    subroutine dd
    end subroutine dd
#endif

    subroutine cc
    end subroutine cc

end subroutine foo
