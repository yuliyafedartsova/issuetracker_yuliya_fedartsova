package org.training.issuetracker.model.impl;

import java.util.List;

import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.model.DAO.StatusesDAO;
import org.training.issuetracker.model.beans.properties.Status;


public class DBStatusImpl extends AbstractPropertyDAO implements StatusesDAO {
	public List<Status> getAll() throws DaoException {
		List<Status> statuses = (List<Status>)getAll(Constants.STATUSES_SOURCE_NAME);
		return statuses;
	}
	
	public Status getById(int id) throws DaoException {
		Status status = (Status)getById(id, Constants.STATUSES_SOURCE_NAME);
	    return status;
	}
	
	public void add(Status status) throws DaoException {
		add(status, Constants.STATUSES_SOURCE_NAME);
	}
	
	
	public void update(Status status) throws DaoException {
    	
    }


  



}
