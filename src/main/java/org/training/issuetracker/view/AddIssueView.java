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
import org.training.issuetracker.model.beans.Project;
import org.training.issuetracker.model.beans.PropertyParameter;
import org.training.issuetracker.model.beans.User;


public class AddIssueView extends AbstractController {
	private static final long serialVersionUID = 1L;
       
    public AddIssueView() {
        super();
        
    }

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<PropertyParameter> statuses = 
				(List<PropertyParameter>)request.getAttribute(Constants.STATUSES);
		List<PropertyParameter> priorities = 
				(List<PropertyParameter>)request.getAttribute(Constants.PRIORITIES);
		List<PropertyParameter> types = 
				(List<PropertyParameter>)request.getAttribute(Constants.TYPES);
		List<User> users = (List<User>)request.getAttribute(Constants.USERS);
		List<Project> projects = (List<Project>)request.getAttribute(Constants.PROJECTS);
		PrintWriter out = response.getWriter();
		request.setAttribute(Constants.WRITER, out);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(Constants.HEADER);
		rd.include(request, response);
	 	out.println("<html>");
	    out.println("<head>");
	 	out.println("<title>Add issue</title>");
	 	out.println("</head>");
	 	out.println("<body>");
	 	out.println("Add issue:");
	 	out.println("<br>");
	 	out.println("<form action=''>");
	 	out.println("<table>");
	 	out.println("<tr>" + "<td>" + "Summary: " + "</td>");
	 	out.println("<td>" + "<input type='text' name='summary' value=''>" + "</td>" + "</tr>");
	 	out.println("<tr>" + "<td>" +"Description: "+ "</td>");
 		out.println("<td>" +"<input type='text' name='description' value=''>"+ "</td>" + "</tr>");
 		out.println("<tr>" + "<td>" +"Status: "+ "</td>");
 		out.println("<td>" +"<select name='status' size='1'>");
 		for(PropertyParameter par : statuses) {
 			out.println("<option value='" + par.getId() +  "'>" + 
 					par.getName() + "</option>");
 		}
 		out.println("</select>" + "</td>" + "</tr>");
 		out.println("<tr>" + "<td>" + "Type: " + "</td>");
 		out.println("<td>" + "<select name='type' size='1'>");
 		for(PropertyParameter par : types) {
 			out.println("<option value='" + par.getId() +  "'>" + 
 					par.getName() + "</option>");
 		}
 		out.println("</select>" + "</td>" + "</tr>");
 		out.println("<tr>" + "<td>" + "Priority: " + "</td>");
 		out.println("<td>" + "<select name='priority' size='1'>");
 		for(PropertyParameter par : priorities) {
 			out.println("<option value='" + par.getId() +  "'>" + 
 					par.getName() + "</option>");
 		}
 		out.println("</select>" + "</td>" + "</tr>");
 		out.println("<tr>" + "<td>" + "Project" + "</td>");
 	 	out.println("<td>" + "<select name='project' size='1'>");
 	 	for(Project project : projects) {
 	 		 out.println("<option value='" + project.getId() + "'>" + 
 	 			project.getName() + "</option>");
 	 	}
 	 	out.println("</select>" + "</td>" + "</tr>");
 	 	out.println("<tr>" + "<td>" + "Build found" + "</td>");
 	 	out.println("<td>" + "<select name='version' size='1'>");
 	 	for(Project project : projects) {
  		   for(PropertyParameter version : project.getBuildVersions())
  			   out.println("<option value='" + version.getId() + "'>" + 
  					   version.getName() + "</option>");
  	   }
  	   out.println("</select>" + "</td>" + "</tr>");
  	   out.println("<tr>" + "<td>" + "Assignee: "  + "</td>");
  	   out.println("<td>" + "<select name='assignee' size='1'>");
  	   for(User user : users) {
 			out.println("<option value='" + user.getId() +  "'>" + 
 					user.getFirstName() + " " + user.getLastName() + "</option>");
  	   }
  	   out.println("</select>" + "</td>" + "</tr>");
  	   out.println("</table>");
  	   out.println("</form>");
  	   out.println("</body>");
  	   out.println("</html>");
  	   out.close();
	}

}
