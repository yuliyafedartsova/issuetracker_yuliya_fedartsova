package org.training.issuetracker.binders;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.training.issuetracker.model.beans.Project;
import org.training.issuetracker.services.PriorityService;
import org.training.issuetracker.services.ProjectService;
import org.training.issuetracker.services.StatusService;

public class ProjectBinder extends PropertyEditorSupport {
	@Autowired
	private ProjectService projectService;
	
	
	@Override
	public void setAsText(String id) throws IllegalArgumentException {
		Project project = null;
		if(id != null && !id.isEmpty()) {
			project = projectService.getById(Integer.valueOf(id));
		}
		setValue(project);
	}
}
