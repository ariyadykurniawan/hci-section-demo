package hci.section.demo.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import hci.section.demo.SectionApplication;
import hci.section.demo.entity.User;
import hci.section.demo.repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = SectionApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application.properties")
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private String username = null;
    private Long ID = null;

    @Before
    public void init(){
        username = RandomStringUtils.randomAlphabetic(10);
        ID = Long.valueOf(RandomUtils.nextInt());
        User user1 = new User(ID,username,"bar","good", (long) 3);
        userRepository.save(user1);
    }

    @Test
    public void getAlluser() throws Exception{
        mockMvc.perform(get("/api/users")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void getUserById_NotFound() throws Exception{
        mockMvc.perform(get("/api/user/"+ID)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getUserByUsername() throws Exception{
        mockMvc.perform(get("/api/user/search/"+username)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status",is("OK")));
    }

    @Test
    public void getUserSection() {
    }

    @Test
    public void addUser() throws Exception{
        String username = RandomStringUtils.randomAlphabetic(10);
        User user1 = new User(username,username,"topia", (long) 3);
        userRepository.save(user1);

        mockMvc.perform(post("/api/user")
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .content(objectMapper.writeValueAsString(user1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status",is("OK")));
    }



    @Test
    public void editUser() throws Exception{
        String username = RandomStringUtils.randomAlphabetic(10);
        User user1 = new User(ID,username,"barzoom","random", (long) 3);

        mockMvc.perform(put("/api/user/"+ID)
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .content(objectMapper.writeValueAsString(user1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status",is("OK")));
    }

    @Test
    public void deleteUser_NotFound() throws Exception{

        mockMvc.perform(delete("/api/user/"+ID))
                .andExpect(status().isNotFound());
    }
}
