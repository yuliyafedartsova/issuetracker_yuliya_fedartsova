package org.training.issuetracker.controllers.spring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.training.issuetracker.constants.Constants;


@Controller
public class UserFormSpringController extends AbstractSpringController {
	
	@RequestMapping("/user-form-add")
	String addUser(ModelMap model) {
		model.addAttribute(Constants.ROLES, roleDao.getAll());
	return "add_user";
	}

}
