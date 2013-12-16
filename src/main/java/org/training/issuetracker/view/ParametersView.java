package org.training.issuetracker.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.training.issuetracker.model.beans.PropertyParameter;

/**
 * Servlet implementation class ParametersView
 */
public class ParametersView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ParametersView() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<PropertyParameter> parametres = 
				(List<PropertyParameter>)request.getAttribute("parametres");
		String property = request.getParameter("property");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Sample Servlet interface implementation</title>");
		out.println("</head>");
		out.println("<body>");
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

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
