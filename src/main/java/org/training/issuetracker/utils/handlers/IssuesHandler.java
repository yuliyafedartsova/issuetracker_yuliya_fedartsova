package org.training.issuetracker.utils.handlers;

import java.sql.Date;
import java.util.List;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.exceptions.ValidationException;
import org.training.issuetracker.model.beans.Issue;
import org.training.issuetracker.model.beans.Project;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.model.beans.properties.Priority;
import org.training.issuetracker.model.beans.properties.Resolution;
import org.training.issuetracker.model.beans.properties.Status;
import org.training.issuetracker.model.beans.properties.Type;
import org.training.issuetracker.model.beans.properties.Version;
import org.training.issuetracker.model.factories.PriorityFactory;
import org.training.issuetracker.model.factories.ProjectFactory;
import org.training.issuetracker.model.factories.ResolutionFactory;
import org.training.issuetracker.model.factories.StatusFactory;
import org.training.issuetracker.model.factories.TypeFactory;
import org.training.issuetracker.model.factories.UserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import org.training.issuetracker.model.DAO.PrioritiesDAO;
import org.training.issuetracker.model.DAO.ProjectDAO;
import org.training.issuetracker.model.DAO.ResolutionDAO;
import org.training.issuetracker.model.DAO.StatusesDAO;
import org.training.issuetracker.model.DAO.TypesDAO;
import org.training.issuetracker.model.DAO.UserDAO;

public class IssuesHandler extends DefaultHandler {
	
	public IssuesHandler(List <Issue> issues) {
		this.issues = issues;
	}
	
	private static enum IssuesXMLEnum {
		ISSUES, ISSUE, ID, PRIORITY, ASSIGNEE, TYPE, SUMMARY, DESCRIPTION,   
		STATUS, PROJECT, RESOLUTION, FOUND, CREATEDATE, AUTHOR, MODIFYDATE, MODIFIER;
	}
	
	private List <Issue> issues;
	private IssuesXMLEnum currentEnum = null;
	private int currentId;
	private Priority currentPriority;
	private Type currentType;
	private Resolution currentResolution;
	private Status currentStatus;
	private Version currentVersion;
	private String currentSummary;
	private String currentDescription;
	private Project currentProject;
	private User currentAssignee;
	private Date currentCreateDate;
	private User currentAuthor;
	private Date currentModifyDate;
	private User currentModifier;
	UserDAO usersDAO = UserFactory.getClassFromFactory();
	StatusesDAO statusDAO = StatusFactory.getClassFromFactory();
	TypesDAO typesDAO = TypeFactory.getClassFromFactory();
	PrioritiesDAO priorityDAO = PriorityFactory.getClassFromFactory();
	ResolutionDAO resolutionDAO = ResolutionFactory.getClassFromFactory();
	ProjectDAO projectDAO = ProjectFactory.getClassFromFactory();
	
	
	public void startElement(String uri, String localName, String qName, Attributes attrs) {
	   currentEnum = IssuesXMLEnum.valueOf(qName.toUpperCase());
	}
	
	public void characters(char[] ch, int start, int length) {
		try {
		if(currentEnum == IssuesXMLEnum.ID) {
			String s = new String(ch, start, length).trim();
			if(!s.isEmpty()){
				currentId = Integer.parseInt(s);
			}
		}
		if(currentEnum == IssuesXMLEnum.PRIORITY) {
			String s = new String(ch, start, length).trim();
			if(!s.isEmpty()){
				int id = Integer.parseInt(s);
				currentPriority = priorityDAO.getById(id);
					
			}
		}
		if(currentEnum == IssuesXMLEnum.ASSIGNEE) {
			String s = new String(ch, start, length).trim();
			if(!s.isEmpty()){
				int id = Integer.parseInt(s);
				currentAssignee = usersDAO.getUserById(id);
			}
		}
		
		if(currentEnum == IssuesXMLEnum.TYPE) {
			String s = new String(ch, start, length).trim();
			if(!s.isEmpty()){
				int id = Integer.parseInt(s);
				currentType = typesDAO.getById(id);
						
			}
		}
		if(currentEnum == IssuesXMLEnum.SUMMARY) {
			String s = new String(ch, start, length).trim();
			if(!s.isEmpty()){
				currentSummary = s;
			}
		}
		if(currentEnum == IssuesXMLEnum.DESCRIPTION) {
			String s = new String(ch, start, length).trim();
			if(!s.isEmpty()){
				currentDescription = s;
			}
		}
		
		if(currentEnum == IssuesXMLEnum.STATUS) {
			String s = new String(ch, start, length).trim();
			if(!s.isEmpty()){
				int id = Integer.parseInt(s);
				currentStatus = statusDAO.getById(id);
						
			}
		}
		if(currentEnum == IssuesXMLEnum.PROJECT) {
			String s = new String(ch, start, length).trim();
			if(!s.isEmpty()){
				int id = Integer.parseInt(s);
				currentProject = projectDAO.getProjectById(id);
			}
		}
		
		if(currentEnum == IssuesXMLEnum.RESOLUTION) {
			String s = new String(ch, start, length).trim();
			if(!s.isEmpty()){
				int id = Integer.parseInt(s);
				currentResolution = resolutionDAO.getById(id);
			} 
		}
		
		
		if(currentEnum == IssuesXMLEnum.FOUND) {
			String s = new String(ch, start, length).trim();
			if(!s.isEmpty()){
				int id = Integer.parseInt(s);
				currentVersion = projectDAO.getVersionById(id);
		    }
		}
		
		
		if(currentEnum == IssuesXMLEnum.CREATEDATE) {
			String s = new String(ch, start, length).trim();
			if(!s.isEmpty()){
				currentCreateDate = Date.valueOf(s);
			}
		}
		
		if(currentEnum == IssuesXMLEnum.AUTHOR) {
			String s = new String(ch, start, length).trim();
			if(!s.isEmpty()){
				int id = Integer.parseInt(s);
				currentAuthor = usersDAO.getUserById(id);
			}
		}
		
		if(currentEnum == IssuesXMLEnum.MODIFYDATE) {
			String s = new String(ch, start, length).trim();
			if(!s.isEmpty()){
				currentModifyDate = Date.valueOf(s);
			} 
		}
		
		if(currentEnum == IssuesXMLEnum.MODIFIER) {
			String s = new String(ch, start, length).trim();
			if(!s.isEmpty()){
				int id = Integer.parseInt(s);
			    currentModifier = usersDAO.getUserById(id);
				
			} 
		}
		}catch (DaoException e) {
			throw new RuntimeException(e);
		}catch (ValidationException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public void endElement(String uri, 
			String localName, String qName) { 
			currentEnum = IssuesXMLEnum.valueOf(qName.toUpperCase()); 
			if(currentEnum == IssuesXMLEnum.ISSUE) {
				Issue issue = new Issue(currentId, currentPriority,
						currentType, currentSummary, currentDescription, currentStatus,
							currentProject, currentVersion, currentCreateDate, currentAuthor);
				if(currentAssignee != null) {
					 issue.setAssignee(currentAssignee);
					 currentAssignee = null;
				}
				if(currentModifyDate != null) {
					 issue.setModifyDate(currentModifyDate);
					 currentModifyDate = null;
				}
				if(currentModifier != null) {
					 issue.setModifier(currentModifier);
					 currentModifier = null;
				}
				if(currentResolution != null) {
					issue.setResolution(currentResolution);
					currentResolution = null;
				}
				
				issues.add(issue);
			}
	} 
}

