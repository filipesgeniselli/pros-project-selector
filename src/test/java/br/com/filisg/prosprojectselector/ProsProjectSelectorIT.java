package br.com.filisg.prosprojectselector;

import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class ProsProjectSelectorIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void maxScorePro__shouldBeEligibleToAllProjects() throws Exception {
        String jsonBody = "{\"age\": 32, \"education_level\": \"bachelors_degree_or_high\", \"past_experiences\": { \"sales\": true, \"support\": true }, \"internet_test\": { \"download_speed\": 150.4, \"upload_speed\": 80.2 }, \"writing_score\": 0.8, \"referral_code\": \"token1234\" }";
        
        mockMvc
            .perform(MockMvcRequestBuilders.post("/projects/select").content(jsonBody).contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.score", Is.is(12)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.selected_project", Is.is("calculate_dark_matter_nasa")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.ineligible_projects").isEmpty())
            .andExpect(MockMvcResultMatchers.jsonPath("$.eligible_projects", Matchers.hasSize(4)));
    }

    @Test
    void proWith7Score__shouldBeEligibleTo3Projects() throws Exception {
        String jsonBody = "{\"age\": 32, \"education_level\": \"high_school\", \"past_experiences\": { \"sales\": false, \"support\": true }, \"internet_test\": { \"download_speed\": 50.4, \"upload_speed\": 40.2 }, \"writing_score\": 0.7, \"referral_code\": \"token1234\" }";
        
        mockMvc
            .perform(MockMvcRequestBuilders.post("/projects/select").content(jsonBody).contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.score", Is.is(7)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.selected_project", Is.is("determine_schrodinger_cat_is_alive")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.eligible_projects", Matchers.hasSize(3)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.ineligible_projects", Matchers.hasSize(1)));
    }



    
}
