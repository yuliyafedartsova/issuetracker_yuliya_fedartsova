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
	 	out.println("<title>Sample Servlet interface implementation</title>");
	 	out.println("</head>");
	 	out.println("<body>");
	 	out.println("<form action=''>");
	 	out.println("First Name: ");
	 	out.println("<input type='text' name='firstName' value=''>");
 		out.println("<br>");
 		out.println("Last Name: ");
	 	out.println("<input type='text' name='lastName' value=''>");
 		out.println("<br>");
 		out.println("Email Address: ");
	 	out.println("<input type='text' name='email' value=''>");
 		out.println("<br>");
 		out.println("Role: ");
 		out.println("<select name='role' size='1'>");
 		for(PropertyParameter role : roles) {
 			if(role.getName() != Constants.GUEST) {
 				out.println("<option value='" + role.getId() + "'>" + role.getName() 
 					+ "</option>");
 			}
 		}
 		out.println("</select>");
 		out.println("<br>");
 		out.println("Password: ");
	 	out.println("<input type='text' name='password' value=''>");
 		out.println("<br>");
 		out.println("Confirm password: ");
	 	out.println("<input type='text' name='password2' value=''>");
 		out.println("<br>");
 		out.println("</form>");
	 	out.println("</body>");
	 	out.println("</html>");
	 	out.close();
	}

}
