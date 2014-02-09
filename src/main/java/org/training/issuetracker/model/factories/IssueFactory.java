package org.training.issuetracker.model.factories;
import org.training.issuetracker.model.impl.xml.XMLIssueImpl;
import org.training.issuetracker.model.impl.db.DBIssueImpl;
import org.training.issuetracker.model.DAO.IssueDAO;

public class IssueFactory {
	public static IssueDAO getClassFromFactory() {
	//	return new XMLIssueImpl();
		return new DBIssueImpl();
	
	
	
	}

}
