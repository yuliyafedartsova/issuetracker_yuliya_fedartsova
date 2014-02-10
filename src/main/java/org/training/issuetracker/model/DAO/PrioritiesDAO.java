package org.training.issuetracker.model.DAO;

import java.util.List;

import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.exceptions.ValidationException;
import org.training.issuetracker.model.beans.properties.Priority;


public interface PrioritiesDAO {
	List<Priority> getAll() throws DaoException;
	Priority getById(int id) throws DaoException;
	void add(Priority parameter) throws DaoException, ValidationException;
	void update(int id, String name) throws DaoException, ValidationException;
}
