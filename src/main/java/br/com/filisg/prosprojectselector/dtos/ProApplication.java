package br.com.filisg.prosprojectselector.dtos;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.filisg.prosprojectselector.enums.EducationLevelEnum.EducationLevelConstants;
import br.com.filisg.prosprojectselector.utils.validator.AcceptedValuesValidator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProApplication {

    @JsonProperty("age")
    @NotNull(message = "Age must be informed")
    @Min(value = 0, message = "Age must be greater than 0")
    private Integer age;

    @JsonProperty("education_level")
    @AcceptedValuesValidator(acceptedValues = {
       EducationLevelConstants.NO_EDUCATION, 
       EducationLevelConstants.HIGH_SCHOOL, 
       EducationLevelConstants.BACHELORS_DEGREE_OR_HIGH
    }, message = "Education level must be informed and one of the following(no_education, high_school, bachelors_degree_or_high)")
    private String educationLevel;

    @JsonProperty("past_experiences")
    @Valid
    @NotNull(message = "Past experiences must be informed")
    private ProApplicationExperience pastExperiences;

    @JsonProperty("internet_test")
    @Valid
    @NotNull(message = "Internet test must be informed")
    private ProApplicationInternetTest internetTest;

    @JsonProperty("writing_score")
    @NotNull(message = "Writing score must be informed")
    @DecimalMin(value = "0", message = "Writing score must have a value between 0 and 1")
    @DecimalMax(value = "1", message = "Writing score must have a value between 0 and 1")
    private BigDecimal writingScore;

    @JsonProperty("referral_code")
    private String referralCode;

}