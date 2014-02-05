package org.training.issuetracker.model.impl;

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
import org.training.issuetracker.model.DAO.PropertyDAO;
import org.training.issuetracker.model.beans.Issue;
import org.training.issuetracker.model.beans.Parameter;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.model.enums.Properties;
import org.training.issuetracker.utils.ConnectionManager;

public class DBPropertyImpl implements PropertyDAO {
	public List<Parameter> getStatuses() throws DaoException {
		return getParameters(Constants.STATUSES_SOURCE_NAME);
	}
	
	public Parameter getStatusById(int id) throws DaoException {
		return getParameterById(id, Constants.STATUSES_SOURCE_NAME);
	}
	
	public List <Parameter> getTypes() throws DaoException {
		return getParameters(Constants.TYPES_SOURCE_NAME);
	}
	
	public Parameter getTypeById(int id) throws DaoException {
		return getParameterById(id, Constants.TYPES_SOURCE_NAME);
	}
	
	public List<Parameter> getPriorities() throws DaoException {
		return getParameters(Constants.PRIORITIES_SOURCE_NAME);
	}
	
	
	public Parameter getPriorityById(int id) throws DaoException {
		return getParameterById(id, Constants.PRIORITIES_SOURCE_NAME);
	}
	
	public List<Parameter> getResolutions() throws DaoException {
		return getParameters(Constants.RESOLUTIONS_SOURCE_NAME);
	}
	
	public Parameter getResolutionById(int id) throws DaoException {
		return getParameterById(id, Constants.RESOLUTIONS_SOURCE_NAME);
	}
	
	public List<Parameter> getRoles() throws DaoException {
		return getParameters(Constants.ROLES_SOURCE_NAME);
	}
	
	public Parameter getRoleById(int id) throws DaoException {
		return getParameterById(id, Constants.ROLES_SOURCE_NAME);
	}
	
	public List<Parameter> getParametersByPropertyName(String propertyName) throws DaoException {
		List<Parameter> parametres = new ArrayList<Parameter>();
		switch(propertyName) {
		case Constants.STATUS:
			parametres = getStatuses();
			break;
		case Constants.TYPE:
			parametres = getTypes();
			break;
		case Constants.PRIORITY:
			parametres = getPriorities();
			break;
		case Constants.RESOLUTION:
			parametres = getResolutions();
			break;
    	}
    	return parametres;
	}
	
	public Parameter getParameter(String propertyName, int id) throws DaoException {
    	Parameter parameter = null;
    	switch(propertyName) {
		case Constants.STATUS:
			parameter = getStatusById(id);
			break;
		case Constants.TYPE:
			parameter = getTypeById(id);
			break;
		case Constants.PRIORITY:
			parameter = getPriorityById(id);
			break;
		case Constants.RESOLUTION:
			parameter = getResolutionById(id);
			break;
    	}
    	return parameter;
    }
	
	private List<Parameter> getParameters(String tableName) throws DaoException {
		final String SELECT_PARAMETERS = "SELECT * FROM " + tableName;
		List<Parameter> parameters = new ArrayList<Parameter>();
		ConnectionManager manager = new ConnectionManager();
		Connection connection = manager.getConnection();
		ResultSet rs = null;
		try{
			Statement ptmSelectTypes = connection.createStatement();
			rs = ptmSelectTypes.executeQuery(SELECT_PARAMETERS);	
			while (rs.next()){
				int id = rs.getInt(ConstantsSQL.ID_COLUMN);
			    String name = rs.getString(ConstantsSQL.NAME_COLUMN);
			    parameters.add(new Parameter(id, name));
			}
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
		return parameters;
	}
	
	private Parameter getParameterById(int id, String tableName) throws DaoException {
		final String SELECT_PARAMETER = "SELECT name FROM " + tableName + 
				" WHERE id = ?;";
		Parameter parameter = null;
		ConnectionManager manager = new ConnectionManager();
		Connection connection = manager.getConnection();
		ResultSet rs = null;
		try{
			PreparedStatement ptmSelectParameter = connection.prepareStatement(SELECT_PARAMETER);
			ptmSelectParameter.setInt(1, id);
			rs = ptmSelectParameter.executeQuery();
			rs.next();
			String name = rs.getString(ConstantsSQL.NAME_COLUMN);
			parameter = new Parameter(id, name);
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
		return parameter;
	}
	
	public void addParameter(Properties propertyName, Parameter parameter) throws DaoException {
		switch(propertyName) {
		case TYPE:
			add(parameter, Constants.TYPES_SOURCE_NAME);
			break;
		case STATUS:
			add(parameter, Constants.STATUSES_SOURCE_NAME);
			break;
		case RESOLUTION:
			add(parameter, Constants.RESOLUTIONS_SOURCE_NAME);
			break;
		case PRIORITY:
			add(parameter, Constants.PRIORITIES_SOURCE_NAME);
			break;
    	}
	}
	
	private void add(Parameter parameter, String tableName) {
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
	
	private boolean whetherTheParameterExists(Parameter parameter, String tableName) {
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
