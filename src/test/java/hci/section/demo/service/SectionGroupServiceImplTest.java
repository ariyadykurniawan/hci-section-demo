package hci.section.demo.service;

import hci.section.demo.entity.SectionGroup;
import hci.section.demo.repository.SectionGroupRepository;
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
public class SectionGroupServiceImplTest {
    @MockBean
    private SectionGroupRepository sectionGroupRepository;

    @TestConfiguration
    static class SectionGroupServiceImplTestContextConfiguration {
        @Bean
        public SectionGroupService sectionGroupService() {
            return new SectionGroupServiceImpl();
        }
    }

    @Autowired
    private SectionGroupService sectionGroupService;

    private static final Long ID = Long.valueOf(1);

    @Before
    public void setUp() throws Exception {
        SectionGroup sectionGroup1 = new SectionGroup("GroupA");
        SectionGroup sectionGroup2 = new SectionGroup("GroupB");
        SectionGroup sectionGroup3 = new SectionGroup("GroupC");

        List<SectionGroup> sectionGroups = Arrays.asList(sectionGroup1,sectionGroup2,sectionGroup3);
        Mockito.when(sectionGroupRepository.findAll()).thenReturn(sectionGroups);
        Mockito.when(sectionGroupRepository.findById(ID)).thenReturn(java.util.Optional.of(sectionGroup1));
        Mockito.when(sectionGroupRepository.findBySectionGroupName(sectionGroup1.getSectionGroupName())).thenReturn(sectionGroup1);
    }

    @Test
    public void getAllSectionGroup() {
        List<SectionGroup> sectionGroups = sectionGroupService.getAllSectionGroup();
        assertTrue(sectionGroups.size() > 0);
    }

    @Test
    public void getSectionGroupById() {
        SectionGroup found = sectionGroupService.getSectionGroupById(ID);
        assertTrue(!found.toString().isEmpty());
    }

    @Test
    public void getSectionGroupByName() {
        String sectionGroupName = "GroupA";
        SectionGroup found = sectionGroupService.getSectionGroupByName(sectionGroupName);
        assertThat(found.getSectionGroupName()).isEqualTo(sectionGroupName);
    }

    @Test
    public void addSectionGroup() {
        SectionGroup sectionGroupAdd = new SectionGroup("GroupX");
        Mockito.when(sectionGroupRepository.save(sectionGroupAdd)).thenReturn(sectionGroupAdd);

        SectionGroup created = sectionGroupService.addSectionGroup(sectionGroupAdd);
        assertThat(created.getSectionGroupName()).isEqualTo(sectionGroupAdd.getSectionGroupName());
    }

    @Test
    public void editSectionGroup() {
        SectionGroup sectionGroupEdit = new SectionGroup(ID,"GroupA");
        Mockito.when(sectionGroupRepository.save(sectionGroupEdit)).thenReturn(sectionGroupEdit);

        SectionGroup updated = sectionGroupService.editSectionGroup(sectionGroupEdit,ID);
        assertThat(updated.getSectionGroupName()).isEqualTo(sectionGroupEdit.getSectionGroupName());
    }

    @Test
    public void deleteSectionGroup() {
        SectionGroup sectionGroupDelete = new SectionGroup(ID,"GroupA");

        sectionGroupService.deleteSectionGroup(ID);
        verify(sectionGroupRepository, times(1)).deleteById(sectionGroupDelete.getId());
    }
}