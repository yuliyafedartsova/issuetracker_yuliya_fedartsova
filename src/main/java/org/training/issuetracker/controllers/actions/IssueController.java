package org.training.issuetracker.controllers.actions;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.constants.Pages;
import org.training.issuetracker.controllers.AbstractController;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.exceptions.ValidationException;
import org.training.issuetracker.model.DAO.IssueDAO;
import org.training.issuetracker.model.DAO.PrioritiesDAO;
import org.training.issuetracker.model.DAO.ProjectDAO;
import org.training.issuetracker.model.DAO.ResolutionDAO;
import org.training.issuetracker.model.DAO.StatusesDAO;
import org.training.issuetracker.model.DAO.TypesDAO;
import org.training.issuetracker.model.DAO.UserDAO;
import org.training.issuetracker.model.beans.Issue;
import org.training.issuetracker.model.beans.Project;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.model.beans.properties.Priority;
import org.training.issuetracker.model.beans.properties.Resolution;
import org.training.issuetracker.model.beans.properties.Status;
import org.training.issuetracker.model.beans.properties.Type;
import org.training.issuetracker.model.beans.properties.Version;
import org.training.issuetracker.model.factories.IssueFactory;
import org.training.issuetracker.model.factories.PriorityFactory;
import org.training.issuetracker.model.factories.ProjectFactory;
import org.training.issuetracker.model.factories.ResolutionFactory;
import org.training.issuetracker.model.factories.StatusFactory;
import org.training.issuetracker.model.factories.TypeFactory;
import org.training.issuetracker.model.factories.UserFactory;
import org.training.issuetracker.utils.ValidationManagers.IssueValidator;


public class IssueController extends AbstractController {
	private static final long serialVersionUID = 1L;
       
    
    public IssueController() {
        super();
        
    }
    
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IssueDAO issueDao = IssueFactory.getClassFromFactory();
        Issue issue = null;
        String action = request.getParameter(Constants.ACTION);
        UserDAO userDAO = UserFactory.getClassFromFactory();
		StatusesDAO statusDAO = StatusFactory.getClassFromFactory();
		TypesDAO typesDAO = TypeFactory.getClassFromFactory();
		PrioritiesDAO priorityDAO = PriorityFactory.getClassFromFactory();
		ProjectDAO projectDAO = ProjectFactory.getClassFromFactory();
		IssueValidator validator = new IssueValidator();
		try{
		String statusIdPar = request.getParameter(Constants.STATUS);
		validator.validateIdParameters(statusIdPar);
		Status status = statusDAO.getById(Integer.parseInt(statusIdPar));
		if(status.getName().equals(Constants.REOPENED)) {
			reopenIssue(issueDao, request, response);
			return;
		}
		String summary = request.getParameter(Constants.SUMMARY).trim();
		String description = request.getParameter(Constants.DESCRIPTION).trim();
		String typeIdPar = request.getParameter(Constants.TYPE);
		String priorityIdPar = request.getParameter(Constants.PRIORITY);
		String projectIdPar = request.getParameter(Constants.PROJECT);
	    String versionIdPar = request.getParameter(Constants.VERSION);
	    String assigneeIdPar = request.getParameter(Constants.ASSIGNEE);
	    validator.validateIdParameters(typeIdPar, priorityIdPar, projectIdPar, 
	    		versionIdPar);
	    Type type = typesDAO.getById(Integer.parseInt(typeIdPar));
	    Priority priority = priorityDAO.getById(Integer.parseInt(priorityIdPar));
	    Project project = projectDAO.getProjectById(Integer.parseInt(projectIdPar));
	    Version version = projectDAO.getVersionById(Integer.parseInt(versionIdPar));
	    HttpSession session = request.getSession();
	    User currentUser = (User)session.getAttribute(Constants.USER);
	    long curTime = System.currentTimeMillis(); 
		Date currentDate = new Date(curTime);
		User assignee = null;
		if(!assigneeIdPar.isEmpty()) {
			validator.validateIdParameters(assigneeIdPar);
			assignee = userDAO.getUserById(Integer.parseInt(assigneeIdPar));
        }
		switch(action) {
    		case Constants.ADD:
    			issue = new Issue(priority, type, summary, description, status, project, version, 
    		    		currentDate, currentUser);
    			issue.setAssignee(assignee);
    			issueDao.addIssue(issue);
    			request.setAttribute(Constants.MESSAGE, Constants.SUCCESSFULLY_ADD_ISSUE);
    			break;
    		case Constants.UPDATE:
    			int id = Integer.parseInt(request.getParameter(Constants.ID));
    			Issue originalIssue = issueDao.getIssueById(id);
        		issue = new Issue(originalIssue.getId(), priority, type, summary, description, status, project, version, 
        		     originalIssue.getCreateDate(), originalIssue.getAuthor());
        		issue.setModifyDate(currentDate);
        	    issue.setModifier(currentUser);
        	    issue.setAssignee(assignee);
        	    String resolutionIdPar = request.getParameter(Constants.RESOLUTION);
        		if(!resolutionIdPar.isEmpty()) {
        			validator.validateIdParameters(assigneeIdPar);
        			int resolutionId = Integer.parseInt(resolutionIdPar);
        	        ResolutionDAO resolutionDAO = ResolutionFactory.getClassFromFactory();
        	        Resolution resolution  = resolutionDAO.getById(resolutionId);
        	        issue.setResolution(resolution);
        	    }
    			issueDao.updateIssue(issue);
    		    request.setAttribute(Constants.MESSAGE, Constants.SUCCESSFULLY_UPDATE_ISSUE);
    			break;
    	    }
		request.getRequestDispatcher("/main").forward(request, response);
        } catch (DaoException e) {
        	request.setAttribute(Constants.ERROR_MESSAGE, Constants.SOME_PROBLEMS);
        	jumpPage(Constants.MAIN, request, response);
  		} catch (ValidationException e) {
  			request.setAttribute(Constants.ERROR_MESSAGE, e.getMessage());
  			jumpPage(Constants.ISSUE_FORM_CONTROLLER, request, response);
    	} 
     }
    
    private void reopenIssue(IssueDAO issueDAO, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int id = Integer.parseInt(request.getParameter(Constants.ID));
    	try {
    	Issue issue = issueDAO.getIssueById(id);
	    StatusesDAO statusDAO = StatusFactory.getClassFromFactory();
	    Status status = statusDAO.getByName(Constants.NEW);
	    issue.setStatus(status);
	    issue.setAssignee(null);
	    issue.setResolution(null);
	    issueDAO.updateIssue(issue);
    	}catch (DaoException e) {
    		request.setAttribute(Constants.ERROR_MESSAGE, Constants.SOME_PROBLEMS);
        	jumpPage(Constants.MAIN, request, response); 
     	}catch (ValidationException e) {
     		request.setAttribute(Constants.ERROR_MESSAGE, e.getMessage());
  			jumpPage(Constants.ISSUE_FORM_CONTROLLER, request, response);
    	} 
	    request.setAttribute(Constants.MESSAGE, Constants.SUCCESSFULLY_REOPEN_ISSUE);
	    request.getRequestDispatcher(Constants.MAIN).forward(request, response);
    }
}
