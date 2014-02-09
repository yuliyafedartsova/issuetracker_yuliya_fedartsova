package org.training.issuetracker.model.factories;

import org.training.issuetracker.model.DAO.TypesDAO;
import org.training.issuetracker.model.impl.db.DBTypeImp;



public class TypeFactory {
	public static TypesDAO getClassFromFactory() {
		return new DBTypeImp();
	}
}
