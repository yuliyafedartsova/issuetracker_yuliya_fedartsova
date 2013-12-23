package org.training.issuetracker.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.controllers.AbstractController;
import org.training.issuetracker.model.beans.PropertyParameter;

public class ParametersView extends AbstractController {
	private static final long serialVersionUID = 1L;
       
    public ParametersView() {
        super();
    }

	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<PropertyParameter> parametres = 
				(List<PropertyParameter>)request.getAttribute(Constants.PARAMETRES);
		String property = request.getParameter(Constants.PROPERTY);
		PrintWriter out = response.getWriter();
		request.setAttribute(Constants.WRITER, out);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(Constants.HEADER);
		rd.include(request, response);
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Parameters</title>");
		out.println("</head>");
		out.println("<body>");
		switch(property) {
    		case Constants.STATUS:
    			out.println("Statuses:" + "<br>");
    			break;
    		case Constants.TYPE:
    			out.println("Types:" + "<br>");
    			break;
    		case Constants.PRIORITY:
    			out.println("Priorities:" + "<br>");
    			break;
    		case Constants.RESOLUTION:
    			out.println("Resolutions:" + "<br>");
    			break;
    	}
		for(PropertyParameter par : parametres) {
			out.println("<a href='ParameterUpdateView?property=" + 
					property + "&id=" +  par.getId() + "&value=" + par.getName() + 
						"'" + ">"+ par.getName() +"</a>");
			out.println("<br>");
		}
		out.println("</body>");
		out.println("</html>");
		out.close();
	}

}
