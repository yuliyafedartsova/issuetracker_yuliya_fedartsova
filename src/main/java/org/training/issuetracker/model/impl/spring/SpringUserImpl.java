package org.training.issuetracker.model.impl.spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.services.IssueService;
import org.training.issuetracker.services.UserService;

public class SpringUserImpl {

	@Autowired
	UserService userService;
	
	
	public List<User> getUsers() {
		return userService.getUsers();
	}
	
	public User getUserById(int id) {
		return userService.getById(id);
	}

	public void addUser(User user) {
		userService.save(user);
	}


}
