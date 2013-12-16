package org.training.issuetracker.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.training.issuetracker.enums.Properties;
import org.training.issuetracker.enums.Role;
import org.training.issuetracker.model.beans.Issue;
import org.training.issuetracker.model.beans.User;

/**
 * Servlet implementation class MainView
 */
public class MainView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public MainView() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute("user");
		List<Issue> issues = (List<Issue>)request.getAttribute("issues");
		PrintWriter out = response.getWriter();
		request.setAttribute("writer", out);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/HeaderView");
		rd.include(request, response);
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Sample Servlet interface implementation</title>");
		out.println("</head>");
		out.println("<body>");
		if(user != null) {
			out.println("<a href='AddIssueController'>");
			out.println("Submit Issue");
			out.println("</a>");
			out.println("<br>");
		}
		if(user != null && user.getRole() == Role.ADMINISTRATOR) {
			out.println("<a href='ProjectController'>" + "Projects" +"</a>");
			for(int i = 0; i < Properties.values().length; i++) {
				out.println("<a href='PropertyController?property=" + 
						Properties.values()[i] + "'" + ">"+ 
							Properties.values()[i].toString().toLowerCase() +"</a>");
			}
			
			out.println("<br>");
			out.println("<a href='AddUserView'>" + "Add user" +"</a>");
			
		}
		out.println("<table>");
		out.println("<tr>" + "<td> Id </td>" + "<td> Priority </td>" + 
				"<td> Assignee </td>" + "<td> Type </td>" + "<td> Status </td>" + 
					"<td> Summary </td>");
		out.println("<br>");
		
		for(Issue is : issues) {
			out.println("<tr>");
			out.println("<td>" + "<a href='IssueController?id=" + is.getId() + "'" + ">"+ is.getId() +"</a>" + "</td>");
				out.println("<td>" + is.getPriority() + "</td>");
				if(is.getAssignee() != null) {
					out.println("<td>" + is.getAssignee().getFirstName() + " " + 
							is.getAssignee().getLastName() + "</td>");
				} else {
					out.println("<td>" + "</td>");
				}
				out.println("<td>" + is.getType() + "</td>");
				out.println("<td>" + is.getStatus() + "</td>");
				out.println("<td>" + is.getSummary() + "</td>");
				out.println("</tr>");
		}
		out.println("</table>");
		out.println("</body>");
		out.println("</html>");
		out.close();
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute("user");
		List<Issue> issues = (List<Issue>)request.getAttribute("issues");
		PrintWriter out = response.getWriter();
		request.setAttribute("writer", out);
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/HeaderView");
		rd.include(request, response);
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Sample Servlet interface implementation</title>");
		out.println("</head>");
		out.println("<body>");
		if(user != null) {
			out.println("<a href='AddIssueController'>");
			out.println("Submit Issue");
			out.println("</a>");
			out.println("<br>");
		}
		if(user != null && user.getRole() == Role.ADMINISTRATOR) {
			out.println("<a href='ProjectController'>" + "Projects" +"</a>");
			for(int i = 0; i < Properties.values().length; i++) {
				out.println("<a href='PropertyController?property=" + 
						Properties.values()[i] + "'" + ">"+ 
							Properties.values()[i].toString().toLowerCase() +"</a>");
			}
			out.println("<br>");
			out.println("<a href='AddUserView'>" + "Add user" +"</a>");
		}
		out.println("<table>");
		out.println("<tr>" + "<td> Id </td>" + "<td> Priority </td>" + 
				"<td> Assignee </td>" + "<td> Type </td>" + "<td> Status </td>" + 
					"<td> Summary </td>");
		out.println("<br>");
		for(Issue is : issues) {
			out.println("<tr>");
			out.println("<td>" + "<a href='IssueController?id=" + is.getId() + "'" + ">"+ is.getId() +"</a>" + "</td>");
				out.println("<td>" + is.getPriority() + "</td>");
				if(is.getAssignee() != null) {
					out.println("<td>" + is.getAssignee().getFirstName() + " " + 
							is.getAssignee().getLastName() + "</td>");
				} else {
					out.println("<td>" + "</td>");
				}
				out.println("<td>" + is.getType() + "</td>");
				out.println("<td>" + is.getStatus() + "</td>");
				out.println("<td>" + is.getSummary() + "</td>");
				out.println("</tr>");
		}
		out.println("</table>");
		out.println("</body>");
		out.println("</html>");
		out.close();
	}

}
