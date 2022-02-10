package com.cinema.booking.sacurity.repository;

import com.cinema.booking.sacurity.securityEntites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {


//    @Query(
//            "SELECT u,r,p FROM User u JOIN u.roles r  JOIN r.permissions p WHERE u.username=:username"
//    )
//    User findEmailtsw (String username);

    //TODO to będzie wykorzystane
    @Query(
            "SELECT u,r,p FROM User u JOIN u.roles r  JOIN r.permissions p WHERE u.email=:email"
    )
    User findRealEmail (String email);




























    //without param
    @Query(
            value = "select * from user_application U " +
                    "full outer join role R on U.user_id =R.role_id " +
                    "full outer join permission P on R.role_id=P.permission_id " +
                    "WHERE U.username='najman'",
            nativeQuery = true
    )
    User findEmailNativeQuery();


//    User findByUsername(String username);

}
