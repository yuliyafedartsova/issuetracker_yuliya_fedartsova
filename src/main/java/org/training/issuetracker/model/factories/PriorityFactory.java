package org.training.issuetracker.model.factories;


import org.training.issuetracker.model.DAO.PrioritiesDAO;
import org.training.issuetracker.model.impl.DBPriorityImpl;


public class PriorityFactory {
	public static PrioritiesDAO getClassFromFactory() {
		//return new XMLPropertyImpl();
		return new DBPriorityImpl();
	}


}
