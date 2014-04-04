package org.training.issuetracker.controllers.spring;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.constants.Pages;
import org.training.issuetracker.model.beans.Project;
import org.training.issuetracker.services.ProjectService;

@Controller
public class ProjectsReviewSpringController {

	@RequestMapping("/review-projects")
	public String projectsReview(ModelMap model, 
			HttpServletRequest request) {
		List<Project> projects = new ProjectService().getProjects();
		cutDescriptionsOff(projects);
		model.addAttribute(Constants.PROJECTS, projects);
		return "projects";
	}


	private void cutDescriptionsOff(List<Project> projects) {
    	for(Project project : projects) {
    		if(project.getDescription().length() > Constants.DESCRIPTION_LENTH_LIMIT){
    			String description = 
    					project.getDescription().substring(Constants.NULL, 
    							Constants.DESCRIPTION_LENTH_LIMIT);
    			description += Constants.THREE_DOTS;
    			project.setDescription(description);
    		}
    	}
    }

}
