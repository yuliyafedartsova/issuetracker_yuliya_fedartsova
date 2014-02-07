package org.training.issuetracker.model.factories;


import org.training.issuetracker.model.DAO.RolesDAO;
import org.training.issuetracker.model.impl.DBRoleImpl;


public class RoleFactory {
	public static RolesDAO getClassFromFactory() {
		//return new XMLPropertyImpl();
		return new DBRoleImpl();
	}


}
