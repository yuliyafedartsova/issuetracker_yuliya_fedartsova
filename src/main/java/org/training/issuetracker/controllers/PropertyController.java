package org.training.issuetracker.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.training.issuetracker.ifaces.AbstractController;
import org.training.issuetracker.ifaces.IssuePropertyDAO;
import org.training.issuetracker.model.beans.PropertyParameter;
import org.training.issuetracker.model.factories.IssuePropertyFactory;


public class PropertyController extends AbstractController {
	private static final long serialVersionUID = 1L;
       
    
    public PropertyController() {
        super();
        
    }

    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String property = request.getParameter("property");
    	List<PropertyParameter> parametres;
    	IssuePropertyDAO issuePropertyDAO = IssuePropertyFactory.getClassFromFactory();
    	parametres = issuePropertyDAO.getPropertyParameters(property);
    	request.setAttribute("parametres", parametres);
    	RequestDispatcher rd = getServletContext().getRequestDispatcher("/ParametersView");
		rd.forward(request, response);
    }
}
