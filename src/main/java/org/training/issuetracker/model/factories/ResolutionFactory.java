package org.training.issuetracker.model.factories;

import org.training.issuetracker.model.DAO.PropertyDAO;
import org.training.issuetracker.model.DAO.ResolutionDAO;
import org.training.issuetracker.model.impl.DBPropertyImpl;
import org.training.issuetracker.model.impl.DBResolutionImpl;

public class ResolutionFactory {
	public static ResolutionDAO getClassFromFactory() {
		//return new XMLPropertyImpl();
		return new DBResolutionImpl();
	}

}
