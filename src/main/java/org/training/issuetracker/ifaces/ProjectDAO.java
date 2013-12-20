package org.training.issuetracker.ifaces;

import java.util.List;

import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.model.beans.Project;
import org.training.issuetracker.model.beans.PropertyParameter;

public interface ProjectDAO {
	List<Project> getProjects() throws DaoException;
	Project getProjectById(int id) throws DaoException;
	PropertyParameter getVersionById(int id) throws DaoException;
	List<PropertyParameter> getVersionsOfProject(int projectId) throws DaoException;
}
