package org.training.issuetracker.model.beans;


import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.training.issuetracker.model.beans.properties.Priority;
import org.training.issuetracker.model.beans.properties.Resolution;
import org.training.issuetracker.model.beans.properties.Status;
import org.training.issuetracker.model.beans.properties.Type;
import org.training.issuetracker.model.beans.properties.Version;

@Entity
@Table(name = "ISSUES")
public class Issue extends Persistent {
	private Priority priority;
	private User assignee;
	private Type type;
	private String summary;
	private String description;
	private Status status;
	private Project project;
	private Resolution resolution;
	private Version buildFound;
	private Date createDate;
	private Date modifyDate;
	private User author;
	private User modifier;
	
	public Issue(int id, Priority priority, Type type,
			String summary, String description, Status status, Project project,
			Version buildFound, Date createDate, User author) {
		super(id);
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
	
	public Issue(int id, Priority priority, User assignee, Type type,
			String summary, String description, Status status, Project project,
			Resolution resolution, Version buildFound, Date createDate, Date modifyDate,
			User author, User modifier) {
		this(id, priority, type, summary, description, status, project, buildFound, 
				createDate, author);
		this.assignee = assignee;
		this.resolution = resolution;
		this.modifyDate = modifyDate;
		this.modifier = modifier;
	}
	
	
	public Issue(Priority priority, Type type,
			String summary, String description, Status status, Project project,
			Version buildFound, Date createDate, User author) {
		this(0, priority, type, summary, description, status, project, buildFound, 
				createDate, author);
	}

	
    public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public User getAssignee() {
		return assignee;
	}

	public void setAssignee(User assignee) {
		this.assignee = assignee;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Version getBuildFound() {
		return buildFound;
	}

	public void setBuildFound(Version buildFound) {
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

	public Resolution getResolution() {
		return resolution;
	}

	public void setResolution(Resolution resolution) {
		this.resolution = resolution;
	}
	
}
