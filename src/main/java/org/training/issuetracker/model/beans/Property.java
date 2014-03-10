package org.training.issuetracker.model.beans;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)

public class Property extends Persistent {
	
	private String name;

	public Property(int id, String name) {
		super(id);
		this.name = name;
	}
	
	public Property(String name) {
		this.name = name;
	}

	public Property() {
		super();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
	
}
