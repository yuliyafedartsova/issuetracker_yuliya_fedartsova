package org.training.issuetracker.controllers.spring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.training.issuetracker.model.beans.Project;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.model.beans.properties.Version;

@Controller
public class ProjectSpringController extends AbstractSpringController {

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
	    projectDao.updateProject(project);
		if(!versionName.isEmpty() && versionName != null) {
			projectDao.addVersion(new Version(versionName, project));
		}
	    return "main";
	}
	
	@RequestMapping("/add-project")
	public String addProject(ModelMap model, @ModelAttribute Project project,
			@RequestParam("name") String name,
			@RequestParam("description") String description,
			@RequestParam("version") String versionName,
		   @RequestParam("manager") User manager
	) {
		projectDao.addProject(project);
		projectDao.addVersion(new Version(versionName, project));
		return "main";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Project.class, projectBinder);
		binder.registerCustomEditor(User.class, userBinder);
    }
}
