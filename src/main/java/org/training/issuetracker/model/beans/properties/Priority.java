package org.training.issuetracker.model.beans.properties;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.training.issuetracker.model.beans.Property;

@Entity
@Table(name = "PRIORITIES")
public class Priority extends Property {
	
	public Priority() {
		super();
	}
	
	public Priority(int id, String name) {
		super(id, name);
	}
	
	public Priority(String name) {
		super(name);
	}
}
