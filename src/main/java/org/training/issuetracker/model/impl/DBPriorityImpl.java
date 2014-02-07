package org.training.issuetracker.model.impl;

import java.util.List;

import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.model.DAO.PrioritiesDAO;
import org.training.issuetracker.model.beans.properties.Priority;


public class DBPriorityImpl extends AbstractPropertyDAO implements PrioritiesDAO {
	public List<Priority> getAll() throws DaoException {
		List<Priority> priorities = (List<Priority>)getAll(Constants.PRIORITIES_SOURCE_NAME);
		return priorities;
	}
	
	public Priority getById(int id) throws DaoException {
		Priority priority = (Priority)getById(id, Constants.PRIORITIES_SOURCE_NAME);
	    return priority;
	}
	
	public void add(Priority priority) throws DaoException {
		add(priority, Constants.PRIORITIES_SOURCE_NAME);
	}
	
	
	public void update(Priority priority) throws DaoException {
    	
    }
	

}
