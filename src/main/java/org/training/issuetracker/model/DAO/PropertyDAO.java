package org.training.issuetracker.model.DAO;

import java.util.List;

import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.model.beans.PropertyParameter;

public interface PropertyDAO {
	List<PropertyParameter> getStatuses() throws DaoException;
	PropertyParameter getStatusById(int id) throws DaoException;
	List<PropertyParameter> getTypes() throws DaoException;
	PropertyParameter getTypeById(int id) throws DaoException;
	List<PropertyParameter> getPriorities() throws DaoException;
	PropertyParameter getPriorityById(int id) throws DaoException;
	List<PropertyParameter> getResolutions() throws DaoException;
	PropertyParameter getResolutionById(int id) throws DaoException;
	List<PropertyParameter> getRoles() throws DaoException;
	PropertyParameter getRoleById(int id) throws DaoException;
	List<PropertyParameter> getParametersByPropertyName(String propertyName) throws DaoException;
	PropertyParameter getParameter(String propertyName, int id) throws DaoException;

}
