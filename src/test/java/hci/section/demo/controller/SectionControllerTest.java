package hci.section.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hci.section.demo.entity.Section;
import hci.section.demo.service.SectionService;
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
@WebMvcTest(SectionController.class)
public class SectionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SectionService sectionService;

    private static final Long ID = Long.valueOf(1);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void init(){
        Section section1 = new Section(ID,"News");
        given(sectionService.getSectionById(ID)).willReturn(section1);
        given(sectionService.getSectionByName(section1.getSectionName())).willReturn(section1);
    }

    @Test
    public void getAllSections() throws Exception{
        Section section1 = new Section("News");
        Section section2 = new Section("Flash Sale");
        Section section3 = new Section("Discount");

        List<Section> sections = Arrays.asList(section1,section2,section3);
        given(sectionService.getAllSection()).willReturn(sections);
        mockMvc.perform(get("/api/sections")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data",hasSize(3)));
    }

    @Test
    public void getSectionById() throws Exception{
        mockMvc.perform(get("/api/section/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.sectionName",is("News")));
    }

    @Test
    public void getSectionByName() throws Exception{
        mockMvc.perform(get("/api/section/search/News")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.sectionName",is("News")));
    }

    @Test
    public void addSection() throws Exception{
        Section section1 = new Section("News");
        given(sectionService.addSection(section1)).willReturn(section1);
        mockMvc.perform(post("/api/section")
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .content(objectMapper.writeValueAsString(section1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status",is("OK")));
    }

    @Test
    public void editSection() throws Exception{
        Section section1 = new Section(ID,"News");
        given(sectionService.editSection(section1,ID)).willReturn(section1);

        mockMvc.perform(put("/api/section/1")
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .content(objectMapper.writeValueAsString(section1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status",is("OK")));
    }

    @Test
    public void deleteSection() throws Exception{
        doNothing().when(sectionService).deleteSection(1L);

        mockMvc.perform(delete("/api/section/1"))
                .andExpect(status().isOk());

        Mockito.verify(sectionService, Mockito.times(1)).deleteSection(1L);
    }
}