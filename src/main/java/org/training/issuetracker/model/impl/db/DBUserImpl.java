package org.training.issuetracker.model.impl.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.constants.ConstantsSQL;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.exceptions.ValidationException;
import org.training.issuetracker.model.DAO.RolesDAO;
import org.training.issuetracker.model.DAO.UserDAO;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.model.beans.properties.Role;
import org.training.issuetracker.model.factories.RoleFactory;
import org.training.issuetracker.utils.ConnectionManager;
import org.training.issuetracker.utils.ValidationManagers.UserValidator;

public class DBUserImpl implements UserDAO {
	public User getUserById(int id) throws DaoException, ValidationException {
		User user = null;
		ConnectionManager manager = new ConnectionManager();
		Connection connection = manager.getConnection();
		PreparedStatement ptmSelectUser = null;
		ResultSet rs = null;
		try {
			manager = new ConnectionManager();
			connection = manager.getConnection();
			ptmSelectUser = 
				connection.prepareStatement(ConstantsSQL.SELECT_USER_BY_ID);
			ptmSelectUser.setInt(Constants.INDEX_1, id);
			rs = ptmSelectUser.executeQuery();
			if(!rs.next()){
				throw new ValidationException(Constants.SOME_PROBLEMS);
			}
			String firstName = rs.getString(Constants.INDEX_1);
			String lastName = rs.getString(Constants.INDEX_2);
			String email = rs.getString(Constants.INDEX_3);
			Role role = new Role(rs.getInt(Constants.INDEX_4), rs.getString(Constants.INDEX_5));
			String password = rs.getString(Constants.INDEX_6);
			user = new User(id, firstName, lastName, email, role, password);
			return user;
		}catch (SQLException e) {
			throw new DaoException(Constants.DB_PROBLEM);
		}finally {
			if(manager != null) {
				manager.closeStatements(ptmSelectUser);
				manager.closeResultSets(rs);
				manager.closeConnection();
			}
		}
	}
	
	public User getUser(String email, String password) throws ValidationException, DaoException {
		final String ERROR_MESSAGE = "Wrong email or password";
		User user = null;
		ConnectionManager manager = null;
		Connection connection = null;
		ResultSet rs = null;
		PreparedStatement ptmSelectUser = null;
		try {
			manager = new ConnectionManager();
			connection = manager.getConnection();
			ptmSelectUser = 
				connection.prepareStatement(ConstantsSQL.SELECT_USER_BY_EMAIL_AND_PASSWORD);	
			ptmSelectUser.setString(Constants.INDEX_1, email);
			ptmSelectUser.setString(Constants.INDEX_2, password);
			rs = ptmSelectUser.executeQuery();
			if (!rs.next()) {
				throw new ValidationException(ERROR_MESSAGE);
			}
			int id = rs.getInt(Constants.INDEX_1);
			String firstName = rs.getString(Constants.INDEX_2);
			String lastName = rs.getString(Constants.INDEX_3);
			Role role = new Role(rs.getInt(Constants.INDEX_4), rs.getString(Constants.INDEX_5));
			user = new User(id, firstName, lastName, email, role, password);
			return user;
		}catch (SQLException e) {
			throw new DaoException(Constants.DB_PROBLEM);
		}finally {
			if(manager != null) {
				manager.closeStatements(ptmSelectUser);
				manager.closeResultSets(rs);
				manager.closeConnection();
			}
		}
	}
	
	public List<User> getUsers() throws DaoException, ValidationException {
		List<User> users = new ArrayList<User>();
		ConnectionManager manager = null;
		Connection connection = null;
		ResultSet rs = null;
		Statement ptmSelectUsers = null;
		try {
			manager = new ConnectionManager();
			connection = manager.getConnection();
			ptmSelectUsers = connection.createStatement();
			rs = ptmSelectUsers.executeQuery(ConstantsSQL.SELECT_USERS);		
			while (rs.next()){
				int id = rs.getInt(Constants.INDEX_1);
			    String firstName = rs.getString(Constants.INDEX_2);
			    String lastName = rs.getString(Constants.INDEX_3);
			    String email = rs.getString(Constants.INDEX_4);
			    Role role = new Role(rs.getInt(Constants.INDEX_5), rs.getString(Constants.INDEX_6));
			    String password = rs.getString(ConstantsSQL.PASSWORD);
			    users.add(new User(id, firstName, lastName, email, role, password));
			}
			return users;
		}catch (SQLException e) {
			throw new DaoException(Constants.DB_PROBLEM);
		}finally {
			if(manager != null) {
				manager.closeStatements(ptmSelectUsers);
				manager.closeResultSets(rs);
				manager.closeConnection();
			}
		}
	}
	
	public void addUser(User user) throws DaoException, ValidationException {
		ConnectionManager connectionMng = new ConnectionManager();
		Connection connection = connectionMng.getConnection();
		PreparedStatement ptmInsertUser = null;
		PreparedStatement ptmSelectUser = null;
		UserValidator validator = new UserValidator();
		String errorMessage = null;
		ResultSet rs = null;
		errorMessage = validator.validateUserFields(user);
		if(!errorMessage.isEmpty()) {
			throw new ValidationException(errorMessage);
		}
		errorMessage = validator.validateUserValues(user);
		if(!errorMessage.isEmpty()) {
			throw new ValidationException(errorMessage);
		}
		try {
			connectionMng = new ConnectionManager();
			connection = connectionMng.getConnection();
			ptmInsertUser = 
				connection.prepareStatement(ConstantsSQL.ADD_USER);
			ptmSelectUser = 
					connection.prepareStatement(ConstantsSQL.SELECT_IF_USER_EXISTS);
			ptmInsertUser.setString(Constants.INDEX_1, user.getFirstName());	
			ptmInsertUser.setString(Constants.INDEX_2, user.getLastName());
			ptmInsertUser.setString(Constants.INDEX_3, user.getEmail());
			ptmInsertUser.setInt(Constants.INDEX_4, user.getRole().getId());
			ptmInsertUser.setString(Constants.INDEX_5, user.getPassword());
			ptmSelectUser.setString(Constants.INDEX_1, user.getEmail());
			ptmSelectUser.setString(Constants.INDEX_2, user.getPassword());
			rs = ptmSelectUser.executeQuery();
			synchronized (DBUserImpl.class) {
				if(rs.next()) {
					throw new ValidationException(Constants.USER_EXIST);
				}
				ptmInsertUser.executeUpdate();
			}
		}catch (SQLException e) {
			throw new DaoException(Constants.DB_PROBLEM);
		}finally {
			if(connectionMng != null) {
				connectionMng.closeStatements(ptmInsertUser, ptmSelectUser);
				connectionMng.closeResultSets(rs);
				connectionMng.closeConnection();
			}
		}
	}
	
	public void updateUserData(User user) throws DaoException, ValidationException {
		ConnectionManager connectionMng = null;
		Connection connection = null;
		PreparedStatement ptmUpdateUser = null;
		PreparedStatement ptmSelectUser = null;
		UserValidator validator = new UserValidator();
		String errorMessage = null;
		ResultSet rs = null;
		errorMessage = validator.validateUserFields(user);
		if(!errorMessage.isEmpty()) {
			throw new ValidationException(errorMessage);
		}
		errorMessage = validator.validateUserValues(user);
		if(!errorMessage.isEmpty()) {
			throw new ValidationException(errorMessage);
		}
		try {
			connectionMng = new ConnectionManager();
			connection = connectionMng.getConnection();
			ptmUpdateUser = 
				connection.prepareStatement(ConstantsSQL.UPDATE_USER);
			ptmSelectUser = 
					connection.prepareStatement(ConstantsSQL.SELECT_IF_USER_EXISTS);
			ptmUpdateUser.setString(Constants.INDEX_1, user.getFirstName());	
			ptmUpdateUser.setString(Constants.INDEX_2, user.getLastName());
			ptmUpdateUser.setString(Constants.INDEX_3, user.getEmail());
			ptmUpdateUser.setInt(Constants.INDEX_4, user.getRole().getId());
			ptmUpdateUser.setString(Constants.INDEX_5, user.getPassword());
			ptmUpdateUser.setInt(Constants.INDEX_6, user.getId());
			ptmSelectUser.setString(Constants.INDEX_1, user.getEmail());
			ptmSelectUser.setString(Constants.INDEX_2, user.getPassword());
			rs = ptmSelectUser.executeQuery();
			synchronized (DBUserImpl.class) {
				if(rs.next()) {
					int id = rs.getInt(Constants.INDEX_1);
					if(id != user.getId()) {
						throw new ValidationException(Constants.USER_EXIST);
					}
				}
			ptmUpdateUser.executeUpdate();
			}
		}catch (SQLException e) {
			throw new DaoException(Constants.DB_PROBLEM);
		}finally {
			if(connectionMng != null) {
				connectionMng.closeStatements(ptmUpdateUser, ptmSelectUser);
				connectionMng.closeResultSets(rs);
				connectionMng.closeConnection();
			}
		}
	}
	
}