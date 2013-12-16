package org.training.issuetracker.model.factories;

import org.training.issuetracker.ifaces.IssuePropertyDAO;
import org.training.issuetracker.model.impl.XMLIssuePropertyImpl;


public class IssuePropertyFactory {
	public static IssuePropertyDAO getClassFromFactory() {
		return new XMLIssuePropertyImpl();
	}
}
