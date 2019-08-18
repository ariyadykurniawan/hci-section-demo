package hci.section.demo.service;

import hci.section.demo.entity.Section;
import hci.section.demo.entity.User;
import hci.section.demo.repository.SectionRepository;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class SectionServiceImplTest {
    @MockBean
    private SectionRepository sectionRepository;

    @TestConfiguration
    static class SectionServiceImplTestContextConfiguration {
        @Bean
        public SectionService sectionService() {
            return new SectionServiceImpl();
        }
    }

    @Autowired
    private SectionService sectionService;

    private static final Long ID = Long.valueOf(1);

    @Before
    public void setup(){
        Section section1 = new Section("News");
        Section section2 = new Section("Promo");
        Section section3 = new Section("Sale");

        List<Section> sections = Arrays.asList(section1,section2,section3);
        Mockito.when(sectionRepository.findAll()).thenReturn(sections);
        Mockito.when(sectionRepository.findBySectionName(section1.getSectionName())).thenReturn(section1);
        Mockito.when(sectionRepository.findById(ID)).thenReturn(java.util.Optional.of(section1));
    }

    @Test
    public void getAllSection() {
        List<Section> sections = sectionService.getAllSection();
        assertTrue(sections.size() > 0);
    }

    @Test
    public void getSectionById() {
        Section found = sectionService.getSectionById(ID);
        assertTrue(!found.toString().isEmpty());
    }

    @Test
    public void getSectionByName() {
        String sectionName = "News";
        Section found = sectionService.getSectionByName(sectionName);
        assertThat(found.getSectionName()).isEqualTo(sectionName);
    }

    @Test
    public void addSection() {
        Section sectionAdd = new Section("Discount");
        Mockito.when(sectionRepository.save(sectionAdd)).thenReturn(sectionAdd);

        Section created = sectionService.addSection(sectionAdd);
        assertThat(created.getSectionName()).isEqualTo(sectionAdd.getSectionName());
    }

    @Test
    public void editSection() {
        Section sectionEdit = new Section("History");
        sectionEdit.setId(ID);
        Mockito.when(sectionRepository.save(sectionEdit)).thenReturn(sectionEdit);

        Section updated = sectionService.editSection(sectionEdit,ID);
        assertThat(updated.getSectionName()).isEqualTo(sectionEdit.getSectionName());
    }

    @Test
    public void deleteSection() {
        Section sectionDelete = new Section("News");
        sectionDelete.setId(ID);

        sectionService.deleteSection(ID);
        verify(sectionRepository, times(1)).deleteById(sectionDelete.getId());
    }
}