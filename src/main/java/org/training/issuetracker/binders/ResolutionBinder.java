package org.training.issuetracker.binders;

import java.beans.PropertyEditorSupport;

import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.model.beans.properties.Resolution;
import org.training.issuetracker.services.ResolutionService;
import org.training.issuetracker.services.UserService;

public class ResolutionBinder extends PropertyEditorSupport {
	@Override
	public void setAsText(String id) throws IllegalArgumentException {
		Resolution resolution = null;
		if(id != null && !id.isEmpty()) {
			resolution = new ResolutionService().getById(Integer.valueOf(id));
		}
		setValue(resolution);
	}
}
