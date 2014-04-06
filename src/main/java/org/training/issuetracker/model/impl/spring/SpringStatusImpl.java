package org.training.issuetracker.model.impl.spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.training.issuetracker.model.beans.properties.Status;
import org.training.issuetracker.services.StatusService;

public class SpringStatusImpl {

	@Autowired
	private StatusService statusService;
	
	public List<Status> getAll() {
		return statusService.getStatuses();
	}
	
	public Status getById(int id) {
		return statusService.getById(id);
	}
	
	public Status getByName(String name) {
		return statusService.getByName(name);
	}
	
	public void update(Status status) {
		statusService.update(status);
	}

}
