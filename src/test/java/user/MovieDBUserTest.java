package user;

import com.shreyas.Application;
import com.shreyas.repository.UserRepository;
import com.shreyas.user.MovieDBUser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class MovieDBUserTest {

    @Autowired
    UserRepository repository;

    private MovieDBUser user1;
    private MovieDBUser user2;
    private MovieDBUser user3;

    @Before
    public void setUp() {
        List<MovieDBUser> users = Arrays.asList(
                user1 = new MovieDBUser("Master", "Bruce", "batman007", "celina", new String[]{"ROLE_USER", "ROLE_ADMIN"}),
                user2 = new MovieDBUser("Alfred", "Pennyworth", "alfredo", "waynemanor", new String[]{"ROLE_USER"}),
                user3 = new MovieDBUser("Celina", "Kyle", "catwoman", "silkycat", new String[]{"ROLE_USER"})
        );
        repository.saveAll(users);
    }

    @Test
    public void testFetchUserByUsername() {
        String userName1 = "batman007";
        String userName2 = "alfredo";
        String userName3 = "catwoman";

        assertThat(user1.getUserName()).isEqualTo(userName1);
        assertThat(user2.getUserName()).isEqualTo(userName2);
        assertThat(user3.getUserName()).isEqualTo(userName3);
    }

    @Test
    public void testFetchAdminUser() {
        assertThat(user1.getUserRoles()).contains("ROLE_ADMIN");
    }

    @Test
    public void testFetchUserRoles() {
        assertThat(user2.getUserRoles()).contains("ROLE_USER");
    }
}
