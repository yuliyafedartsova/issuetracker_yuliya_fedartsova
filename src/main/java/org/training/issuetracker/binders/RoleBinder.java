package org.training.issuetracker.binders;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.training.issuetracker.model.beans.Project;
import org.training.issuetracker.model.beans.properties.Role;
import org.training.issuetracker.services.ProjectService;
import org.training.issuetracker.services.RoleService;

public class RoleBinder extends PropertyEditorSupport {

	@Autowired
	private RoleService roleService;
	
	
	@Override
	public void setAsText(String id) throws IllegalArgumentException {
		Role role = null;
		if(id != null && !id.isEmpty()) {
			role = roleService.getById(Integer.valueOf(id));
		}
		setValue(role);
	}


}
