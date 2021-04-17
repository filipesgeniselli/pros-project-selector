package br.com.filisg.prosprojectselector.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SelectedProjects {

    @JsonProperty("score")
    private Integer score;

    @JsonProperty("selected_project")
    private String selectedProject;

    @JsonProperty("eligible_projects")
    private String[] eligibleProjects;
    
    @JsonProperty("ineligible_projects")
    private String[] ineligibleProjects;

}
