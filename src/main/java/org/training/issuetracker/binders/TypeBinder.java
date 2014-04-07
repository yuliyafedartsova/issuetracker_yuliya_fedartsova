package org.training.issuetracker.binders;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.training.issuetracker.model.beans.properties.Status;
import org.training.issuetracker.model.beans.properties.Type;
import org.training.issuetracker.services.ResolutionService;
import org.training.issuetracker.services.StatusService;
import org.training.issuetracker.services.TypeService;

public class TypeBinder extends PropertyEditorSupport {
	
	@Autowired
	private TypeService typeService;
	
	
	@Override
	public void setAsText(String id) throws IllegalArgumentException {
		Type type = null;
		if(id != null && !id.isEmpty()) {
			type = typeService.getById(Integer.valueOf(id));
		}
		setValue(type);
	}




}
