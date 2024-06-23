//package com.example.movie.mov;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import java.time.LocalTime;
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public interface MovieRepository extends JpaRepository<Movie, Long>{
//    @Query("SELECT m FROM Movie m WHERE m.movieName LIKE %:movieName%")
//    List<Movie> findByMovieName(@Param("movieName") String movieName);
//
//    @Query("SELECT m FROM Movie m WHERE m.movieName = :movieName AND m.showTiming = :showTiming")
//    Optional<Movie> findByNameAndTimeSlot(@Param("movieName") String movieName, @Param("showTiming") LocalTime showTiming);
//}
//
//
package com.example.movie.mov;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query("SELECT m FROM Movie m WHERE m.movieName LIKE %:movieName%")
    List<Movie> findByMovieName(@Param("movieName") String movieName);

    @Query("SELECT m FROM Movie m WHERE m.movieName = :movieName AND m.showTiming = :showTiming")
    Optional<Movie> findByNameAndTimeSlot(@Param("movieName") String movieName, @Param("showTiming") LocalTime showTiming);
}
