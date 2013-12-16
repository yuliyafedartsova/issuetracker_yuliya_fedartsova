package org.training.issuetracker;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.training.issuetracker.model.beans.Issue;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.model.factories.IssueFactory;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * Sample Servlet interface implementation.
 */
public class SampleServlet implements Servlet {

	private ServletConfig servletConfig;

	@Override
	public void init(ServletConfig config) throws ServletException {
		servletConfig = config;
	}

	@Override
	public void destroy() {
	}

	@Override
	public ServletConfig getServletConfig() {
		return servletConfig;
	}

	@Override
	public String getServletInfo() {
		return "Sample Servlet interface implementation";
	}

	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {

		String realPath = getServletConfig().getServletContext().getRealPath( "/") +
				 "WEB-INF\\classes\\";

		
		try{
			XMLReader reader = XMLReaderFactory.createXMLReader();
		//	IssuesHandler handler = new IssuesHandler(null, null);
		//	UsersHandler userHandler = new UsersHandler();
			
		//	reader.setContentHandler(handler);
			reader.parse(realPath + "issues.xml");
			
		//	reader.setContentHandler(userHandler);
			reader.parse(realPath + "users.xml");
			}catch (SAXException e) {
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			}

		
		
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Sample Servlet interface implementation</title>");
		out.println("</head>");
		out.println("<body><b>Hello world!111</b></body>");
		out.println("</html>");
		out.close();
	}
}