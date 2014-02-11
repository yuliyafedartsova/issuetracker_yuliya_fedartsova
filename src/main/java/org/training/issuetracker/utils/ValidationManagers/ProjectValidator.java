package org.training.issuetracker.utils.ValidationManagers;


import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.model.beans.Project;

public class ProjectValidator extends PersistentObjectValidator {
	
	public String validateProject(Project project) {
		String message = Constants.EMPTY;
		final String ERROR_MESSAGE_NAME = " Project name is empty.";	
		final String ERROR_MESSAGE_DESCRIPTION = " Description is empty.";
		
		if(project.getName().isEmpty() || project.getName() == null) {
	    	 message += ERROR_MESSAGE_NAME;	
		}
		
		if(project.getDescription().isEmpty() || project.getDescription() == null) {
	    	 message += ERROR_MESSAGE_DESCRIPTION;	
		}
		
		return message;
	}
	
	public String validateVersion(String version) {
		String message = Constants.EMPTY;
		final String ERROR_MESSAGE = "Version is empty.";
		if(version.isEmpty() || version == null) {
	    	 message += ERROR_MESSAGE;	
		}
		return message;
	}
}
