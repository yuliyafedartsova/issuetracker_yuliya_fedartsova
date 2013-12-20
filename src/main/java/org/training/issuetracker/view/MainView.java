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
		request.setAttribute(Constants.WRITER, out);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(Constants.HEADER);
		rd.include(request, response);
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Main</title>");
		out.println("</head>");
		out.println("<body>");
		if(user != null) {
			out.println("<a href='submit-issue'>");
			out.println("Submit Issue");
			out.println("</a>");
			out.println("<br>");
		}
		if(user != null && user.getRole().equals(Constants.ADMINISTRATOR)) {
			out.println("<a href='project-controller?action=review'>" + "Projects" +"</a>");
			out.println("<a href='property-controller?property=" + 
					Constants.TYPE + "'" + ">"+ "Types" +"</a>");
			out.println("<a href='property-controller?property=" + 
					Constants.STATUS + "'" + ">"+ "Statuses" +"</a>");
			out.println("<a href='property-controller?property=" + 
					Constants.RESOLUTION + "'" + ">"+ "Resolutions" +"</a>");
			out.println("<a href='property-controller?property=" + 
					Constants.PRIORITY + "'" + ">"+ "Priorities" +"</a>");
			out.println("<br>");
			out.println("<a href='project-controller?action=add'>" + "Add project" +"</a>");
			out.println("<a href='user-controller?action=add'>" + "Add user" +"</a>");
		}
		out.println("<table>");
		out.println("<tr>" + "<td> Id </td>" + "<td> Priority </td>" + 
				"<td> Assignee </td>" + "<td> Type </td>" + "<td> Status </td>" + 
					"<td> Summary </td>");
		out.println("<br>");
		for(Issue is : issues) {
			out.println("<tr>");
			out.println("<td>" + "<a href='issue-controller?id=" + is.getId() + "'" + ">"+ is.getId() +"</a>" + "</td>");
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
