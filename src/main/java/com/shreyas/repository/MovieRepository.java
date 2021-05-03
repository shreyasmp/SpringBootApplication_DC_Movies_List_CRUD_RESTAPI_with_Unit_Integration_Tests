package com.shreyas.repository;

import com.shreyas.movie.Movie;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface MovieRepository extends CrudRepository<Movie, Long> {

    Collection<Movie> findMovieByMovieName(String movieName);
}
