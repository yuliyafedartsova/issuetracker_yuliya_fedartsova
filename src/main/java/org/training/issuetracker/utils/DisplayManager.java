package org.training.issuetracker.utils;

import java.util.ArrayList;
import java.util.List;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.model.beans.Issue;
import org.training.issuetracker.model.beans.Project;
import org.training.issuetracker.model.beans.properties.Status;



public class DisplayManager {

	public static void cutDescriptionsOff(List<Project> projects) {
    	for(Project project : projects) {
    		if(project.getDescription().length() > Constants.DESCRIPTION_LENTH_LIMIT){
    			String description = 
    					project.getDescription().substring(Constants.NULL, 
    							Constants.DESCRIPTION_LENTH_LIMIT);
    			description += Constants.THREE_DOTS;
    			project.setDescription(description);
    		}
    	}
    }//
	
	public static List<Status> getAvailableStatuses(List<Status> statuses, Issue issue) {
    	List<Status> availableStatuses = new ArrayList<Status>();
    	
    	switch(issue.getStatus().getName()) {
    		case Constants.NEW:
    			for(Status par : statuses) {
        			if(par.getName().equals(Constants.NEW) || par.getName().equals(Constants.ASSIGNED)) {
        				availableStatuses.add(par);
        			}
        		}
    			break;
    		case Constants.ASSIGNED:
    			for(Status par : statuses) {
        			if(par.getName().equals(Constants.IN_PROGRESS) || par.getName().equals(Constants.ASSIGNED)) {
        				availableStatuses.add(par);
        			}
        		}
    			break;	
    		case Constants.CLOSED:
    			for(Status par : statuses) {
        			if(par.getName().equals(Constants.CLOSED) || par.getName().equals(Constants.REOPENED)) {
        				availableStatuses.add(par);
        			}
        		}
    			break;
    		case Constants.IN_PROGRESS:
    			for(Status par : statuses) {
        			if(par.getName().equals(Constants.CLOSED) || par.getName().equals(Constants.IN_PROGRESS) ||
        					par.getName().equals(Constants.RESOLVED)) {
        				availableStatuses.add(par);
        			}
        		}
    			break;
    		case Constants.RESOLVED:
    			for(Status par : statuses) {
        			if(par.getName().equals(Constants.CLOSED) || par.getName().equals(Constants.REOPENED) || 
        					par.getName().equals(Constants.RESOLVED)) {
        				availableStatuses.add(par);
        			}
        		}
    			break;
    	}
    	return availableStatuses;
    }//
	
	public static List<Status> getAvailableStatusesForSubmitIssue(List<Status> statuses) {
    	List<Status> availableStatuses = new ArrayList<Status>();
    	for(Status par : statuses) {
    		if(par.getName().equals(Constants.NEW) || par.getName().equals(Constants.ASSIGNED) ) {
    			availableStatuses.add(par);
    		}
    	}
    	return availableStatuses;
    }

}
