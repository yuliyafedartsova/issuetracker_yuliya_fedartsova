package org.training.issuetracker.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.controllers.AbstractController;
import org.training.issuetracker.model.beans.PropertyParameter;
import org.training.issuetracker.model.beans.User;

/**
 * Servlet implementation class AddUserView
 */
public class AddUserView extends AbstractController {
	private static final long serialVersionUID = 1L;
       
    
    public AddUserView() {
        super();
        
    }

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<PropertyParameter> roles = (List<PropertyParameter>)request.getAttribute(Constants.ROLES);
		PrintWriter out = response.getWriter();
		request.setAttribute(Constants.WRITER, out);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/HeaderView");
		rd.include(request, response);
	 	out.println("<html>");
	    out.println("<head>");
	 	out.println("<title>Add user</title>");
	 	out.println("</head>");
	 	out.println("<body>");
	 	out.println("Add user:");
	 	out.println("<br>");
	 	out.println("<form action=''>");
	 	out.println("<table>");
	 	out.println("<tr>" + "<td>" + "First Name: " + "</td>");
	 	out.println("<td>" + "<input type='text' name='firstName' value=''>" + "</td>" + "</tr>");
 		out.println("<tr>" + "<td>" + "Last Name: " + "</td>");
	 	out.println("<td>" + "<input type='text' name='lastName' value=''>" + "</td>" + "</tr>");
 		out.println("<tr>" + "<td>" + "Email Address: " + "</td>");
	 	out.println("<td>" + "<input type='text' name='email' value=''>" + "</td>" + "</tr>");
 		out.println("<tr>" + "<td>" + "Role: " + "</td>");
 		out.println("<td>" + "<select name='role' size='1'>");
 		for(PropertyParameter role : roles) {
 			if(!role.getName().equals(Constants.GUEST)) {
 				out.println("<option value='" + role.getId() + "'>" + role.getName() 
 					+ "</option>");
 			}
 		}
 		out.println("</select>" + "</td>" + "</tr>");
 		out.println("<tr>" + "<td>" + "Password: " + "</td>");
	 	out.println("<td>" + "<input type='text' name='password' value=''>" + "</td>" + "</tr>");
 		out.println("<tr>" + "<td>" + "Confirm password: " + "</td>");
	 	out.println("<td>" +  "<input type='text' name='password2' value=''>" + "</td>" + "</tr>");
 		out.println("</table>");
 		out.println("</form>");
	 	out.println("</body>");
	 	out.println("</html>");
	 	out.close();
	}

}
