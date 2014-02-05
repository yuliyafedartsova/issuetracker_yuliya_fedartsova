package org.training.issuetracker.model.factories;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.exceptions.ValidationException;
import org.training.issuetracker.model.DAO.ProjectDAO;
import org.training.issuetracker.model.DAO.PropertyDAO;
import org.training.issuetracker.model.DAO.UserDAO;
import org.training.issuetracker.model.beans.Issue;
import org.training.issuetracker.model.beans.Parameter;
import org.training.issuetracker.model.beans.Project;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.utils.ValidationManager;

public class IssueInstanceFactory {
	public static Issue getIssue(HttpServletRequest request) throws DaoException, ValidationException {
		UserDAO userDAO = UserFactory.getClassFromFactory();
		PropertyDAO propertyDAO = PropertyFactory.getClassFromFactory();
		ProjectDAO projectDAO = ProjectFactory.getClassFromFactory();
		String summary = request.getParameter(Constants.SUMMARY).trim();
		String description = request.getParameter(Constants.DESCRIPTION).trim();
		int statusId = Integer.parseInt(request.getParameter(Constants.STATUS));
		int typeId = Integer.parseInt(request.getParameter(Constants.TYPE));
		int priorityId = Integer.parseInt(request.getParameter(Constants.PRIORITY));
		int projectId = Integer.parseInt(request.getParameter(Constants.PROJECT));
	    int versionId = Integer.parseInt(request.getParameter(Constants.VERSION));
	    int authorId = Integer.parseInt(request.getParameter(Constants.AUTHOR));
	    Parameter status = propertyDAO.getStatusById(statusId);
	    Parameter type = propertyDAO.getTypeById(typeId);
	    Parameter priority = propertyDAO.getPriorityById(priorityId);
	    Project project = projectDAO.getProjectById(projectId);
	    Parameter version = projectDAO.getVersionById(versionId);
	    String assigneeIdPar = request.getParameter(Constants.ASSIGNEE);
	    User author =  userDAO.getUserById(authorId);
	    User assignee = null;
	    if(!assigneeIdPar.isEmpty()) {
	       assignee = userDAO.getUserById(Integer.parseInt(assigneeIdPar));
	    }
	    long curTime = System.currentTimeMillis(); 
		Date currentDate = new Date(curTime);
	    Issue issue = new Issue(priority, type, summary, description, status, project, version, 
	    		currentDate, author);
	    if(assignee != null) {
	    	issue.setAssignee(assignee);
	    }
	    ValidationManager.validateIssue(issue);
	    return issue;
	}

}
