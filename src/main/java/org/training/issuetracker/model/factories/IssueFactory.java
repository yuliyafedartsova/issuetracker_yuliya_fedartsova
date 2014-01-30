package org.training.issuetracker.model.factories;
import org.training.issuetracker.model.impl.XMLIssueImpl;
import org.training.issuetracker.model.DAO.IssueDAO;

public class IssueFactory {
	public static IssueDAO getClassFromFactory() {
		return new XMLIssueImpl();
	}

}
