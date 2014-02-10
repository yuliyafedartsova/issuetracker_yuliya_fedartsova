package org.training.issuetracker.utils.ValidationManagers;

import org.training.issuetracker.exceptions.ValidationException;

public class NamedObjectValidator extends PersistentObjectValidator {
	public void validateName(String name) throws ValidationException {
		final String MESSAGE = "Name field is empty";
		if(name.trim().isEmpty()) {
			throw new ValidationException(MESSAGE);
		}
	}


}
