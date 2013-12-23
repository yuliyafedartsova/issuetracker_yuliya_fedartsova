package org.training.issuetracker.view;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.controllers.AbstractController;



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
	   RequestDispatcher rd = getServletContext().getRequestDispatcher(Constants.HEADER);
	   rd.include(request, response);
	   out.println("<html>");
	   out.println("<head>");
	   out.println("<title>Update parameter</title>");
	   out.println("</head>");
	   out.println("<body>");
	   switch(property) {
   			case Constants.STATUS:
   				out.println("Update status:" + "<br>");
   				break;
   			case Constants.TYPE:
   				out.println("Update type:" + "<br>");
   				break;
   			case Constants.PRIORITY:
   				out.println("Update priority:" + "<br>");
   				break;
   			case Constants.RESOLUTION:
   				out.println("Update resolution:" + "<br>");
   				break;
   		}
	   out.println("<form>");
	   out.println(value);
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
