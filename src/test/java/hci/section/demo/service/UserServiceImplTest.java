package hci.section.demo.service;

import hci.section.demo.entity.User;
import hci.section.demo.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.Arrays;
import java.util.List;

import static com.sun.tools.doclint.Entity.times;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class UserServiceImplTest {
    @MockBean
    private UserRepository userRepository;

    @TestConfiguration
    static class UserServiceImplTestContextConfiguration {
        @Bean
        public UserService userService() {
            return new UserServiceImpl();
        }
    }

    @Autowired
    private UserService userService;

    private static final Long ID = Long.valueOf(1);

    @Before
    public void setup(){
        User ariyady = new User("ariyady","ariyady","kurniawan", (long) 3);
        User bobi = new User("bobi","Bobi","kurniawan", (long) 2);
        User charlie = new User("charlie","charlie","kurniawan", (long) 1);
        List<User>users = Arrays.asList(ariyady, bobi, charlie);
        Mockito.when(userRepository.findAll()).thenReturn(users);
        Mockito.when(userRepository.findByUsername(ariyady.getUsername())).thenReturn(ariyady);
        Mockito.when(userRepository.findById(ID)).thenReturn(java.util.Optional.of(ariyady));
    }

    @Test
    public void getAllUser() {
        List<User> users = userService.getAllUser();
        assertTrue(users.size() > 0);
    }

    @Test
    public void getUserSection() {
    }

    @Test
    public void getUserById() {
        User found = userService.getUserById(ID);
        assertTrue(!found.toString().isEmpty());
    }

    @Test
    public void getUserByUsername() {
        String username = "ariyady";
        User found = userService.getUserByUsername(username);

        assertThat(found.getUsername()).isEqualTo(username);
    }

    @Test
    public void addUser() {
        User david = new User("david","david","kurniawan", (long) 1);
        Mockito.when(userRepository.save(david)).thenReturn(david);

        User created = userService.addUser(david);
        assertThat(created.getUsername()).isEqualTo(david.getUsername());
    }

    @Test
    public void editUser() {
        User editAriyady = new User("akurniawan","ariyady","kurniawan", (long) 1);
        editAriyady.setId(ID);
        Mockito.when(userRepository.save(editAriyady)).thenReturn(editAriyady);

        User updated = userService.editUser(editAriyady, ID);
        assertThat(updated.getUsername()).isEqualTo(editAriyady.getUsername());
        assertThat(updated.getFirstName()).isEqualTo(editAriyady.getFirstName());
        assertThat(updated.getLastName()).isEqualTo(editAriyady.getLastName());
    }

    @Test
    public void deleteUser() {

        User userDelete = new User("ariyady","ariyady","kurniawan", (long) 3);
        userDelete.setId(Long.valueOf(ID));

        userService.deleteUser(userDelete.getId());

        verify(userRepository, times(1)).deleteById(userDelete.getId());

    }
}