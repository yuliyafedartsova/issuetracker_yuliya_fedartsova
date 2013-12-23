package org.training.issuetracker.view;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.ifaces.AbstractController;
import org.training.issuetracker.model.beans.Issue;

public class IssueReviewView extends AbstractController {
	private static final long serialVersionUID = 1L;
       
    public IssueReviewView() {
        super();
    }

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Issue issue = (Issue) request.getAttribute(Constants.ISSUE);
	 	PrintWriter out = response.getWriter();
	 	request.setAttribute(Constants.WRITER, out);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(Constants.HEADER);
		rd.include(request, response);
	 	out.println("<html>");
	    out.println("<head>");
	 	out.println("<title>Issue review</title>");
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
	 	out.println("Summary: " + issue.getSummary() + "<br>");
	    out.println("Description: " + issue.getDescription() + "<br>");
	 	out.println("Status: " + issue.getStatus().getName() + "<br>");
	 	if(issue.getStatus().equals(Constants.CLOSED)) {
	 		out.println("Resolution: " + issue.getResolution() + "<br>");
	 	}
	 	out.println("Type: " + issue.getType().getName() + "<br>");
	 	out.println("Priority: " + issue.getPriority().getName() + "<br>");
	 	out.println("Project: " + issue.getProject().getName() + "<br>");
	 	out.println("Build found: " + issue.getBuildFound().getName() + "<br>");
	 	if(issue.getAssignee() != null) {
	 		out.println("Assignee: " + issue.getAssignee().getFirstName() + 
	 				" " + issue.getAssignee().getLastName()  + "<br>");
	 	}
	 	out.println("</body>");
	 	out.println("</html>");
	 	out.close();
	}

}
