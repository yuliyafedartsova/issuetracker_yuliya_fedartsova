package org.training.issuetracker.model.beans;

import java.util.List;

public class Project {
	String name;
	int id;
	User manager;
	List<PropertyParameter> buildVersions;
	String currentVersion;
	String description;
	
	public Project(int id, String name, User manager,
			List<PropertyParameter> buildVersions, String currentVersion, String description) {
		super();
		this.name = name;
		this.id = id;
		this.manager = manager;
		this.buildVersions = buildVersions;
		this.currentVersion = currentVersion;
		this.description = description;
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


	public List<PropertyParameter> getBuildVersions() {
		return buildVersions;
	}


	public void setBuildVersions(List<PropertyParameter> buildVersions) {
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


	public String getCurrentVersion() {
		return currentVersion;
	}


	public void setCurrentVersion(String currentVersion) {
		this.currentVersion = currentVersion;
	}
	
	
	

}
