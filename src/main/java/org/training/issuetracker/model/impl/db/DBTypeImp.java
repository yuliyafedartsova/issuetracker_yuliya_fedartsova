package org.training.issuetracker.model.impl.db;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.exceptions.ValidationException;
import org.training.issuetracker.model.DAO.TypesDAO;
import org.training.issuetracker.model.beans.properties.Priority;
import org.training.issuetracker.model.beans.properties.Type;

public class DBTypeImp extends AbstractPropertyDAO implements TypesDAO {
	public List<Type> getAll() throws DaoException {
		Map<Integer, String> map = getMap(Constants.TYPES_SOURCE_NAME);
		List<Type> types = new ArrayList<Type>();
		for(Map.Entry<Integer, String> s : map.entrySet()) {
			types.add(new Type(s.getKey(), s.getValue()));
		}
	    return types;
	}
	
	public Type getById(int id) throws DaoException {
		String name = getNameById(id, Constants.TYPES_SOURCE_NAME);
		Type type = new Type(id, name);
	    return type;
	}
	
	public void add(Type type) throws DaoException, ValidationException {
		add(type, Constants.TYPES_SOURCE_NAME);
	}
	
	
	public void update(int id, String name) throws DaoException, ValidationException {
		update(id, name, Constants.TYPES_SOURCE_NAME);
    }




}
