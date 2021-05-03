package com.shreyas.util;

import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Util {

    public static ResponseEntity<String> createResponseEntity(String message, HttpStatus statusCode) {
        return new ResponseEntity<>(message, statusCode);
    }

    public static <T> T findMovieByRanking(CrudRepository<T, Long> repo, Long movieRanking) {
        return repo.findOne(movieRanking);
    }

    public static <T> T save(CrudRepository<T, Long> repo, T entity) {
        return repo.save(entity);
    }
}
