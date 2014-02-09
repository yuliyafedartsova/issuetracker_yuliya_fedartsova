package org.training.issuetracker.utils.ValidationManagers;

import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.exceptions.ValidationException;

public class AbstractValidator {

	public void validateIdParameter(String idPar) throws ValidationException {
		int id = 0;
		try {
			id = Integer.parseInt(idPar);
		}catch (NumberFormatException e) {
	  		throw new ValidationException();
	  	} 
		if( id < 0) {
			throw new ValidationException();
		}
	}
}
