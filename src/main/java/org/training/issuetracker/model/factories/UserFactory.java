package org.training.issuetracker.model.factories;

import org.training.issuetracker.model.impl.db.DBUserImpl;
import org.training.issuetracker.model.DAO.UserDAO;

public class UserFactory {
	public static UserDAO getClassFromFactory() {
		return new DBUserImpl();
	}
}
