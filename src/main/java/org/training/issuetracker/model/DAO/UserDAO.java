package org.training.issuetracker.model.DAO;

import java.util.List;

import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.exceptions.ValidationException;
import org.training.issuetracker.model.beans.User;

public interface UserDAO {
	User getUserById(int id) throws DaoException;
	User getUser(String email, String password) throws ValidationException, DaoException;
	List<User> getUsers() throws DaoException;
	void addUser(User user) throws DaoException;

}
