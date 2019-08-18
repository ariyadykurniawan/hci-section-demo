package hci.section.demo.repository;

import hci.section.demo.entity.Section;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SectionRepositoryTest {
    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void findBySectionName() {
        Section section = new Section("FlashPromo");
        testEntityManager.persist(section);
        testEntityManager.flush();

        Section found = sectionRepository.findBySectionName(section.getSectionName());
        assertEquals(found.getSectionName(), section.getSectionName());
    }
}