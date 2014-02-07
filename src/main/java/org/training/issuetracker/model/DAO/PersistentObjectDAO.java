package org.training.issuetracker.model.DAO;

import java.util.List;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.model.beans.Persistent;


public interface PersistentObjectDAO {
	 Persistent getById(int id) throws DaoException;
	 List<? extends Persistent> getAll() throws DaoException;
	 void add(Persistent object) throws DaoException;
	 void update(Persistent object) throws DaoException;
}
