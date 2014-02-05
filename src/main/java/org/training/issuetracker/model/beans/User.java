package org.training.issuetracker.model.beans;



public class User {
	private int id;
	private String firstName; 
	private String lastName;
	private String email;
	private Parameter role; //Property Parameter!!!!!
	private String password;
	
	public User() {
		super();
	}
	
	public User(int id, String firstName, String lastName, String email, Parameter role,
			String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Parameter getRole() {
		return role;
	}

	public void setRole(Parameter role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
