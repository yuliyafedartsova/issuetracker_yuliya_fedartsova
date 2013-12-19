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
import org.training.issuetracker.model.beans.Issue;
import org.training.issuetracker.model.beans.Project;
import org.training.issuetracker.model.beans.PropertyParameter;
import org.training.issuetracker.model.beans.User;


/**
 * Servlet implementation class IssueUpdateView
 */
public class IssueUpdateView extends AbstractController {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IssueUpdateView() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 	   Issue issue = (Issue) request.getAttribute(Constants.ISSUE);
 	   List<User> users = (List<User>)request.getAttribute(Constants.USERS);
 	   List<Project> projects = (List<Project>)request.getAttribute(Constants.PROJECTS);
 	   List<PropertyParameter> statuses = 
				(List<PropertyParameter>)request.getAttribute(Constants.STATUSES);
 	   List<PropertyParameter> priorities = 
				(List<PropertyParameter>)request.getAttribute(Constants.PRIORITIES);
 	   List<PropertyParameter> types = 
				(List<PropertyParameter>)request.getAttribute(Constants.TYPES);
 	   List<PropertyParameter> resolutions = 
				(List<PropertyParameter>)request.getAttribute(Constants.RESOLUTIONS);
 	   PrintWriter out = response.getWriter();
 	   request.setAttribute(Constants.WRITER, out);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/HeaderView");
		rd.include(request, response);
		out.println("<html>");
 		out.println("<head>");
 		out.println("<title>Sample Servlet interface implementation</title>");
 		out.println("</head>");
 		out.println("<body>");
 		
 		out.println("Id " + issue.getId() + "<br>");
 		out.println("Create Date " + issue.getCreateDate() + "<br>");
 		out.println("Created By " + issue.getAuthor().getFirstName() + " "
 				+ issue.getAuthor().getLastName() + "<br>");
 		if(issue.getModifyDate() != null) {
 			out.println("Modify Date " + issue.getModifyDate() + "<br>");
 		}
 		if(issue.getModifier() != null) {
 			out.println("Modified By " + issue.getModifier().getFirstName() + " " 
 		      + issue.getModifier().getLastName() + "<br>");
 		}
 		out.println("<form action=''>");
 		out.println("Summary");
 		out.println("<input type='text' name='summary' value=" + issue.getSummary() + ">");
 		out.println("<br>");
 		out.println("Description");
 		out.println("<input type='text' name='description' value=" + issue.getDescription() + ">");
 		out.println("<br>");
 		out.println("Status");
 		out.println("<select name='status' size='1'>");
 		for(PropertyParameter par : statuses) {
 			out.println("<option value='" + par.getId() + "'>" + par.getName() + 
 					"</option>");
 		}
 		out.println("</select>");
 		out.println("<br>");
 		out.println("Resolution");
 		out.println("<select name='resolution' size='1'>");
 		for(PropertyParameter par : resolutions) {
 			out.println("<option value='" + par.getId() + "'>" + par.getName() + 
 					"</option>");
 		}
 		out.println("</select>");
 		out.println("<br>");
 		out.println("Type");
 		out.println("<select name='type' size='1'>");
 		for(PropertyParameter par : types) {
 			out.println("<option value='" + par.getId() + "'>" + par.getName() + 
 					"</option>");
 		}
 		out.println("</select>");
 		out.println("<br>");
 		out.println("Priority");
 		out.println("<select name='priority' size='1'>");
 		for(PropertyParameter par : priorities) {
 			out.println("<option value='" + par.getId() + "'>" + par.getName() + 
 					"</option>");
 		}
 		out.println("</select>");
 		out.println("<br>");
 		out.println("Project");
 		out.println("<select name='project' size='1'>");
 		for(Project project : projects) {
 			out.println("<option value='" + project.getId() + "'>" + 
 					project.getName() + "</option>");
 		}
 		out.println("</select>");
 		out.println("<br>");
 		out.println("Build found");
 		out.println("<select name='version' size='1'>");
 		for(PropertyParameter version : issue.getProject().getBuildVersions()) {
 			out.println("<option value='" + version.getId() + "'>" + 
 					version.getName() + "</option>");
 		}
 		out.println("</select>");
 		out.println("<br>");
 		out.println("Assignee");
 		out.println("<select name='assignee' size='1'>");
 		for(User user : users) {
 			out.println("<option value='" + user.getId() + "'>" + user.getFirstName() 
 					+ user.getLastName() + "</option>");
 		}
 		out.println("</select>");
 		out.println("</form>");
 		out.println("</body>");
 		out.println("</html>");
 		out.close();
 	}
}
