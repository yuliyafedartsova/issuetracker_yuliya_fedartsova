package org.training.issuetracker.binders;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.training.issuetracker.model.beans.Issue;
import org.training.issuetracker.services.IssueService;
import org.training.issuetracker.services.StatusService;
import org.training.issuetracker.services.UserService;

public class IssueBinder extends PropertyEditorSupport {

	@Autowired
	private IssueService issueService;
	
	@Override
	public void setAsText(String id) throws IllegalArgumentException {
		Issue issue = null;
		if(id != null && !id.isEmpty()) {
			issue = issueService.getById(Integer.valueOf(id));
			
		}
		setValue(issue);
	}

}


