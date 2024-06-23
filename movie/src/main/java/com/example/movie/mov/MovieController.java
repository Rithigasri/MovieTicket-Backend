////package com.example.movie.mov;
////
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.http.HttpStatus;
////import org.springframework.http.ResponseEntity;
////import org.springframework.web.bind.annotation.*;
////
////import java.util.List;
////import java.util.regex.Pattern;
////import java.util.stream.Collectors;
////
////@RestController
////@RequestMapping(path="/api/v1/mov")
////public class MovieController {
////    private final MovieService movieService;
////
////    @Autowired
////    public MovieController(MovieService movieService) {
////        this.movieService = movieService;
////    }
////
////    @GetMapping("/all")
////    public List<Movie> getMovieDetails() {
////        return movieService.displayMovieDetails();
////    }
////
////    @GetMapping("/search")
////    public ResponseEntity<List<Movie>> getMovieBySearch(@RequestParam("movieName") String movieName) {
////        List<Movie> movies = movieService.searchByName(movieName);
////        return new ResponseEntity<>(movies, HttpStatus.OK);
////    }
////
////    @PostMapping(value="/add")
////    public void addMovie(@RequestBody Movie mov) {
////        movieService.registerNewMovie(mov);
////    }
////
////    @PostMapping(value="/book")
////    public ResponseEntity<String> bookSeats(@RequestParam("movieId") Long movieId, @RequestParam("seatNumbers") List<String> seatNumbers) {
////        if (seatNumbers == null || seatNumbers.isEmpty()) {
////            return ResponseEntity.badRequest().body("Seat numbers cannot be empty.");
////        }
////        Pattern pattern = Pattern.compile("^[A-D][1-9]$|^[A-D]10$");
////        List<String> invalidSeats = seatNumbers.stream()
////                .filter(seatNumber -> !pattern.matcher(seatNumber).matches())
////                .toList();
////
////        if (!invalidSeats.isEmpty()) {
////            return ResponseEntity.badRequest().body("Invalid seat numbers: " + invalidSeats + ". Seat numbers must be in the range A1 to D10.");
////        }
////
////
////        boolean isBooked = movieService.bookSeats(movieId, seatNumbers);
////        if (isBooked) {
////            int pricePerSeat=180;
////            int totalPrice=seatNumbers.size()*pricePerSeat;
////            return ResponseEntity.ok("Seats " + seatNumbers + " booked successfully for Movie ID " + movieId+ ". Total price: " + totalPrice);
////        } else {
////            return ResponseEntity.badRequest().body("One or more seats are already booked or invalid.");
////        }
////    }
////}
package com.example.movie.mov;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/api/v1/mov")
public class MovieController {
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/all")
    public List<Movie> getMovieDetails() {
        return movieService.displayMovieDetails();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Movie>> getMovieBySearch(@RequestParam("movieName") String movieName) {
        List<Movie> movies = movieService.searchByName(movieName);
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @PostMapping(value="/add")
    public void addMovie(@RequestBody Movie mov) {
        movieService.registerNewMovie(mov);
    }

    @PostMapping(value="/book")
    public ResponseEntity<String> bookSeats(@RequestBody Map<String, Object> payload) {
        Long movieId = ((Number) payload.get("movieId")).longValue();
        Map<String, String> seatNumbers = (Map<String, String>) payload.get("seatNumbers");

        if (seatNumbers == null || seatNumbers.isEmpty()) {
            return ResponseEntity.badRequest().body("Seat numbers cannot be empty.");
        }

        // Validate seat numbers and types
        Pattern elitePattern = Pattern.compile("^[A-B][1-9]$|^[A-B]10$");
        Pattern premiumPattern = Pattern.compile("^[C-G][1-9]$|^[C-G]10$");
        Pattern executivePattern = Pattern.compile("^[H-I][1-9]$|^[H-I]10$");

        List<String> invalidSeats = seatNumbers.keySet().stream()
                .filter(seatNumber -> {
                    String seatType = seatNumbers.get(seatNumber);
                    switch (seatType) {
                        case "Elite":
                            return !elitePattern.matcher(seatNumber).matches();
                        case "Premium":
                            return !premiumPattern.matcher(seatNumber).matches();
                        case "Executive":
                            return !executivePattern.matcher(seatNumber).matches();
                        default:
                            return true;
                    }
                })
                .collect(Collectors.toList());

        if (!invalidSeats.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid seat numbers or types: " + invalidSeats + ". Elite: A1-B10, Premium: C1-G10, Executive: H1-I10.");
        }

        boolean isBooked = movieService.bookSeats(movieId, seatNumbers);
        if (isBooked) {
            int totalPrice = seatNumbers.values().stream()
                    .mapToInt(seatType -> {
                        switch (seatType) {
                            case "Elite":
                                return 280;
                            case "Premium":
                                return 200;
                            case "Executive":
                                return 150;
                            default:
                                return 0;
                        }
                    }).sum();
            return ResponseEntity.ok("Seats " + seatNumbers.keySet() + " booked successfully for Movie ID " + movieId + ". Total price: " + totalPrice);
        } else {
            return ResponseEntity.badRequest().body("One or more seats are already booked or invalid.");
        }
    }
}

