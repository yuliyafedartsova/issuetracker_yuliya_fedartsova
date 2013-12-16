package org.training.issuetracker.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.training.issuetracker.enums.Role;
import org.training.issuetracker.model.beans.User;


public class UserDataUpdateView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public UserDataUpdateView() {
        super();
        
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute("user"); 
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
		if(user.getRole() == Role.ADMINISTRATOR) {
			out.println("Role <br>");
			out.println("<select name='role' size='1'>");
			for(int i = 0; i < Role.values().length; i++) {
					out.println("<option value='" + Role.values()[i] + 
						"' selected >" + Role.values()[i] + "</option>");
			}
		}
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
		out.close();
	
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
