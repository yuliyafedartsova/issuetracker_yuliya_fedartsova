package org.training.issuetracker.model.DAO;

import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.model.beans.Property;

public interface IssuePropertyDAO extends PropertyDAO {
	void add(Property parameter) throws DaoException;
    void update(Property parameter) throws DaoException;
}
