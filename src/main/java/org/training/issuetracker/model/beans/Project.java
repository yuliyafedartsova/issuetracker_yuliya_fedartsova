package org.training.issuetracker.model.beans;

import java.util.List;

import org.training.issuetracker.model.beans.properties.Version;

public class Project {
	String name;
	int id;
	User manager;
	List<Version> buildVersions;
	String description;
	
	public Project(String name, User manager,
			List<Version> buildVersions, String description) {
		super();
		this.name = name;
		this.manager = manager;
		this.buildVersions = buildVersions;
		this.description = description;
	}
	
	public Project(int id, String name, User manager,
			List<Version> buildVersions, String description) {
		this(name, manager, buildVersions, description);
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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
