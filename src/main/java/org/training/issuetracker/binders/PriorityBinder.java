package org.training.issuetracker.binders;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.training.issuetracker.model.beans.properties.Priority;
import org.training.issuetracker.services.IssueService;
import org.training.issuetracker.services.PriorityService;;

public class PriorityBinder extends PropertyEditorSupport {
	@Autowired
	private PriorityService priorityService;
	
	@Override
	public void setAsText(String id) throws IllegalArgumentException {
		Priority priority = null;
		if(id != null && !id.isEmpty()) {
			priority = priorityService.getById(Integer.valueOf(id));
		}
		setValue(priority);
	}

}
