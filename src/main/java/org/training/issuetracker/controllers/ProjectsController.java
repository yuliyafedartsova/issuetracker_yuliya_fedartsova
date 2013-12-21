package org.training.issuetracker.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.ifaces.AbstractController;
import org.training.issuetracker.ifaces.ProjectDAO;
import org.training.issuetracker.model.beans.Project;
import org.training.issuetracker.model.factories.ProjectFactory;


public class ProjectsController extends AbstractController {
	private static final long serialVersionUID = 1L;
       
   
    public ProjectsController() {
        super();
        
    }

    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	ProjectDAO projectDAO = ProjectFactory.getClassFromFactory();
    	List<Project> projects = null;
    	try {
    		projects = projectDAO.getProjects();
    	}catch (DaoException e) {
    		jumpPage(Constants.ERROR, request, response);
    		return;
    	}catch (Exception e) {
			jumpPage(Constants.ERROR, request, response);
			return;
		}
    	cutDescriptionsOff(projects);
		request.setAttribute(Constants.PROJECTS, projects);
		jumpPage(Constants.JUMP_PROJECTS, request, response);
    }
    
    protected void cutDescriptionsOff(List<Project> projects) {
    	for(Project project : projects) {
    		if(project.getDescription().length() > Constants.DESCRIPTION_LENTH_LIMIT){
    			String description = 
    					project.getDescription().substring(Constants.NULL, 
    							Constants.DESCRIPTION_LENTH_LIMIT);
    			description += Constants.THREE_DOTS;
    			project.setDescription(description);
    		}
    	}
    }
}
