package org.training.issuetracker.model.DAO;

import java.util.List;

import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.model.beans.properties.Priority;


public interface PrioritiesDAO {
	List<Priority> getAll() throws DaoException;
	Priority getById(int id) throws DaoException;
//	Priority getParameterByName(String name) throws DaoException;
	void add(Priority parameter) throws DaoException;
 //   void update(Priority parameter) throws DaoException;
}
