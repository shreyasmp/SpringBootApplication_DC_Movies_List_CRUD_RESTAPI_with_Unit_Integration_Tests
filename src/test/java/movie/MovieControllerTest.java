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
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@WebMvcTest(controllers = MovieController.class)
@WebAppConfiguration
public class MovieControllerTest {

    @InjectMocks
    private MovieController movieController;

    @Mock
    private MovieRepository movieRepository;

    private MockMvc mockMvc;

    private Movie movie1;
    private Movie movie2;
    private Movie movie3;

    private ArrayList<Movie> movies = new ArrayList<>();


    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        movie3 = new Movie();
        mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
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

    @Test
    public void testGetAllMovies_HTTP_SUCCESS() throws Exception {
        movies.add(movie1);
        movies.add(movie2);

        Mockito.when(movieRepository.findAll()).thenReturn(movies);

        movieController.getAllMovies();

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get("/movies")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        assertThat(result).isNotNull();
        MockHttpServletResponse response = result.getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void testCreateAMovie_HTTP_SUCCESS() throws Exception {
        Movie movie = new Movie("Dawn Of Justice", 6);

        Mockito.when(movieRepository.save(Mockito.any(Movie.class))).thenReturn(movie);

        movieController.addMovie(movie);

        String createNewMovieJson = "{\n" +
                "    \"movieName\": \"Batman: Under the Redhood\",\n" +
                "    \"movieRanking\": 2\n" +
                "}";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .post("/movies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createNewMovieJson)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string("Successful creation of resource"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        assertThat(result).isNotNull();
        MockHttpServletResponse response = result.getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    public void testUpdateAMovieByRanking_HTTP_SUCCESS() throws Exception {
        Movie movie = new Movie("Dawn Of Justice", 6);

        Mockito.when(movieRepository.save(Mockito.any(Movie.class))).thenReturn(movie);

        movieController.updateMovie(movie, 1);

        String updateMovieJson = "{\n" +
                "    \"movieName\": \"Batman: Under the Redhood\",\n" +
                "    \"movieRanking\": 5\n" +
                "}";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .put("/movies/movieRanking/6")
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateMovieJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Data updated successfully"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        assertThat(result).isNotNull();
        MockHttpServletResponse response = result.getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
    
    @Test
    public void testDeleteAMovie_HTTP_SUCCESS() throws Exception {
        Mockito.doNothing().when(movieRepository).delete(movie1);

        movieController.deleteMovie(movie1.getMovieRanking());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .delete("/movies/movieRanking/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(MockMvcResultMatchers.content().string("Data deleted successfully"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        assertThat(result).isNotNull();
        MockHttpServletResponse response = result.getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.ACCEPTED.value());
    }

    @After
    public void tearDown() {
        movies.clear();
    }
}
