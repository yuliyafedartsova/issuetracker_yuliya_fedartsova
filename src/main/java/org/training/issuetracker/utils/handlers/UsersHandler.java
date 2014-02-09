package org.training.issuetracker.utils.handlers;

import java.util.List;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.model.beans.properties.Role;
import org.training.issuetracker.model.factories.RoleFactory;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import org.training.issuetracker.model.DAO.RolesDAO;

public class UsersHandler extends DefaultHandler {
	
	
	public UsersHandler(List<User> users) {
		this.users = users;
	}
	
	private static enum UsersXMLEnum {
		USERS, USER, ID, FIRSTNAME, LASTNAME, EMAIL, ROLE, PASSWORD;
	}
	
	private List<User> users;
	private UsersXMLEnum currentEnum = null;
	private int currentId;
	private String currentFirstName;
	private String currentLastName;
	private String currentEmail;
	private Role currentRole;
	private String currentPassword;
	RolesDAO rolesDAO = RoleFactory.getClassFromFactory();
	
	
	public void startElement(String uri, String localName, String qName, Attributes attrs) {
	   currentEnum = UsersXMLEnum.valueOf(qName.toUpperCase());
	}
	
	public void characters(char[] ch, int start, int length) {
		if(currentEnum == UsersXMLEnum.ID) {
			String s = new String(ch, start, length).trim();
			if(!s.isEmpty()){
				currentId = Integer.parseInt(s);
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
				int id = Integer.parseInt(s);
				try {
				currentRole = rolesDAO.getById(id);
				} catch (DaoException e) {
					throw new RuntimeException(e);
				}
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
			currentEnum = UsersXMLEnum.valueOf(qName.toUpperCase()); 
			if(currentEnum == UsersXMLEnum.USER) {
				users.add(new User(currentId, currentFirstName, currentLastName,currentEmail,
						currentRole, currentPassword));
			}
	} 
}



