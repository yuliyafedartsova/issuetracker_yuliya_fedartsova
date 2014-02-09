package org.training.issuetracker.model.impl.xml;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.model.beans.Project;
import org.training.issuetracker.model.beans.properties.Version;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import org.training.issuetracker.model.DAO.ProjectDAO;
import org.training.issuetracker.utils.handlers.ProjectHandler;
import org.training.issuetracker.utils.handlers.VersionsHandler;

public class XMLProjectImpl implements ProjectDAO {
	public Project getProjectById(int id) throws DaoException {
		Project project = null;
		List<Project> projects = getProjects();
	    for(Project p : projects) {
	    	if(p.getId() == id) {
	    		project = p;
	    	}
	    }
	    return project;
	}
	
	public Version getVersionById(int id) throws DaoException {
		Version version = null;
		List<Version> versions = new ArrayList<Version>();
		try {
			XMLReader reader = XMLReaderFactory.createXMLReader();
			VersionsHandler handler = new VersionsHandler(id, versions, Constants.VERSION);
			reader.setContentHandler(handler);
			reader.parse(Constants.PATH + Constants.FILES_PACKAGE + Constants.VERSIONS_SOURCE_NAME + Constants.FILE_EXT);
			}catch (SAXException e) {
				throw new DaoException();
			}catch (IOException e) {
				throw new DaoException();
			}
		version = versions.get(0);
		return version;
	}
	
	public List<Project> getProjects() throws DaoException {
		List<Project> projects = new ArrayList<Project>();
		try {
			XMLReader reader = XMLReaderFactory.createXMLReader();
			ProjectHandler handler = new ProjectHandler(projects);
			reader.setContentHandler(handler);
			reader.parse(Constants.PATH + Constants.FILES_PACKAGE + Constants.PROJECTS_SOURCE_NAME + Constants.FILE_EXT);
			}catch (SAXException e) {
				throw new DaoException();
			}catch (IOException e) {
				throw new DaoException();
			}
		return projects;
	}
	
	public List<Version> getVersionsOfProject(int projectId) throws DaoException {
		List<Version> versions = new ArrayList<Version>();
		try {
			XMLReader reader = XMLReaderFactory.createXMLReader();
			VersionsHandler handler = new VersionsHandler(projectId, versions, Constants.PROJECT);
			reader.setContentHandler(handler);
			reader.parse(Constants.PATH + Constants.FILES_PACKAGE + Constants.VERSIONS_SOURCE_NAME + Constants.FILE_EXT);
			}catch (SAXException e) {
				throw new DaoException();
			}catch (IOException e) {
				throw new DaoException();
			}
		return versions;
	
	}
	
	public void addProject(Project project) throws DaoException {
		
	}
	
	public void addVersion(String version, int projectId) throws DaoException {
		
	}
	
	public void updateProject(Project project) throws DaoException {
		
	}

}
