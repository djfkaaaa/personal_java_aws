package com.james.api.user.repository;
import com.james.api.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Long>{

    Optional<User> findUserByUsername(String username);

    @Modifying
    @Query("update users set token=:token where id = :id")
    public void modifyTokenById(@Param("id") Long id, @Param("token") String deleteToken);

//    @Query("select u from users u where u.username = :username")
//    Optional<UserDto> findByUsername(@Param("username") String username);


    Boolean existsByUsername(String username);
}