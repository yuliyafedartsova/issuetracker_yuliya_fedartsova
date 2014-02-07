package org.training.issuetracker.utils;

import org.training.issuetracker.exceptions.ValidationException;
import org.training.issuetracker.model.beans.Issue;

public class ValidationManager {
//
	public static void validateIssue(Issue issue) throws ValidationException {
		 if(issue.getAssignee() == null && !issue.getStatus().getName().equals("New")) { 
	    	 throw new ValidationException();
	     }
		 
	     if(issue.getResolution() != null && 
	    		 !issue.getStatus().getName().equals("Closed")) {
	    	 throw new ValidationException();
	     }
	     
	     if(issue.getResolution() == null && 
	    		 issue.getStatus().getName().equals("Closed")) {
	    	 throw new ValidationException();
	     }
	     
	     if(issue.getSummary().isEmpty()) {
	    	 throw new ValidationException(); 
	     }
	     
	     if(issue.getDescription().isEmpty()) {
	    	 throw new ValidationException(); 
	     }
	
	
	
	
	}



}
