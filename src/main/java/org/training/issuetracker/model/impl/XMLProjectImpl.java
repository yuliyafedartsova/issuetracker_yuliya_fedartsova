package org.training.issuetracker.model.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.ifaces.ProjectDAO;
import org.training.issuetracker.model.beans.Project;
import org.training.issuetracker.model.beans.PropertyParameter;
import org.training.issuetracker.model.handlers.ProjectHandler;
import org.training.issuetracker.model.handlers.VersionsHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class XMLProjectImpl implements ProjectDAO {
	public Project getProjectById(int id) {
		Project project = null;
		List<Project> projects = getProjects();
	    for(Project p : projects) {
	    	if(p.getId() == id) {
	    		project = p;
	    	}
	    }
	    return project;
	}
	
	public PropertyParameter getVersionById(int id) {
		PropertyParameter version = null;
		List<PropertyParameter> versions = new ArrayList<PropertyParameter>();
		try {
			XMLReader reader = XMLReaderFactory.createXMLReader();
			VersionsHandler handler = new VersionsHandler(id, versions, Constants.VERSION);
			reader.setContentHandler(handler);
			reader.parse(Constants.REAL_PATH + "build_versions.xml");
			}catch (SAXException e) {
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			}
		
		version = versions.get(0);
		return version;
	}
	
	public List<Project> getProjects() {
		List<Project> projects = new ArrayList<Project>();
		try {
			XMLReader reader = XMLReaderFactory.createXMLReader();
			ProjectHandler handler = new ProjectHandler(projects);
			reader.setContentHandler(handler);
			reader.parse(Constants.REAL_PATH + "projects.xml");
			}catch (SAXException e) {
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			}
		return projects;
	}
	
	public List<PropertyParameter> getVersionsOfProject(int projectId) {
		List<PropertyParameter> versions = new ArrayList<PropertyParameter>();
		try {
			XMLReader reader = XMLReaderFactory.createXMLReader();
			VersionsHandler handler = new VersionsHandler(projectId, versions, Constants.PROJECT);
			reader.setContentHandler(handler);
			reader.parse(Constants.REAL_PATH + "build_versions.xml");
			}catch (SAXException e) {
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			}
		return versions;
	
	}

}
