package org.training.issuetracker.model.impl.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.training.issuetracker.constants.ConstantsSQL;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.exceptions.ValidationException;
import org.training.issuetracker.model.DAO.RolesDAO;
import org.training.issuetracker.model.DAO.TypesDAO;
import org.training.issuetracker.model.DAO.UserDAO;
import org.training.issuetracker.model.beans.Project;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.model.beans.properties.Role;
import org.training.issuetracker.model.factories.RoleFactory;
import org.training.issuetracker.model.factories.TypeFactory;
import org.training.issuetracker.utils.ConnectionManager;
import org.training.issuetracker.utils.ValidationManagers.UserValidator;

public class DBUserImpl implements UserDAO {
	public User getUserById(int id) throws DaoException {
		User user = null;
		RolesDAO rolesDAO = RoleFactory.getClassFromFactory();
		ConnectionManager manager = new ConnectionManager();
		Connection connection = manager.getConnection();
		PreparedStatement ptmSelectUser = null;
		ResultSet rs = null;
		try {
			manager = new ConnectionManager();
			connection = manager.getConnection();
			ptmSelectUser = 
				connection.prepareStatement(ConstantsSQL.SELECT_USER_BY_ID);
			ptmSelectUser.setInt(1, id);
			rs = ptmSelectUser.executeQuery();
			rs.next();
			String firstName = rs.getString(ConstantsSQL.FIRST_NAME_COLUMN);
			String lastName = rs.getString(ConstantsSQL.LAST_NAME_COLUMN);
			String email = rs.getString(ConstantsSQL.EMAIL_COLUMN);
			int roleId = rs.getInt(ConstantsSQL.ROLE_ID_COLUMN);
			String password = rs.getString(5);
			Role role = rolesDAO.getById(roleId);
			user = new User(id, firstName, lastName, email, role, password);
			return user;
		}catch (SQLException e) {
			throw new DaoException(e);
		}finally {
			if(manager != null) {
				manager.closeStatements(ptmSelectUser);
				manager.closeResultSets(rs);
				manager.closeConnection();
			}
		}
	}
	
	public User getUser(String email, String password) throws ValidationException, DaoException {
		RolesDAO rolesDAO = RoleFactory.getClassFromFactory();
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
			ptmSelectUser.setString(1, email);
			ptmSelectUser.setString(2, password);
			rs = ptmSelectUser.executeQuery();
			if (!rs.next()) {
				throw new ValidationException("Wrong email or password");
			}
			int id = rs.getInt(ConstantsSQL.ID_COLUMN);
			String firstName = rs.getString(ConstantsSQL.FIRST_NAME_COLUMN);
			String lastName = rs.getString(ConstantsSQL.LAST_NAME_COLUMN);
			int roleId = rs.getInt(ConstantsSQL.ROLE_ID_COLUMN);
			Role role = rolesDAO.getById(roleId);
			user = new User(id, firstName, lastName, email, role, password);
			return user;
		}catch (SQLException e) {
			throw new DaoException(e);
		}finally {
			if(manager != null) {
				manager.closeStatements(ptmSelectUser);
				manager.closeResultSets(rs);
				manager.closeConnection();
			}
		}
	}
	
	public List<User> getUsers() throws DaoException {
		RolesDAO rolesDAO = RoleFactory.getClassFromFactory();
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
				int id = rs.getInt(ConstantsSQL.ID_COLUMN);
			    String firstName = rs.getString(ConstantsSQL.FIRST_NAME_COLUMN);
			    String lastName = rs.getString(ConstantsSQL.LAST_NAME_COLUMN);
			    String email = rs.getString(ConstantsSQL.EMAIL_COLUMN);
			    int roleId = rs.getInt(ConstantsSQL.ROLE_ID_COLUMN);
			    Role role = rolesDAO.getById(roleId);
			    String password = rs.getString(ConstantsSQL.PASSWORD);
			    users.add(new User(id, firstName, lastName, email, role, password));
			}
			return users;
		}catch (SQLException e) {
			throw new DaoException(e);
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
		UserValidator validator = new UserValidator();
		String errorMessage = null;
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
			ptmInsertUser.setString(1, user.getFirstName());	
			ptmInsertUser.setString(2, user.getLastName());
			ptmInsertUser.setString(3, user.getEmail());
			ptmInsertUser.setInt(4, user.getRole().getId());
			ptmInsertUser.setString(5, user.getPassword());
			synchronized (DBUserImpl.class) {
				if(whetherTheUserExists(user, connection)) {
					return;
				}
				ptmInsertUser.executeUpdate();
			}
		}catch (SQLException e) {
			throw new DaoException(e);
		}finally {
			if(connectionMng != null) {
				connectionMng.closeStatements(ptmInsertUser);
				connectionMng.closeConnection();
			}
		}
	}
	
	private boolean whetherTheUserExists(User user, Connection connection) throws DaoException, SQLException  {
		boolean exist = false;
		PreparedStatement ptmSelectUser = null;
		ResultSet rs = null;
		try {
			ptmSelectUser = 
					connection.prepareStatement(ConstantsSQL.SELECT_IF_USER_EXISTS);
			ptmSelectUser.setString(1, user.getFirstName());
			ptmSelectUser.setString(2, user.getLastName());
			ptmSelectUser.setString(3, user.getEmail());
			rs = ptmSelectUser.executeQuery();
		    if(rs.next()) {
		    	exist = true;
		    }
		    return exist;
		}catch (SQLException e) {
			throw new DaoException(e);
		}finally {
			rs.close();
			ptmSelectUser.close();
		} 
	}
	
	public void updateUserData(User user) throws DaoException, ValidationException {
		ConnectionManager connectionMng = null;
		Connection connection = null;
		PreparedStatement ptmUpdateUser = null;
		UserValidator validator = new UserValidator();
		String errorMessage = null;
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
				connection.prepareStatement("UPDATE users SET firstName = ?, lastName = ?, " +
							"email = ?, roleId = ?, password = ?  WHERE id = ?;");
			ptmUpdateUser.setString(1, user.getFirstName());	
			ptmUpdateUser.setString(2, user.getLastName());
			ptmUpdateUser.setString(3, user.getEmail());
			ptmUpdateUser.setInt(4, user.getRole().getId());
			ptmUpdateUser.setString(5, user.getPassword());
			ptmUpdateUser.setInt(6, user.getId());
			synchronized (DBUserImpl.class) {
				if(whetherTheUserExists(user, connection)) {
					return;
				}
				ptmUpdateUser.executeUpdate();
			}
		}catch (SQLException e) {
			throw new DaoException(e);
		}finally {
			if(connectionMng != null) {
				connectionMng.closeStatements(ptmUpdateUser);
				connectionMng.closeConnection();
			}
		}
	}
	
}