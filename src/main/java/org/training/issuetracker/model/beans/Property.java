package org.training.issuetracker.model.beans;

public class Property extends Persistent {
	
	private String name;

	public Property(int id, String name) {
		super(id);
		this.name = name;
	}
	
	public Property(String name) {
		this.name = name;
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
