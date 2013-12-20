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
import org.training.issuetracker.model.beans.Project;
import org.training.issuetracker.model.beans.PropertyParameter;
import org.training.issuetracker.model.beans.User;


public class ProjectUpdateView extends AbstractController {
	private static final long serialVersionUID = 1L;
       
    public ProjectUpdateView() {
        super();
    }

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Project project = (Project)request.getAttribute(Constants.PROJECT);
		List<User> users = (List<User>)request.getAttribute(Constants.USERS);
		PrintWriter out = response.getWriter();
		request.setAttribute(Constants.WRITER, out);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(Constants.HEADER);
		rd.include(request, response);
	 	out.println("<html>");
	 	out.println("<head>");
	 	out.println("<title>Update project</title>");
	 	out.println("</head>");
	 	out.println("<body>");
	 	out.println("Update project: " + "<br>");
	 	out.println("<form action=''>");
	 	out.println("<table>");
	 	out.println("<tr>" + "<td>" + "Name:" + "</td>");
 		out.println("<td>" + "<input type='text' name='name' value=" + project.getName() + ">" + "</td>" + "</tr>");
 		out.println("<tr>" + "<td>" + "Description:" + "</td>");
 		out.println("<td>" + "<input type='text' name='description' value=" + project.getDescription() + ">" + "</td>" + "</tr>");
 		out.println("<tr>" + "<td>" + "Builds:" + "</td>");
 		out.println("<td>" + "<select name='build' size='1'>");
 		for(PropertyParameter version : project.getBuildVersions()) {
 			out.println("<option value='" + version.getId() + "'>" + 
 					version.getName() + "</option>");
 		}
 		out.println("</select>" + "</td>" + "</tr>");
 		out.println("<tr>" + "<td>" + "Manager:" + "</td>");
 		out.println("<td>" + "<select name='manager' size='1'>");
 		for(User user : users) {
 			out.println("<option value='" + user.getId() + "'>" + 
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
