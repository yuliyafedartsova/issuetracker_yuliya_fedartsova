package org.training.issuetracker.model.factories;
import org.training.issuetracker.model.impl.db.DBProjectImpl;
import org.training.issuetracker.model.impl.xml.XMLProjectImpl;
import org.training.issuetracker.model.DAO.ProjectDAO;

public class ProjectFactory {
	public static ProjectDAO getClassFromFactory() {
	//	return new XMLProjectImpl();
		return new DBProjectImpl();
	
	}
}
