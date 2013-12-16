package org.training.issuetracker.model.factories;

import org.training.issuetracker.ifaces.ProjectDAO;
import org.training.issuetracker.model.impl.XMLProjectImpl;

public class ProjectFactory {
	public static ProjectDAO getClassFromFactory() {
		return new XMLProjectImpl();
	}
}
