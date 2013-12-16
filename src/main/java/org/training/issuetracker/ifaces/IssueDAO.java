package org.training.issuetracker.ifaces;

import java.util.List;

import org.training.issuetracker.model.beans.Issue;
import org.training.issuetracker.model.beans.User;

public interface IssueDAO {
	List<Issue> getIssues();
	Issue getIssueById(int id); 
	List<Issue> getNLastAddedIssues(int n);
	List<Issue> getNAssignedIssues(int n, User user);
}
