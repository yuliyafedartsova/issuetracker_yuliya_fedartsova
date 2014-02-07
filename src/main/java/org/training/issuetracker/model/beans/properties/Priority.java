package org.training.issuetracker.model.beans.properties;

import org.training.issuetracker.model.beans.Property;

public class Priority extends Property {

	public Priority(int id, String name) {
		super(id, name);
	}
	
	public Priority(String name) {
		super(name);
	}
}
