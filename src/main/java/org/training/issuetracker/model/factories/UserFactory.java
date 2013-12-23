package org.training.issuetracker.model.factories;

import org.training.issuetracker.model.impl.XMLUserImpl;

import DAO.UserDAO;

public class UserFactory {
	public static UserDAO getClassFromFactory() {
		return new XMLUserImpl();
	}
}
