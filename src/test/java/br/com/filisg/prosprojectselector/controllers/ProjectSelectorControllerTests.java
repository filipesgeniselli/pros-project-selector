package br.com.filisg.prosprojectselector.controllers;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.filisg.prosprojectselector.services.ProjectSelectorService;

@WebMvcTest
@AutoConfigureMockMvc
class ProjectSelectorControllerTests {

    @MockBean
    ProjectSelectorService projectSelectorService;

    @Autowired
    ProjectSelectorController projectSelectorController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void whenControllerInjected__thenNotNull() {
        assertNotNull(projectSelectorController);
    }

    @Test
    void invalidAgeShouldReturnBadRequest() throws Exception {
        String simpleBodyWithoutAge = "{\"education_level\": \"high_school\", \"past_experiences\": { \"sales\": false, \"support\": true }, \"internet_test\": { \"download_speed\": 50.4, \"upload_speed\": 40.2 }, \"writing_score\": 0.6, \"referral_code\": \"token1234\" }";

        mockMvc.perform(MockMvcRequestBuilders.post("/projects/select")
                .content(simpleBodyWithoutAge)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.age", Is.is("Age must be informed")));
    }

    @Test
    void invalidEducationLevel__ShouldReturnBadRequest() throws Exception {
        String simpleBodyWithoutAge = "{\"age\": 32, \"education_level\": \"not_informed\", \"past_experiences\": { \"sales\": false, \"support\": true }, \"internet_test\": { \"download_speed\": 50.4, \"upload_speed\": 40.2 }, \"writing_score\": 0.6, \"referral_code\": \"token1234\" }";

        mockMvc.perform(MockMvcRequestBuilders.post("/projects/select")
                .content(simpleBodyWithoutAge)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.educationLevel", Is.is("Education level must be informed and one of the following(no_education, high_school, bachelors_degree_or_high)")));
    }

    @Test
    void nullEducationLevel__ShouldReturnBadRequest() throws Exception {
        String simpleBodyWithoutAge = "{\"age\": 32, \"past_experiences\": { \"sales\": false, \"support\": true }, \"internet_test\": { \"download_speed\": 50.4, \"upload_speed\": 40.2 }, \"writing_score\": 0.6, \"referral_code\": \"token1234\" }";

        mockMvc.perform(MockMvcRequestBuilders.post("/projects/select")
                .content(simpleBodyWithoutAge)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.educationLevel", Is.is("Education level must be informed and one of the following(no_education, high_school, bachelors_degree_or_high)")));
    }

    @Test
    void nullPastExperiences__ShouldReturnBadRequest() throws Exception {
        String simpleBodyWithoutAge = "{\"age\": 32, \"education_level\": \"high_school\", \"internet_test\": { \"download_speed\": 50.4, \"upload_speed\": 40.2 }, \"writing_score\": 0.6, \"referral_code\": \"token1234\" }";

        mockMvc.perform(MockMvcRequestBuilders.post("/projects/select")
                .content(simpleBodyWithoutAge)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.pastExperiences", Is.is("Past experiences must be informed")));
    }

    @Test
    void invalidPastExperiences__ShouldReturnBadRequest() throws Exception {
        String simpleBodyWithoutAge = "{\"age\": 32, \"education_level\": \"high_school\", \"past_experiences\": {}, \"internet_test\": { \"download_speed\": 50.4, \"upload_speed\": 40.2 }, \"writing_score\": 0.6, \"referral_code\": \"token1234\" }";

        mockMvc.perform(MockMvcRequestBuilders.post("/projects/select")
                .content(simpleBodyWithoutAge)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.['pastExperiences.sales']", Is.is("Sales experience must be informed")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.['pastExperiences.support']", Is.is("Support experience must be informed")));
    }

    @Test
    void nullInternetTest__ShouldReturnBadRequest() throws Exception {
        String simpleBodyWithoutAge = "{\"age\": 32, \"education_level\": \"high_school\", \"past_experiences\": { \"sales\": false, \"support\": true }, \"writing_score\": 0.6}";

        mockMvc.perform(MockMvcRequestBuilders.post("/projects/select")
                .content(simpleBodyWithoutAge)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.internetTest", Is.is("Internet test must be informed")));
    }

    @Test
    void invalidInternetTest__ShouldReturnBadRequest() throws Exception {
        String simpleBodyWithoutAge = "{\"age\": 32, \"education_level\": \"high_school\", \"past_experiences\": { \"sales\": false, \"support\": true }, \"internet_test\": {}, \"writing_score\": 0.6}";

        mockMvc.perform(MockMvcRequestBuilders.post("/projects/select")
                .content(simpleBodyWithoutAge)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.['internetTest.downloadSpeed']", Is.is("Download speed must be informed")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.['internetTest.uploadSpeed']", Is.is("Upload speed must be informed")));
    }

    @Test
    void emptyWritingScore__ShouldReturnBadRequest() throws Exception {
        String simpleBodyWithoutAge = "{\"age\": 32, \"education_level\": \"high_school\", \"past_experiences\": { \"sales\": false, \"support\": true }, \"internet_test\": { \"download_speed\": 50.4, \"upload_speed\": 40.2 } }";

        mockMvc.perform(MockMvcRequestBuilders.post("/projects/select")
                .content(simpleBodyWithoutAge)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.writingScore", Is.is("Writing score must be informed")));
    }

    @Test
    void emptyReferralCode__ShouldReturnOk() throws Exception {
        String simpleBodyWithoutAge = "{\"age\": 32, \"education_level\": \"high_school\", \"past_experiences\": { \"sales\": false, \"support\": true }, \"internet_test\": { \"download_speed\": 50.4, \"upload_speed\": 40.2 }, \"writing_score\": 0.6}";

        mockMvc.perform(MockMvcRequestBuilders.post("/projects/select")
                .content(simpleBodyWithoutAge)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void allParamsValid__ShouldReturnOk() throws Exception {
        String simpleBodyWithoutAge = "{\"age\": 32, \"education_level\": \"high_school\", \"past_experiences\": { \"sales\": false, \"support\": true }, \"internet_test\": { \"download_speed\": 50.4, \"upload_speed\": 40.2 }, \"writing_score\": 0.6, \"referral_code\": \"token1234\" }";

        mockMvc.perform(MockMvcRequestBuilders.post("/projects/select")
                .content(simpleBodyWithoutAge)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
