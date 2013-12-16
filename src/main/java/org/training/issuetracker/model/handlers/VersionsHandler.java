package org.training.issuetracker.model.handlers;

import java.util.List;

import org.training.issuetracker.model.beans.PropertyParameter;
import org.training.issuetracker.model.beans.User;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class VersionsHandler extends DefaultHandler {
	
	public VersionsHandler(int id, List<PropertyParameter> versions, String idOf) {
		this.versions = versions;
		this.id = id;
		this.idOf = idOf;
	}
	
	private static enum VersionsXMLEnum {
		VERSIONS, VERSION, ID, NAME, PROJECT;
	}
	
	private List<PropertyParameter> versions;
	private int id;
	String idOf;
	private VersionsXMLEnum currentEnum = null;
	private int currentId;
	private String currentName;
	private String currentProject;
	
	public void startElement(String uri, String localName, String qName, Attributes attrs) {
		   currentEnum = VersionsXMLEnum.valueOf(qName.toUpperCase());
		}

	public void characters(char[] ch, int start, int length) {
		if(currentEnum == VersionsXMLEnum.ID) {
			String s = new String(ch, start, length).trim();
			if(!s.isEmpty()){
				currentId = Integer.parseInt(s);
			}
		}
		
		if(currentEnum == VersionsXMLEnum.NAME) {
			String s = new String(ch, start, length).trim();
			if(!s.isEmpty()){
				currentName = s;
			}
		}
		
		if(currentEnum == VersionsXMLEnum.PROJECT) {
			String s = new String(ch, start, length).trim();
			if(!s.isEmpty()){
				currentProject = s;
			}
		}
	}
	
	public void endElement(String uri, 
			String localName, String qName) { 
			currentEnum = VersionsXMLEnum.valueOf(qName.toUpperCase()); //!!!
			if(currentEnum == VersionsXMLEnum.VERSION) {
			   switch(idOf) {
			   case "Version":
				   if(currentId == id) {
					  versions.add(new PropertyParameter(currentId, currentName));
				   }
			   case "Project" :
				   if(Integer.parseInt(currentProject) == id) {
					   versions.add(new PropertyParameter(currentId, currentName)); 
				   }
			   
			   }
			}
	  } 
}
