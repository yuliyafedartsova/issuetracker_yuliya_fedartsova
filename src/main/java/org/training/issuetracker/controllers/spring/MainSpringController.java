package org.training.issuetracker.controllers.spring;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.training.issuetracker.constants.Constants;




@Controller
public class MainSpringController extends AbstractSpringController {
	
	@RequestMapping("/")
	public String main(ModelMap model) {
		model.addAttribute(Constants.ISSUES, issueDao.getIssues());
		return "main";
	}
}
