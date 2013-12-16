package org.training.issuetracker.model.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.ifaces.UserDAO;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.model.handlers.UsersHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class XMLUserImpl implements UserDAO {
	public List<User> getUsers() {
		List<User> users = new ArrayList<User>();
		try {
			XMLReader reader = XMLReaderFactory.createXMLReader();
			UsersHandler handler = new UsersHandler(users);
			reader.setContentHandler(handler);
			reader.parse(Constants.REAL_PATH + "users.xml");
			}catch (SAXException e) {
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			}
		return users;
	}
	
	public User getUser(String email, String password) {
		List<User> users = getUsers();
		User user = null;
		for(User u : users) {
			if(u.getEmail().equals(email) && u.getPassword().equals(password)) {
				user = u;
			}
		}
		return user;
	}
	
	public User getUserById(int id) {
		List<User> users = getUsers();
		User user = null;
		for(User u : users) {
			if(u.getId() == id) {
				user = u;
			}
		}
		return user;
	}
}
