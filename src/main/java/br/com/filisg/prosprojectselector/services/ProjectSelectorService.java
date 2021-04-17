package br.com.filisg.prosprojectselector.services;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.filisg.prosprojectselector.dtos.ProApplication;
import br.com.filisg.prosprojectselector.dtos.ProApplicationExperience;
import br.com.filisg.prosprojectselector.dtos.SelectedProjects;
import br.com.filisg.prosprojectselector.entities.Projects;
import br.com.filisg.prosprojectselector.enums.EducationLevelEnum;

@Service
public class ProjectSelectorService {

    public SelectedProjects selectProject(ProApplication proApplication) {

        int score = calculateScore(proApplication);
        List<Projects> projects = loadProjects();

        String[] ineligibleProjects = projects.stream().filter(project -> score <= project.getMinScore())
                .map(Projects::getProjectCode).toArray(String[]::new);

        List<Projects> listEligibleProjects = projects.stream().filter(project -> score > project.getMinScore())
                .collect(Collectors.toList());
        String[] elegibleProjects = listEligibleProjects.stream().map(Projects::getProjectCode).toArray(String[]::new);
        String selectedProject = listEligibleProjects.stream().max(Comparator.comparing(Projects::getMinScore))
                .orElse(new Projects(null, -1)).getProjectCode();

        return new SelectedProjects(Integer.valueOf(score), selectedProject, elegibleProjects, ineligibleProjects);
    }

    protected int calculateScore(ProApplication proApplication) {
        if (proApplication.getAge() < 18) {
            return -1;
        }

        int score = 0;
        score += calculateScoreEducationLevel(proApplication.getEducationLevel());
        score += calculateScorePastExperiences(proApplication.getPastExperiences());
        score += calculateScoreInternetSpeed(proApplication.getInternetTest().getDownloadSpeed());
        score += calculateScoreInternetSpeed(proApplication.getInternetTest().getUploadSpeed());
        score += calculateScoreWritingTest(proApplication.getWritingScore());
        score += checkReferralCode(proApplication.getReferralCode());

        return score;
    }

    protected int calculateScoreEducationLevel(String educationLevel) {
        return EducationLevelEnum.getFromValue(educationLevel).getScoreValue();
    }

    protected int calculateScorePastExperiences(ProApplicationExperience pastExperiences) {
        if (Boolean.TRUE.equals(pastExperiences.getSales())) {
            return 5;
        } else if (Boolean.TRUE.equals(pastExperiences.getSupport())) {
            return 3;
        } else {
            return 0;
        }
    }

    protected int calculateScoreInternetSpeed(BigDecimal internetSpeed) {
        if (internetSpeed.compareTo(new BigDecimal(50)) > 0) {
            return 1;
        } else if (internetSpeed.compareTo(new BigDecimal(5)) < 0) {
            return -1;
        } else {
            return 0;
        }
    }

    protected int calculateScoreWritingTest(BigDecimal writingScore) {
        if (writingScore.compareTo(BigDecimal.valueOf(0.7)) > 0) {
            return 2;
        } else if (writingScore.compareTo(BigDecimal.valueOf(0.3)) >= 0
                && writingScore.compareTo(BigDecimal.valueOf(0.7)) <= 0) {
            return 1;
        } else {
            return -1;
        }
    }

    protected int checkReferralCode(String referralCode) {
        if ("token1234".equals(referralCode)) {
            return 1;
        } else {
            return 0;
        }
    }

    protected List<Projects> loadProjects() {
        return Arrays.asList(
            new Projects("calculate_dark_matter_nasa", 10),
            new Projects("determine_schrodinger_cat_is_alive", 5),
            new Projects("support_users_from_xyz", 3),
            new Projects("collect_information_for_xpto", 2)
        );
    }

}
