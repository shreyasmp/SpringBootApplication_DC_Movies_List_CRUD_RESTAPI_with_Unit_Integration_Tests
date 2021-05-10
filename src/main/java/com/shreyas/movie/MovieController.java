package com.shreyas.movie;

import com.google.common.collect.Lists;
import com.shreyas.repository.MovieRepository;
import com.shreyas.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Movie> getAllMovies() {
        return Lists.newArrayList(movieRepository.findAll());
    }

    @RequestMapping(method = RequestMethod.GET, value = "movieName/{movieName}")
    public Collection<Movie> getMovieByMovieName(@PathVariable String movieName) {
        return movieRepository.findMovieByMovieName(movieName);
    }

    @RequestMapping(method = RequestMethod.GET, value = "movieRanking/{movieRanking}")
    public Movie getMovieByRank(@PathVariable Long movieRanking) {
        return Util.findMovieByRanking(movieRepository, movieRanking);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addMovie(@RequestBody Movie movie) {
        if (Util.save(movieRepository, new Movie(movie.getMovieName(), movie.getMovieRanking())) != null) {
            return Util.createResponseEntity("Successful creation of resource", HttpStatus.CREATED);
        }
        return Util.createResponseEntity("Error creating resource", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "movieRanking/{movieRanking}")
    public ResponseEntity<?> updateMovie(@RequestBody Movie movie, @PathVariable long movieRanking) {
        Movie newMovie = Util.findMovieByRanking(movieRepository, movieRanking);
        if (newMovie != null) {
            newMovie.setMovieName(movie.getMovieName());
            newMovie.setMovieRanking(movie.getMovieRanking());
            if (Util.save(movieRepository, newMovie).getId().equals(movieRanking)) {
                return Util.createResponseEntity("Data updated successfully", HttpStatus.OK);
            }
        }
        return Util.createResponseEntity("Error updating data", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "movieRanking/{movieRanking}")
    public ResponseEntity<?> deleteMovie(@PathVariable long movieRanking) {
        try {
            movieRepository.delete(movieRanking);
            return Util.createResponseEntity("Data deleted successfully", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return Util.createResponseEntity("Resource not found", HttpStatus.NOT_FOUND);
        }
    }
}
