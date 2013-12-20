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
import org.training.issuetracker.ifaces.AbstractController;
import org.training.issuetracker.model.beans.Issue;
import org.training.issuetracker.model.beans.User;


public class AddProjectView extends AbstractController {
	private static final long serialVersionUID = 1L;
       
    
    public AddProjectView() {
        super();
    }

    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	List<User> users = (List<User>)request.getAttribute(Constants.USERS);
    	PrintWriter out = response.getWriter();
    	request.setAttribute(Constants.WRITER, out);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(Constants.HEADER);
		rd.include(request, response);
    	out.println("<html>");
		out.println("<head>");
		out.println("<title>Add project</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("Add project:<br>");
		out.println("<form action=''>");
		out.println("<table>");
		out.println("<tr>" + "<td>" + "Name: " + "</td>");
	 	out.println("<td>" + "<input type='text' name='name' value=''>" + "</td>" + "</tr>");
	 	out.println("<tr>" + "<td>" + "Description: " + "</td>");
	 	out.println("<td>" + "<input type='text' name='description' value=''>" + "</td>" + "</tr>");
 		out.println("<tr>" + "<td>" + "Build: " + "</td>");
	 	out.println("<td>" + "<input type='text' name='build' value=''>" + "</td>" + "</tr>");
 		out.println("<tr>" + "<td>" + "Manager: " + "</td>");
 		out.println("<td>" + "<select name='manager' size='1'>");
 		for(User user : users) {
 			out.println("<option value='" + user.getId() +  "'>" + 
 					user.getFirstName() + " " + user.getLastName() + "</option>");
 		}
 		out.println("</select>" + "</td>" + "</tr>");
 		out.println("</table>");
 		out.println("</form>");
 		out.println("</body>");
		out.println("</html>");
		out.close();
    }

}
