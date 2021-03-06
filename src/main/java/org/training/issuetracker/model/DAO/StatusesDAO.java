package org.training.issuetracker.model.DAO;

import java.util.List;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.exceptions.ValidationException;
import org.training.issuetracker.model.beans.properties.Status;

public interface StatusesDAO {
	List<Status> getAll() throws DaoException;
	Status getById(int id) throws DaoException, ValidationException;
	Status getByName(String name) throws DaoException;
	void update(int id, String name) throws DaoException, ValidationException;
}
