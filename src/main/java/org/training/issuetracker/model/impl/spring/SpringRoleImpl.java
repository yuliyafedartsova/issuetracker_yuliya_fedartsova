package org.training.issuetracker.model.impl.spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.training.issuetracker.model.beans.properties.Role;
import org.training.issuetracker.services.RoleService;

public class SpringRoleImpl {

	@Autowired
	private RoleService roleService;

	public List<Role> getAll() {
		return roleService.getRoles();
	}

}
