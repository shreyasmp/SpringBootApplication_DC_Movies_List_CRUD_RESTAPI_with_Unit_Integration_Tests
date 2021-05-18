package repository;

import com.shreyas.Application;
import com.shreyas.repository.UserRepository;
import com.shreyas.user.MovieDBUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = Application.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Test
    public void testSaveUser() {
        MovieDBUser expected = new MovieDBUser("Master", "Bruce", "batman007", "celina", new String[]{"ROLE_USER", "ROLE_ADMIN"});
        repository.save(expected);
        MovieDBUser actual = repository.findByUserName("batman007");
        assertNotNull(expected);
        assertEquals(actual, expected);
    }

    @Test
    public void testGetUserByUserName() {
        MovieDBUser expected = new MovieDBUser("Alfred", "Pennyworth", "alfredo", "waynemanor", new String[]{"ROLE_USER"});
        repository.save(expected);
        MovieDBUser actual = repository.findByUserName("alfredo");
        assertEquals(expected.getUserName(), actual.getUserName());
    }

    @Test
    public void testFinalAllUsers() {
        MovieDBUser expected = new MovieDBUser("Celina", "Kyle", "catwoman", "silkycat", new String[]{"ROLE_USER"});
        repository.save(expected);
        assertNotNull(repository.findAll());
    }

    @Test
    public void testDeleteUser() {
        MovieDBUser user = new MovieDBUser("Celina", "Kyle", "catwoman", "silkycat", new String[]{"ROLE_USER"});
        repository.save(user);
        repository.delete(user);
    }
}
