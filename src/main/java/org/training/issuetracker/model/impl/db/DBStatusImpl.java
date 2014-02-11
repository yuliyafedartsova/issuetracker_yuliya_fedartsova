package org.training.issuetracker.model.impl.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.constants.ConstantsSQL;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.exceptions.ValidationException;
import org.training.issuetracker.model.DAO.StatusesDAO;
import org.training.issuetracker.model.beans.properties.Role;
import org.training.issuetracker.model.beans.properties.Status;
import org.training.issuetracker.utils.ConnectionManager;


public class DBStatusImpl extends AbstractPropertyDAO implements StatusesDAO {
	public List<Status> getAll() throws DaoException {
		Map<Integer, String> map = getMap(Constants.STATUSES_SOURCE_NAME);
		List<Status> statuses = new ArrayList<Status>();
		for(Map.Entry<Integer, String> s : map.entrySet()) {
			statuses.add(new Status(s.getKey(), s.getValue()));
		}
	    return statuses;
	}
	
	public Status getById(int id) throws DaoException, ValidationException {
		String name = getNameById(id, Constants.STATUSES_SOURCE_NAME);
		Status status = new Status(id, name);
	    return status;
	}
	
	public Status getByName(String name) throws DaoException {
		Status status = null;
		final String SELECT_PARAMETER = "SELECT id FROM statuses WHERE name = ?;";
		ConnectionManager manager = new ConnectionManager();
		Connection connection = manager.getConnection();
		ResultSet rs = null;
		PreparedStatement ptmSelectParameter = null;
		try{
			manager = new ConnectionManager();
			connection = manager.getConnection();
			ptmSelectParameter = connection.prepareStatement(SELECT_PARAMETER);
			ptmSelectParameter.setString(1, name);
			rs = ptmSelectParameter.executeQuery();
			rs.next();
			int id = rs.getInt(ConstantsSQL.ID_COLUMN);
			status = new Status(id, name);
			return status;
		}catch (SQLException e) {
			throw new DaoException();
		}finally {
			if(manager != null) {
				manager.closeStatements(ptmSelectParameter);
				manager.closeResultSets(rs);
				manager.closeConnection();
			}
		}
		
	}
	
	public void update(int id, String name) throws DaoException, ValidationException {
		update(id, name, Constants.STATUSES_SOURCE_NAME);
	}
	
	
}
