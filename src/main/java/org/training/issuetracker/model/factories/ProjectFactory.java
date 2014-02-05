package org.training.issuetracker.model.factories;
import org.training.issuetracker.model.impl.DBProjectImpl;
import org.training.issuetracker.model.impl.XMLProjectImpl;
import org.training.issuetracker.model.DAO.ProjectDAO;

public class ProjectFactory {
	public static ProjectDAO getClassFromFactory() {
	//	return new XMLProjectImpl();
		return new DBProjectImpl();
	
	}
}
