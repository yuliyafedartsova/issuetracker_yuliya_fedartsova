package org.training.issuetracker.model.DAO;

import java.util.List;

import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.model.beans.Parameter;
import org.training.issuetracker.model.beans.Project;


public interface ProjectDAO {
	List<Project> getProjects() throws DaoException;
	Project getProjectById(int id) throws DaoException;
	Parameter getVersionById(int id) throws DaoException;
	List<Parameter> getVersionsOfProject(int projectId) throws DaoException;
    void addProject(Project project) throws DaoException;


}
