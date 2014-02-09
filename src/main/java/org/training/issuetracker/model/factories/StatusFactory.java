package org.training.issuetracker.model.factories;


import org.training.issuetracker.model.DAO.StatusesDAO;
import org.training.issuetracker.model.impl.db.DBStatusImpl;


public class StatusFactory {
	public static StatusesDAO getClassFromFactory() {
		return new DBStatusImpl();
	}

}
