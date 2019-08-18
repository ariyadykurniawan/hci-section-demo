package hci.section.demo.repository;

import hci.section.demo.entity.SectionGroup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SectionGroupRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private SectionGroupRepository sectionGroupRepository;


    @Test
    public void findBySectionGroupName() {
        SectionGroup sectionGroup = new SectionGroup("GroupX");
        testEntityManager.persist(sectionGroup);
        testEntityManager.flush();

        SectionGroup found = sectionGroupRepository.findBySectionGroupName(sectionGroup.getSectionGroupName());
        assertEquals(found.getSectionGroupName(), sectionGroup.getSectionGroupName());


    }
}