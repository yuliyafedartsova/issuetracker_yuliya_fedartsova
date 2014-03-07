package org.training.issuetracker.model.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)

public class Persistent {
	@Id
    @Column(name = "ID")
    @GeneratedValue
	private int id;

	public Persistent(int id) {
		super();
		this.id = id;
	}
	
	public Persistent() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
