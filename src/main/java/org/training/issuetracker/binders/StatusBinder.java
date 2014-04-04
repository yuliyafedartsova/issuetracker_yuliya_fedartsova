package org.training.issuetracker.binders;

import java.beans.PropertyEditorSupport;

import org.training.issuetracker.model.beans.Property;
import org.training.issuetracker.model.beans.properties.Status;
import org.training.issuetracker.services.PropertyService;
import org.training.issuetracker.services.StatusService;

public class StatusBinder extends PropertyEditorSupport {
	@Override
	public void setAsText(String id) throws IllegalArgumentException {
		Status status = null;
		if(id != null && !id.isEmpty()) {
			status = new StatusService().getById(Integer.valueOf(id));
		}
		setValue(status);
	}
}
