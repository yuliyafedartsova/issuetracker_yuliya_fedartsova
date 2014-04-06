package org.training.issuetracker.model.impl.spring;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.training.issuetracker.model.beans.properties.Status;
import org.training.issuetracker.model.beans.properties.Type;
import org.training.issuetracker.services.TypeService;

public class SpringTypeImpl {

	@Autowired
	private TypeService typeService;

	public List<Type> getAll() {
		return typeService.getTypes();
	}
	
	public Type getById(int id) {
		return typeService.getById(id);
	}
	
	public void update(Type type) {
		typeService.update(type);
	}
	
	public void add(Type type) {
		typeService.save(type);
	}
}
