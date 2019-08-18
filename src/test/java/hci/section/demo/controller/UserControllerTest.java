package hci.section.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hci.section.demo.entity.User;
import hci.section.demo.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private static final Long ID = Long.valueOf(1);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void init(){
        User user1 = new User("akurniawan","ariyady","kurniawan", (long) 3);
        user1.setId(ID);
        given(userService.getUserById(ID)).willReturn(user1);
        given(userService.getUserByUsername(user1.getUsername())).willReturn(user1);
    }

    @Test
    public void getAlluser() throws Exception{
        User user1 = new User("ariyady","ariyady","kurniawan", (long) 3);
        User user2 = new User("bobi","Bobi","kurniawan", (long) 2);
        User user3 = new User("charlie","charlie","kurniawan", (long) 1);

        List<User> users = Arrays.asList(user1,user2,user3);

        given(userService.getAllUser()).willReturn(users);

        mockMvc.perform(get("/api/users")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data",hasSize(3)));
    }

    @Test
    public void getUserById() throws Exception{
        mockMvc.perform(get("/api/user/1")
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.data.username",is("akurniawan")))
                        .andExpect(jsonPath("$.data.firstName",is("ariyady")))
                        .andExpect(jsonPath("$.data.lastName",is("kurniawan")));
    }

    @Test
    public void getUserByUsername() throws Exception{
        mockMvc.perform(get("/api/user/search/akurniawan")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.username",is("akurniawan")))
                .andExpect(jsonPath("$.data.firstName",is("ariyady")))
                .andExpect(jsonPath("$.data.lastName",is("kurniawan")));

        Mockito.verify(userService, Mockito.times(1)).getUserByUsername("akurniawan");
    }

    @Test
    public void getUserSection() {
    }

    @Test
    public void addUser() throws Exception{
        User user1 = new User("akurniawan","ariyady","kurniawan", (long) 3);
        given(userService.addUser(user1)).willReturn(user1);

        mockMvc.perform(post("/api/user")
                        .header(HttpHeaders.CONTENT_TYPE, "application/json")
                        .content(objectMapper.writeValueAsString(user1)))
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.status",is("OK")));
    }

    @Test
    public void editUser() throws Exception{
        User user1 = new User("akurniawan","ariyady","kurniawan", (long) 3);
        user1.setId(ID);
        given(userService.addUser(user1)).willReturn(user1);

        mockMvc.perform(put("/api/user/1")
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .content(objectMapper.writeValueAsString(user1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status",is("OK")));
    }

    @Test
    public void deleteUser() throws Exception{
        doNothing().when(userService).deleteUser(1L);

        mockMvc.perform(delete("/api/user/1"))
                .andExpect(status().isOk());

        Mockito.verify(userService, Mockito.times(1)).deleteUser(1L);
    }
}