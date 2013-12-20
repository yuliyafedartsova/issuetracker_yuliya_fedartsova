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
		out.println("<form name='loginForm' method='POST' action='login-controller'>");
		out.println("<table>");
		out.println("<tr>" + "<td>" + "email:" + "</td>");
		out.println("<td>" + "<input type='text' name='email' value=''>" + "</td>" + "</tr>");
		out.println("<tr>" + "<td>" + "password:" + "</td>");
		out.println("<td>" + "<input type='password' name='password' value=''>" + "</td>" + "</tr>");
		out.println("</table>");
		out.println("<br>");
		out.println("<input type='submit' value='Login'>");
		out.println("</form>");
	    out.println("</body>");
		out.println("</html>");
	}

}
