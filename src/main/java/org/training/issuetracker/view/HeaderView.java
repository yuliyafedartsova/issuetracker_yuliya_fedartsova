package org.training.issuetracker.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.training.issuetracker.model.beans.User;

/**
 * Servlet implementation class HeaderView
 */
public class HeaderView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public HeaderView() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = (PrintWriter)request.getAttribute("writer");
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Sample Servlet interface implementation</title>");
		out.println("</head>");
		out.println("<body>");
		if(user != null) {
			out.println("Hello, " + user.getFirstName());
			out.println("<br>");
			out.println("<a href='/issuetracker/changePasswordForm.html'>"+ "Change password" +"</a>");
			out.println("<br>");
			out.println("<a href='UserDataUpdateView'>"+ "Update your data" +"</a>");
			out.println("<br><br>");
		} else {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/LoginForm.html");
			rd.include(request, response);
		}
		out.println("</body>");
		out.println("</html>");
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = (PrintWriter)request.getAttribute("writer");
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Sample Servlet interface implementation</title>");
		out.println("</head>");
		out.println("<body>");
		if(user != null) {
			out.println("Hello, " + user.getFirstName());
			out.println("<br>");
			out.println("<a href='/issuetracker/changePasswordForm.html'>"+ "Change password" +"</a>");
			out.println("<br>");
			out.println("<a href='UserDataUpdateView'>"+ "Update your data" +"</a>");
			out.println("<br><br>");
		} else {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/LoginForm.html");
			rd.include(request, response);
		}
		out.println("</body>");
		out.println("</html>");
		
	}

}
