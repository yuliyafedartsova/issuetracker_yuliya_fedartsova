package org.training.issuetracker.utils.ValidationManagers;

import org.training.issuetracker.exceptions.ValidationException;

public class DescribingValidator extends PersistentObjectValidator {
	
	public void validateDescription(String description) throws ValidationException {
		final String MESSAGE = "Description field is empty";
		if(description.trim().isEmpty()) {
			throw new ValidationException(MESSAGE);
		}
	}
	
}
