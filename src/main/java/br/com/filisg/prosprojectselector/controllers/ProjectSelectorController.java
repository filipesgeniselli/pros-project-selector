package br.com.filisg.prosprojectselector.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.filisg.prosprojectselector.dtos.ProApplication;
import br.com.filisg.prosprojectselector.dtos.SelectedProjects;
import br.com.filisg.prosprojectselector.services.ProjectSelectorService;

@RestController()
@RequestMapping("/projects")
public class ProjectSelectorController {

    @Autowired
    private ProjectSelectorService projectSelectorService;

    @PostMapping("/select")
    public ResponseEntity<SelectedProjects> selectProject (@Valid @RequestBody ProApplication proApplication) {
        return ResponseEntity.ok(projectSelectorService.selectProject(proApplication));
    }

}
