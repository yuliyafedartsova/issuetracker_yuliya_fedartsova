package org.training.issuetracker.model.factories;

import org.training.issuetracker.model.DAO.ResolutionDAO;
import org.training.issuetracker.model.impl.db.DBResolutionImpl;


public class ResolutionFactory {
	public static ResolutionDAO getClassFromFactory() {
		return new DBResolutionImpl();
	}

}
