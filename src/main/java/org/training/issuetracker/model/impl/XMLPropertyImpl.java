package org.training.issuetracker.model.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.ifaces.PropertyDAO;
import org.training.issuetracker.model.beans.PropertyParameter;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import utils.handlers.ParametrsHandler;

public class XMLPropertyImpl implements PropertyDAO {
	
	private List<PropertyParameter> getParameters(String resourceName) throws DaoException {
		List<PropertyParameter> parameters = new ArrayList<PropertyParameter>();
		try {
			XMLReader reader = XMLReaderFactory.createXMLReader();
			ParametrsHandler handler = new ParametrsHandler(parameters);
			reader.setContentHandler(handler);
			reader.parse(Constants.PATH + resourceName + Constants.FILE_EXT);
			}catch (SAXException e) {
				throw new DaoException();
			}catch (IOException e) {
				throw new DaoException();
			}
	    return parameters;
	}
	
	private PropertyParameter getParameterById(String resourceName, int id) throws DaoException {
		 PropertyParameter  parameter = null;
		 List<PropertyParameter> parameters = getParameters(resourceName);
		 for(PropertyParameter par : parameters) {
			 if(par.getId() == id) {
				parameter = par;
			 }
		 }
		 return parameter;
	}
	
	
	
	public List<PropertyParameter> getStatuses() throws DaoException{
		List<PropertyParameter> statuses = getParameters(Constants.STATUSES_SOURCE_NAME);
	    return statuses;
    }
	
	public List<PropertyParameter> getTypes() throws DaoException{
		List<PropertyParameter> types = getParameters(Constants.TYPES_SOURCE_NAME);
	    return types;
	}
	
	public List<PropertyParameter> getPriorities() throws DaoException{
		List<PropertyParameter> priorities = getParameters(Constants.PRIORITIES_SOURCE_NAME);
	    return priorities;
	}
	
	public List<PropertyParameter> getResolutions() throws DaoException{
		List<PropertyParameter> resolutions = getParameters(Constants.RESOLUTIONS_SOURCE_NAME);
	    return resolutions;
	}

	public List<PropertyParameter> getRoles() throws DaoException{
		List<PropertyParameter> roles = getParameters(Constants.ROLES_SOURCE_NAME);
	    return roles;
	}
	
	public PropertyParameter getStatusById(int id) throws DaoException{
		PropertyParameter status = getParameterById(Constants.STATUSES_SOURCE_NAME, id);
		return status;
	}
	
	public PropertyParameter getTypeById(int id) throws DaoException{
		PropertyParameter type = getParameterById(Constants.TYPES_SOURCE_NAME, id);
		return type;
	}

	public PropertyParameter getResolutionById(int id) throws DaoException{
		PropertyParameter resolution = getParameterById(Constants.RESOLUTIONS_SOURCE_NAME, id);
		return resolution;
	}
	
	public PropertyParameter getPriorityById(int id) throws DaoException{
		PropertyParameter priority = getParameterById(Constants.PRIORITIES_SOURCE_NAME, id);
		return priority;
	}
	
	public PropertyParameter getRoleById(int id) throws DaoException{
		PropertyParameter role = getParameterById(Constants.ROLES_SOURCE_NAME, id);
		return role;
	}

}
