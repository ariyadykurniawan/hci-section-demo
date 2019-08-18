package hci.section.demo.service;

import hci.section.demo.entity.User;
import hci.section.demo.model.UserSection;

import java.util.List;

public interface UserService {
    List<User> getAllUser();
    List<UserSection> getUserSection(Long id);
    User getUserById(Long id);
    User getUserByUsername(String username);
    User addUser(User user);
    User editUser(User user, Long id);
    void deleteUser(Long id);
}
