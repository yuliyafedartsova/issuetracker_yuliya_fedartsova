package org.training.issuetracker.utils.ValidationManagers;

import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.model.beans.Property;

public class ParameterValidator extends PersistentObjectValidator {
	
	public String validateParameter(Property parameter) {
		return validateParameter(parameter.getName());
	} 
	    
	public String validateParameter(String parameter) {
		String message = Constants.EMPTY;
		if(parameter.isEmpty() || parameter == null) { 
	    	 message = "Name is empty";
	    }
	    return message;	
    }
}
