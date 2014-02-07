package org.training.issuetracker.model.impl;

import java.util.List;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.model.DAO.ResolutionDAO;
import org.training.issuetracker.model.beans.properties.Resolution;


public class DBResolutionImpl extends AbstractPropertyDAO implements ResolutionDAO {
	public List<Resolution> getAll() throws DaoException {
		List<Resolution> resolutions = (List<Resolution>)getAll(Constants.RESOLUTIONS_SOURCE_NAME);
		return resolutions;
	}
	
	public Resolution getById(int id) throws DaoException {
		Resolution resolution = (Resolution)getById(id, Constants.RESOLUTIONS_SOURCE_NAME);
	    return resolution;
	}
	
	public void add(Resolution resolution) throws DaoException {
		add(resolution, Constants.RESOLUTIONS_SOURCE_NAME);
	}
	
	
	public void update(Resolution resolution) throws DaoException {
    	
    }



}
