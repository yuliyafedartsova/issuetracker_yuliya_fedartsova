package org.training.issuetracker.controllers.spring;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.model.beans.Project;


@Controller
public class ProjectFormSpringController extends AbstractSpringController {

	@RequestMapping("/form-project-update/{Id}")
	public String updateProject(ModelMap model,
			@PathVariable("Id") Project project
			) {
		model.addAttribute(Constants.USERS, userDao.getUsers());
	    model.addAttribute(Constants.PROJECT, project);
		return "update_project";
    }
	
	@RequestMapping("/form-project-add")
	public String addProject(ModelMap model) {
		model.addAttribute(Constants.USERS, userDao.getUsers());
		return "add_project";
	}
	
	@InitBinder
		public void initBinder(WebDataBinder binder) {
			binder.registerCustomEditor(Project.class, projectBinder);
	    }



}


