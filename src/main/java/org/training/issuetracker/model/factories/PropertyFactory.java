package org.training.issuetracker.model.factories;

import org.training.issuetracker.model.impl.DBPropertyImpl;
import org.training.issuetracker.model.impl.XMLPropertyImpl;
import org.training.issuetracker.model.DAO.PropertyDAO;

public class PropertyFactory {
	public static PropertyDAO getClassFromFactory() {
		//return new XMLPropertyImpl();
		return new DBPropertyImpl();
	}
}
