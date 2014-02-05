package org.training.issuetracker.model.beans;

import java.sql.Date;



public class Issue {
	private int id;
	private Parameter priority;
	private User assignee;
	private Parameter type;
	private String summary;
	private String description;
	private Parameter status;
	private Project project;
	private Parameter resolution;
	private Parameter buildFound;
	private Date createDate;
	private Date modifyDate;
	private User author;
	private User modifier;
	
	public Issue(Parameter priority, Parameter type,
			String summary, String description, Parameter status, Project project,
			Parameter buildFound, Date createDate, User author) {
		super();
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
	
	public Issue(int id, Parameter priority, Parameter type,
			String summary, String description, Parameter status, Project project,
			Parameter buildFound, Date createDate, User author) {
		this(priority, type, summary, description, status, project, buildFound, 
				createDate, author);
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Parameter getPriority() {
		return priority;
	}

	public void setPriority(Parameter priority) {
		this.priority = priority;
	}

	public User getAssignee() {
		return assignee;
	}

	public void setAssignee(User assignee) {
		this.assignee = assignee;
	}

	public Parameter getType() {
		return type;
	}

	public void setType(Parameter type) {
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

	public Parameter getStatus() {
		return status;
	}

	public void setStatus(Parameter status) {
		this.status = status;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Parameter getBuildFound() {
		return buildFound;
	}

	public void setBuildFound(Parameter buildFound) {
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

	public Parameter getResolution() {
		return resolution;
	}

	public void setResolution(Parameter resolution) {
		this.resolution = resolution;
	}
	
}
