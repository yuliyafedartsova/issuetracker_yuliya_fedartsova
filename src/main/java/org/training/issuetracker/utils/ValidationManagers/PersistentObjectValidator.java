package org.training.issuetracker.utils.ValidationManagers;

import org.training.issuetracker.exceptions.ValidationException;

public class PersistentObjectValidator {
	public void validateIdParameters(String... idParameters) throws ValidationException {
		int id;
		for(String idPar : idParameters) {
			try {
				id = Integer.parseInt(idPar);
			}catch (NumberFormatException e) {
		  		throw new ValidationException("Some problem");
		  	}
			if(id < 0) {
				throw new ValidationException("Some problem");
			}
		}
	}
}
