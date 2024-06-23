//package com.example.movie.mov;
//
//import jakarta.persistence.*;
//import java.time.LocalTime;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@Table(name = "movies")
//public class Movie {
//    @Id
//    @SequenceGenerator(
//            name = "movieSequence",
//            sequenceName = "movieSequence",
//            allocationSize = 1
//    )
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "movieSequence"
//    )
//    @Column(name = "movie_id")
//    private Long movieId;
//
//    @Column(name = "movie_name")
//    private String movieName;
//
//    @Column(name = "movie_genre")
//    private String movieGenre;
//
//    @Column(name = "show_timing")
//    private LocalTime showTiming;
//
//    @ElementCollection
//    @CollectionTable(name = "movie_seats", joinColumns = @JoinColumn(name = "movie_id"))
//    @Column(name = "seat_number")
//    private List<String> bookedSeats = new ArrayList<>();
//
//    public Movie() {
//    }
//
//    public Movie(Long movieId, String movieName, String movieGenre, LocalTime showTiming) {
//        this.movieId = movieId;
//        this.movieName = movieName;
//        this.movieGenre = movieGenre;
//        this.showTiming = showTiming;
//    }
//
//    public Long getMovieId() {
//        return movieId;
//    }
//
//    public void setMovieId(Long movieId) {
//        this.movieId = movieId;
//    }
//
//    public String getMovieName() {
//        return movieName;
//    }
//
//    public void setMovieName(String movieName) {
//        this.movieName = movieName;
//    }
//
//    public String getMovieGenre() {
//        return movieGenre;
//    }
//
//    public void setMovieGenre(String movieGenre) {
//        this.movieGenre = movieGenre;
//    }
//
//    public LocalTime getShowTiming() {
//        return showTiming;
//    }
//
//    public void setShowTiming(LocalTime showTiming) {
//        this.showTiming = showTiming;
//    }
//
//    public List<String> getBookedSeats() {
//        return bookedSeats;
//    }
//
//    public void setBookedSeats(List<String> bookedSeats) {
//        this.bookedSeats = bookedSeats;
//    }
//
//    @Override
//    public String toString() {
//        return "Movie{" +
//                "movieId=" + movieId +
//                ", movieName='" + movieName + '\'' +
//                ", movieGenre='" + movieGenre + '\'' +
//                ", showTiming=" + showTiming +
//                ", bookedSeats=" + bookedSeats +
//                '}';
//    }
//
//    public boolean addBookedSeat(String seatNumber) {
//        if (bookedSeats.contains(seatNumber)) {
//            return false;
//        }
//        bookedSeats.add(seatNumber);
//        return true;
//    }
//
//    public boolean addBookedSeats(List<String> seatNumbers) {
//        for (String seatNumber : seatNumbers) {
//            if (bookedSeats.contains(seatNumber)) {
//                return false;
//            }
//        }
//        bookedSeats.addAll(seatNumbers);
//        return true;
//    }
//}
package com.example.movie.mov;

import jakarta.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @SequenceGenerator(
            name = "movieSequence",
            sequenceName = "movieSequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "movieSequence"
    )
    @Column(name = "movie_id")
    private Long movieId;

    @Column(name = "movie_name")
    private String movieName;

    @Column(name = "movie_genre")
    private String movieGenre;

    @Column(name = "show_timing")
    private LocalTime showTiming;

    @ElementCollection
    @CollectionTable(name = "movie_seats", joinColumns = @JoinColumn(name = "movie_id"))
    @MapKeyColumn(name = "seat_number")
    @Column(name = "seat_type")
    private Map<String, String> bookedSeats = new HashMap<>();

    public Movie() {
    }

    public Movie(Long movieId, String movieName, String movieGenre, LocalTime showTiming) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.movieGenre = movieGenre;
        this.showTiming = showTiming;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieGenre() {
        return movieGenre;
    }

    public void setMovieGenre(String movieGenre) {
        this.movieGenre = movieGenre;
    }

    public LocalTime getShowTiming() {
        return showTiming;
    }

    public void setShowTiming(LocalTime showTiming) {
        this.showTiming = showTiming;
    }

    public Map<String, String> getBookedSeats() {
        return bookedSeats;
    }

    public void setBookedSeats(Map<String, String> bookedSeats) {
        this.bookedSeats = bookedSeats;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", movieName='" + movieName + '\'' +
                ", movieGenre='" + movieGenre + '\'' +
                ", showTiming=" + showTiming +
                ", bookedSeats=" + bookedSeats +
                '}';
    }

    public boolean addBookedSeat(String seatNumber, String seatType) {
        if (bookedSeats.containsKey(seatNumber)) {
            return false;
        }
        bookedSeats.put(seatNumber, seatType);
        return true;
    }

    public boolean addBookedSeats(Map<String, String> seats) {
        for (String seatNumber : seats.keySet()) {
            if (bookedSeats.containsKey(seatNumber)) {
                return false;
            }
        }
        bookedSeats.putAll(seats);
        return true;
    }
}
