package org.training.issuetracker.binders;

import java.beans.PropertyEditorSupport;

import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.services.PropertyService;
import org.training.issuetracker.services.UserService;

public class UserBinder extends PropertyEditorSupport {

	@Override
	public void setAsText(String id) throws IllegalArgumentException {
		User user = null;
		if(id != null && !id.isEmpty()) {
			user = new UserService().getById(Integer.valueOf(id));
		}
		setValue(user);
	}
}


