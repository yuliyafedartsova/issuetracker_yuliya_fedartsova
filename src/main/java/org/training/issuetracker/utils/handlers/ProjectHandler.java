package org.training.issuetracker.utils.handlers;

import java.util.List;

import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.exceptions.ValidationException;
import org.training.issuetracker.model.beans.Project;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.model.beans.properties.Version;
import org.training.issuetracker.model.factories.ProjectFactory;
import org.training.issuetracker.model.factories.UserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import org.training.issuetracker.model.DAO.ProjectDAO;
import org.training.issuetracker.model.DAO.UserDAO;

public class ProjectHandler extends DefaultHandler {
	
	public ProjectHandler(List<Project> projects) {
		this.projects = projects;
	}

	private static enum ProjectXMLEnum {
		PROJECTS, PROJECT, ID, NAME, DESCRIPTION, MANAGER, BUILD;
	}
	
	List<Project> projects;
	private ProjectXMLEnum currentEnum = null;
	private int currentId;
	private String currentName;
	private String currentDescription;
	private User currentManager;
    ProjectDAO projectDAO = ProjectFactory.getClassFromFactory();
	UserDAO userDAO = UserFactory.getClassFromFactory();
	
	
	public void startElement(String uri, String localName, String qName, Attributes attrs) {
		   currentEnum = ProjectXMLEnum.valueOf(qName.toUpperCase());
		}

	public void characters(char[] ch, int start, int length) {
		if(currentEnum == ProjectXMLEnum.ID) {
			String s = new String(ch, start, length).trim();
			if(!s.isEmpty()){
				currentId = Integer.parseInt(s);
			}
		}
		
		if(currentEnum == ProjectXMLEnum.NAME) {
			String s = new String(ch, start, length).trim();
			if(!s.isEmpty()){
				currentName = s;
			}
		}
		
		if(currentEnum == ProjectXMLEnum.MANAGER) {
			String s = new String(ch, start, length).trim();
			if(!s.isEmpty()){
				int id = Integer.parseInt(s);
				try {
				currentManager = userDAO.getUserById(id);
				}catch (DaoException e) {
					throw new RuntimeException(e);
				}catch (ValidationException e) {
					throw new RuntimeException(e);
				}
			}
		}
		
		if(currentEnum == ProjectXMLEnum.DESCRIPTION) {
			String s = new String(ch, start, length).trim();
			if(!s.isEmpty()){
				currentDescription = s;
			}
		}
	}
	
	public void endElement(String uri, 
			String localName, String qName) { 
			currentEnum = ProjectXMLEnum.valueOf(qName.toUpperCase()); 
			if(currentEnum == ProjectXMLEnum.PROJECT) {
				List<Version> buildVersions = null;
				try {
				buildVersions = projectDAO.getVersionsOfProject(currentId);
				}catch (DaoException e) {
					throw new RuntimeException(e);
				}
				projects.add(new Project(currentId, currentName, currentManager, 
			    	buildVersions, currentDescription));
			}
	} 
	

}

