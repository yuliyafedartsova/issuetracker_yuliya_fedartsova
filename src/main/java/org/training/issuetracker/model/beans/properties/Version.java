package org.training.issuetracker.model.beans.properties;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.training.issuetracker.model.beans.Property;

@Entity
@Table(name = "VERSIONS")
public class Version extends Property {

	public Version() {
		super("test");
	}
	
	public Version(int id, String name) {
		super(id, name);
	}
	
	public Version(String name) {
		super(name);
	}
}
