package hci.section.demo.repository;

import hci.section.demo.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void getSection() {
    }

    @Test
    public void findByUsername() {
        User user = new User("akurniawan","ariyady","kurniawan", (long) 3);
        testEntityManager.persist(user);
        testEntityManager.flush();

        User found = userRepository.findByUsername(user.getUsername());
        assertEquals(found.getUsername(), user.getUsername());
    }
}