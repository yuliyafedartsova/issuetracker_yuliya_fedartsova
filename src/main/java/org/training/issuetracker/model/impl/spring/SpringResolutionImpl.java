package org.training.issuetracker.model.impl.spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.training.issuetracker.model.beans.properties.Resolution;
import org.training.issuetracker.model.beans.properties.Type;
import org.training.issuetracker.services.ResolutionService;

public class SpringResolutionImpl {

	@Autowired
	private ResolutionService resolutionService;
	
	public List<Resolution> getAll() {
		return resolutionService.getResolutions();
	}
	
	public Resolution getById(int id) {
		return resolutionService.getById(id);
	}
	
	public void update(Resolution resolution) {
		resolutionService.update(resolution);
	}
	
	public void add(Resolution resolution) {
		resolutionService.save(resolution);
	}


}
