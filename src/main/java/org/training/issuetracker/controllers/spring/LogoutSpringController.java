package org.training.issuetracker.controllers.spring;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.training.issuetracker.constants.Constants;


@Controller
public class LogoutSpringController {
	
	@RequestMapping("/logout")
	public String logout(ModelMap model, HttpServletRequest request) {
		request.getSession().invalidate();
		return Constants.MAIN;
	}
}
