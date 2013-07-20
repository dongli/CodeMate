subroutine foo(u, v)

    type(var_t), intent(inout), pointer :: u
    type(var_t), intent(inout), pointer :: v

    integer i, j, k

    print *, "check"

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

end subroutine foo
