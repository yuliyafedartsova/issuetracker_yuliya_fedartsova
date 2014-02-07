package org.training.issuetracker.model.beans.properties;

import org.training.issuetracker.model.beans.Property;

public class Role extends Property {

	public Role(int id, String name) {
		super(id, name);
	}
	
	public Role(String name) {
		super(name);
	}
	
}
