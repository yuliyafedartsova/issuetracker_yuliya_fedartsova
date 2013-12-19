package org.training.issuetracker.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.ifaces.AbstractController;
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
		out.println("<title>Sample Servlet interface implementation</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<form name='update' method='POST' action=''>");
		out.println("First Name <br>");
		out.println("<input type='text' name='firstName' value=''><br>");
		out.println("Last Name <br>");
		out.println("<input type='text' name='lastName' value=''><br>");
		out.println("Email Address <br>");
		out.println("<input type='text' name='email' value=''><br>");
		if(user.getRole() == Constants.ADMINISTRATOR) {
			out.println("Role <br>");
			out.println("<select name='role' size='1'>");
			for(PropertyParameter role : roles) {
					out.println("<option value='" + role.getId() + 
						"' selected >" + role.getName() + "</option>");
			}
		}
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
		out.close();
	
	}
	
}
