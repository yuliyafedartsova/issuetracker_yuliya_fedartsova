package org.training.issuetracker.utils.ValidationManagers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.model.beans.User;

public class UserValidator extends PersistentObjectValidator {
	
	public String validateUserFields(User user) {
		String message = Constants.EMPTY;
		final String ERROR_MESSAGE_NAME = " First name is empty.";
		final String ERROR_MESSAGE_LAST_NAME = " Last name is empty.";	
		final String ERROR_MESSAGE_EMAIL = " Email is empty.";	
		final String ERROR_MESSAGE_PASSWORD = " Password is empty.";
		final String ERROR_MESSAGE_ROLE = " Role is empty.";	
		
		if(user.getFirstName().isEmpty() || user.getFirstName() == null) {
	    	 message += ERROR_MESSAGE_NAME;	
		}
	
		if(user.getLastName().isEmpty() || user.getLastName() == null) {
	    	 message += ERROR_MESSAGE_LAST_NAME;	
		}
		
		if(user.getEmail().isEmpty() || user.getEmail() == null) {
	    	 message += ERROR_MESSAGE_EMAIL;	
		}
		
		if(user.getPassword().isEmpty() || user.getPassword() == null) {
	    	 message += ERROR_MESSAGE_PASSWORD;	
		}
		
		if(user.getRole().getName().isEmpty() || user.getRole() == null) {
	    	 message += ERROR_MESSAGE_ROLE;	
		}
		
		return message;
	
	}
	
	public String validateUserValues(User user) {
		final String NAME_PATTERN = "[a-zA-Z0-9]{3,10}";
		final String PASSWORD_PATTERN = "[a-zA-Z0-9]{3,6}";
		final String EMAIL_PATTERN = "^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\\.([a-zA-Z])+";
		final String ERROR_MESSAGE_NAME = " Invalid first name.";
		final String ERROR_MESSAGE_LAST_NAME = " Invalid last name.";
		final String ERROR_MESSAGE_PASSWORD = " Invalid password.";
		final String ERROR_MESSAGE_EMAIL = " Invalid email.";	
		String message = Constants.EMPTY;
		Pattern namePat = Pattern.compile(NAME_PATTERN);
		Pattern passwordPat = Pattern.compile(PASSWORD_PATTERN);
		Pattern emailPat = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = null;
		matcher = namePat.matcher(user.getFirstName());
		if(!matcher.matches()) {
			message += ERROR_MESSAGE_NAME;	
		}
		
		matcher = namePat.matcher(user.getLastName());
		if(!matcher.matches()) {
			message += ERROR_MESSAGE_LAST_NAME;	
		}
		
		matcher = passwordPat.matcher(user.getPassword());
		if(!matcher.matches()) {
			message += ERROR_MESSAGE_PASSWORD;	
		}
		
		matcher = emailPat.matcher(user.getEmail());
		if(!matcher.matches()) {
			message += ERROR_MESSAGE_EMAIL;	
		}
		return message;
	}
	
	public String validatePasswords(String password1, String password2) {
		String message = Constants.EMPTY;
		final String ERROR_MESSAGE = "Mismatch of passwords.";
		
		if(!password1.equals(password2)) {
			message = ERROR_MESSAGE;
		}
		return message; 
	}

}
