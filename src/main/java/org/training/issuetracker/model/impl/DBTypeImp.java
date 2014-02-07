package org.training.issuetracker.model.impl;


import java.util.List;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.model.DAO.TypesDAO;
import org.training.issuetracker.model.beans.properties.Type;

public class DBTypeImp extends AbstractPropertyDAO implements TypesDAO {
	public List<Type> getAll() throws DaoException {
		List<Type> types = (List<Type>)getAll(Constants.TYPES_SOURCE_NAME);
		return types;
	}
	
	public Type getById(int id) throws DaoException {
		Type type = (Type)getById(id, Constants.TYPES_SOURCE_NAME);
	    return type;
	}
	
	public void add(Type type) throws DaoException {
		add(type, Constants.TYPES_SOURCE_NAME);
	}
	
	
	public void update(Type parameter) throws DaoException {
    	
    }




}
