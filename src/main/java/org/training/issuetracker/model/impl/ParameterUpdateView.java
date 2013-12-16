package org.training.issuetracker.model.impl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.training.issuetracker.ifaces.AbstractController;

/**
 * Servlet implementation class ParameterUpdateView
 */
public class ParameterUpdateView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ParameterUpdateView() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   String property = request.getParameter("property");
	   String value = request.getParameter("value");
	   int id = Integer.parseInt(request.getParameter("id"));
	   PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Sample Servlet interface implementation</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<form>");
		out.println("Update: ");
		out.println("<input type='text' name='value' value='" + value + "'><br>");
		out.println("<input type='hidden' name='property'  value='" + property +  "'>");	
		out.println("<input type='hidden' name='id'  value='" + id +  "'>");	
		
		out.println("<br>");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
		out.close();
	
	
	
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String property = request.getParameter("property");
		   String value = request.getParameter("value");
		   int id = Integer.parseInt(request.getParameter("id"));
		   PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Sample Servlet interface implementation</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<form>");
			out.println("Update: ");
			out.println("<input type='text' name='value' value='" + value + "'><br>");
			out.println("<input type='hidden' name='property'  value='" + property +  "'>");	
			out.println("<input type='hidden' name='id'  value='" + id +  "'>");	
			
			out.println("<br>");
			out.println("</form>");
			out.println("</body>");
			out.println("</html>");
			out.close();
	}

}
