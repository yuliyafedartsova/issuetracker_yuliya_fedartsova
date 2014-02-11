package org.training.issuetracker.model.DAO;

import java.util.List;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.exceptions.ValidationException;
import org.training.issuetracker.model.beans.Project;
import org.training.issuetracker.model.beans.properties.Version;

public interface ProjectDAO {
	List<Project> getProjects() throws DaoException, ValidationException;
	Project getProjectById(int id) throws DaoException, ValidationException;
	Version getVersionById(int id) throws DaoException, ValidationException;
	List<Version> getVersionsOfProject(int projectId) throws DaoException;
    void addProject(Project project) throws DaoException, ValidationException;
    void addVersion(String version, int projectId) throws DaoException, ValidationException;
    void updateProject(Project project) throws DaoException, ValidationException;
}
