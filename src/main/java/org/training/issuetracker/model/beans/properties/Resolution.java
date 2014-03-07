package org.training.issuetracker.model.beans.properties;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.training.issuetracker.model.beans.Property;

@Entity
@Table(name = "RESOLUTIONS")
public class Resolution extends Property {

	public Resolution(int id, String name) {
		super(id, name);
		
	}
	
	public Resolution(String name) {
		super(name);
		
	}
	

}
