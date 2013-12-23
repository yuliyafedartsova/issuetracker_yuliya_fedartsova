package org.training.issuetracker.model.factories;
import org.training.issuetracker.model.impl.XMLProjectImpl;

import DAO.ProjectDAO;

public class ProjectFactory {
	public static ProjectDAO getClassFromFactory() {
		return new XMLProjectImpl();
	}
}
