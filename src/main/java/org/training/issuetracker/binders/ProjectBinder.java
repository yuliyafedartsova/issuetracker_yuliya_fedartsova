package org.training.issuetracker.binders;

import java.beans.PropertyEditorSupport;

import org.training.issuetracker.model.beans.Project;
import org.training.issuetracker.services.ProjectService;
import org.training.issuetracker.services.StatusService;

public class ProjectBinder extends PropertyEditorSupport {
	@Override
	public void setAsText(String id) throws IllegalArgumentException {
		Project project = null;
		if(id != null && !id.isEmpty()) {
			project = new ProjectService().getById(Integer.valueOf(id));
		}
		setValue(project);
	}
}
