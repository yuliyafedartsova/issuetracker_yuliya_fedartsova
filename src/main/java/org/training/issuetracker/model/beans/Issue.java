package org.training.issuetracker.model.beans;

import java.util.Date;

public class Issue {
	private int id;
	private String priority;
	private User assignee;
	private String type;
	private String summary;
	private String description;
	private String status;
	private Project project;
	private String resolution;
	private String buildFound;
	private Date createDate;
	private Date modifyDate;
	private User createdBy;
	private User modifiedBy;
	
	public Issue(int id, String priority, String type,
			String summary, String description, String status, Project project,
			String buildFound, Date createDate, User createdBy) {
		super();
		this.id = id;
		this.priority = priority;
		this.type = type;
		this.summary = summary;
		this.description = description;
		this.status = status;
		this.project = project;
		this.buildFound = buildFound;
		this.createDate = createDate;
		this.createdBy = createdBy;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public User getAssignee() {
		return assignee;
	}

	public void setAssignee(User assignee) {
		this.assignee = assignee;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getBuildFound() {
		return buildFound;
	}

	public void setBuildFound(String buildFound) {
		this.buildFound = buildFound;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public User getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(User modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	
}
