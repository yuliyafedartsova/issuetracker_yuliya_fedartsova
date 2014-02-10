package org.training.issuetracker.utils.ValidationManagers;


import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.model.beans.Project;

public class ProjectValidator extends NamedObjectValidator {
	
	public String validateProject(Project project) {
		String message = Constants.EMPTY;
		
		if(project.getName().isEmpty() || project.getName() == null) {
	    	 message += " Project name is empty.";	
		}
		
		if(project.getDescription().isEmpty() || project.getDescription() == null) {
	    	 message += " Description is empty.";	
		}
		
		return message;
	}
	
	public String validateVersion(String version) {
		String message = Constants.EMPTY;
		if(version.isEmpty() || version == null) {
	    	 message += "Version is empty.";	
		}
		return message;
	}
}
