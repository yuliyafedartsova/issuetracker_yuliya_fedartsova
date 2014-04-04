package org.training.issuetracker.model.beans;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.training.issuetracker.model.beans.properties.Version;

@Entity
@Table(name = "PROJECTS")
public class Project extends Persistent {
	private String name;
	@OneToOne
	@JoinColumn(name = "managerId")
	private  User manager;
	@OneToMany(mappedBy="project")
	private  List<Version> versions;
	private String description;
	
	public Project() {
		
	}
	
	
	public Project(int id, String name, User manager,
			List<Version> buildVersions, String description) {
		super(id);
		this.name = name;
		this.manager = manager;
		this.versions = buildVersions;
		this.description = description;
	}
	
	public Project(String name, User manager,
			List<Version> buildVersions, String description) {
		this(0, name, manager, buildVersions, description);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Version> getVersions() {
		return versions;
	}

	public void setVersions(List<Version> versions) {
		this.versions = versions;
	}

	public User getManager() {
		return manager;
	}
	
	public void setManager(User manager) {
		this.manager = manager;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
