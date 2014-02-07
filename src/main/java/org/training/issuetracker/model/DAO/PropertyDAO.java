package org.training.issuetracker.model.DAO;

import java.util.List;

import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.model.beans.Property;
import org.training.issuetracker.model.beans.properties.Priority;
import org.training.issuetracker.model.beans.properties.Resolution;
import org.training.issuetracker.model.beans.properties.Role;
import org.training.issuetracker.model.beans.properties.Status;
import org.training.issuetracker.model.beans.properties.Type;


public interface PropertyDAO extends PersistentObjectDAO {
	List<? extends Property> getAllParameters() throws DaoException;
    Property getParameterById(int id) throws DaoException;
    Property getParameterByName(String name) throws DaoException;
}
