package com.example.movie.mov;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.username = :username AND u.password = :password")
    boolean existsByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    @Query("SELECT m FROM User m WHERE m.username = :username ")
    Optional<User> existsByUsername(@Param("username") String username);
}
