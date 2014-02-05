package org.training.issuetracker.model.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.model.beans.Parameter;

import org.training.issuetracker.model.enums.Properties;
import org.training.issuetracker.model.factories.PropertyFactory;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import org.training.issuetracker.model.DAO.PropertyDAO;
import org.training.issuetracker.utils.handlers.ParametrsHandler;

public class XMLPropertyImpl implements PropertyDAO {
	
	private List<Parameter> getParameters(String resourceName) throws DaoException {
		List<Parameter> parameters = new ArrayList<Parameter>();
		try {
			XMLReader reader = XMLReaderFactory.createXMLReader();
			ParametrsHandler handler = new ParametrsHandler(parameters);
			reader.setContentHandler(handler);
			reader.parse(Constants.PATH + Constants.FILES_PACKAGE + resourceName + Constants.FILE_EXT);
			}catch (SAXException e) {
				throw new DaoException();
			}catch (IOException e) {
				throw new DaoException();
			}
	    return parameters;
	}
	
	private Parameter getParameterById(String resourceName, int id) throws DaoException {
		 Parameter  parameter = null;
		 List<Parameter> parameters = getParameters(resourceName);
		 for(Parameter par : parameters) {
			 if(par.getId() == id) {
				parameter = par;
			 }
		 }
		 return parameter;
	}
	
	public List<Parameter> getParametersByPropertyName(String propertyName) throws DaoException {
    	List<Parameter> parametres = null;
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
	
	public Parameter getParameter(String propertyName, int id) throws DaoException {
    	Parameter parameter = null;
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
	
	
	
	
	public List<Parameter> getStatuses() throws DaoException{
		List<Parameter> statuses = getParameters(Constants.STATUSES_SOURCE_NAME);
	    return statuses;
    }
	
	public List<Parameter> getTypes() throws DaoException{
		List<Parameter> types = getParameters(Constants.TYPES_SOURCE_NAME);
	    return types;
	}
	
	public List<Parameter> getPriorities() throws DaoException{
		List<Parameter> priorities = getParameters(Constants.PRIORITIES_SOURCE_NAME);
	    return priorities;
	}
	
	public List<Parameter> getResolutions() throws DaoException{
		List<Parameter> resolutions = getParameters(Constants.RESOLUTIONS_SOURCE_NAME);
	    return resolutions;
	}

	public List<Parameter> getRoles() throws DaoException{
		List<Parameter> roles = getParameters(Constants.ROLES_SOURCE_NAME);
	    return roles;
	}
	
	public Parameter getStatusById(int id) throws DaoException{
		Parameter status = getParameterById(Constants.STATUSES_SOURCE_NAME, id);
		return status;
	}
	
	public Parameter getTypeById(int id) throws DaoException{
		Parameter type = getParameterById(Constants.TYPES_SOURCE_NAME, id);
		return type;
	}

	public Parameter getResolutionById(int id) throws DaoException{
		Parameter resolution = getParameterById(Constants.RESOLUTIONS_SOURCE_NAME, id);
		return resolution;
	}
	
	public Parameter getPriorityById(int id) throws DaoException{
		Parameter priority = getParameterById(Constants.PRIORITIES_SOURCE_NAME, id);
		return priority;
	}
	
	public Parameter getRoleById(int id) throws DaoException{
		Parameter role = getParameterById(Constants.ROLES_SOURCE_NAME, id);
		return role;
	}
	
	public void addParameter(Properties propertyName, Parameter parameter) throws DaoException {
		
	}

}
