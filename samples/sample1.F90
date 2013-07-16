program main

    use foo_mod, only: foo_init, a => b

    implicit none

 public foo, bar
 
  integer, allocatable :: foo(:,:)
   real(kind= 8) foo

 if (a == 1) then
    ! this is comment line
   a = 2
  else if (b==1) then
      b = 3
  else if(c==1) then
      c = 4
    else
    d = 4
 end if

 do i = 1, nx-1, 2
     a(i,1) = i
  end do

  do while(.true.)
    do

    end do
  end do


  select case (foo%n)
  case(1)
    hello<>
  case(2,3)
   case default
  end select


  open(10, file="abc", form="formatted", status="old")
  write(10, "(A)") a
  read(10, "(I10, F10.5)") b, c
  rewind(10)
   close(10)
        
     if (a == 1) print *, 'a'

end program main
