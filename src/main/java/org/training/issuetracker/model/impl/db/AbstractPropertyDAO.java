package org.training.issuetracker.model.impl.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.training.issuetracker.constants.ConstantsSQL;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.model.beans.Property;
import org.training.issuetracker.utils.ConnectionManager;

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
			throw new DaoException(e);
		}finally {
			if(manager != null) {
				manager.closeStatements(ptmSelectTypes);
				manager.closeResultSets(rs);
				manager.closeConnection();
			}
		}
		
	}
	
	public String getNameById(int id, String tableName) throws DaoException {
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
			rs.next();
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
	
	public void add(Property parameter, String tableName) throws DaoException {
		String INSERT_PARAMETER = "INSERT INTO " + tableName + "(name) VALUES (?);";
		ConnectionManager manager = null;
		Connection connection = null;
		PreparedStatement ptmInsertParameter = null;
		try {
			manager = new ConnectionManager();
			connection = manager.getConnection();
			ptmInsertParameter = 
					connection.prepareStatement(INSERT_PARAMETER);
			ptmInsertParameter.setString(1, parameter.getName());
			synchronized (AbstractPropertyDAO.class) {
			if(whetherTheParameterExists(parameter.getName(), tableName, connection)) {
				return;
			}
			ptmInsertParameter.executeUpdate();
			}
		}catch (SQLException e) {
			throw new DaoException(e);
		}finally {
			if(manager != null) {
				manager.closeStatements(ptmInsertParameter);
				manager.closeConnection();
			}
		}
	
	}
	
	public boolean whetherTheParameterExists(String name, String tableName, Connection connection) throws DaoException, SQLException {
		String SELECT_PARAMETER = "SELECT id FROM " + tableName + " WHERE name = ?";
		boolean exist = false;
		ResultSet rs = null;
		PreparedStatement ptmSelectParameter = null;
		try {
			ptmSelectParameter = 
					connection.prepareStatement(SELECT_PARAMETER);
			ptmSelectParameter.setString(1, name);
			rs = ptmSelectParameter.executeQuery();
		    if(rs.next()) {
		    	exist = true;
		    }
		    return exist;
		}catch (SQLException e) {
			throw new DaoException(e);
		}finally {
			rs.close();
			ptmSelectParameter.close();
		} 
	}
	
	public void update(int id, String name, String tableName) throws DaoException {
		String UPDATE_PARAMETER = "UPDATE " + tableName + " SET name = ? WHERE id = ?;";
		ConnectionManager manager = null;
		Connection connection = null;
		PreparedStatement ptmUpdateParameter = null;
		try {
			manager = new ConnectionManager();
			connection = manager.getConnection();
			ptmUpdateParameter = 
					connection.prepareStatement(UPDATE_PARAMETER);
			ptmUpdateParameter.setString(1, name);
			ptmUpdateParameter.setInt(2, id);
			synchronized (AbstractPropertyDAO.class) {
			if(whetherTheParameterExists(name, tableName, connection)) {
				return; 
			}
			ptmUpdateParameter.executeUpdate();
			}
		}catch (SQLException e) {
			throw new DaoException(e);
		}finally {
			if(manager != null) {
				manager.closeStatements(ptmUpdateParameter);
				manager.closeConnection();
			}
		}
	}
}
