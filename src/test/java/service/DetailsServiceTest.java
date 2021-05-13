package service;


import com.shreyas.repository.UserRepository;
import com.shreyas.service.DetailsService;
import com.shreyas.user.MovieDBUser;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
@SpringBootTest(classes = UserDetailsService.class)
public class DetailsServiceTest {

    @InjectMocks
    private DetailsService service;

    @Mock
    private UserRepository repository;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(service).build();
    }

    @Test
    @WithMockUser(username = "shreyas", password = "123456", roles = "USER")
    public void testFindUserName() throws Exception {

        MovieDBUser user = new MovieDBUser("shreyas", "muthkur", "ggshmp", "123456", new String[]{"ROLE_USER"});

        when(repository.findByUserName(user.getUserName())).thenReturn(user);

        service.loadUserByUsername(user.getUserName());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get("/movies")
                .with(user(user.getUserName()))
                .accept(MediaType.ALL))
                .andExpect(status().isNotFound())
                .andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }
}
