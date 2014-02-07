package org.training.issuetracker.model.DAO;

import java.util.List;

import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.model.beans.properties.Role;

public interface RolesDAO {
	List<Role> getAll() throws DaoException;
	Role getById(int id) throws DaoException;
}
