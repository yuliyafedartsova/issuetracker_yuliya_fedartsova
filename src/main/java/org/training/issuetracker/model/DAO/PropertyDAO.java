package org.training.issuetracker.model.DAO;

import java.util.List;

import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.model.beans.Parameter;
import org.training.issuetracker.model.enums.Properties;

public interface PropertyDAO {
	List<Parameter> getStatuses() throws DaoException;
	Parameter getStatusById(int id) throws DaoException;
	List <Parameter> getTypes() throws DaoException;
	Parameter getTypeById(int id) throws DaoException;
	List<Parameter> getPriorities() throws DaoException;
	Parameter getPriorityById(int id) throws DaoException;
	List<Parameter> getResolutions() throws DaoException;
	Parameter getResolutionById(int id) throws DaoException;
	List<Parameter> getRoles() throws DaoException;
	Parameter getRoleById(int id) throws DaoException;
	List<Parameter> getParametersByPropertyName(String propertyName) throws DaoException;
	Parameter getParameter(String propertyName, int id) throws DaoException;
	void addParameter(Properties propertyName, Parameter parameter) throws DaoException;


}
