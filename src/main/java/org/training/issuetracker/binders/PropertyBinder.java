package org.training.issuetracker.binders;

import java.beans.PropertyEditorSupport;

import org.training.issuetracker.model.beans.Property;
import org.training.issuetracker.services.PropertyService;

public class PropertyBinder extends PropertyEditorSupport {
	private Property property;

	@Override
	public void setAsText(String id) throws IllegalArgumentException {
	    if(id != null && !id.isEmpty()) { 
			property = new PropertyService().getById(property, Integer.valueOf(id));
		} else {
			property = null;
		}
		setValue(property);
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}
	
	
}
