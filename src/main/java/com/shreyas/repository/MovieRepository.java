package com.shreyas.repository;

import com.shreyas.movie.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Long> {

    Collection<Movie> findMovieByMovieName(String movieName);
}
