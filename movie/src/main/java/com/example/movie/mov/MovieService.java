//package com.example.movie.mov;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.Optional;
//
//@Component
//public class MovieService {
//    private final MovieRepository movieRepository;
//
//    @Autowired
//    public MovieService(MovieRepository movieRepository) {
//        this.movieRepository = movieRepository;
//    }
//
//    public List<Movie> displayMovieDetails() {
//        return movieRepository.findAll();
//    }
//
//    public List<Movie> searchByName(String movieName) {
//        List<Movie> movies = movieRepository.findByMovieName(movieName);
//        if (movies.isEmpty()) {
//            throw new IllegalStateException("Movie name not found.");
//        }
//        return movies;
//    }
//
//    public void registerNewMovie(Movie movie) {
//        Optional<Movie> existingMovie = movieRepository.findByNameAndTimeSlot(movie.getMovieName(), movie.getShowTiming());
//        if (existingMovie.isPresent()) {
//            throw new IllegalStateException("Movie already exists with the given name and time slot");
//        }
//        movieRepository.save(movie);
//    }
//
//    public boolean bookSeats(Long movieId, List<String> seatNumbers) {
//        Movie movie = movieRepository.findById(movieId)
//                .orElseThrow(() -> new IllegalArgumentException("Movie with ID " + movieId + " not found"));
//
//        boolean isBooked = movie.addBookedSeats(seatNumbers);
//        if (isBooked) {
//            movieRepository.save(movie);
//        }
//        return isBooked;
//    }
//}
package com.example.movie.mov;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class MovieService {
    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> displayMovieDetails() {
        return movieRepository.findAll();
    }

    public List<Movie> searchByName(String movieName) {
        List<Movie> movies = movieRepository.findByMovieName(movieName);
        if (movies.isEmpty()) {
            throw new IllegalStateException("Movie name not found.");
        }
        return movies;
    }

    public void registerNewMovie(Movie movie) {
        Optional<Movie> existingMovie = movieRepository.findByNameAndTimeSlot(movie.getMovieName(), movie.getShowTiming());
        if (existingMovie.isPresent()) {
            throw new IllegalStateException("Movie already exists with the given name and time slot");
        }
        movieRepository.save(movie);
    }

    public boolean bookSeats(Long movieId, Map<String, String> seatNumbers) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new IllegalArgumentException("Movie with ID " + movieId + " not found"));

        boolean isBooked = movie.addBookedSeats(seatNumbers);
        if (isBooked) {
            movieRepository.save(movie);
        }
        return isBooked;
    }
}
