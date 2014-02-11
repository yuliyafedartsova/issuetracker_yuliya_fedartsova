package org.training.issuetracker.model.impl.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.exceptions.ValidationException;
import org.training.issuetracker.model.DAO.ResolutionDAO;
import org.training.issuetracker.model.beans.properties.Resolution;
import org.training.issuetracker.model.beans.properties.Type;


public class DBResolutionImpl extends AbstractPropertyDAO implements ResolutionDAO {
	public List<Resolution> getAll() throws DaoException {
		Map<Integer, String> map = getMap(Constants.RESOLUTIONS_SOURCE_NAME);
		List<Resolution> resolutions = new ArrayList<Resolution>();
		for(Map.Entry<Integer, String> s : map.entrySet()) {
			resolutions.add(new Resolution(s.getKey(), s.getValue()));
		}
	    return resolutions;
	}
	
	public Resolution getById(int id) throws DaoException, ValidationException {
		String name = getNameById(id, Constants.RESOLUTIONS_SOURCE_NAME);
		Resolution resolution = new Resolution(id, name);
	    return resolution;
	}
	
	public void add(Resolution resolution) throws DaoException, ValidationException {
		add(resolution, Constants.RESOLUTIONS_SOURCE_NAME);
	}
	
	
	public void update(int id, String name) throws DaoException, ValidationException {
		update(id, name, Constants.RESOLUTIONS_SOURCE_NAME);
    }



}
