package org.training.issuetracker.model.beans.properties;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.training.issuetracker.model.beans.Property;

@Entity
@Table(name = "STATUSES")
public class Status extends Property {

	public Status(int id, String name) {
		super(id, name);
	}
	
	public Status(String name) {
		super(name);
	}
	
	public Status() {
		super();
	}
	
}
