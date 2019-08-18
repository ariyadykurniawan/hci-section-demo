package hci.section.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hci.section.demo.entity.Section;
import hci.section.demo.entity.SectionGroup;
import hci.section.demo.service.SectionGroupService;
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
@WebMvcTest(SectionGroupController.class)
public class SectionGroupControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SectionGroupService sectionGroupService;

    private static final Long ID = Long.valueOf(1);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void init(){
        SectionGroup sectionGroup1 = new SectionGroup("Group1");
        sectionGroup1.setId(ID);
        given(sectionGroupService.getSectionGroupById(ID)).willReturn(sectionGroup1);
        given(sectionGroupService.getSectionGroupByName(sectionGroup1.getSectionGroupName())).willReturn(sectionGroup1);
    }

    @Test
    public void getAllSectionGroup() throws Exception{
        SectionGroup sectionGroup1 = new SectionGroup("Group1");
        SectionGroup sectionGroup2 = new SectionGroup("Group2");
        SectionGroup sectionGroup3 = new SectionGroup("Group2");

        List<SectionGroup> sectionGroups = Arrays.asList(sectionGroup1,sectionGroup2,sectionGroup3);
        given(sectionGroupService.getAllSectionGroup()).willReturn(sectionGroups);
        mockMvc.perform(get("/api/section-group")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data",hasSize(3)));
    }

    @Test
    public void getSectionGroupById() throws Exception{
        mockMvc.perform(get("/api/section-group/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.sectionGroupName",is("Group1")));
    }

    @Test
    public void getSectionGroupByName() throws Exception{
        mockMvc.perform(get("/api/section-group/search/Group1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.sectionGroupName",is("Group1")));
    }

    @Test
    public void addSectionGroup() throws Exception{
        SectionGroup sectionGroup1 = new SectionGroup("Group1");
        given(sectionGroupService.addSectionGroup(sectionGroup1)).willReturn(sectionGroup1);
        mockMvc.perform(post("/api/section-group")
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .content(objectMapper.writeValueAsString(sectionGroup1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status",is("OK")));
    }

    @Test
    public void editSection() throws Exception{
        SectionGroup sectionGroup1 = new SectionGroup("Group1");
        sectionGroup1.setId(ID);
        given(sectionGroupService.editSectionGroup(sectionGroup1,ID)).willReturn(sectionGroup1);

        mockMvc.perform(put("/api/section-group/1")
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .content(objectMapper.writeValueAsString(sectionGroup1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status",is("OK")));
    }

    @Test
    public void deleteSection() throws Exception{

        doNothing().when(sectionGroupService).deleteSectionGroup(1L);

        mockMvc.perform(delete("/api/section-group/1"))
                .andExpect(status().isOk());

        Mockito.verify(sectionGroupService, Mockito.times(1)).deleteSectionGroup(1L);
    }
}