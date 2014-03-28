package org.training.issuetracker.model.beans.properties;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.training.issuetracker.model.beans.Project;
import org.training.issuetracker.model.beans.Property;

@Entity
@Table(name = "VERSIONS")
public class Version extends Property {
	@ManyToOne
	private Project project;
	
	public Version() {
		super();
	}
	
	public Version(int id, String name, Project project) {
		super(id, name);
		this.project = project;
	}
	
	public Version(String name, Project  project) {
		super(name);
		this.project = project;
		
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
	
}
