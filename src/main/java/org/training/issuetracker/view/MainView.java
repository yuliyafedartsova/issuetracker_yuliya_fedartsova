package org.training.issuetracker.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.controllers.AbstractController;
import org.training.issuetracker.model.beans.Issue;
import org.training.issuetracker.model.beans.PropertyParameter;
import org.training.issuetracker.model.beans.User;


public class MainView extends AbstractController {
	private static final long serialVersionUID = 1L;
       
	public MainView() {
        super();
       
    }

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute(Constants.USER);
		List<Issue> issues = (List<Issue>)request.getAttribute(Constants.ISSUES);
		String message = (String)request.getAttribute(Constants.MESSAGE);
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
			out.println("<a href='projects'>" + "Projects" +"</a>");
			out.println("<a href='property?property=" + 
					Constants.TYPE + "'" + ">"+ "Types" +"</a>");
			out.println("<a href='property?property=" + 
					Constants.STATUS + "'" + ">"+ "Statuses" +"</a>");
			out.println("<a href='property?property=" + 
					Constants.RESOLUTION + "'" + ">"+ "Resolutions" +"</a>");
			out.println("<a href='property?property=" + 
					Constants.PRIORITY + "'" + ">"+ "Priorities" +"</a>");
			out.println("<br>");
			out.println("<a href='project?action=add'>" + "Add project" +"</a>");
			out.println("<a href='user?action=add'>" + "Add user" +"</a>");
		}
		if(message == null) {
		out.println("<form action='main'>");
		out.println("<select name='sorting' size='1'>");
		out.println("<option value=id>" + "Sort by id" + "</option>"); 
		out.println("<option value=type>" + "Sort by type"  + "</option>"); 
		out.println("<option value=priority>" + "Sort by priority" +  "</option>"); 
		out.println("<option value=assignee>" + "Sort by assignee" + "</option>"); 
		out.println("<option value=status>" + "Sort by status" + "</option>"); 
	 	out.println("</select>");
	 	out.println("<input type='submit' value='Sort'>");
	 	out.println("</form>");
	 	out.println("<br>");
		out.println("<table>");
		out.println("<tr>" + "<td> Id </td>" + "<td> Priority </td>" + 
				"<td> Assignee </td>" + "<td> Type </td>" + "<td> Status </td>" + 
					"<td> Summary </td>");
		out.println("<br>");
		for(Issue is : issues) {
			out.println("<tr>");
			out.println("<td>" + "<a href='issue?id=" + is.getId() + "'" + ">"+ is.getId() +"</a>" + "</td>");
				out.println("<td>" + is.getPriority().getName() + "</td>");
				if(is.getAssignee() != null) {
					out.println("<td>" + is.getAssignee().getFirstName() + " " + 
							is.getAssignee().getLastName() + "</td>");
				} else {
					out.println("<td>" + "</td>");
				}
				out.println("<td>" + is.getType().getName() + "</td>");
				out.println("<td>" + is.getStatus().getName() + "</td>");
				out.println("<td>" + is.getSummary() + "</td>");
				out.println("</tr>");
		}
		out.println("</table>");
		}else {
			out.println(message);
		}
		out.println("</body>");
		out.println("</html>");
		out.close();
	}
}
