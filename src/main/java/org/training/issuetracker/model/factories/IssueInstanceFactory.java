package org.training.issuetracker.model.factories;

import java.sql.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.exceptions.ValidationException;
import org.training.issuetracker.model.DAO.IssueDAO;
import org.training.issuetracker.model.DAO.ProjectDAO;
import org.training.issuetracker.model.DAO.PropertyDAO;
import org.training.issuetracker.model.DAO.UserDAO;
import org.training.issuetracker.model.beans.Issue;
import org.training.issuetracker.model.beans.Project;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.model.beans.properties.Priority;
import org.training.issuetracker.model.beans.properties.Resolution;
import org.training.issuetracker.model.beans.properties.Status;
import org.training.issuetracker.model.beans.properties.Type;
import org.training.issuetracker.model.beans.properties.Version;
import org.training.issuetracker.utils.ValidationManager;

public class IssueInstanceFactory {
	public static Issue getIssue(HttpServletRequest request) throws DaoException, ValidationException {
		UserDAO userDAO = UserFactory.getClassFromFactory();
		PropertyDAO propertyDAO = PropertyFactory.getClassFromFactory();
		ProjectDAO projectDAO = ProjectFactory.getClassFromFactory();
		Issue issue;
		String summary = request.getParameter(Constants.SUMMARY).trim();
		String description = request.getParameter(Constants.DESCRIPTION).trim();
		int statusId = Integer.parseInt(request.getParameter(Constants.STATUS));
		int typeId = Integer.parseInt(request.getParameter(Constants.TYPE));
		int priorityId = Integer.parseInt(request.getParameter(Constants.PRIORITY));
		int projectId = Integer.parseInt(request.getParameter(Constants.PROJECT));
	    int versionId = Integer.parseInt(request.getParameter(Constants.VERSION));
	    Status status = propertyDAO.getStatusById(statusId);
	    Type type = propertyDAO.getTypeById(typeId);
	    Priority priority = propertyDAO.getPriorityById(priorityId);
	    Project project = projectDAO.getProjectById(projectId);
	    Version version = projectDAO.getVersionById(versionId);
	    HttpSession session = request.getSession();
	    User currentUser = (User)session.getAttribute(Constants.USER);
	    long curTime = System.currentTimeMillis(); 
		Date currentDate = new Date(curTime);
	    String idPar = request.getParameter(Constants.ID);
	    if(idPar != null) {
	    	int id = Integer.parseInt(idPar);
	    	IssueDAO issueDAO = IssueFactory.getClassFromFactory();
	        Issue originalIssue = issueDAO.getIssueById(id);
	        issue = new Issue(originalIssue.getId(), priority, type, summary, description, status, project, version, 
	        		originalIssue.getCreateDate(), originalIssue.getAuthor());
	        if(status.getName() != "New") {
	        	issue.setAssignee(originalIssue.getAssignee());
	        }
	        String resolutionIdPar = request.getParameter(Constants.RESOLUTION);
	        if(resolutionIdPar != null) {
	        	int resolutionId = Integer.parseInt(resolutionIdPar);
	        	Resolution resolution  = propertyDAO.getResolutionById(resolutionId);
	        	issue.setResolution(resolution);
	        }
	        issue.setModifyDate(currentDate);
	        issue.setModifier(currentUser);
	    } else {
	    	issue = new Issue(priority, type, summary, description, status, project, version, 
		    		currentDate, currentUser);
	    	String assigneeIdPar = request.getParameter(Constants.ASSIGNEE);
	    	if(!assigneeIdPar.isEmpty()) {
	    		User assignee = null;
	    		assignee = userDAO.getUserById(Integer.parseInt(assigneeIdPar));
	    		issue.setAssignee(assignee);
	    	}
	    }
	    ValidationManager.validateIssue(issue);
	    return issue;
	}
	
	public static Issue getReopenedIssue(HttpServletRequest request) throws DaoException, ValidationException {
		int id = Integer.parseInt(request.getParameter(Constants.ID));
		IssueDAO issueDAO = IssueFactory.getClassFromFactory();
	    Issue issue = issueDAO.getIssueById(id);
	    PropertyDAO propertyDAO = PropertyFactory.getClassFromFactory();
	    List<Status> statuses = propertyDAO.getStatuses();
	    Status status = null; 
	    for(Status st : statuses) {
	    	if(st.getName().equals(Constants.NEW)) {
	    		status = st;
	    	}
	    }
	    issue.setStatus(status);
	    issue.setAssignee(null);
	    issue.setResolution(null);
	    return issue;
	}

}
