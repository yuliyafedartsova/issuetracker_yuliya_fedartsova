package org.training.issuetracker.controllers.spring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.training.issuetracker.constants.Constants;


@Controller
public class IssueReviewSpringController extends AbstractSpringController {

	@RequestMapping("/issue-review")
	public String reviewIssue(ModelMap model, @RequestParam("id") int id) {
		model.addAttribute(Constants.ISSUE, issueDao.getIssueById(id));
	    return "/review_issue";
	}

}
