package com.shreyas.movie;

import com.shreyas.core.BaseEntity;

import javax.persistence.Entity;

@Entity
public class Movie extends BaseEntity {

    private String movieName;
    private int movieRanking;

    protected Movie() {
        super();
    }

    public Movie(String movieName, int movieRanking) {
        this();
        this.movieName = movieName;
        this.movieRanking = movieRanking;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public int getMovieRanking() {
        return movieRanking;
    }

    public void setMovieRanking(int movieRanking) {
        this.movieRanking = movieRanking;
    }
}
