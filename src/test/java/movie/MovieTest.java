package movie;

import com.shreyas.Application;
import com.shreyas.movie.Movie;
import com.shreyas.repository.MovieRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class MovieTest {

    @Autowired
    MovieRepository repository;

    private Movie movie1;
    private Movie movie2;
    private Movie movie3;

    @Before
    public void setUp() {
        movie1 = new Movie("Dark Knight", 1);
        movie2 = new Movie("Redhood", 2);
        movie3 = new Movie("Justice League", 3);

        repository.deleteAll();
        repository.save(Arrays.asList(movie1, movie2, movie3));
    }

    @Test
    public void testFetchMovieByName() {
        String mov1 = "Dark Knight";
        String mov2 = "Redhood";
        String mov3 = "Justice League";

        assertThat(movie1.getMovieName()).isEqualTo(mov1);
        assertThat(movie2.getMovieName()).isEqualTo(mov2);
        assertThat(movie3.getMovieName()).isEqualTo(mov3);
    }

    @Test
    public void testFetchMovieByRanking() {
        assertThat(movie1.getMovieRanking()).isEqualTo(1);
        assertThat(movie2.getMovieRanking()).isEqualTo(2);
        assertThat(movie3.getMovieRanking()).isEqualTo(3);
    }
}
