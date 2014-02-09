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
		try{
		int statusId = Integer.parseInt(request.getParameter(Constants.STATUS));
		Status status = statusDAO.getById(statusId);
		if(status.getName().equals("Reopened")) {
			reopenIssue(issueDao, request, response);
			return;
		}
		String summary = request.getParameter(Constants.SUMMARY).trim();
		String description = request.getParameter(Constants.DESCRIPTION).trim();
		int typeId = Integer.parseInt(request.getParameter(Constants.TYPE));
		int priorityId = Integer.parseInt(request.getParameter(Constants.PRIORITY));
		int projectId = Integer.parseInt(request.getParameter(Constants.PROJECT));
	    int versionId = Integer.parseInt(request.getParameter(Constants.VERSION));
	    String assigneeIdPar = request.getParameter(Constants.ASSIGNEE);
	    Type type = typesDAO.getById(typeId);
	    Priority priority = priorityDAO.getById(priorityId);
	    Project project = projectDAO.getProjectById(projectId);
	    Version version = projectDAO.getVersionById(versionId);
	    HttpSession session = request.getSession();
	    User currentUser = (User)session.getAttribute(Constants.USER);
	    long curTime = System.currentTimeMillis(); 
		Date currentDate = new Date(curTime);
		User assignee = null;
		if(!assigneeIdPar.isEmpty()) {
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
  		  System.out.println("DaoException");
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
    		  System.out.println("DaoException");
     		   
   	    } 
	    request.setAttribute(Constants.MESSAGE, Constants.REOPEN);
	    request.getRequestDispatcher("/main").forward(request, response);
    }
}
