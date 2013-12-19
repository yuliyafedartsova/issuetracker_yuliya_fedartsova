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
import org.training.issuetracker.model.beans.Project;

/**
 * Servlet implementation class ProjectsView
 */
public class ProjectsView extends  AbstractController {
	private static final long serialVersionUID = 1L;
       
    
    public ProjectsView() {
        super();
       
    }

	
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Project> projects = (List<Project>)request.getAttribute(Constants.PROJECTS);
		PrintWriter out = response.getWriter();
		request.setAttribute(Constants.WRITER, out);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/HeaderView");
		rd.include(request, response);
	 	out.println("<html>");
	 	out.println("<head>");
	 	out.println("<title>Sample Servlet interface implementation</title>");
	 	out.println("</head>");
	 	out.println("<body>");
	 	out.println("Projects: " + "<br>");
	 	out.println("<table>");
		out.println("<tr>" + "<td> Name </td>" + "<td> Manager </td>" + 
				"<td> Description </td>");
		out.println("<br>");
	 	for(Project project : projects) {
	 		out.println("<tr>");
	 		
	 		out.println("<td>" + "<a href='ProjectController?action=update&id=" + 
	 				project.getId() + "'" + ">" +  project.getName() +"</a>" + "</td>");
	    	out.println("<td>" + project.getManager().getFirstName() 
	    			+  " " + project.getManager().getLastName() + "</td>");
	    	out.println("<td>" + project.getDescription() + "</td>");
	    	out.println("</tr>");
	    }
	 	out.println("</table>");
	 	out.println("</body>");
 		out.println("</html>");
 		out.close();
	}

}
