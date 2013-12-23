package org.training.issuetracker.model.factories;


import org.training.issuetracker.model.impl.XMLPropertyImpl;

import DAO.PropertyDAO;

public class PropertyFactory {
	public static PropertyDAO getClassFromFactory() {
		return new XMLPropertyImpl();
	}
}
