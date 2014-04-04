package org.training.issuetracker.controllers.spring;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.model.beans.properties.Role;
import org.training.issuetracker.services.PropertyService;
import org.training.issuetracker.services.RoleService;

@Controller
public class UserFormSpringController {
	
	@RequestMapping("/user-form-add")
	String addUser(ModelMap model) {
		
		List<Role> roles = new RoleService().getRoles();	
		model.addAttribute(Constants.ROLES, roles);
		
	
	return "add_user";
	}





}
