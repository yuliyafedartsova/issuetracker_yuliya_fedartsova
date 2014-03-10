package org.training.issuetracker.model.beans.properties;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.training.issuetracker.model.beans.Property;

@Entity
@Table(name = "ROLES")
public class Role extends Property {

	public Role(int id, String name) {
		super(id, name);
	}
	
	public Role(String name) {
		super(name);
	}
	
	public Role() {
		super();
	}
	
}
