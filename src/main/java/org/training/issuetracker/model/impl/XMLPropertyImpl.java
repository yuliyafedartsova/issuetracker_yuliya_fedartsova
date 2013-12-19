package org.training.issuetracker.model.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.ifaces.PropertyDAO;
import org.training.issuetracker.model.beans.PropertyParameter;
import org.training.issuetracker.model.handlers.ParametrsHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class XMLPropertyImpl implements PropertyDAO {
	
	private List<PropertyParameter> getParameters(String resourceName) {
		List<PropertyParameter> parameters = new ArrayList<PropertyParameter>();
		try {
			XMLReader reader = XMLReaderFactory.createXMLReader();
			ParametrsHandler handler = new ParametrsHandler(parameters);
			reader.setContentHandler(handler);
			reader.parse(Constants.REAL_PATH + resourceName + ".xml");
			}catch (SAXException e) {
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			}
	    return parameters;
	}
	
	private PropertyParameter getParameterById(String resourceName, int id) {
		 PropertyParameter  parameter = null;
		 List<PropertyParameter> parameters = getParameters(resourceName);
		 for(PropertyParameter par : parameters) {
			 if(par.getId() == id) {
				parameter = par;
			 }
		 }
		 return parameter;
	}
	
	
	
	public List<PropertyParameter> getStatuses() {
		List<PropertyParameter> statuses = getParameters(Constants.STATUSES_SOURCE_NAME);
	    return statuses;
    }
	
	public List<PropertyParameter> getTypes() {
		List<PropertyParameter> types = getParameters(Constants.TYPES_SOURCE_NAME);
	    return types;
	}
	
	public List<PropertyParameter> getPriorities() {
		List<PropertyParameter> priorities = getParameters(Constants.PRIORITIES_SOURCE_NAME);
	    return priorities;
	}
	
	public List<PropertyParameter> getResolutions() {
		List<PropertyParameter> resolutions = getParameters(Constants.RESOLUTIONS_SOURCE_NAME);
	    return resolutions;
	}

	public List<PropertyParameter> getRoles() {
		List<PropertyParameter> roles = getParameters(Constants.ROLES_SOURCE_NAME);
	    return roles;
	}
	
	public PropertyParameter getStatusById(int id) {
		PropertyParameter status = getParameterById(Constants.STATUSES_SOURCE_NAME, id);
		return status;
	}
	
	public PropertyParameter getTypeById(int id) {
		PropertyParameter type = getParameterById(Constants.TYPES_SOURCE_NAME, id);
		return type;
	}

	public PropertyParameter getResolutionById(int id) {
		PropertyParameter resolution = getParameterById(Constants.RESOLUTIONS_SOURCE_NAME, id);
		return resolution;
	}
	
	public PropertyParameter getPriorityById(int id) {
		PropertyParameter priority = getParameterById(Constants.PRIORITIES_SOURCE_NAME, id);
		return priority;
	}
	
	public PropertyParameter getRoleById(int id) {
		PropertyParameter role = getParameterById(Constants.ROLES_SOURCE_NAME, id);
		return role;
	}

}
