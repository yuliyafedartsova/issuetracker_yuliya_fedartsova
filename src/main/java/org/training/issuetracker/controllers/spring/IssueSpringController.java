package org.training.issuetracker.controllers.spring;


import java.sql.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.model.beans.Issue;
import org.training.issuetracker.model.beans.Project;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.model.beans.properties.Priority;
import org.training.issuetracker.model.beans.properties.Resolution;
import org.training.issuetracker.model.beans.properties.Status;
import org.training.issuetracker.model.beans.properties.Type;
import org.training.issuetracker.model.beans.properties.Version;



@Controller
public class IssueSpringController extends AbstractSpringController {

	@RequestMapping("/add-issue")
	public String addIssue(ModelMap model, @ModelAttribute Issue issue,
			@RequestParam("statusId") Status status,
			@RequestParam("typeId") Type type,
			@RequestParam("priorityId") Priority priority,
			@RequestParam("assigneeId") User assignee,
			@RequestParam("projectId") Project project,
			@RequestParam("versionId") Version version,
			HttpServletRequest request
	) {
		HttpSession session = request.getSession();
	    User currentUser = (User)session.getAttribute(Constants.USER);
	    Date currentDate = new Date(System.currentTimeMillis());
		issue.setAssignee(assignee);
		issue.setAuthor(currentUser);
		issue.setBuildFound(version);
		issue.setCreateDate(currentDate);
		issue.setPriority(priority);
		issue.setProject(project);
		issue.setStatus(status);
		issue.setType(type);
		issueDao.addIssue(issue);
		request.setAttribute(Constants.MESSAGE, Constants.SUCCESSFULLY_ADD_ISSUE);
	    return "forward:/";
	}
	
	@RequestMapping("/reopen-issue/{Id}")
	public String reopenIssue(ModelMap model, @PathVariable("Id") Issue issue,
			@RequestParam("statusId") Status status
	) {
		if(status.getName().equals(Constants.REOPENED)) {
			issue.setStatus(statusDao.getByName(Constants.NEW));
		    issue.setAssignee(null);
		    issue.setResolution(null);
		    issueDao.updateIssue(issue);
		    model.addAttribute(Constants.MESSAGE, Constants.SUCCESSFULLY_REOPEN_ISSUE);
		}
		return "forward:/";
	}

    
    
    @RequestMapping("/update-issue/{Id}")
    public String updateIssue(@PathVariable("Id") Issue issue,
    		@RequestParam("statusId") Status status,
			@RequestParam("typeId") Type type,
			@RequestParam("priorityId") Priority priority,
			@RequestParam("assigneeId") User assignee,
			@RequestParam("projectId") Project project,
			@RequestParam("versionId") Version version,
			@RequestParam("resolutionId") Resolution resolution,
			@RequestParam("description") String description,
			@RequestParam("summary") String summary,
			HttpServletRequest request,
			ModelMap model
    ) {
    	HttpSession session = request.getSession();
	    User currentUser = (User)session.getAttribute(Constants.USER);
	    Date currentDate = new Date(System.currentTimeMillis());
	    issue.setAssignee(assignee);
	    issue.setModifier(currentUser);
	    issue.setModifyDate(currentDate);
	    issue.setBuildFound(version);
	    issue.setDescription(description);
	    issue.setPriority(priority);
	    issue.setProject(project);
	    issue.setResolution(resolution);
	    issue.setStatus(status);
	    issue.setSummary(summary);
	    issue.setType(type);
	    issueDao.updateIssue(issue);
	    model.addAttribute(Constants.MESSAGE, Constants.SUCCESSFULLY_UPDATE_ISSUE);
    	return "forward:/";
    }
    
     @InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Status.class, statusBinder);
		binder.registerCustomEditor(Type.class, typeBinder);
		binder.registerCustomEditor(Priority.class, priorityBinder);
		binder.registerCustomEditor(User.class, userBinder);
		binder.registerCustomEditor(Project.class, projectBinder);
		binder.registerCustomEditor(Version.class, versionBinder);
		binder.registerCustomEditor(Resolution.class, resolutionBinder);
		binder.registerCustomEditor(Issue.class, issueBinder);
    
    }
     
  }
