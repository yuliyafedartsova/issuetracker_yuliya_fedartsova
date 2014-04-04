package org.training.issuetracker.binders;

import java.beans.PropertyEditorSupport;

import org.training.issuetracker.services.PropertyService;
import org.training.issuetracker.services.VersionService;

public class VersionBinder extends PropertyEditorSupport {
	@Override
	public void setAsText(String id) throws IllegalArgumentException {
		setValue(new VersionService().getById(Integer.valueOf(id)));
	}
}

//