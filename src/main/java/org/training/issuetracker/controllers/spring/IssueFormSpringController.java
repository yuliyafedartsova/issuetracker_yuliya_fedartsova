package org.training.issuetracker.controllers.spring;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.model.beans.Issue;
import org.training.issuetracker.model.beans.properties.Status;


@Controller
public class IssueFormSpringController extends AbstractSpringController {
    @RequestMapping("/form-issue-add")
	public String addIssue(ModelMap model) {
    	model.addAttribute(Constants.USERS, userDao.getUsers());
		model.addAttribute(Constants.PRIORITIES, priorityDao.getAll());
		model.addAttribute(Constants.STATUSES, getAvailableStatusesForSubmitIssue());
		model.addAttribute(Constants.TYPES, typeDao.getAll());
		model.addAttribute(Constants.PROJECTS, projectDao.getProjects());
		return "/add_issue";
	}
	
	@RequestMapping("/form-issue-update")
	public String updateIssue(ModelMap model, 
			@RequestParam("id") Issue issue) {
	    model.addAttribute(Constants.RESOLUTIONS, resolutionDao.getAll());
		model.addAttribute(Constants.ISSUE, issue);
		model.addAttribute(Constants.USERS, userDao.getUsers());
		model.addAttribute(Constants.PRIORITIES, priorityDao.getAll());
		model.addAttribute(Constants.STATUSES, getAvailableStatuses(issue));
		model.addAttribute(Constants.TYPES, typeDao.getAll());
		model.addAttribute(Constants.PROJECTS, projectDao.getProjects());
    	return "/update_issue";
	}
	
	private List<Status> getAvailableStatusesForSubmitIssue() {
    	List<Status> statuses = new ArrayList<Status>();
    	statuses.add(statusDao.getByName(Constants.NEW));
    	statuses.add(statusDao.getByName(Constants.ASSIGNED));
    	return statuses;
    }
	
	 @InitBinder
		public void initBinder(WebDataBinder binder) {
			binder.registerCustomEditor(Issue.class, issueBinder);
	    }
	    

	 private List<Status> getAvailableStatuses(Issue issue) {
		    List<Status> statuses = new ArrayList<Status>();
	        switch(issue.getStatus().getName()) {
	    		case Constants.NEW:
	    			statuses.add(issue.getStatus());
	    			statuses.add(statusDao.getByName(Constants.ASSIGNED));
	    			break;
	    		case Constants.ASSIGNED:
	    			statuses.add(issue.getStatus());
	    			statuses.add(statusDao.getByName(Constants.IN_PROGRESS));
	    			break;	
	    		case Constants.CLOSED:
	    			statuses.add(issue.getStatus());
	    			statuses.add(statusDao.getByName(Constants.REOPENED));
	    			break;
	    		case Constants.IN_PROGRESS:
	    			statuses.add(issue.getStatus());
	    			statuses.add(statusDao.getByName(Constants.CLOSED));
	    			statuses.add(statusDao.getByName(Constants.RESOLVED));
	    			break;
	    		case Constants.RESOLVED:
	    			statuses.add(statusDao.getByName(Constants.CLOSED));
	    			statuses.add(issue.getStatus());
	    			statuses.add(statusDao.getByName(Constants.REOPENED));
	    			break;
	    	
	    	}
	    	return statuses;
	    }



}
