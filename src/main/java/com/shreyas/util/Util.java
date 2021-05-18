package com.shreyas.util;

import com.shreyas.movie.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class Util {

    private static List<Movie> movies = new ArrayList<>();

    public static ResponseEntity<String> createResponseEntity(String message, HttpStatus statusCode) {
        return new ResponseEntity<>(message, statusCode);
    }

    public static List<Movie> getAllMovies(List<Movie> inComingMoviesList) {
        movies = inComingMoviesList;
        return movies;
    }

    public static Movie findMovieByRanking(Long movieRanking) {
        for (Movie movie : movies) {
            if (movie.getMovieRanking() == movieRanking) {
                return movie;
            }
        }
        return null;
    }

    public static Iterable<Movie> createMovie(CrudRepository<Movie, Long> repo, Movie movie) {
        movies.add(movie);
        return repo.saveAll(movies);
    }

    public static Iterable<Movie> updateMovie(CrudRepository<Movie, Long> repo, Movie movie) {
        int movieRanking = movies.indexOf(movie);
        movies.set(movieRanking, movie);
        return repo.saveAll(movies);
    }
}
