package integration;

import com.shreyas.Application;
import com.shreyas.movie.Movie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieControllerWebIntegrationTest {

    @LocalServerPort
    private int port;

    private final TestRestTemplate restTemplate = new TestRestTemplate();

    private final HttpHeaders headers = new HttpHeaders();

    @Test
    public void test_GET_AllMovies_401_UNAUTHROIZED() {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.
                exchange(createURLWithPort("/movies"),
                        HttpMethod.GET,
                        entity,
                        String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    @Test
    public void test_GET_AllMovies_200_OK() throws URISyntaxException {
        final String baseURL = createURLWithPort("/movies");
        URI uri = new URI(baseURL);

        headers.add("Authorization", "Basic YndheW5lOjEyMzQ1Ng==");

        HttpEntity<Movie> requestEntity = new HttpEntity<>(null, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(uri,
                HttpMethod.GET,
                requestEntity,
                String.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void test_POST_AMovie_201_OK() throws URISyntaxException {
        final String baseURL = createURLWithPort("/movies/");
        URI uri = new URI(baseURL);

        headers.add("Authorization", "Basic YndheW5lOjEyMzQ1Ng==");
        Movie movie = new Movie("Batman: Beyond", 11);

        HttpEntity<Movie> requestEntity = new HttpEntity<>(movie, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                uri,
                HttpMethod.POST,
                requestEntity,
                String.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void test_PUT_AMovie_200_OK() throws URISyntaxException {
        final String baseURL = createURLWithPort("/movies/movieRanking/11");
        URI uri = new URI(baseURL);

        headers.add("Authorization", "Basic YndheW5lOjEyMzQ1Ng==");
        Movie movie = new Movie("Batman: Beyond", 5);

        HttpEntity<Movie> requestEntity = new HttpEntity<>(movie, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                uri,
                HttpMethod.PUT,
                requestEntity,
                String.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void test_DELETE_AMovie_202_OK() throws URISyntaxException {
        final String baseURL = createURLWithPort("/movies/movieRanking/11");
        URI uri = new URI(baseURL);

        headers.add("Authorization", "Basic YndheW5lOjEyMzQ1Ng==");

        HttpEntity<Movie> requestEntity = new HttpEntity<>(null, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                uri,
                HttpMethod.DELETE,
                requestEntity,
                String.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
