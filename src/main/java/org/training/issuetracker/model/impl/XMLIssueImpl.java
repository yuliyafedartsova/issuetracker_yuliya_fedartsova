package org.training.issuetracker.model.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.ifaces.IssueDAO;
import org.training.issuetracker.model.beans.Issue;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.model.beans.comparators.IssueComparatorByDate;
import org.training.issuetracker.model.handlers.IssuesHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class XMLIssueImpl implements IssueDAO {
	public List<Issue> getIssues() { //возможно этот метод нужно сделать private
		Set<Issue> set_issues = new TreeSet<Issue>(new IssueComparatorByDate()); 
		try {
			XMLReader reader = XMLReaderFactory.createXMLReader();
			IssuesHandler handler = new IssuesHandler(set_issues);
			reader.setContentHandler(handler);
			reader.parse(Constants.REAL_PATH + "issues.xml");
			}catch (SAXException e) {
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			}
		
		List<Issue> issues = new ArrayList<Issue>(set_issues);
		return issues;
	}
	
	public Issue getIssueById(int id) {
		Issue issue = null;
		List<Issue> issues = getIssues();
		for(Issue i : issues) {
			if(i.getId() == id) {
				issue = i;
			}
		}
		return issue;
	
	}
	
	public List<Issue> getNLastAddedIssues(int n) {
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
	
	public List<Issue> getNAssignedIssues(int n, User user) {
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
