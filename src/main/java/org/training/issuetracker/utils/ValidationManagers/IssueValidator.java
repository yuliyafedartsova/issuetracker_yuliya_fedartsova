package org.training.issuetracker.utils.ValidationManagers;

import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.exceptions.ValidationException;
import org.training.issuetracker.model.beans.Issue;

public class IssueValidator extends PersistentObjectValidator {
	
	public String validateIssue(Issue issue) {
		String message = Constants.EMPTY;
		final String ERROR_MESSAGE_CHANGE_STATUS = "Choose assignee or change status.";
		final String ERROR_MESSAGE_NOT_CLOSED = " Issue is not closed.";
		final String ERROR_MESSAGE_CHOSE_RESOLUTION = " Choose resolution.";
		final String ERROR_MESSAGE_EMPTY_DESCRIPTION = " Description is empty.";
		final String ERROR_MESSAGE_EMPTY_SUMMARY = " Summary is empty.";
		
		if(issue.getAssignee() == null && !issue.getStatus().getName().equals(Constants.NEW)) { 
	    	 message = ERROR_MESSAGE_CHANGE_STATUS;
	    }
		 
	     if(issue.getResolution() != null && 
	    		 !issue.getStatus().getName().equals(Constants.CLOSED)) {
	    	 if(!issue.getStatus().getName().equals(Constants.RESOLVED)) {
	    		 message += ERROR_MESSAGE_NOT_CLOSED;
	    	 }
	     }
	     
	     if(issue.getResolution() == null && 
	    		(issue.getStatus().getName().equals(Constants.CLOSED) || issue.getStatus().getName().equals(Constants.RESOLVED))) {
	    	 message += ERROR_MESSAGE_CHOSE_RESOLUTION;
	     }
	     
	     if(issue.getDescription().isEmpty() || issue.getDescription() == null) {
	    	 message += ERROR_MESSAGE_EMPTY_DESCRIPTION;	
		 }
	     
	     if(issue.getSummary().isEmpty() || issue.getSummary() == null ) {
	    	 message += ERROR_MESSAGE_EMPTY_SUMMARY;		
		 }
	     
	     return message;
	     
	}

}
