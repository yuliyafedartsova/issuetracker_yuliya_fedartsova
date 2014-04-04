package org.training.issuetracker.binders;

import java.beans.PropertyEditorSupport;

import org.training.issuetracker.model.beans.Issue;
import org.training.issuetracker.services.IssueService;
import org.training.issuetracker.services.UserService;

public class IssueBinder extends PropertyEditorSupport {

	@Override
	public void setAsText(String id) throws IllegalArgumentException {
		Issue issue = null;
		if(id != null && !id.isEmpty()) {
			issue = new IssueService().getById(Integer.valueOf(id));
			
		}
		setValue(issue);
	}

}


