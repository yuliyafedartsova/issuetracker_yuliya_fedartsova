package org.training.issuetracker.utils.ValidationManagers;

import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.exceptions.ValidationException;

public class PersistentObjectValidator {
	public void validateIdParameters(String... idParameters) throws ValidationException {
		int id;
		for(String idPar : idParameters) {
			try {
				id = Integer.parseInt(idPar);
			}catch (NumberFormatException e) {
		  		throw new ValidationException(Constants.SOME_PROBLEMS);
		  	}
			if(id < Constants.NULL || id == Constants.NULL) {
				throw new ValidationException(Constants.SOME_PROBLEMS);
			}
		}
	}
}
