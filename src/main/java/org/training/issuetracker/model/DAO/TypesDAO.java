package org.training.issuetracker.model.DAO;

import java.util.List;

import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.exceptions.ValidationException;

import org.training.issuetracker.model.beans.properties.Status;
import org.training.issuetracker.model.beans.properties.Type;

public interface TypesDAO  {
	List<Type> getAll() throws DaoException;
	Type getById(int id) throws DaoException;
	void add(Type parameter) throws DaoException, ValidationException;
	void update(int id, String name) throws DaoException, ValidationException;
}
