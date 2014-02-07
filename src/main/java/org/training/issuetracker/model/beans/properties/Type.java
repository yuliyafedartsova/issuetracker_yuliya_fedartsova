package org.training.issuetracker.model.beans.properties;

import org.training.issuetracker.model.beans.Property;

public class Type extends Property {

	public Type(int id, String name) {
		super(id, name);
	}
	
	public Type(String name) {
		super(name);
	}
}
