package org.training.issuetracker.model.DAO;

import java.util.List;

import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.exceptions.ValidationException;
import org.training.issuetracker.model.beans.properties.Resolution;

public interface ResolutionDAO {
	List<Resolution> getAll() throws DaoException;
	Resolution getById(int id) throws DaoException, ValidationException;
	void add(Resolution parameter) throws DaoException, ValidationException;
    void update(int id, String name) throws DaoException, ValidationException;
}
