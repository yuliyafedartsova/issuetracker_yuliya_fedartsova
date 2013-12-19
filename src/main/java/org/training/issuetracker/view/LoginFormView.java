package org.training.issuetracker.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.ifaces.AbstractController;

/**
 * Servlet implementation class LoginFormView
 */
public class LoginFormView extends AbstractController {
	private static final long serialVersionUID = 1L;
       
    
    public LoginFormView() {
        super();
      
    }

	
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String errorMessage = (String)request.getAttribute(Constants.ERROR_MESSAGE);
		PrintWriter out = (PrintWriter)request.getAttribute(Constants.WRITER);
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Sample Servlet interface implementation</title>");
		out.println("</head>");
		out.println("<body>");
	    if(errorMessage != null) {
	    	out.println(errorMessage);
	    }
		out.println("<form name='loginForm' method='POST' action='LoginController'>");
		out.println("email:<br>");
		out.println("<input type='text' name='email' value=''><br>");
		out.println("password:<br>");
		out.println("<input type='password' name='password' value=''><br>");
		out.println("<input type='submit' value='Enter'>");
		out.println("<br></form>");
	    out.println("</body>");
		out.println("</html>");
	}

}
