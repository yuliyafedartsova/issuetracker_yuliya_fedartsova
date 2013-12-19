package org.training.issuetracker.ifaces;

import java.util.List;

import org.training.issuetracker.exceptions.ValidationException;
import org.training.issuetracker.model.beans.User;


public interface UserDAO  {
	
	User getUserById(int id);
	User getUser(String email, String password) throws ValidationException;
	List<User> getUsers();
}
