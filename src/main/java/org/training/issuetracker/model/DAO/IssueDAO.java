package org.training.issuetracker.model.DAO;

import java.util.List;

import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.model.beans.Issue;
import org.training.issuetracker.model.beans.User;

public interface IssueDAO {
	List<Issue> getIssues() throws DaoException;
	Issue getIssueById(int id) throws DaoException; 
	List<Issue> getNLastAddedIssues(int n, String sortingType) throws DaoException;
	List<Issue> getNAssignedIssues(int n, User user, String sortingType) throws DaoException;
}
