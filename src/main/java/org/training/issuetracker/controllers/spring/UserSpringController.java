package org.training.issuetracker.controllers.spring;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.training.issuetracker.binders.ProjectBinder;
import org.training.issuetracker.binders.PropertyBinder;
import org.training.issuetracker.binders.UserBinder;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.model.beans.Project;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.model.beans.properties.Role;
import org.training.issuetracker.services.UserService;


@Controller
public class UserSpringController extends AbstractSpringController {
	
	@RequestMapping("/add-user")
	String addUser(ModelMap model, @ModelAttribute User user,
			@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName,
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			@RequestParam("role") Role role, 
			HttpSession session
			) {
	userDao.addUser(user);
	model.addAttribute(Constants.USER, session.getAttribute(Constants.USER));
	return "main";
	}

    @InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Role.class, new PropertyBinder(new Role()));
	}

}
