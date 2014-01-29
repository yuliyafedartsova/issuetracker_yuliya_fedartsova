package org.training.issuetracker.model.impl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.exceptions.ValidationException;
import org.training.issuetracker.model.beans.User;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import DAO.UserDAO;

import utils.handlers.UsersHandler;

public class XMLUserImpl implements UserDAO {
	public List<User> getUsers() throws DaoException {
		List<User> users = new ArrayList<User>();
		try {
			XMLReader reader = XMLReaderFactory.createXMLReader();
			UsersHandler handler = new UsersHandler(users);
			reader.setContentHandler(handler);
			reader.parse(Constants.PATH + Constants.USERS_SOURCE_NAME + Constants.FILE_EXT);
			}catch (SAXException e) {
				throw new DaoException();
			}catch (IOException e) {
				throw new DaoException();
			}
		return users;
	}
	
	public User getUser(String email, String password) throws ValidationException, DaoException {
		List<User> users = getUsers();
		User user = null;
		for(User u : users) {
			if(u.getEmail().equals(email) && u.getPassword().equals(password)) {
				user = u;
			}
		}
		if(user == null) throw new ValidationException(Constants.WRONG_LOGIN);
		return user;
	}
	
	public User getUserById(int id) throws DaoException {
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
