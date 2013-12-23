package org.training.issuetracker.model.beans;

import java.util.Date;

public class Issue {
	private int id;
	private PropertyParameter priority;
	private User assignee;
	private PropertyParameter type;
	private String summary;
	private String description;
	private PropertyParameter status;
	private Project project;
	private PropertyParameter resolution;
	private PropertyParameter buildFound;
	private Date createDate;
	private Date modifyDate;
	private User author;
	private User modifier;
	
	public Issue(int id, PropertyParameter priority, PropertyParameter type,
			String summary, String description, PropertyParameter status, Project project,
			PropertyParameter buildFound, Date createDate, User author) {
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
		this.author = author;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PropertyParameter getPriority() {
		return priority;
	}

	public void setPriority(PropertyParameter priority) {
		this.priority = priority;
	}

	public User getAssignee() {
		return assignee;
	}

	public void setAssignee(User assignee) {
		this.assignee = assignee;
	}

	public PropertyParameter getType() {
		return type;
	}

	public void setType(PropertyParameter type) {
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

	public PropertyParameter getStatus() {
		return status;
	}

	public void setStatus(PropertyParameter status) {
		this.status = status;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public PropertyParameter getBuildFound() {
		return buildFound;
	}

	public void setBuildFound(PropertyParameter buildFound) {
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

	public User getAuthor() {
		return author;
	}

	public void setAuthorBy(User author) {
		this.author = author;
	}

	public User getModifier() {
		return modifier;
	}

	public void setModifier(User modifier) {
		this.modifier = modifier;
	}

	public PropertyParameter getResolution() {
		return resolution;
	}

	public void setResolution(PropertyParameter resolution) {
		this.resolution = resolution;
	}
	
}
