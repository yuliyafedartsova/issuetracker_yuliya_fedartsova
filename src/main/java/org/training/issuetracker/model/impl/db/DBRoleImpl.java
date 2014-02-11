package org.training.issuetracker.model.impl.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.exceptions.ValidationException;
import org.training.issuetracker.model.DAO.RolesDAO;
import org.training.issuetracker.model.beans.properties.Role;


public class DBRoleImpl  extends AbstractPropertyDAO implements RolesDAO {
	public List<Role> getAll() throws DaoException {
		Map<Integer, String> map = getMap(Constants.ROLES_SOURCE_NAME);
		List<Role> roles = new ArrayList<Role>();
		for(Map.Entry<Integer, String> s : map.entrySet()) {
			roles.add(new Role(s.getKey(), s.getValue()));
		}
	    return roles;
	}
	
	public Role getById(int id) throws DaoException, ValidationException {
		String name = getNameById(id, Constants.ROLES_SOURCE_NAME);
	    Role role = new Role(id, name);
		return role;
	}
}
