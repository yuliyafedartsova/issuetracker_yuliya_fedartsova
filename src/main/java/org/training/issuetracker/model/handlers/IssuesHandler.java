package org.training.issuetracker.model.handlers;


import java.sql.Date;
import java.util.Set;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.ifaces.ProjectDAO;
import org.training.issuetracker.ifaces.PropertyDAO;
import org.training.issuetracker.ifaces.UserDAO;
import org.training.issuetracker.model.beans.Issue;
import org.training.issuetracker.model.beans.Project;
import org.training.issuetracker.model.beans.PropertyParameter;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.model.factories.ProjectFactory;
import org.training.issuetracker.model.factories.PropertyFactory;
import org.training.issuetracker.model.factories.UserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class IssuesHandler extends DefaultHandler {
	
	public IssuesHandler(Set<Issue> issues) {
		this.issues = issues;
	}
	
	private static enum IssuesXMLEnum {
		ISSUES, ISSUE, ID, PRIORITY, ASSIGNEE, TYPE, SUMMARY, DESCRIPTION,   
		STATUS, PROJECT, RESOLUTION, FOUND, CREATEDATE, AUTHOR, MODIFYDATE, MODIFIER;
	}
	
	private Set<Issue> issues;
	private IssuesXMLEnum currentEnum = null;
	private int currentId;
	private PropertyParameter currentPriority;
	private PropertyParameter currentType;
	private PropertyParameter currentResolution;
	private PropertyParameter currentStatus;
	private PropertyParameter currentVersion;
	private String currentSummary;
	private String currentDescription;
	private Project currentProject;
	private User currentAssignee;
	private Date currentCreateDate;
	private User currentAuthor;
	private Date currentModifyDate;
	private User currentModifier;
	UserDAO usersDAO = UserFactory.getClassFromFactory();
	PropertyDAO propertyDAO = PropertyFactory.getClassFromFactory();
	ProjectDAO projectDAO = ProjectFactory.getClassFromFactory();
	
	
	public void startElement(String uri, String localName, String qName, Attributes attrs) {
	   currentEnum = IssuesXMLEnum.valueOf(qName.toUpperCase());
	}
	
	public void characters(char[] ch, int start, int length) {
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
				currentPriority = 
					propertyDAO.getPriorityById(id);
					
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
				currentType = propertyDAO.getTypeById(id);
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
				currentStatus = propertyDAO.getStatusById(id);
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
				currentResolution = propertyDAO.getResolutionById(id);
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
		
	}
	
	public void endElement(String uri, 
			String localName, String qName) { 
			currentEnum = IssuesXMLEnum.valueOf(qName.toUpperCase()); 
			if(currentEnum == IssuesXMLEnum.ISSUE) {
				Issue issue = new Issue(currentId, currentPriority.getName(),
						currentType.getName(), currentSummary, currentDescription, currentStatus.getName(),
							currentProject, currentVersion.getName(), currentCreateDate, currentAuthor);
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
					issue.setResolution(currentResolution.getName());
					currentResolution = null;
				}
				
				issues.add(issue);
			}
	} 
}
