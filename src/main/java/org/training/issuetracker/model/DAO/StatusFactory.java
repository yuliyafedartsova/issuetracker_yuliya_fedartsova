package org.training.issuetracker.model.DAO;


import org.training.issuetracker.model.impl.DBStatusImpl;

public class StatusFactory {
	public static StatusesDAO getClassFromFactory() {
		//return new XMLPropertyImpl();
		return new DBStatusImpl();
	}

}
