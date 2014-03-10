package org.training.issuetracker.model.beans;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.training.issuetracker.model.beans.properties.Role;

@Entity
@Table(name = "USERS")
public class User extends Persistent {
	private String firstName; 
	private String lastName;
	private String email;
	@OneToOne
	@JoinColumn(name = "roleId")
	private Role role; 
	private String password;
	
	public User() {
		super();
	}
	
	public User(String firstName, String lastName, String email, Role role,
			String password) {
		this(0, firstName, lastName, email, role, password);
	}
	
	public User(int id, String firstName, String lastName, String email, Role role,
			String password) {
		super(id);
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
		this.password = password;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
