package org.training.issuetracker.utils.ValidationManagers;

import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.exceptions.ValidationException;
import org.training.issuetracker.model.beans.Issue;

public class IssueValidator extends PersistentObjectValidator {
	
	public String validateIssue(Issue issue) {
		String message = Constants.EMPTY;
		
		if(issue.getAssignee() == null && !issue.getStatus().getName().equals(Constants.NEW)) { 
	    	 message = "Choose assignee or change status.";
	    }
		 
	     if(issue.getResolution() != null && 
	    		 !issue.getStatus().getName().equals(Constants.CLOSED)) {
	    	 message += " Issue is not closed.";
	     }
	     
	     if(issue.getResolution() == null && 
	    		 issue.getStatus().getName().equals(Constants.CLOSED)) {
	    	 message += " Choose resolution.";
	     }
	     
	     if(issue.getDescription().isEmpty() || issue.getDescription() == null) {
	    	 message += " Description is empty.";	
		 }
	     
	     if(issue.getSummary().isEmpty() || issue.getSummary() == null ) {
	    	 message += " Summary is empty.";		
		 }
	     
	     return message;
	     
	}

}
