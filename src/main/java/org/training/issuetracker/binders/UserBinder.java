package org.training.issuetracker.binders;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.services.PropertyService;
import org.training.issuetracker.services.ResolutionService;
import org.training.issuetracker.services.UserService;

public class UserBinder extends PropertyEditorSupport {

	@Autowired
	private UserService userService;
	
	@Override
	public void setAsText(String id) throws IllegalArgumentException {
		User user = null;
		if(id != null && !id.isEmpty()) {
			user = userService.getById(Integer.valueOf(id));
		}
		setValue(user);
	}
}


