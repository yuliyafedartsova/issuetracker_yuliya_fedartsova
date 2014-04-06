package org.training.issuetracker.model.impl.spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.training.issuetracker.model.beans.properties.Priority;
import org.training.issuetracker.model.beans.properties.Status;
import org.training.issuetracker.services.PriorityService;

public class SpringPriorityImpl {

	@Autowired
	private PriorityService priorityService;
	
	public List<Priority> getAll() {
		return priorityService.getPriorities();
	}
	
	public Priority getById(int id) {
		return priorityService.getById(id);
	}
	
	public void update(Priority priority) {
		priorityService.update(priority);
	}
	
	public void add(Priority priority) {
		priorityService.save(priority);
	}


}
