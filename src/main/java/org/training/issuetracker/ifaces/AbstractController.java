package org.training.issuetracker.ifaces;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.training.issuetracker.constants.Constants;

/**
 * Servlet implementation class AbstractController
 */
public abstract class AbstractController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	performTask(request, response);
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		performTask(request, response);
    }
	
	abstract protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	protected void jumpError(String url, String message, HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute(Constants.ERROR_MESSAGE, message);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}
			
	protected void jumpPage(String url, HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}



}

	
