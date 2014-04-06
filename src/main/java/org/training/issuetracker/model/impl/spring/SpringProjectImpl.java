package org.training.issuetracker.model.impl.spring;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.training.issuetracker.model.beans.Project;
import org.training.issuetracker.model.beans.properties.Version;
import org.training.issuetracker.services.ProjectService;
import org.training.issuetracker.services.VersionService;

public class SpringProjectImpl {

	@Autowired
	private ProjectService projectService;
	@Autowired
	private VersionService versionService;
	
	public List<Project> getProjects() {
		return projectService.getProjects();
	}
	
	public Project getProjectById(int id) {
		return projectService.getById(id);
	}
	
	public Version getVersionById(int id) {
		return versionService.getById(id);
	}
	
	public void updateProject(Project project) {
		projectService.update(project);
	}
	
	public void addProject(Project project) {
		projectService.save(project);
	}
	
	public void addVersion(Version version) {
		versionService.save(version);
	}

}
