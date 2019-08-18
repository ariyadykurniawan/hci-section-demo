package hci.section.demo.repository;

import hci.section.demo.entity.User;
import hci.section.demo.model.UserSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(nativeQuery = true, name = "User.UserSectionMapping")
    List<UserSection> getSection(Long id_user);

    User findByUsername(String username);
}
