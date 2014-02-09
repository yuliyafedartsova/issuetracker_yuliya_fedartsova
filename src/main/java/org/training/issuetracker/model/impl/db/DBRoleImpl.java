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
import org.training.issuetracker.model.DAO.RolesDAO;
import org.training.issuetracker.model.beans.properties.Role;
import org.training.issuetracker.model.beans.properties.Status;
import org.training.issuetracker.model.beans.properties.Type;
import org.training.issuetracker.utils.ConnectionManager;

public class DBRoleImpl  extends AbstractPropertyDAO implements RolesDAO {
	public List<Role> getAll() throws DaoException {
		Map<Integer, String> map = getMap(Constants.ROLES_SOURCE_NAME);
		List<Role> roles = new ArrayList<Role>();
		for(Map.Entry<Integer, String> s : map.entrySet()) {
			roles.add(new Role(s.getKey(), s.getValue()));
		}
	    return roles;
	}
	
	public Role getById(int id) throws DaoException {
		String name = getNameById(id, Constants.ROLES_SOURCE_NAME);
	    Role role = new Role(id, name);
		return role;
	}
	
	public Role getByName(String name) throws DaoException {
		Role role = null;
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
			role = new Role(id, name);
			return role;
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


}
