package org.training.issuetracker.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.enums.Properties;
import org.training.issuetracker.enums.Role;
import org.training.issuetracker.ifaces.AbstractController;
import org.training.issuetracker.ifaces.IssueDAO;
import org.training.issuetracker.ifaces.UserDAO;
import org.training.issuetracker.model.beans.Issue;
import org.training.issuetracker.model.beans.PropertyParameter;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.model.factories.IssueFactory;
import org.training.issuetracker.model.factories.UserFactory;
import org.training.issuetracker.model.handlers.ParametrsHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class MainController extends AbstractController {
	private static final long serialVersionUID = 1L;
       
    
    public MainController() {
        super();
    }

    public void init() {
		String realPath = getServletContext().getRealPath( "/") +
				 "WEB-INF\\classes\\";
		
		Constants.REAL_PATH = realPath;
		System.out.println(realPath);
	
    }

    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	IssueDAO issuesDao = IssueFactory.getClassFromFactory();
    	List<Issue> issues = null;
    	User user = (User)request.getSession().getAttribute("user");
    	if(user != null) {
    		issues = issuesDao.getNAssignedIssues(10, user);
    	} else {
    		issues = issuesDao.getNLastAddedIssues(10);
    	}
    	request.setAttribute("issues", issues);
	    RequestDispatcher rd = getServletContext().getRequestDispatcher("/MainView");
		rd.forward(request, response);
	}
	
	
	
}
