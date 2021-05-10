package com.shreyas.database;

import com.shreyas.movie.Movie;
import com.shreyas.repository.MovieRepository;
import com.shreyas.repository.UserRepository;
import com.shreyas.user.MovieDBUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@Component
public class DatabaseLoader implements ApplicationRunner {

    private MovieRepository moviesRepository;
    private UserRepository userRepository;

    @Autowired
    public DatabaseLoader(MovieRepository moviesRepository, UserRepository userRepository) {
        this.moviesRepository = moviesRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) {

        List<MovieDBUser> users = Arrays.asList(
                new MovieDBUser("thomas", "wayne", "dadbat", "password", new String[]{"ROLE_USER"}),
                new MovieDBUser("james", "gordon", "commissioner", "password", new String[]{"ROLE_USER"}),
                new MovieDBUser("dick", "grayson", "dickg", "password", new String[]{"ROLE_USER"}),
                new MovieDBUser("celina", "kyle", "catsy", "password", new String[]{"ROLE_USER"})
        );
        userRepository.save(users);

        userRepository.save(new MovieDBUser("bruce", "wayne", "bwayne", "123456", new String[]{"ROLE_USER", "ROLE_ADMIN"}));

        ArrayList<Movie> movies = new ArrayList<>();

        String[] movieNames = {
                "The Dark Knight",
                "The Dark Knight Rises",
                "Batman Begins",
                "Zack Snyder's Justice League",
                "Batman vs Superman",
                "Batman: Under the Redhood",
                "The Dark Knight Returns Part 1",
                "The Dark Knight Returns Part 2",
                "Wonder Woman: 1984",
                "AquaMan: Rise of Atlantis"
        };

        IntStream.range(0, 10).forEach(it -> {
            String name = movieNames[it % movieNames.length];
            Movie movie = new Movie(name, (it % movieNames.length));
            movies.add(movie);
        });
        moviesRepository.save(movies);
    }
}
