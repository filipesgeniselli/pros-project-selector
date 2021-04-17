package br.com.filisg.prosprojectselector.enums;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

public enum EducationLevelEnum {
    NO_EDUCATION(EducationLevelConstants.NO_EDUCATION, 0), 
    HIGH_SCHOOL(EducationLevelConstants.HIGH_SCHOOL, 1), 
    BACHELORS_DEGREE_OR_HIGH(EducationLevelConstants.BACHELORS_DEGREE_OR_HIGH, 2);

    static Map<String, EducationLevelEnum> valueMap;

    @Getter
    private final String value;
    
    @Getter
    private final Integer scoreValue;

    private EducationLevelEnum(String value, Integer scoreValue) {
        this.value = value;
        this.scoreValue = scoreValue;
    }

    static {
        valueMap = new HashMap<>();
        valueMap.put(EducationLevelEnum.NO_EDUCATION.getValue(), EducationLevelEnum.NO_EDUCATION);
        valueMap.put(EducationLevelEnum.HIGH_SCHOOL.getValue(), EducationLevelEnum.HIGH_SCHOOL);
        valueMap.put(EducationLevelEnum.BACHELORS_DEGREE_OR_HIGH.getValue(), EducationLevelEnum.BACHELORS_DEGREE_OR_HIGH);
    }

    public static EducationLevelEnum getFromValue(String value) {
        return valueMap.get(value);
    }

    public static class EducationLevelConstants {
        public static final String NO_EDUCATION = "no_education";
        public static final String HIGH_SCHOOL = "high_school";
        public static final String BACHELORS_DEGREE_OR_HIGH = "bachelors_degree_or_high";

        private EducationLevelConstants() {}
    }
}
