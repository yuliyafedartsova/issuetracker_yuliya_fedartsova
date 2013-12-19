package org.training.issuetracker.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.ifaces.AbstractController;
import org.training.issuetracker.model.beans.Issue;
import org.training.issuetracker.model.beans.User;

/**
 * Servlet implementation class AddProjectView
 */
public class AddProjectView extends AbstractController {
	private static final long serialVersionUID = 1L;
       
    
    public AddProjectView() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	List<User> users = (List<User>)request.getAttribute(Constants.USERS);
    	PrintWriter out = response.getWriter();
    	out.println("<html>");
		out.println("<head>");
		out.println("<title>Sample Servlet interface implementation</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("Add project:<br>");
		out.println("<form action=''>");
	 	out.println("Name: ");
	 	out.println("<input type='text' name='name' value=''>");
 		out.println("<br>");
 		out.println("Description: ");
	 	out.println("<input type='text' name='description' value=''>");
 		out.println("<br>");
 		out.println("Build: ");
	 	out.println("<input type='text' name='build' value=''>");
 		out.println("<br>");
 		out.println("Manager: ");
 		out.println("<select name='manager' size='1'>");
 		for(User user : users) {
 			out.println("<option value='" + user.getId() +  "'>" + 
 					user.getFirstName() + " " + user.getLastName() + "</option>");
 		}
 		out.println("</select>");
 		out.println("</form>");
 		out.println("</body>");
		out.println("</html>");
		out.close();
    }

}
