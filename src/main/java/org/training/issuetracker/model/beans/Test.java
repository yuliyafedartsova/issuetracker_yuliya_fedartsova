package org.training.issuetracker.model.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Test {
	@Id
    @Column(name = "ID")
    @GeneratedValue
	private int id;
	@Column(name="NAME")
	private String name;
	
	public Test(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public Test(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	

}
