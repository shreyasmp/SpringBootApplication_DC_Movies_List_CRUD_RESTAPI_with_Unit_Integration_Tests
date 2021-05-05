package movie;

import com.shreyas.movie.Movie;
import com.shreyas.movie.MovieController;
import com.shreyas.repository.MovieRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

public class MovieControllerTest {

    @InjectMocks
    private MovieController movieController;

    @Mock
    private MovieRepository movieRepository;

    private Movie movie1;
    private Movie movie2;
    private Movie movie3;

    private ArrayList<Movie> movies = new ArrayList<>();

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
        movie3 = new Movie();
        movie3.setMovieName("Batman/Superman: Public Enemies");
        movie1 = new Movie("Dark Knight Returns Pat 1", 5);
        movie2 = new Movie("Dark Knight Returns Pat 2", 4);
    }

    @Test
    public void testGetMovieByName() {

        movies.add(movie3);

        Mockito.when(movieRepository.findMovieByMovieName("Batman/Superman: Public Enemies")).thenReturn(movies);

        Collection<Movie> movieCollection = movieController.getMovieByMovieName("Batman/Superman: Public Enemies");

        Mockito.verify(movieRepository).findMovieByMovieName("Batman/Superman: Public Enemies");

        assertThat(movieCollection.contains(movie3)).isTrue();
    }

    @Test
    public void testGetAllMovies() {

        movies.add(movie1);
        movies.add(movie2);

        Mockito.when(movieRepository.findAll()).thenReturn(movies);

        Collection<Movie> movieCollection = movieController.getAllMovies();

        Mockito.verify(movieRepository).findAll();

        assertThat(movieCollection.size()).isEqualTo(2);
    }

    @After
    public void tearDown() {
        movies.clear();
    }
}
