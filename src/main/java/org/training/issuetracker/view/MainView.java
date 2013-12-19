package org.training.issuetracker.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.ifaces.AbstractController;
import org.training.issuetracker.model.beans.Issue;
import org.training.issuetracker.model.beans.User;


public class MainView extends AbstractController {
	private static final long serialVersionUID = 1L;
       
   
    public MainView() {
        super();
       
    }

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute(Constants.USER);
		List<Issue> issues = (List<Issue>)request.getAttribute(Constants.ISSUES);
		PrintWriter out = response.getWriter();
		String action = "review";
		request.setAttribute(Constants.WRITER, out);
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
			action = "update";
		}
		if(user != null && user.getRole() == Constants.ADMINISTRATOR) {
			out.println("<a href='ProjectController?action=show'>" + "Projects" +"</a>");
			out.println("<a href='PropertyController?property=" + 
					Constants.TYPES + "'" + ">"+ "Types" +"</a>");
			out.println("<a href='PropertyController?property=" + 
					Constants.STATUSES + "'" + ">"+ "Statuses" +"</a>");
			out.println("<a href='PropertyController?property=" + 
					Constants.RESOLUTIONS + "'" + ">"+ "Resolutions" +"</a>");
			out.println("<a href='PropertyController?property=" + 
					Constants.PRIORITIES + "'" + ">"+ "Priorities" +"</a>");
			out.println("<br>");
			out.println("<a href='ProjectController?action=add'>" + "Add project" +"</a>");
			
			out.println("<a href='UserController?action=add'>" + "Add user" +"</a>");
			
		}
		out.println("<table>");
		out.println("<tr>" + "<td> Id </td>" + "<td> Priority </td>" + 
				"<td> Assignee </td>" + "<td> Type </td>" + "<td> Status </td>" + 
					"<td> Summary </td>");
		out.println("<br>");
		
		for(Issue is : issues) {
			out.println("<tr>");
			out.println("<td>" + "<a href='IssueController?action=" + action + "&id=" + is.getId() + "'" + ">"+ is.getId() +"</a>" + "</td>");
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
