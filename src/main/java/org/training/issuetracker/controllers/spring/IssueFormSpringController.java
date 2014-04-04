package org.training.issuetracker.controllers.spring;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.training.issuetracker.binders.IssueBinder;
import org.training.issuetracker.binders.ProjectBinder;
import org.training.issuetracker.binders.PropertyBinder;
import org.training.issuetracker.binders.UserBinder;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.exceptions.ValidationException;
import org.training.issuetracker.model.DAO.StatusesDAO;
import org.training.issuetracker.model.beans.Issue;
import org.training.issuetracker.model.beans.Project;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.model.beans.properties.Priority;
import org.training.issuetracker.model.beans.properties.Resolution;
import org.training.issuetracker.model.beans.properties.Status;
import org.training.issuetracker.model.beans.properties.Type;
import org.training.issuetracker.model.beans.properties.Version;
import org.training.issuetracker.model.factories.StatusFactory;
import org.training.issuetracker.services.PriorityService;
import org.training.issuetracker.services.ProjectService;
import org.training.issuetracker.services.ResolutionService;
import org.training.issuetracker.services.StatusService;
import org.training.issuetracker.services.TypeService;
import org.training.issuetracker.services.UserService;



@Controller
public class IssueFormSpringController {
    @RequestMapping("/form-issue-add")
	public String addIssue(ModelMap model) {
		List<Status> statuses = getAvailableStatusesForSubmitIssue();
		List<User> users = new UserService().getUsers();
		List<Priority> priorities = new PriorityService().getPriorities();
		List<Type> types = new TypeService().getTypes();
		List<Project> projects = new ProjectService().getProjects();
		model.addAttribute(Constants.USERS, users);
		model.addAttribute(Constants.PRIORITIES, priorities);
		model.addAttribute(Constants.STATUSES, statuses);
		model.addAttribute(Constants.TYPES, types);
		model.addAttribute(Constants.PROJECTS, projects);
		return "/add_issue";
	}
	
	@RequestMapping("/form-issue-update")
	public String updateIssue(ModelMap model, 
			@RequestParam("id") Issue issue) {
		List<Resolution> resolutions = new ResolutionService().getResolutions();
		List<User> users = new UserService().getUsers();
		List<Project> projects = new ProjectService().getProjects();
		List<Type> types = new TypeService().getTypes();
		List<Priority> priorities = new PriorityService().getPriorities();
		List<Status> statuses = getAvailableStatuses(issue);
		model.addAttribute(Constants.RESOLUTIONS, resolutions);
		model.addAttribute(Constants.ISSUE, issue);
		model.addAttribute(Constants.USERS, users);
		model.addAttribute(Constants.PRIORITIES, priorities);
		model.addAttribute(Constants.STATUSES, statuses);
		model.addAttribute(Constants.TYPES, types);
		model.addAttribute(Constants.PROJECTS, projects);
    	return "/update_issue";
	}
	
	private List<Status> getAvailableStatusesForSubmitIssue() {
    	List<Status> statuses = new ArrayList<Status>();
    	StatusService statusService = new StatusService();
    	statuses.add(statusService.getByName(Constants.NEW));
    	statuses.add(statusService.getByName(Constants.ASSIGNED));
    	return statuses;
    }
	
	 @InitBinder
		public void initBinder(WebDataBinder binder) {
			binder.registerCustomEditor(Issue.class, new IssueBinder());
	    }
	    

	 private List<Status> getAvailableStatuses(Issue issue) {
		    StatusService statusService = new StatusService();
	    	List<Status> statuses = new ArrayList<Status>();
	        switch(issue.getStatus().getName()) {
	    		case Constants.NEW:
	    			statuses.add(issue.getStatus());
	    			statuses.add(statusService.getByName(Constants.ASSIGNED));
	    			break;
	    		case Constants.ASSIGNED:
	    			statuses.add(issue.getStatus());
	    			statuses.add(statusService.getByName(Constants.IN_PROGRESS));
	    			break;	
	    		case Constants.CLOSED:
	    			statuses.add(issue.getStatus());
	    			statuses.add(statusService.getByName(Constants.REOPENED));
	    			break;
	    		case Constants.IN_PROGRESS:
	    			statuses.add(issue.getStatus());
	    			statuses.add(statusService.getByName(Constants.CLOSED));
	    			statuses.add(statusService.getByName(Constants.RESOLVED));
	    			break;
	    		case Constants.RESOLVED:
	    			statuses.add(statusService.getByName(Constants.CLOSED));
	    			statuses.add(issue.getStatus());
	    			statuses.add(statusService.getByName(Constants.REOPENED));
	    			break;
	    	
	    	}
	    	return statuses;
	    }



}
