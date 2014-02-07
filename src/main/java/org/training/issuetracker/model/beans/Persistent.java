package org.training.issuetracker.model.beans;

public class Persistent {
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
