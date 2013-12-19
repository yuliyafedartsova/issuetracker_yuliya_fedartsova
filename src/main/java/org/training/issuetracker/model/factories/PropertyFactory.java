package org.training.issuetracker.model.factories;

import org.training.issuetracker.ifaces.PropertyDAO;
import org.training.issuetracker.model.impl.XMLPropertyImpl;

public class PropertyFactory {
	public static PropertyDAO getClassFromFactory() {
		return new XMLPropertyImpl();
	}
}
