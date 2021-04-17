package br.com.filisg.prosprojectselector.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class EducationLevelEnumTests {
    
    @Test
    void noEducation__hasCorrectValues() {
        assertEquals("no_education", EducationLevelEnum.NO_EDUCATION.getValue());
        assertEquals(0, EducationLevelEnum.NO_EDUCATION.getScoreValue());
    }

    @Test
    void highSchool__hasCorrectValues() {
        assertEquals("high_school", EducationLevelEnum.HIGH_SCHOOL.getValue());
        assertEquals(1, EducationLevelEnum.HIGH_SCHOOL.getScoreValue());
    }

    @Test
    void bachelors__hasCorrectValues() {
        assertEquals("bachelors_degree_or_high", EducationLevelEnum.BACHELORS_DEGREE_OR_HIGH.getValue());
        assertEquals(2, EducationLevelEnum.BACHELORS_DEGREE_OR_HIGH.getScoreValue());
    }

    @Test
    void getFromValues__returnsCorrectValues() {
        assertEquals("no_education", EducationLevelEnum.getFromValue("no_education").getValue());
        assertEquals("high_school", EducationLevelEnum.getFromValue("high_school").getValue());
        assertEquals("bachelors_degree_or_high", EducationLevelEnum.getFromValue("bachelors_degree_or_high").getValue());
    }

}
