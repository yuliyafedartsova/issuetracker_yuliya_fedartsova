package org.training.issuetracker.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.training.issuetracker.model.beans.Project;
import org.training.issuetracker.model.beans.PropertyParameter;
import org.training.issuetracker.model.beans.User;

/**
 * Servlet implementation class ProjectUpdateView
 */
public class ProjectUpdateView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ProjectUpdateView() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Project project = (Project)request.getAttribute("project");
		List<User> users = (List<User>)request.getAttribute("users");
		PrintWriter out = response.getWriter();
	 	out.println("<html>");
	 	out.println("<head>");
	 	out.println("<title>Sample Servlet interface implementation</title>");
	 	out.println("</head>");
	 	out.println("<body>");
	 	out.println("<form action=''>");
	 	out.println("Name: ");
 		out.println("<input type='text' name='name' value=" + project.getName() + ">");
 		out.println("<br>");
 		out.println("Description: ");
 		out.println("<input type='text' name='description' value=" + project.getDescription() + ">");
 		out.println("<br>");
 		out.println("Builds: ");
 		out.println("<select name='build' size='1'>");
 		for(PropertyParameter version : project.getBuildVersions()) {
 			out.println("<option value='" + version.getId() + "'>" + 
 					version.getName() + "</option>");
 		}
 		out.println("</select>");
 		out.println("<br>");
 		out.println("Manager: ");
 		out.println("<select name='manager' size='1'>");
 		for(User user : users) {
 			out.println("<option value='" + user.getId() + "'>" + 
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
		Project project = (Project)request.getAttribute("project");
		List<User> users = (List<User>)request.getAttribute("users");
		PrintWriter out = response.getWriter();
	 	out.println("<html>");
	 	out.println("<head>");
	 	out.println("<title>Sample Servlet interface implementation</title>");
	 	out.println("</head>");
	 	out.println("<body>");
	 	out.println("<form action=''>");
	 	out.println("Name: ");
 		out.println("<input type='text' name='name' value=" + project.getName() + ">");
 		out.println("<br>");
 		out.println("Description: ");
 		out.println("<input type='text' name='description' value=" + project.getDescription() + ">");
 		out.println("<br>");
 		out.println("Builds: ");
 		out.println("<select name='build' size='1'>");
 		for(PropertyParameter version : project.getBuildVersions()) {
 			out.println("<option value='" + version.getId() + "'>" + 
 					version.getName() + "</option>");
 		}
 		out.println("</select>");
 		out.println("<br>");
 		out.println("Manager: ");
 		out.println("<select name='manager' size='1'>");
 		for(User user : users) {
 			out.println("<option value='" + user.getId() + "'>" + 
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
