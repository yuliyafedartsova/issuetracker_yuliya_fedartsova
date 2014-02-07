package org.training.issuetracker.model.beans.properties;

import org.training.issuetracker.model.beans.Property;

public class Status extends Property {

	public Status(int id, String name) {
		super(id, name);
	}
	
	public Status(String name) {
		super(name);
	}
	
}
