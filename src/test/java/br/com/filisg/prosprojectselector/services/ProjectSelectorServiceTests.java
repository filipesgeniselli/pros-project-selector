package br.com.filisg.prosprojectselector.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import br.com.filisg.prosprojectselector.dtos.ProApplication;
import br.com.filisg.prosprojectselector.dtos.ProApplicationExperience;
import br.com.filisg.prosprojectselector.dtos.ProApplicationInternetTest;
import br.com.filisg.prosprojectselector.dtos.SelectedProjects;
import br.com.filisg.prosprojectselector.enums.EducationLevelEnum.EducationLevelConstants;

class ProjectSelectorServiceTests {
    
    @Test
    void proWithHighScore__shouldGetAllProjects() {
        ProApplication proApplication = new ProApplication(25, EducationLevelConstants.BACHELORS_DEGREE_OR_HIGH, new ProApplicationExperience(true, true), new ProApplicationInternetTest(BigDecimal.valueOf(152.2), BigDecimal.valueOf(55.4)), BigDecimal.valueOf(0.8), "token1234");
        SelectedProjects result = new ProjectSelectorService().selectProject(proApplication);

        assertNotNull(result);
        assertEquals("calculate_dark_matter_nasa", result.getSelectedProject());
        assertEquals(0, result.getIneligibleProjects().length);
        assertEquals(4, result.getEligibleProjects().length);
    }
    
}
