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
import org.training.issuetracker.model.beans.Issue;
import org.training.issuetracker.model.beans.PropertyParameter;
import org.training.issuetracker.model.beans.User;

/**
 * Servlet implementation class IssueReviewView
 */
public class IssueReviewView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public IssueReviewView() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Issue issue = (Issue) request.getAttribute("issue");
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
	 	out.println("Summary: " + issue.getSummary() + "<br>");
	    out.println("Description: " + issue.getDescription() + "<br>");
	 	out.println("Status: " + issue.getStatus() + "<br>");
	 	out.println("Resolution: " + issue.getResolution() + "<br>");
	 	out.println("Type: " + issue.getType() + "<br>");
	 	out.println("Priority: " + issue.getPriority() + "<br>");
	 	out.println("Project: " + issue.getProject().getName() + "<br>");
	 	out.println("Build found: " + issue.getBuildFound() + "<br>");
	 	if(issue.getAssignee() != null) {
	 		out.println("Assignee: " + issue.getAssignee().getFirstName() + 
	 				" " + issue.getAssignee().getLastName()  + "<br>");
	 	}
	 	out.println("</body>");
	 	out.println("</html>");
	 	out.close();
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Issue issue = (Issue) request.getAttribute("issue");
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
	 	out.println("Summary: " + issue.getSummary() + "<br>");
	    out.println("Description: " + issue.getDescription() + "<br>");
	 	out.println("Status: " + issue.getStatus() + "<br>");
	 	out.println("Resolution: " + issue.getResolution() + "<br>");
	 	out.println("Type: " + issue.getType() + "<br>");
	 	out.println("Priority: " + issue.getPriority() + "<br>");
	 	if(issue.getAssignee() != null) {
	 		out.println("Assignee: " + issue.getAssignee().getFirstName() + 
	 				" " + issue.getAssignee().getLastName()  + "<br>");
	 	}
	 	out.println("</body>");
	 	out.println("</html>");
	 	out.close();
	}

}
