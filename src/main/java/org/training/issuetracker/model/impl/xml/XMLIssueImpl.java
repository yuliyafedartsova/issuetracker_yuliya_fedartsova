package org.training.issuetracker.model.impl.xml;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.model.beans.Issue;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.model.beans.comparators.IssueComparatorByAssignee;
import org.training.issuetracker.model.beans.comparators.IssueComparatorByDate;
import org.training.issuetracker.model.beans.comparators.IssueComparatorById;
import org.training.issuetracker.model.beans.comparators.IssueComparatorByPriority;
import org.training.issuetracker.model.beans.comparators.IssueComparatorByStatus;
import org.training.issuetracker.model.beans.comparators.IssueComparatorByType;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import org.training.issuetracker.model.DAO.IssueDAO;
import org.training.issuetracker.utils.handlers.IssuesHandler;

public class XMLIssueImpl implements IssueDAO {
	public List<Issue> getIssues() throws DaoException { 
		List<Issue> issues = new ArrayList<Issue>(); 
		try {
			XMLReader reader = XMLReaderFactory.createXMLReader();
			IssuesHandler handler = new IssuesHandler(issues);
			reader.setContentHandler(handler);
			reader.parse(Constants.PATH + Constants.FILES_PACKAGE + Constants.ISSUES_SOURCE_NAME + Constants.FILE_EXT);
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
	
	public List<Issue> getNLastAddedIssues(int n, String sortingType) throws DaoException {
		List<Issue> lastAdded = new ArrayList<Issue>();
		List<Issue> issues = getIssues();
		if(issues.size() < n) {
			n = issues.size();
		}
		
		for(int i = 0; i < n; i++) {
			lastAdded.add(issues.get(i));
		}
		sortIssues(sortingType, lastAdded);
		return lastAdded;
	}
	
	public List<Issue> getNAssignedIssues(int n, User user, String sortingType) throws DaoException {
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
	    sortIssues(sortingType, n_assigned);
	    return n_assigned;
	}
	
	private void sortIssues(String sortingType, List<Issue> issues) {
    	switch(sortingType) {
    	    case "default":
    	    	
    	    	break;
    		case Constants.TYPE:
    			Collections.sort(issues, new IssueComparatorByType());
    			break;
    		case Constants.STATUS:
    			Collections.sort(issues, new IssueComparatorByStatus());
    			break;
    		case Constants.PRIORITY:
    			Collections.sort(issues, new IssueComparatorByPriority());
    			break;
    		case Constants.ID:
    			Collections.sort(issues, new IssueComparatorById());
    			break;
    		case Constants.ASSIGNEE:
    			Collections.sort(issues, new IssueComparatorByAssignee());
    			break;
    	}
   
    }
	
	public void addIssue(Issue issue) throws DaoException {
		
	}
	
	public void updateIssue(Issue issue) throws DaoException {
		
	}
}
