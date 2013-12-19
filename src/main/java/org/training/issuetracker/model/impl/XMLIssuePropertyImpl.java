package org.training.issuetracker.model.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.ifaces.IssuePropertyDAO;
import org.training.issuetracker.model.beans.PropertyParameter;
import org.training.issuetracker.model.handlers.ParametrsHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class XMLIssuePropertyImpl implements IssuePropertyDAO {
	public List<PropertyParameter> getPropertyParameters(String propertyName) {
		List<PropertyParameter> parameters = new ArrayList<PropertyParameter>();
		try {
			XMLReader reader = XMLReaderFactory.createXMLReader();
			ParametrsHandler handler = new ParametrsHandler(parameters);
			reader.setContentHandler(handler);
			reader.parse(Constants.REAL_PATH + propertyName + ".xml");
			}catch (SAXException e) {
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			}
	    return parameters;
	}
	
	 public PropertyParameter getPropertyParameterById(String propertyName, int id) {
		 PropertyParameter  parameter = null;
		 List<PropertyParameter> parameters = getPropertyParameters(propertyName);
		 for(PropertyParameter par : parameters) {
			 if(par.getId() == id) {
				parameter = par;
			 }
		 }
		 return parameter;
	 }
	 
	// public Map <String, List <PropertyParameter>> getPropertiesMap() {
	//	 Map <String, List <PropertyParameter>> map = new HashMap <String, List <PropertyParameter>>();
	//	 for(int i = 0; i < Properties.values().length; i++) {
	//  	    	String propertyname = Properties.values()[i].toString().toLowerCase();
	//  	        List<PropertyParameter> parametres = getPropertyParameters(propertyname); 
	//  	        map.put(propertyname, parametres);
	//  	 }
		 
	//	 return map;
	// }
	 
	 public void updateProperty(int id, String propertyName, String newValue) {
		 
	 }
}
