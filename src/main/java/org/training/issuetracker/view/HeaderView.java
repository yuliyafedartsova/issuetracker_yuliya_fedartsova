package org.training.issuetracker.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.ifaces.AbstractController;
import org.training.issuetracker.model.beans.User;


public class HeaderView extends AbstractController {
	private static final long serialVersionUID = 1L;
       
    
    public HeaderView() {
        super();
        
    }

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = (PrintWriter)request.getAttribute(Constants.WRITER);
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute(Constants.USER);
		out.println("<html>");
		out.println("<head>");
		out.println("</head>");
		out.println("<body>");
		if(user != null) {
			out.println("Hello, " + user.getFirstName());
			out.println("<br>");
			out.println("<a href='/issuetracker/changePasswordForm.html'>"+ "Change password" +"</a>");
			out.println("<br>");
			out.println("<a href='user-controller?action=update'>"+ "Update your data" +"</a>");
			out.println("<br><br>");
		} else {
			RequestDispatcher rd = getServletContext().getRequestDispatcher(Constants.LOGIN_FORM);
			rd.include(request, response);
		}
		out.println("</body>");
		out.println("</html>");
	}

}
