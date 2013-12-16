package org.training.issuetracker.model.factories;

import org.training.issuetracker.ifaces.IssueDAO;
import org.training.issuetracker.model.impl.XMLIssueImpl;

public class IssueFactory {
	public static IssueDAO getClassFromFactory() {
		return new XMLIssueImpl();
	}

}
