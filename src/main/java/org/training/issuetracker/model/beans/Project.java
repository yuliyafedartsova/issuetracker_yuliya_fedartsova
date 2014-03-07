package org.training.issuetracker.model.beans;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.training.issuetracker.model.beans.properties.Version;

@Entity
@Table(name = "PROJECTS")
public class Project extends Persistent {
	private String name;
	private  User manager;
	private  List<Version> buildVersions;
	private String description;
	
	public Project(int id, String name, User manager,
			List<Version> buildVersions, String description) {
		super(id);
		this.name = name;
		this.manager = manager;
		this.buildVersions = buildVersions;
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

	public List<Version> getBuildVersions() {
		return buildVersions;
	}

	public void setBuildVersions(List<Version> buildVersions) {
		this.buildVersions = buildVersions;
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
