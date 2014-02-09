package org.training.issuetracker.controllers.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.constants.Pages;
import org.training.issuetracker.controllers.AbstractController;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.model.beans.Issue;
import org.training.issuetracker.model.beans.Project;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.model.beans.properties.Resolution;
import org.training.issuetracker.model.beans.properties.Version;
import org.training.issuetracker.model.factories.IssueFactory;
import org.training.issuetracker.model.factories.ProjectFactory;
import org.training.issuetracker.model.factories.ResolutionFactory;
import org.training.issuetracker.model.factories.UserFactory;
import org.training.issuetracker.model.DAO.IssueDAO;
import org.training.issuetracker.model.DAO.ProjectDAO;
import org.training.issuetracker.model.DAO.ResolutionDAO;
import org.training.issuetracker.model.DAO.UserDAO;
import org.training.issuetracker.utils.DisplayManager;


public class ProjectController extends AbstractController {
	private static final long serialVersionUID = 1L;
       
	public ProjectController() {
        super();
    }
    
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String action = request.getParameter(Constants.ACTION);
    	ProjectDAO projectDAO = ProjectFactory.getClassFromFactory();
    	UserDAO userDAO = UserFactory.getClassFromFactory();
    	String name = request.getParameter(Constants.NAME);
        String description = request.getParameter(Constants.DESCRIPTION);
        int managerId = Integer.parseInt(request.getParameter(Constants.MANAGER));   
        List<Version> versions = new ArrayList<Version>();
        Project project = null;
        try {
        	User manager = userDAO.getUserById(managerId);
        	String versionName = request.getParameter(Constants.VERSION).trim();
            switch(action) {
    		case Constants.ADD:
    			Version version = new Version(versionName);
    	        versions.add(version);
    	        project = new Project(name, manager, versions, description);
    	        projectDAO.addProject(project);
    	        request.setAttribute(Constants.MESSAGE, Constants.SUCCESSFULLY_ADD_PROJECT);
    	 	    request.getRequestDispatcher("/projects").forward(request, response);
    	        break;
    		case Constants.UPDATE:
    			int id = Integer.parseInt(request.getParameter(Constants.ID));
    			if(!versionName.isEmpty()) {
    				projectDAO.addVersion(versionName, id);
                }
            	versions = projectDAO.getVersionsOfProject(id);
            	project = new Project(id, name, manager, versions, description);
            	projectDAO.updateProject(project);
            	request.setAttribute(Constants.MESSAGE, Constants.SUCCESSFULLY_UPDATE_PROJECT);
         	    request.getRequestDispatcher("/projects").forward(request, response);
    			break;
    	    }
        
        
        
        }catch (DaoException e) {
			jumpPage(Pages.ERROR_PAGE, request, response);
			return;
		}
    
    
    }
}