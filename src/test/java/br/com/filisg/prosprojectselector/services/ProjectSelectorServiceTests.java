package br.com.filisg.prosprojectselector.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

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
        ProApplication proApplication = new ProApplication(25, EducationLevelConstants.BACHELORS_DEGREE_OR_HIGH,
                new ProApplicationExperience(true, true),
                new ProApplicationInternetTest(BigDecimal.valueOf(152.2), BigDecimal.valueOf(55.4)),
                BigDecimal.valueOf(0.8), "token1234");
        SelectedProjects result = new ProjectSelectorService().selectProject(proApplication);

        assertNotNull(result);
        assertEquals(12, result.getScore());
        assertEquals("calculate_dark_matter_nasa", result.getSelectedProject());
        assertEquals(0, result.getIneligibleProjects().length);
        assertEquals(4, result.getEligibleProjects().length);
    }

    @Test
    void minorAgePro__shouldBeIneligible() {
        ProApplication proApplication = new ProApplication(15, EducationLevelConstants.HIGH_SCHOOL,
                new ProApplicationExperience(false, false),
                new ProApplicationInternetTest(BigDecimal.valueOf(152.2), BigDecimal.valueOf(55.4)),
                BigDecimal.valueOf(0.8), null);
        SelectedProjects result = new ProjectSelectorService().selectProject(proApplication);

        assertNotNull(result);
        assertEquals(-1, result.getScore());
        assertNull(result.getSelectedProject());
        assertEquals(4, result.getIneligibleProjects().length);
        assertEquals(0, result.getEligibleProjects().length);
    }

    @Test
    void proWithLowScore__shouldBeIneligible() {
        ProApplication proApplication = new ProApplication(25, EducationLevelConstants.NO_EDUCATION,
                new ProApplicationExperience(false, false),
                new ProApplicationInternetTest(BigDecimal.valueOf(10.8), BigDecimal.valueOf(8.8)),
                BigDecimal.valueOf(0.3), null);
        SelectedProjects result = new ProjectSelectorService().selectProject(proApplication);

        assertNotNull(result);
        assertEquals(1, result.getScore());
        assertNull(result.getSelectedProject());
        assertEquals(4, result.getIneligibleProjects().length);
        assertEquals(0, result.getEligibleProjects().length);
    }

    @Test
    void proWithNegativeScore__shouldBeIneligible() {
        ProApplication proApplication = new ProApplication(25, EducationLevelConstants.NO_EDUCATION,
                new ProApplicationExperience(false, false),
                new ProApplicationInternetTest(BigDecimal.valueOf(2.5), BigDecimal.valueOf(0.8)),
                BigDecimal.valueOf(0.2), null);
        SelectedProjects result = new ProjectSelectorService().selectProject(proApplication);

        assertNotNull(result);
        assertEquals(-3, result.getScore());
        assertNull(result.getSelectedProject());
        assertEquals(4, result.getIneligibleProjects().length);
        assertEquals(0, result.getEligibleProjects().length);
    }

    @Test
    void proWithMediumScore__shouldGet3Projects() {
        ProApplication proApplication = new ProApplication(35, EducationLevelConstants.HIGH_SCHOOL,
                new ProApplicationExperience(false, true),
                new ProApplicationInternetTest(BigDecimal.valueOf(50.2), BigDecimal.valueOf(40.5)),
                BigDecimal.valueOf(0.7), "token1234");
        SelectedProjects result = new ProjectSelectorService().selectProject(proApplication);

        assertNotNull(result);
        assertEquals(7, result.getScore());
        assertEquals("determine_schrodinger_cat_is_alive", result.getSelectedProject());
        assertEquals(1, result.getIneligibleProjects().length);
        assertEquals(3, result.getEligibleProjects().length);
    }

    @Test
    void proWith5Score__shouldGet2Projects() {
        ProApplication proApplication = new ProApplication(35, EducationLevelConstants.HIGH_SCHOOL,
                new ProApplicationExperience(false, true),
                new ProApplicationInternetTest(BigDecimal.valueOf(25.2), BigDecimal.valueOf(40.5)),
                BigDecimal.valueOf(0.6), null);
        SelectedProjects result = new ProjectSelectorService().selectProject(proApplication);

        assertNotNull(result);
        assertEquals(5, result.getScore());
        assertEquals("support_users_from_xyz", result.getSelectedProject());
        assertEquals(2, result.getIneligibleProjects().length);
        assertEquals(2, result.getEligibleProjects().length);
    }

    @Test
    void proWith3Score__shouldGet1Project() {
        ProApplication proApplication = new ProApplication(35, EducationLevelConstants.HIGH_SCHOOL,
                new ProApplicationExperience(false, false),
                new ProApplicationInternetTest(BigDecimal.valueOf(25.2), BigDecimal.valueOf(40.5)),
                BigDecimal.valueOf(0.5), "token1234");
        SelectedProjects result = new ProjectSelectorService().selectProject(proApplication);

        assertNotNull(result);
        assertEquals(3, result.getScore());
        assertEquals("collect_information_for_xpto", result.getSelectedProject());
        assertEquals(3, result.getIneligibleProjects().length);
        assertEquals(1, result.getEligibleProjects().length);
    }

}
