package org.training.issuetracker.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.training.issuetracker.enums.Properties;
import org.training.issuetracker.ifaces.IssueDAO;
import org.training.issuetracker.model.beans.Issue;
import org.training.issuetracker.model.beans.Project;
import org.training.issuetracker.model.beans.PropertyParameter;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.model.factories.IssueFactory;

/**
 * Servlet implementation class IssueUpdateView
 */
public class IssueUpdateView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IssueUpdateView() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 	   Issue issue = (Issue) request.getAttribute("issue");
 	   List<User> users = (List<User>)request.getAttribute("users");
 	   Map <String, List <PropertyParameter>> map = 
 			   (Map <String, List <PropertyParameter>>)request.getAttribute("propertiesMap");
 	   List<Project> projects = (List<Project>)request.getAttribute("projects");
 	   
 	   PrintWriter out = response.getWriter();
 	   out.println("<html>");
 		out.println("<head>");
 		out.println("<title>Sample Servlet interface implementation</title>");
 		out.println("</head>");
 		out.println("<body>");
 		
 		out.println("Id " + issue.getId() + "<br>");
 		out.println("Create Date " + issue.getCreateDate() + "<br>");
 		out.println("Created By " + issue.getCreatedBy().getFirstName() + " "
 				+ issue.getCreatedBy().getLastName() + "<br>");
 		if(issue.getModifyDate() != null) {
 			out.println("Modify Date " + issue.getModifyDate() + "<br>");
 		}
 		if(issue.getModifiedBy() != null) {
 			out.println("Modified By " + issue.getModifiedBy().getFirstName() + " " 
 		      + issue.getModifiedBy().getLastName() + "<br>");
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
 		printPropertiesOptions(out, Properties.STATUSES.toString().toLowerCase(), map);
 		out.println("</select>");
 		out.println("<br>");
 		out.println("Resolution");
 		out.println("<select name='resolution' size='1'>");
 		printPropertiesOptions(out, Properties.RESOLUTIONS.toString().toLowerCase(), map);
 		out.println("</select>");
 		out.println("<br>");
 		out.println("Type");
 		out.println("<select name='type' size='1'>");
 		printPropertiesOptions(out, Properties.TYPES.toString().toLowerCase(), map);
 		out.println("</select>");
 		out.println("<br>");
 		out.println("Priority");
 		out.println("<select name='priority' size='1'>");
 		printPropertiesOptions(out, Properties.PRIORITIES.toString().toLowerCase(), map);
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

 	
    protected void printPropertiesOptions(PrintWriter out, String propertyName, 
    		Map <String, List <PropertyParameter>> map) {
    	List <PropertyParameter> parametres = 
 				map.get(propertyName);
 		for(PropertyParameter par : parametres) {
 			out.println("<option value='" + par.getId() + "'>" + par.getName() + 
 					"</option>");
 		}
    }
    
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 	}

}
