package org.training.issuetracker.model.DAO;

import java.util.List;

import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.model.beans.properties.Resolution;

public interface ResolutionDAO {
	List<Resolution> getAll() throws DaoException;
	Resolution getById(int id) throws DaoException;
//	Resolution getByName(String name) throws DaoException;
	void add(Resolution parameter) throws DaoException;
//    void update(Resolution parameter) throws DaoException;
}
