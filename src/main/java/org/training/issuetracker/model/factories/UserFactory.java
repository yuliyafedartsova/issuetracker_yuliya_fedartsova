package org.training.issuetracker.model.factories;

import org.training.issuetracker.model.impl.DBUserImpl;
import org.training.issuetracker.model.impl.XMLUserImpl;
import org.training.issuetracker.model.DAO.UserDAO;

public class UserFactory {
	public static UserDAO getClassFromFactory() {
	//	return new XMLUserImpl();
	    return new DBUserImpl();
	
	}
}
