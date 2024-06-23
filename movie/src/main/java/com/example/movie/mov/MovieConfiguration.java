//package com.example.movie.mov;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import java.time.LocalTime;
//import java.util.List;
//@Configuration
//public class MovieConfiguration {
//    @Bean
//    CommandLineRunner commandLineRunner(MovieRepository movieRepository){
//        return args->{
//            Movie mov1=new Movie(
//                    1L,
//                    "The God father",
//                    "Mafia,Action",
//                    LocalTime.of(22,00)
//            );
//            Movie mov2=new Movie(
//                    2L,
//                    "Titanic",
//                    "Romance",
//                    LocalTime.of(10,00)
//            );
//            Movie mov3=new Movie(
//                    3L,
//                    "The God Father",
//                    "Mafia ,Action",
//                    LocalTime.of(10,00)
//            );
//            Movie mov4=new Movie(
//                    4L,
//                    "Titanic",
//                    "Romance",
//                    LocalTime.of(15,00)
//            );
//            movieRepository.saveAll(List.of(mov1,mov2,mov3,mov4));
//        };
//    }
//
//}



