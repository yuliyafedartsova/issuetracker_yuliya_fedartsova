package org.training.issuetracker.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.ifaces.AbstractController;

/**
 * Servlet implementation class ParameterUpdateView
 */
public class ParameterUpdateView extends AbstractController {
	private static final long serialVersionUID = 1L;
       
    
    public ParameterUpdateView() {
        super();
       
    }

	
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   String property = request.getParameter(Constants.PROPERTY);
	   String value = request.getParameter(Constants.VALUE);
	   int id = Integer.parseInt(request.getParameter(Constants.ID));
	   PrintWriter out = response.getWriter();
	   request.setAttribute(Constants.WRITER, out);
	   RequestDispatcher rd = getServletContext().getRequestDispatcher("/HeaderView");
		rd.include(request, response);
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
