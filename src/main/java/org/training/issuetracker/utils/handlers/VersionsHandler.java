package org.training.issuetracker.utils.handlers;

import java.util.List;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.model.beans.properties.Version;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class VersionsHandler extends DefaultHandler {
	
	public VersionsHandler(int id, List<Version> versions, String idOf) {
		this.versions = versions;
		this.id = id;
		this.idOf = idOf;
	}

	private static enum VersionsXMLEnum {
		VERSIONS, VERSION, ID, NAME, PROJECT;
	}
	
	private List<Version> versions;
	private int id;
	String idOf;
	private VersionsXMLEnum currentEnum = null;
	private int currentId;
	private String currentName;
	private int currentProjectId;
	
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
				currentProjectId = Integer.parseInt(s);
			}
		}
	}
	
	public void endElement(String uri, 
			String localName, String qName) { 
			currentEnum = VersionsXMLEnum.valueOf(qName.toUpperCase()); 
			if(currentEnum == VersionsXMLEnum.VERSION) {
			   switch(idOf) {
			   case Constants.VERSION:
				   if(currentId == id) {
					  versions.add(new Version(currentId, currentName));
				   }
			   case Constants.PROJECT :
				   if(currentProjectId == id) {
					   versions.add(new Version(currentId, currentName)); 
				   }
			   
			   }
			}
	  } 
}