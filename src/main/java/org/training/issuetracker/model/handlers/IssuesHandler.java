package org.training.issuetracker.model.handlers;


import java.sql.Date;
import java.util.Set;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.ifaces.IssuePropertyDAO;
import org.training.issuetracker.ifaces.ProjectDAO;
import org.training.issuetracker.ifaces.UserDAO;
import org.training.issuetracker.model.beans.Issue;
import org.training.issuetracker.model.beans.Project;
import org.training.issuetracker.model.beans.PropertyParameter;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.model.factories.IssuePropertyFactory;
import org.training.issuetracker.model.factories.ProjectFactory;
import org.training.issuetracker.model.factories.UserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class IssuesHandler extends DefaultHandler {
	
	public IssuesHandler(Set<Issue> issues) {
		this.issues = issues;
	}
	
	private static enum IssuesXMLEnum {
		ISSUES, ISSUE, ID, PRIORITY, ASSIGNEE, TYPE, SUMMARY, DESCRIPTION,   
		STATUS, PROJECT, RESOLUTION, FOUND, CREATEDATE, CREATEDBY, MODIFYDATE, MODIFIEDBY;
	}
	
	private Set<Issue> issues;
	private IssuesXMLEnum currentEnum = null;
	private String currentId;
	private PropertyParameter currentPriority;
	private PropertyParameter currentType;
	private String currentResolution;
	private String currentSummary;
	private String currentDescription;
	private PropertyParameter currentStatus;
	private String currentProject;
	private String currentFound;
	
	
	private String currentAssignee;
	private String currentCreateDate;
	private String currentCreatedBy;
	private String currentModifyDate;
	private String currentModifiedBy;
	UserDAO usersDAO = UserFactory.getClassFromFactory();
	IssuePropertyDAO propertyDAO = IssuePropertyFactory.getClassFromFactory();
	ProjectDAO projectDAO = ProjectFactory.getClassFromFactory();
	
	
	public void startElement(String uri, String localName, String qName, Attributes attrs) {
	   currentEnum = IssuesXMLEnum.valueOf(qName.toUpperCase());
	}
	
	public void characters(char[] ch, int start, int length) {
		if(currentEnum == IssuesXMLEnum.ID) {
			String s = new String(ch, start, length).trim();
			if(!s.isEmpty()){
				currentId = s;
			}
		}
		if(currentEnum == IssuesXMLEnum.PRIORITY) {
			String s = new String(ch, start, length).trim();
			if(!s.isEmpty()){
				int id = Integer.parseInt(s);
				currentPriority = 
					propertyDAO.getPropertyParameterById(Constants.PRIORITIES_SOURCE_NAME, id);
					
			}
		}
		if(currentEnum == IssuesXMLEnum.ASSIGNEE) {
			String s = new String(ch, start, length).trim();
			if(!s.isEmpty()){
				currentAssignee = s;
			}
		}
		
		if(currentEnum == IssuesXMLEnum.TYPE) {
			String s = new String(ch, start, length).trim();
			if(!s.isEmpty()){
				int id = Integer.parseInt(s);
				currentType = propertyDAO.getPropertyParameterById(Constants.TYPES_SOURCE_NAME, id);
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
				currentStatus = propertyDAO.getPropertyParameterById(Constants.STATUSES_SOURCE_NAME, id);
			}
		}
		if(currentEnum == IssuesXMLEnum.PROJECT) {
			String s = new String(ch, start, length).trim();
			if(!s.isEmpty()){
				currentProject = s;
			}
		}
		
		if(currentEnum == IssuesXMLEnum.RESOLUTION) {///////////////////
			String s = new String(ch, start, length).trim();
			if(!s.isEmpty()){
				currentResolution = s;
			} 
		}
		
		
		if(currentEnum == IssuesXMLEnum.FOUND) {
			String s = new String(ch, start, length).trim();
			if(!s.isEmpty()){
				currentFound = s;
			}
		}
		
		
		if(currentEnum == IssuesXMLEnum.ASSIGNEE) {
			String s = new String(ch, start, length).trim();
			if(!s.isEmpty()){
				currentAssignee = s;
			} 
		}
		
		if(currentEnum == IssuesXMLEnum.CREATEDATE) {
			String s = new String(ch, start, length).trim();
			if(!s.isEmpty()){
				currentCreateDate = s;
			}
		}
		
		if(currentEnum == IssuesXMLEnum.CREATEDBY) {
			String s = new String(ch, start, length).trim();
			if(!s.isEmpty()){
				currentCreatedBy = s;
			}
		}
		
		if(currentEnum == IssuesXMLEnum.MODIFYDATE) {
			String s = new String(ch, start, length).trim();
			if(!s.isEmpty()){
				currentModifyDate = s;
			} 
		}
		
		if(currentEnum == IssuesXMLEnum.MODIFIEDBY) {
			String s = new String(ch, start, length).trim();
			if(!s.isEmpty()){
				currentModifiedBy = s;
			} 
		}
		
	}
	
	public void endElement(String uri, 
			String localName, String qName) { 
			currentEnum = IssuesXMLEnum.valueOf(qName.toUpperCase()); //!!!
			if(currentEnum == IssuesXMLEnum.ISSUE) {
				Date createDate = Date.valueOf(currentCreateDate);
				User createdBy = usersDAO.getUserById(Integer.parseInt(currentCreatedBy));
				Project project = projectDAO.getProjectById(Integer.parseInt(currentProject));
				PropertyParameter buildFound = projectDAO.getVersionById(Integer.parseInt(currentFound));
				Issue issue = new Issue(Integer.parseInt(currentId), currentPriority.getName(),
						currentType.getName(), currentSummary, currentDescription, currentStatus.getName(),
							project, buildFound.getName(), createDate, createdBy);
				if(currentAssignee != null  && !currentAssignee.isEmpty()) {
					 User assignee = usersDAO.getUserById(Integer.parseInt(currentAssignee));
					 issue.setAssignee(assignee);
					 currentAssignee = "";
				}
				if(currentModifyDate != null &&  !currentModifyDate.isEmpty()) {
					 Date modifyDate = Date.valueOf(currentModifyDate);
					 issue.setModifyDate(modifyDate);
					 currentModifyDate = "";
				}
				if(currentModifiedBy != null &&  !currentModifiedBy.isEmpty()) {
					 User modifiedBy = usersDAO.getUserById(Integer.parseInt(currentModifiedBy));
					 issue.setModifiedBy(modifiedBy);
					 currentModifiedBy = "";
				}
				if(currentResolution != null && !currentResolution.isEmpty()) {
					int id = Integer.parseInt(currentResolution);
					PropertyParameter resolution = 
							propertyDAO.getPropertyParameterById(Constants.RESOLUTIONS_SOURCE_NAME, id);
					issue.setResolution(resolution.getName());
					currentResolution = "";
				}
				
				
				issues.add(issue);
			}
	} 
}
