package org.training.issuetracker.model.impl.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.model.DAO.PrioritiesDAO;
import org.training.issuetracker.model.beans.properties.Priority;


public class DBPriorityImpl extends AbstractPropertyDAO implements PrioritiesDAO {
	public List<Priority> getAll() throws DaoException {
		Map<Integer, String> map = getMap(Constants.PRIORITIES_SOURCE_NAME);
		List<Priority> priorities = new ArrayList<Priority>();
		for(Map.Entry<Integer, String> s : map.entrySet()) {
			priorities.add(new Priority(s.getKey(), s.getValue()));
		}
	    return priorities;
	}
	
	public Priority getById(int id) throws DaoException {
		String name = getNameById(id, Constants.PRIORITIES_SOURCE_NAME);
		Priority priority = new Priority(id, name);
	    return priority;
	}
	
	public void add(Priority priority) throws DaoException {
		add(priority, Constants.PRIORITIES_SOURCE_NAME);
	}
	
	
	public void update(int id, String name) throws DaoException {
		update(id, name, Constants.PRIORITIES_SOURCE_NAME);
    }
	

}
