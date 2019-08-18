package hci.section.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hci.section.demo.entity.SectionGroup;
import hci.section.demo.service.SectionGroupService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SectionGroupController.class)
public class SectionGroupControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SectionGroupService sectionGroupService;

    private static final Long ID = Long.valueOf(1);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void getAllSectionGroup() {

    }

    @Test
    public void getSectionGroupById() {
    }

    @Test
    public void getSectionGroupByName() {
    }

    @Test
    public void addSectionGroup() {
    }

    @Test
    public void editSection() {
    }

    @Test
    public void deleteSection() throws Exception{

        doNothing().when(sectionGroupService).deleteSectionGroup(1L);

        mockMvc.perform(delete("/api/section-group/1"))
                .andExpect(status().isOk());

        Mockito.verify(sectionGroupService, Mockito.times(1)).deleteSectionGroup(1L);
    }
}