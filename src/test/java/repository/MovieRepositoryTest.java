package repository;

import com.shreyas.Application;
import com.shreyas.movie.Movie;
import com.shreyas.repository.MovieRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = Application.class)
public class MovieRepositoryTest {

    @Autowired
    private MovieRepository repository;

    @Test
    public void testSaveMovie() {
        Movie expected = new Movie("Batman: Death in the Family", 8);
        repository.save(expected);
        Collection<Movie> movie2 = repository.findMovieByMovieName("Batman: Death in the Family");
        assertNotNull(expected);
        assertTrue(movie2.contains(expected));
        for (Movie actual : movie2) {
            assertEquals(expected, actual);
        }
    }

    @Test
    public void testGetMovieByName() {
        Movie movie1 = new Movie("Wonder Woman: 1984", 2);
        repository.save(movie1);
        Collection<Movie> movie2 = repository.findMovieByMovieName("Wonder Woman: 1984");
        for (Movie movie : movie2) {
            assertEquals(movie1.getMovieName(), movie.getMovieName());
        }
    }

    @Test
    public void testFindAllMovies() {
        Movie movie = new Movie("Batman: Ninja", 10);
        repository.save(movie);
        assertNotNull(repository.findAll());
    }

    @Test
    public void testDeletedMovie() {
        Movie movie = new Movie("Superman: Rise of Kal El", 5);
        repository.save(movie);
        repository.delete(movie);
    }
}
