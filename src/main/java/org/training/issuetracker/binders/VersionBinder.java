package org.training.issuetracker.binders;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.training.issuetracker.services.PropertyService;
import org.training.issuetracker.services.ResolutionService;
import org.training.issuetracker.services.VersionService;

public class VersionBinder extends PropertyEditorSupport {
	
	@Autowired
	private VersionService versionService;
	
	@Override
	public void setAsText(String id) throws IllegalArgumentException {
		setValue(versionService.getById(Integer.valueOf(id)));
	}
}

//