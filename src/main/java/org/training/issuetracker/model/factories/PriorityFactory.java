package org.training.issuetracker.model.factories;

import org.training.issuetracker.model.DAO.PrioritiesDAO;
import org.training.issuetracker.model.impl.db.DBPriorityImpl;
import org.training.issuetracker.model.impl.xml.XMLPropertyImpl;



public class PriorityFactory {
	public static PrioritiesDAO getClassFromFactory() {
		return new DBPriorityImpl();
	}


}
