package org.training.issuetracker.model.beans.properties;

import org.training.issuetracker.model.beans.Property;

public class Version extends Property {

	public Version(int id, String name) {
		super(id, name);
	}
	
	public Version(String name) {
		super(name);
	}
}
