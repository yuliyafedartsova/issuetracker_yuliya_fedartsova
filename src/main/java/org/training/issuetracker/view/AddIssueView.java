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
import org.training.issuetracker.model.beans.PropertyParameter;
import org.training.issuetracker.model.beans.User;

/**
 * Servlet implementation class AddIssueView
 */
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
		PrintWriter out = response.getWriter();
		request.setAttribute(Constants.WRITER, out);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/HeaderView");
		rd.include(request, response);
	 	out.println("<html>");
	    out.println("<head>");
	 	out.println("<title>Sample Servlet interface implementation</title>");
	 	out.println("</head>");
	 	out.println("<body>");
	 	out.println("Add issue:");
	 	out.println("<br>");
	 	out.println("<form action=''>");
	 	out.println("Summary: ");
	 	out.println("<input type='text' name='summary' value=''>");
 		out.println("<br>");
 		out.println("Description: ");
 		out.println("<input type='text' name='description' value=''>");
 		out.println("<br>");
 		out.println("Status: ");
 		out.println("<select name='status' size='1'>");
 		for(PropertyParameter par : statuses) {
 			out.println("<option value='" + par.getId() +  "'>" + 
 					par.getName() + "</option>");
 		}
 		out.println("</select>");
 		out.println("<br>");
 		out.println("Type: ");
 		out.println("<select name='type' size='1'>");
 		for(PropertyParameter par : types) {
 			out.println("<option value='" + par.getId() +  "'>" + 
 					par.getName() + "</option>");
 		}
 		out.println("</select>");
 		out.println("<br>");
 		out.println("Priority: ");
 		out.println("<select name='priority' size='1'>");
 		for(PropertyParameter par : priorities) {
 			out.println("<option value='" + par.getId() +  "'>" + 
 					par.getName() + "</option>");
 		}
 		out.println("</select>");
 		out.println("<br>");
 		out.println("Assignee: ");
 		out.println("<select name='assignee' size='1'>");
 		for(User user : users) {
 			out.println("<option value='" + user.getId() +  "'>" + 
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
