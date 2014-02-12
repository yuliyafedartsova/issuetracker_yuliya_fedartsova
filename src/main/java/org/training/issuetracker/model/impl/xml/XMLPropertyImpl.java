package org.training.issuetracker.model.impl.xml;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.training.issuetracker.constants.Configurations;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.model.beans.Property;
import org.training.issuetracker.model.enums.Properties;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import org.training.issuetracker.utils.handlers.ParametrsHandler;

public class XMLPropertyImpl  {
	
	private List<Property> getParameters(String resourceName) throws DaoException {
		List<Property> parameters = new ArrayList<Property>();
		try {
			XMLReader reader = XMLReaderFactory.createXMLReader();
			ParametrsHandler handler = new ParametrsHandler(parameters);
			reader.setContentHandler(handler);
			reader.parse(Configurations.PATH + Constants.FILES_PACKAGE + resourceName + Constants.FILE_EXT);
			}catch (SAXException e) {
				throw new DaoException();
			}catch (IOException e) {
				throw new DaoException();
			}
	    return parameters;
	}
	
	private Property getParameterById(String resourceName, int id) throws DaoException {
		 Property  parameter = null;
		 List<Property> parameters = getParameters(resourceName);
		 for(Property par : parameters) {
			 if(par.getId() == id) {
				parameter = par;
			 }
		 }
		 return parameter;
	}
	
	public List<Property> getParametersByPropertyName(String propertyName) throws DaoException {
    	List<Property> parametres = null;
    	switch(propertyName) {
		case Constants.STATUS:
			parametres = getStatuses();
			break;
		case Constants.TYPE:
			parametres = getTypes();
			break;
		case Constants.PRIORITY:
			parametres = getPriorities();
			break;
		case Constants.RESOLUTION:
			parametres = getResolutions();
			break;
    	}
    	return parametres;
    }
	
	public Property getParameter(String propertyName, int id) throws DaoException {
		Property parameter = null;
    	switch(propertyName) {
		case Constants.STATUS:
			parameter = getStatusById(id);
			break;
		case Constants.TYPE:
			parameter = getTypeById(id);
			break;
		case Constants.PRIORITY:
			parameter = getPriorityById(id);
			break;
		case Constants.RESOLUTION:
			parameter = getResolutionById(id);
			break;
    	}
    	return parameter;
    }
	
	
	
	
	public List<Property> getStatuses() throws DaoException{
		List<Property> statuses = getParameters(Constants.STATUSES_SOURCE_NAME);
	    return statuses;
    }
	
	public List<Property> getTypes() throws DaoException{
		List<Property> types = getParameters(Constants.TYPES_SOURCE_NAME);
	    return types;
	}
	
	public List<Property> getPriorities() throws DaoException{
		List<Property> priorities = getParameters(Constants.PRIORITIES_SOURCE_NAME);
	    return priorities;
	}
	
	public List<Property> getResolutions() throws DaoException{
		List<Property> resolutions = getParameters(Constants.RESOLUTIONS_SOURCE_NAME);
	    return resolutions;
	}

	public List<Property> getRoles() throws DaoException{
		List<Property> roles = getParameters(Constants.ROLES_SOURCE_NAME);
	    return roles;
	}
	
	public Property getStatusById(int id) throws DaoException{
		Property status = getParameterById(Constants.STATUSES_SOURCE_NAME, id);
		return status;
	}
	
	public Property getTypeById(int id) throws DaoException{
		Property type = getParameterById(Constants.TYPES_SOURCE_NAME, id);
		return type;
	}

	public Property getResolutionById(int id) throws DaoException{
		Property resolution = getParameterById(Constants.RESOLUTIONS_SOURCE_NAME, id);
		return resolution;
	}
	
	public Property getPriorityById(int id) throws DaoException{
		Property priority = getParameterById(Constants.PRIORITIES_SOURCE_NAME, id);
		return priority;
	}
	
	public Property getRoleById(int id) throws DaoException{
		Property role = getParameterById(Constants.ROLES_SOURCE_NAME, id);
		return role;
	}
	
	public void addParameter(Properties propertyName, Property parameter) throws DaoException {
		
	}

}
