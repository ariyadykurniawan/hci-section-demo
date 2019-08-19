package hci.section.demo.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import hci.section.demo.SectionApplication;
import hci.section.demo.entity.SectionGroup;
import hci.section.demo.repository.SectionGroupRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = SectionApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application.properties")
public class SectionControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    SectionGroupRepository sectionGroupRepository;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private String sectionGroupName = null;
    private Long ID = null;

    @Before
    public void init(){
        sectionGroupName = RandomStringUtils.randomAlphabetic(10);
        ID = Long.valueOf(RandomUtils.nextInt());
        SectionGroup sectionGroup1 = new SectionGroup(ID,sectionGroupName);
        sectionGroupRepository.save(sectionGroup1);
    }

    @Test
    public void getAllSectionGroup() throws Exception{
        mockMvc.perform(get("/api/section-group")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getSectionGroupById_NotFound() throws Exception{
        mockMvc.perform(get("/api/section-group/"+ID)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getSectionGroupByName() throws Exception{
        mockMvc.perform(get("/api/section-group/search/"+sectionGroupName)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void addSectionGroup() throws Exception{
        String sectionGroupName1 = RandomStringUtils.randomAlphabetic(10);
        SectionGroup sectionGroup1 = new SectionGroup(sectionGroupName1);

        mockMvc.perform(post("/api/section-group")
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .content(objectMapper.writeValueAsString(sectionGroup1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status",is("OK")));
    }

    @Test
    public void editSection() throws Exception{
        String sectionGroupName1 = RandomStringUtils.randomAlphabetic(10);
        SectionGroup sectionGroup1 = new SectionGroup(ID, sectionGroupName1);

        mockMvc.perform(put("/api/section-group/"+ID)
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .content(objectMapper.writeValueAsString(sectionGroup1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status",is("OK")));
    }

    @Test
    public void deleteSection_notFound() throws Exception{

        mockMvc.perform(delete("/api/section-group/"+ID))
                .andExpect(status().isNotFound());
    }
}
