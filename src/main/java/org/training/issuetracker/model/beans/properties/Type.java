package org.training.issuetracker.model.beans.properties;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.training.issuetracker.model.beans.Property;

@Entity
@Table(name = "TYPES")
public class Type extends Property {

	public Type(int id, String name) {
		super(id, name);
	}
	
	public Type(String name) {
		super(name);
	}
}
