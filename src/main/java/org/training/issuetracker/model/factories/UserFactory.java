package org.training.issuetracker.model.factories;

import org.training.issuetracker.ifaces.UserDAO;
import org.training.issuetracker.model.impl.XMLUserImpl;

public class UserFactory {
	public static UserDAO getClassFromFactory() {
		return new XMLUserImpl();
	}
}
