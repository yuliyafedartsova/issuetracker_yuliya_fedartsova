package org.training.issuetracker.ifaces;

import java.util.List;

import org.training.issuetracker.model.beans.Project;
import org.training.issuetracker.model.beans.PropertyParameter;

public interface ProjectDAO {
	List<Project> getProjects();
	Project getProjectById(int id);
	PropertyParameter getVersionById(int id);
	List<PropertyParameter> getVersionsOfProject(int projectId);
}
