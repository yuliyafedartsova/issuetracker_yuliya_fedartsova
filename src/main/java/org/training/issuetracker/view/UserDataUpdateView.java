package org.training.issuetracker.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.controllers.AbstractController;
import org.training.issuetracker.model.beans.PropertyParameter;
import org.training.issuetracker.model.beans.User;


public class UserDataUpdateView extends AbstractController {
	private static final long serialVersionUID = 1L;
       
    
    public UserDataUpdateView() {
        super();
    }

    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute(Constants.USER); 
		List<PropertyParameter> roles = (List<PropertyParameter>)request.getAttribute(Constants.ROLES);
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Update user's date</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("Update your data:" + "<br>");
		out.println("<form name='update' method='POST' action=''>");
		out.println("<table>");
		out.println("<tr>" + "<td>" + "First Name:" + "</td>");
		out.println("<td>" + "<input type='text' name='firstName' value=''>" + "</td>" + "</tr>");
		out.println("<tr>" + "<td>" + "Last Name:" + "</td>");
		out.println("<td>" + "<input type='text' name='lastName' value=''>" + "</td>" + "</tr>");
		out.println("<tr>" + "<td>" + "Email Address:" + "</td>");
		out.println("<td>" + "<input type='text' name='email' value=''>" + "</td>" + "</tr>");
		if(user.getRole() == Constants.ADMINISTRATOR) {
			out.println("<tr>" + "<td>" + "Role:" + "</td>");
			out.println("<td>" + "<select name='role' size='1'>");
			for(PropertyParameter role : roles) {
					out.println("<option value='" + role.getId() + 
						"' selected >" + role.getName() + "</option>");
			}
			out.println("</select>" + "</td>" + "</tr>");
		}
		out.println("</table>");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
		out.close();
	}
}
