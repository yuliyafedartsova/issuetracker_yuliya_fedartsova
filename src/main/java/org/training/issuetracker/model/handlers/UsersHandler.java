package org.training.issuetracker.model.handlers;
import java.util.List;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.enums.Role;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class UsersHandler extends DefaultHandler {
	
	
	public UsersHandler(List<User> users) {
		this.users = users;
	}
	
	private static enum UsersXMLEnum {
		USERS, USER, ID, FIRSTNAME, LASTNAME, EMAIL, ROLE, PASSWORD;
	}
	
	private List<User> users;
	private UsersXMLEnum currentEnum = null;
	private String currentId;
	private String currentFirstName;
	private String currentLastName;
	private String currentEmail;
	private String currentRole;
	private String currentPassword;
	
	
	
	public void startElement(String uri, String localName, String qName, Attributes attrs) {
	   currentEnum = UsersXMLEnum.valueOf(qName.toUpperCase());
	}
	
	public void characters(char[] ch, int start, int length) {
		if(currentEnum == UsersXMLEnum.ID) {
			String s = new String(ch, start, length).trim();
			if(!s.isEmpty()){
				currentId = s;
			}
		}
		
		if(currentEnum == UsersXMLEnum.FIRSTNAME) {
			String s = new String(ch, start, length).trim();
			if(!s.isEmpty()){
				currentFirstName = s;
			}
		}
		if(currentEnum == UsersXMLEnum.LASTNAME) {
			String s = new String(ch, start, length).trim();
			if(!s.isEmpty()){
				currentLastName = s;
			}
		}
		if(currentEnum == UsersXMLEnum.EMAIL) {
			String s = new String(ch, start, length).trim();
			if(!s.isEmpty()){
				currentEmail = s;
			}
		}
		
		if(currentEnum == UsersXMLEnum.ROLE) {
			String s = new String(ch, start, length).trim();
			if(!s.isEmpty()){
				currentRole = s;
			}
		}
		if(currentEnum == UsersXMLEnum.PASSWORD) {
			String s = new String(ch, start, length).trim();
			if(!s.isEmpty()){
				currentPassword = s;
			}
		}
	}
	
	public void endElement(String uri, 
			String localName, String qName) { 
			currentEnum = UsersXMLEnum.valueOf(qName.toUpperCase()); //!!!
			if(currentEnum == UsersXMLEnum.USER) {
				users.add(new User(Integer.parseInt(currentId), currentFirstName, currentLastName,currentEmail,
					Role.valueOf(currentRole), currentPassword));
			}
	} 



}


