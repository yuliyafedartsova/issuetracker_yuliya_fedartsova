package org.training.issuetracker.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.training.issuetracker.enums.Role;
import org.training.issuetracker.model.beans.User;

/**
 * Servlet implementation class AddUserView
 */
public class AddUserView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AddUserView() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
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
 		for(int i = 0; i < Role.values().length; i++) {
 			if(Role.values()[i] != Role.GUEST) {
 				out.println("<option value='" + Role.values()[i] + "'>" + Role.values()[i] 
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

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
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
 		for(int i = 0; i < Role.values().length; i++) {
 			if(Role.values()[i] != Role.GUEST) {
 				out.println("<option value='" + Role.values()[i] + "'>" + Role.values()[i] 
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
