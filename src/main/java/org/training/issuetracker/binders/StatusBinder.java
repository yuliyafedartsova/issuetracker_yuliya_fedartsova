package org.training.issuetracker.binders;

import java.beans.PropertyEditorSupport;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.training.issuetracker.model.beans.Property;
import org.training.issuetracker.model.beans.properties.Status;
import org.training.issuetracker.services.PropertyService;
import org.training.issuetracker.services.StatusService;

public class StatusBinder extends PropertyEditorSupport {
	@Autowired
	private StatusService statusService;
	
	@Override
	public void setAsText(String id) throws IllegalArgumentException {
		Status status = null;
		if(id != null && !id.isEmpty()) {
			status = statusService.getById(Integer.valueOf(id));
		}
		setValue(status);
	}
}
