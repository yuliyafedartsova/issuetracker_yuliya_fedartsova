package org.training.issuetracker.model.factories;

import org.training.issuetracker.model.DAO.RolesDAO;
import org.training.issuetracker.model.impl.db.DBRoleImpl;



public class RoleFactory {
	public static RolesDAO getClassFromFactory() {
		return new DBRoleImpl();
	}


}
