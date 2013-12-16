package org.training.issuetracker.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.training.issuetracker.ifaces.AbstractController;
import org.training.issuetracker.ifaces.IssueDAO;
import org.training.issuetracker.ifaces.IssuePropertyDAO;
import org.training.issuetracker.ifaces.ProjectDAO;
import org.training.issuetracker.ifaces.UserDAO;
import org.training.issuetracker.model.beans.Issue;
import org.training.issuetracker.model.beans.Project;
import org.training.issuetracker.model.beans.PropertyParameter;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.model.factories.IssueFactory;
import org.training.issuetracker.model.factories.IssuePropertyFactory;
import org.training.issuetracker.model.factories.ProjectFactory;
import org.training.issuetracker.model.factories.UserFactory;

/**
 * Servlet implementation class IssueController
 */
public class IssueController extends AbstractController {
	private static final long serialVersionUID = 1L;
       
    
    public IssueController() {
        super();
        
    }

    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int id = Integer.parseInt(request.getParameter("id"));
  	   	Issue issue = null;
  	   	IssueDAO issuesDao = IssueFactory.getClassFromFactory();
  	   	issue = issuesDao.getIssueById(id);
  	   	request.setAttribute("issue", issue);
  	   	HttpSession session = request.getSession();
  	   	User user = (User) session.getAttribute("user");
  	   	ProjectDAO projectDAO = ProjectFactory.getClassFromFactory();
  	   	
  	   	if(user != null) {
  	   		UserDAO userDAO = UserFactory.getClassFromFactory();
  	   		List<User> users = userDAO.getUsers();
  	   		request.setAttribute("users", users);
  	   		IssuePropertyDAO propertyDAO = IssuePropertyFactory.getClassFromFactory();
  	   		Map <String, List <PropertyParameter>> map = propertyDAO.getPropertiesMap();
  	   		request.setAttribute("propertiesMap", map);
  	   		List<Project> projects = projectDAO.getProjects();
  	   		request.setAttribute("projects", projects);
  	   		RequestDispatcher rd = getServletContext().getRequestDispatcher("/IssueUpdateView");
  	   		rd.forward(request, response);
  	   	} else {
  	   		RequestDispatcher rd = getServletContext().getRequestDispatcher("/IssueReviewView");
	   		rd.forward(request, response);
  	   	}
    }
    
 }
