package org.training.issuetracker.controllers.spring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.model.impl.spring.SpringIssueImpl;



@Controller
public class MainSpringController extends AbstractSpringController {
	
	@RequestMapping("/")
	public String main(ModelMap model) {
		model.addAttribute(Constants.ISSUES, issueDao.getIssues());
		return "main";
	}
}
