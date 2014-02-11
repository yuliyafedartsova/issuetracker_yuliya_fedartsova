package org.training.issuetracker.utils.ValidationManagers;

import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.model.beans.Property;

public class ParameterValidator extends PersistentObjectValidator {
	
	public String validateParameter(Property parameter) {
		return validateParameter(parameter.getName());
	} 
	    
	public String validateParameter(String parameter) {
		String ERROR_MESSAGE = "Name is empty";
	    String message = Constants.EMPTY;
		if(parameter.isEmpty() || parameter == null) { 
	    	 message = ERROR_MESSAGE;
	    }
	    return message;	
    }
}
