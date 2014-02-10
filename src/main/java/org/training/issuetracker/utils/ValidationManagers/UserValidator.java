package org.training.issuetracker.utils.ValidationManagers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.model.beans.User;

public class UserValidator extends PersistentObjectValidator {
	
	public String validateUserFields(User user) {
		String message = Constants.EMPTY;
		
		if(user.getFirstName().isEmpty() || user.getFirstName() == null) {
	    	 message += " First name is empty.";	
		}
	
		if(user.getLastName().isEmpty() || user.getLastName() == null) {
	    	 message += " Last name is empty.";	
		}
		
		if(user.getEmail().isEmpty() || user.getEmail() == null) {
	    	 message += " Email is empty.";	
		}
		
		if(user.getPassword().isEmpty() || user.getPassword() == null) {
	    	 message += " Password is empty.";	
		}
		
		if(user.getRole().getName().isEmpty() || user.getRole() == null) {
	    	 message += " Role is empty.";	
		}
		
		return message;
	
	}
	
	public String validateUserValues(User user) {
		String message = Constants.EMPTY;
		Pattern namePat = Pattern.compile("[a-zA-Z0-9]{3,10}");
		Pattern passwordPat = Pattern.compile("[a-zA-Z0-9]{3,6}");
		Pattern emailPat = Pattern.compile("^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\\.([a-zA-Z])+");
		Matcher matcher = null;
		matcher = namePat.matcher(user.getFirstName());
		if(!matcher.matches()) {
			message += " Invalid first name.";	
		}
		
		matcher = namePat.matcher(user.getLastName());
		if(!matcher.matches()) {
			message += " Invalid last name.";	
		}
		
		matcher = passwordPat.matcher(user.getPassword());
		if(!matcher.matches()) {
			message += " Invalid password.";	
		}
		
		matcher = emailPat.matcher(user.getEmail());
		if(!matcher.matches()) {
			message += " Invalid email.";	
		}
		return message;
	}
	
	public String validatePasswords(String password1, String password2) {
		String message = Constants.EMPTY;
		if(!password1.equals(password2)) {
			message = "Mismatch of passwords.";
		}
		return message; 
	}

}
