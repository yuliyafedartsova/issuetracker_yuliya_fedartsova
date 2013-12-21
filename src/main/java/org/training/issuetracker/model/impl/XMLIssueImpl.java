package org.training.issuetracker.model.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.ifaces.IssueDAO;
import org.training.issuetracker.model.beans.Issue;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.model.beans.comparators.IssueComparatorByDate;
import org.training.issuetracker.model.handlers.IssuesHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class XMLIssueImpl implements IssueDAO {
	public List<Issue> getIssues() throws DaoException { 
		List<Issue> issues = new ArrayList<Issue>(); 
		try {
			XMLReader reader = XMLReaderFactory.createXMLReader();
			IssuesHandler handler = new IssuesHandler(issues);
			reader.setContentHandler(handler);
			reader.parse(Constants.PATH + Constants.ISSUES_SOURCE_NAME + Constants.FILE_EXT);
			}catch (SAXException e) {
				throw new DaoException();
			}catch (IOException e) {
				throw new DaoException();
			}
		Collections.sort(issues, new IssueComparatorByDate());
		return issues;
	}
	
	public Issue getIssueById(int id) throws DaoException {
		Issue issue = null;
		List<Issue> issues = getIssues();
		for(Issue i : issues) {
			if(i.getId() == id) {
				issue = i;
			}
		}
		return issue;
	
	}
	
	public List<Issue> getNLastAddedIssues(int n) throws DaoException {
		List<Issue> lastAdded = new ArrayList<Issue>();
		List<Issue> issues = getIssues();
		if(issues.size() < n) {
			n = issues.size();
		}
		
		for(int i = 0; i < n; i++) {
			lastAdded.add(issues.get(i));
		}
		return lastAdded;
	}
	
	public List<Issue> getNAssignedIssues(int n, User user) throws DaoException {
	    List<Issue> allAssigned = new ArrayList<Issue>();
		List<Issue> n_assigned = new ArrayList<Issue>();
		List<Issue> issues = getIssues();
	    for(Issue issue : issues) {
	    	if(issue.getAssignee() != null) {
	    		if(issue.getAssignee().getId() == user.getId()) {
		    		allAssigned.add(issue);
		    	}
	    	}
		}
	    if(allAssigned.size() < n) {
			n = allAssigned.size();
		}
	    for(int i = 0; i < n; i++) {
	    	n_assigned.add(allAssigned.get(i));
	    	
		}
	    return n_assigned;
	}
}
