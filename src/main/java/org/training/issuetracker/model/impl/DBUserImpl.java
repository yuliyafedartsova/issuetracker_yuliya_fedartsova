package org.training.issuetracker.model.impl;

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
import org.training.issuetracker.model.DAO.PropertyDAO;
import org.training.issuetracker.model.DAO.UserDAO;
import org.training.issuetracker.model.beans.Project;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.model.beans.properties.Role;
import org.training.issuetracker.model.factories.PropertyFactory;
import org.training.issuetracker.utils.ConnectionManager;

public class DBUserImpl implements UserDAO {
	public User getUserById(int id) throws DaoException {
		User user = null;
		PropertyDAO propertyDAO = PropertyFactory.getClassFromFactory();
		ConnectionManager manager = new ConnectionManager();
		Connection connection = manager.getConnection();
		ResultSet rs = null;
		try {
		PreparedStatement ptmSelectUser = 
				connection.prepareStatement(ConstantsSQL.SELECT_USER_BY_ID);
		ptmSelectUser.setInt(1, id);
		rs = ptmSelectUser.executeQuery();
		rs.next();
		String firstName = rs.getString(ConstantsSQL.FIRST_NAME_COLUMN);
		String lastName = rs.getString(ConstantsSQL.LAST_NAME_COLUMN);
		String email = rs.getString(ConstantsSQL.EMAIL_COLUMN);
		int roleId = rs.getInt(ConstantsSQL.ROLE_ID_COLUMN);
		String password = rs.getString(5);
		Role role = propertyDAO.getRoleById(roleId);
		user = new User(id, firstName, lastName, email, role, password);
		}catch (SQLException e) {
			e.printStackTrace();
		} 
		return user;
	}
	
	public User getUser(String email, String password) throws ValidationException, DaoException {
		PropertyDAO propertyDAO = PropertyFactory.getClassFromFactory();
		User user = null;
		ConnectionManager manager = new ConnectionManager();
		Connection connection = manager.getConnection();
		ResultSet rs = null;
		try {
		PreparedStatement ptmSelectUser = 
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
		Role role = propertyDAO.getRoleById(roleId);
		user = new User(id, firstName, lastName, email, role, password);
		}catch (SQLException e) {
			e.printStackTrace();
		} 
		return user;
	}
	
	public List<User> getUsers() throws DaoException {
		PropertyDAO propertyDAO = PropertyFactory.getClassFromFactory();
		List<User> users = new ArrayList<User>();
		ConnectionManager manager = new ConnectionManager();
		Connection connection = manager.getConnection();
		ResultSet rs = null;
		try {
			Statement ptmSelectUsers = connection.createStatement();
			rs = ptmSelectUsers.executeQuery(ConstantsSQL.SELECT_USERS);		
			while (rs.next()){
				int id = rs.getInt(ConstantsSQL.ID_COLUMN);
			    String firstName = rs.getString(ConstantsSQL.FIRST_NAME_COLUMN);
			    String lastName = rs.getString(ConstantsSQL.LAST_NAME_COLUMN);
			    String email = rs.getString(ConstantsSQL.EMAIL_COLUMN);
			    int roleId = rs.getInt(ConstantsSQL.ROLE_ID_COLUMN);
			    Role role = propertyDAO.getRoleById(roleId);
			    String password = rs.getString(ConstantsSQL.PASSWORD);
			    users.add(new User(id, firstName, lastName, email, role, password));
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		} 
		return users;
	}
	
	public void addUser(User user) throws DaoException {
		if(whetherTheUserExists(user)) {
			return;
		}
		ConnectionManager connectionMng = new ConnectionManager();
		Connection connection = connectionMng.getConnection();
		try {
			PreparedStatement ptmInsertUser = 
				connection.prepareStatement(ConstantsSQL.ADD_USER);
			ptmInsertUser.setString(1, user.getFirstName());	
			ptmInsertUser.setString(2, user.getLastName());
			ptmInsertUser.setString(3, user.getEmail());
			ptmInsertUser.setInt(4, user.getRole().getId());
			ptmInsertUser.setString(5, user.getPassword());
			ptmInsertUser.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	private boolean whetherTheUserExists(User user) {
		boolean exist = false;
		ConnectionManager connectionMng = new ConnectionManager();
		Connection connection = connectionMng.getConnection();
		ResultSet rs = null;
		try {
			PreparedStatement ptmSelectUser = 
					connection.prepareStatement(ConstantsSQL.SELECT_IF_USER_EXISTS);
			ptmSelectUser.setString(1, user.getFirstName());
			ptmSelectUser.setString(2, user.getLastName());
			ptmSelectUser.setString(3, user.getEmail());
			rs = ptmSelectUser.executeQuery();
		    if(rs.next()) {
		    	exist = true;
		    }
		}catch (SQLException e) {
			e.printStackTrace();
		} 
		return exist;
	}
	
	public void updateUserData(User user) throws DaoException {
		ConnectionManager connectionMng = new ConnectionManager();
		Connection connection = connectionMng.getConnection();
		try {
			PreparedStatement ptmUpdateUser = 
				connection.prepareStatement("UPDATE users SET firstName = ?, lastName = ?, " +
							"email = ?, roleId = ?  WHERE id = ?;");
			ptmUpdateUser.setString(1, user.getFirstName());	
			ptmUpdateUser.setString(2, user.getLastName());
			ptmUpdateUser.setString(3, user.getEmail());
			ptmUpdateUser.setInt(4, user.getRole().getId());
			ptmUpdateUser.setInt(5, user.getId());
			ptmUpdateUser.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		} 
	
	
	}
	
	
}