package org.training.issuetracker.model.impl;

import java.util.List;

import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.model.DAO.RolesDAO;
import org.training.issuetracker.model.beans.properties.Role;

public class DBRoleImpl  extends AbstractPropertyDAO implements RolesDAO {
	public List<Role> getAll() throws DaoException {
		List<Role> roles = (List<Role>)getAll(Constants.ROLES_SOURCE_NAME);
		return roles;
	}
	
	public Role getById(int id) throws DaoException {
		Role role = (Role)getById(id, Constants.ROLES_SOURCE_NAME);
	    return role;
	}


}
