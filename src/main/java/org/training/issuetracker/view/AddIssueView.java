package org.training.issuetracker.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.training.issuetracker.enums.Role;
import org.training.issuetracker.model.beans.PropertyParameter;
import org.training.issuetracker.model.beans.User;

/**
 * Servlet implementation class AddIssueView
 */
public class AddIssueView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddIssueView() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<PropertyParameter> statuses = 
				(List<PropertyParameter>)request.getAttribute("statuses");
		List<PropertyParameter> priorities = 
				(List<PropertyParameter>)request.getAttribute("priorities");
		List<PropertyParameter> types = 
				(List<PropertyParameter>)request.getAttribute("types");
		List<User> users = (List<User>)request.getAttribute("users");
		PrintWriter out = response.getWriter();
	 	out.println("<html>");
	    out.println("<head>");
	 	out.println("<title>Sample Servlet interface implementation</title>");
	 	out.println("</head>");
	 	out.println("<body>");
	 	out.println("Add issue:");
	 	out.println("<br>");
	 	out.println("<form action=''>");
	 	out.println("Summary: ");
	 	out.println("<input type='text' name='summary' value=''>");
 		out.println("<br>");
 		out.println("Description: ");
 		out.println("<input type='text' name='description' value=''>");
 		out.println("<br>");
 		out.println("Status: ");
 		out.println("<select name='status' size='1'>");
 		for(PropertyParameter par : statuses) {
 			out.println("<option value='" + par.getId() +  "'>" + 
 					par.getName() + "</option>");
 		}
 		out.println("</select>");
 		out.println("<br>");
 		out.println("Type: ");
 		out.println("<select name='type' size='1'>");
 		for(PropertyParameter par : types) {
 			out.println("<option value='" + par.getId() +  "'>" + 
 					par.getName() + "</option>");
 		}
 		out.println("</select>");
 		out.println("<br>");
 		out.println("Priority: ");
 		out.println("<select name='priority' size='1'>");
 		for(PropertyParameter par : priorities) {
 			out.println("<option value='" + par.getId() +  "'>" + 
 					par.getName() + "</option>");
 		}
 		out.println("</select>");
 		out.println("<br>");
 		out.println("Assignee: ");
 		out.println("<select name='assignee' size='1'>");
 		for(User user : users) {
 			out.println("<option value='" + user.getId() +  "'>" + 
 					user.getFirstName() + " " + user.getLastName() + "</option>");
 		}
 		out.println("</select>");
 		out.println("<br>");
 		out.println("</form>");
	 	out.println("</body>");
	 	out.println("</html>");
	 	out.close();
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<PropertyParameter> statuses = 
				(List<PropertyParameter>)request.getAttribute("statuses");
		List<PropertyParameter> priorities = 
				(List<PropertyParameter>)request.getAttribute("priorities");
		List<PropertyParameter> types = 
				(List<PropertyParameter>)request.getAttribute("types");
		List<User> users = (List<User>)request.getAttribute("users");
		PrintWriter out = response.getWriter();
	 	out.println("<html>");
	    out.println("<head>");
	 	out.println("<title>Sample Servlet interface implementation</title>");
	 	out.println("</head>");
	 	out.println("<body>");
	 	out.println("<form action=''>");
	 	out.println("Summary: ");
	 	out.println("<input type='text' name='summary' value=''>");
 		out.println("<br>");
 		out.println("Description: ");
 		out.println("<input type='text' name='description' value=''>");
 		out.println("<br>");
 		out.println("Status: ");
 		out.println("<select name='status' size='1'>");
 		for(PropertyParameter par : statuses) {
 			out.println("<option value='" + par.getId() +  "'>" + 
 					par.getName() + "</option>");
 		}
 		out.println("</select>");
 		out.println("<br>");
 		out.println("Type: ");
 		out.println("<select name='type' size='1'>");
 		for(PropertyParameter par : types) {
 			out.println("<option value='" + par.getId() +  "'>" + 
 					par.getName() + "</option>");
 		}
 		out.println("</select>");
 		out.println("<br>");
 		out.println("Priority: ");
 		out.println("<select name='priority' size='1'>");
 		for(PropertyParameter par : priorities) {
 			out.println("<option value='" + par.getId() +  "'>" + 
 					par.getName() + "</option>");
 		}
 		out.println("</select>");
 		out.println("<br>");
 		out.println("Assignee: ");
 		out.println("<select name='assignee' size='1'>");
 		for(User user : users) {
 			out.println("<option value='" + user.getId() +  "'>" + 
 					user.getFirstName() + " " + user.getLastName() + "</option>");
 		}
 		out.println("</select>");
 		out.println("<br>");
 		out.println("</form>");
	 	out.println("</body>");
	 	out.println("</html>");
	 	out.close();
	}

}
