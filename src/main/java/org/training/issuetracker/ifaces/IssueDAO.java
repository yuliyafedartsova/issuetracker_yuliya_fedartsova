package org.training.issuetracker.ifaces;

import java.util.List;

import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.model.beans.Issue;
import org.training.issuetracker.model.beans.User;

public interface IssueDAO {
	List<Issue> getIssues() throws DaoException;
	Issue getIssueById(int id) throws DaoException; 
	List<Issue> getNLastAddedIssues(int n) throws DaoException;
	List<Issue> getNAssignedIssues(int n, User user) throws DaoException;
}
