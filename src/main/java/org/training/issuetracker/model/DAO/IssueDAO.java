package org.training.issuetracker.model.DAO;

import java.util.List;

import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.exceptions.ValidationException;
import org.training.issuetracker.model.beans.Issue;
import org.training.issuetracker.model.beans.User;

public interface IssueDAO {
	Issue getIssueById(int id) throws DaoException, ValidationException; 
	List<Issue> getNLastAddedIssues(int n, String sortingType) throws DaoException, ValidationException;
	List<Issue> getNAssignedIssues(int n, User user, String sortingType) throws DaoException, ValidationException;
    void addIssue(Issue issue) throws DaoException, ValidationException;
    void updateIssue(Issue issue) throws DaoException, ValidationException;
}
