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
import org.training.issuetracker.model.beans.Property;
import org.training.issuetracker.utils.ConnectionManager;

public abstract class AbstractPropertyDAO {
	public List<? extends Property> getAll(String tableName) throws DaoException {
		final String SELECT_PARAMETERS = "SELECT * FROM " + tableName;
	    ConnectionManager manager = new ConnectionManager();
		Connection connection = manager.getConnection();
		ResultSet rs = null;
		List<Property> parameters = new ArrayList<Property>();
		
		try{
			Statement ptmSelectTypes = connection.createStatement();
			rs = ptmSelectTypes.executeQuery(SELECT_PARAMETERS);	
			while (rs.next()){
				int id = rs.getInt(ConstantsSQL.ID_COLUMN);
			    String name = rs.getString(ConstantsSQL.NAME_COLUMN);
			    Property parameter = new Property(id, name);
			    parameters.add(new Property(id, name));
			}
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
		return parameters;
	}
	
	public Property getById(int id, String tableName) throws DaoException {
		final String SELECT_PARAMETER = "SELECT name FROM " + tableName + 
				" WHERE id = ?;";
		Property parameter = null;
		ConnectionManager manager = new ConnectionManager();
		Connection connection = manager.getConnection();
		ResultSet rs = null;
		try{
			PreparedStatement ptmSelectParameter = connection.prepareStatement(SELECT_PARAMETER);
			ptmSelectParameter.setInt(1, id);
			rs = ptmSelectParameter.executeQuery();
			rs.next();
			String name = rs.getString(ConstantsSQL.NAME_COLUMN);
			parameter = new Property(id, name);
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
		return parameter;
	}
	
	public void add(Property parameter, String tableName) throws DaoException {
		String INSERT_PARAMETER = "INSERT INTO " + tableName + "(name) VALUES (?);";
		if(whetherTheParameterExists(parameter, tableName)) {
			return;
		}
		ConnectionManager manager = new ConnectionManager();
		Connection connection = manager.getConnection();
		try {
			PreparedStatement ptmInsertParameter = 
					connection.prepareStatement(INSERT_PARAMETER);
			ptmInsertParameter.setString(1, parameter.getName());
			ptmInsertParameter.executeUpdate();
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
	
	}
	
	public boolean whetherTheParameterExists(Property parameter, String tableName) {
		String SELECT_PARAMETER = "SELECT id FROM " + tableName + "WHERE name = ?";
		boolean exist = false;
		ConnectionManager connectionMng = new ConnectionManager();
		Connection connection = connectionMng.getConnection();
		ResultSet rs = null;
		try {
			PreparedStatement ptmSelectParameter = 
					connection.prepareStatement(SELECT_PARAMETER);
			ptmSelectParameter.setString(1, parameter.getName());
			rs = ptmSelectParameter.executeQuery();
		    if(rs.next()) {
		    	exist = true;
		    }
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
		return exist;
	}

}
