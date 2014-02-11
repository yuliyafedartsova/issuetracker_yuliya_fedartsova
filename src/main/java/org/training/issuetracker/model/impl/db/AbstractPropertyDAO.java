package org.training.issuetracker.model.impl.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.constants.ConstantsSQL;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.exceptions.ValidationException;
import org.training.issuetracker.model.beans.Property;
import org.training.issuetracker.utils.ConnectionManager;
import org.training.issuetracker.utils.ValidationManagers.ParameterValidator;


public abstract class AbstractPropertyDAO {
	public Map<Integer, String> getMap(String tableName) throws DaoException {
		final String SELECT_PARAMETERS = "SELECT * FROM " + tableName;
		Map<Integer, String> propertyMap = new HashMap<Integer, String>();
		ConnectionManager manager = null;
		Connection connection = null;
		ResultSet rs = null;
		Statement ptmSelectTypes = null;
		try{
			manager = new ConnectionManager();
			connection = manager.getConnection();
			ptmSelectTypes = connection.createStatement();
			rs = ptmSelectTypes.executeQuery(SELECT_PARAMETERS);	
			while (rs.next()){
				int id = rs.getInt(ConstantsSQL.ID_COLUMN);
			    String name = rs.getString(ConstantsSQL.NAME_COLUMN);
			    propertyMap.put(id, name);
			}
			return propertyMap;
		}catch (SQLException e) {
			throw new DaoException(Constants.DB_PROBLEM);
		}finally {
			if(manager != null) {
				manager.closeStatements(ptmSelectTypes);
				manager.closeResultSets(rs);
				manager.closeConnection();
			}
		}
		
	}
	
	public String getNameById(int id, String tableName) throws DaoException, ValidationException {
		final String SELECT_PARAMETER = "SELECT name FROM " + tableName + 
				" WHERE id = ?;";
		String name = null;
		ConnectionManager manager = null;
		Connection connection = null;
		ResultSet rs = null;
		PreparedStatement ptmSelectParameter = null;
		try{
			manager = new ConnectionManager();
			connection = manager.getConnection();
			ptmSelectParameter = connection.prepareStatement(SELECT_PARAMETER);
			ptmSelectParameter.setInt(1, id);
			rs = ptmSelectParameter.executeQuery();
			if(!rs.next()) {
				throw new ValidationException(Constants.SOME_PROBLEMS);
			}
			name = rs.getString(ConstantsSQL.NAME_COLUMN);
			return name;
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		}finally {
			if(manager != null) {
				manager.closeStatements(ptmSelectParameter);
				manager.closeResultSets(rs);
				manager.closeConnection();
			}
		}
	}
	
	public void add(Property parameter, String tableName) throws DaoException, ValidationException {
		String INSERT_PARAMETER = "INSERT INTO " + tableName + "(name) VALUES (?);";
		String SELECT_PARAMETER = "SELECT id FROM " + tableName + " WHERE name = ?";
		ConnectionManager manager = null;
		Connection connection = null;
		PreparedStatement ptmInsertParameter = null;
		ResultSet rs = null;
		PreparedStatement ptmSelectParameter = null;
		ParameterValidator validator = new ParameterValidator();
		String errorMessage = validator.validateParameter(parameter);
		if(!errorMessage.isEmpty()) {
			throw new ValidationException(errorMessage);
		}
		try {
			manager = new ConnectionManager();
			connection = manager.getConnection();
			ptmInsertParameter = 
					connection.prepareStatement(INSERT_PARAMETER);
			ptmSelectParameter = 
					connection.prepareStatement(SELECT_PARAMETER);
			ptmInsertParameter.setString(1, parameter.getName());
			ptmSelectParameter.setString(1, parameter.getName());
			rs = ptmSelectParameter.executeQuery();
		    synchronized (AbstractPropertyDAO.class) {
				 if(rs.next()) {
				    throw new ValidationException(Constants.PARAMETER_EXIST);
				 }
				 ptmInsertParameter.executeUpdate();
			}
		}catch (SQLException e) {
			throw new DaoException(Constants.DB_PROBLEM);
		}finally {
			if(manager != null) {
				manager.closeStatements(ptmInsertParameter, ptmSelectParameter);
				manager.closeResultSets(rs);
				manager.closeConnection();
			}
		}
	}
	
	public void update(int id, String name, String tableName) throws DaoException, ValidationException {
		String UPDATE_PARAMETER = "UPDATE " + tableName + " SET name = ? WHERE id = ?;";
		String SELECT_PARAMETER = "SELECT id FROM " + tableName + " WHERE name = ?";
		ConnectionManager manager = null;
		Connection connection = null;
		ResultSet rs = null;
		PreparedStatement ptmUpdateParameter = null;
		PreparedStatement ptmSelectParameter = null;
		ParameterValidator validator = new ParameterValidator();
		String errorMessage = validator.validateParameter(name);
		if(!errorMessage.isEmpty()) {
			throw new ValidationException(errorMessage);
		}
		try {
			manager = new ConnectionManager();
			connection = manager.getConnection();
			ptmUpdateParameter = 
					connection.prepareStatement(UPDATE_PARAMETER);
			ptmSelectParameter = 
					connection.prepareStatement(SELECT_PARAMETER);
			ptmUpdateParameter.setString(1, name);
			ptmUpdateParameter.setInt(2, id);
			ptmSelectParameter.setString(1, name);
			rs = ptmSelectParameter.executeQuery();
			synchronized (AbstractPropertyDAO.class) {
				if(rs.next()) {
				    throw new ValidationException(Constants.PARAMETER_EXIST);
				 }
			     ptmUpdateParameter.executeUpdate();
			}
		}catch (SQLException e) {
			throw new DaoException(Constants.DB_PROBLEM);
		}finally {
			if(manager != null) {
				manager.closeStatements(ptmUpdateParameter, ptmSelectParameter);
				manager.closeResultSets(rs);
				manager.closeConnection();
			}
		}
	}
}
