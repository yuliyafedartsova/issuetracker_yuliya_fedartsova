package org.training.issuetracker.controllers.spring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.training.issuetracker.binders.ProjectBinder;
import org.training.issuetracker.binders.UserBinder;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.model.beans.Issue;
import org.training.issuetracker.model.beans.Project;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.model.beans.properties.Status;
import org.training.issuetracker.model.beans.properties.Version;
import org.training.issuetracker.services.ProjectService;
import org.training.issuetracker.services.VersionService;


@Controller
public class ProjectSpringController {

	@RequestMapping("/update-project/{Id}")
	public String updateProject(ModelMap model,
			@PathVariable("Id") Project project,
			@RequestParam("name") String name,
			@RequestParam("description") String description,
			@RequestParam("version") String versionName,
		   @RequestParam("managerId") User manager
	) {
		project.setName(name);
		project.setDescription(description);
		project.setManager(manager);
	    new ProjectService().update(project);
		return "main";
	}
	
	@RequestMapping("/add-project")
	public String addProject(ModelMap model, @ModelAttribute Project project,
			@RequestParam("name") String name,
			@RequestParam("description") String description,
			@RequestParam("version") String versionName,
		   @RequestParam("manager") User manager
	) {
		new ProjectService().save(project);
		new VersionService().save(new Version(versionName, project));
		return "main";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Project.class, new ProjectBinder());
		binder.registerCustomEditor(User.class, new UserBinder());
    }
	


}
