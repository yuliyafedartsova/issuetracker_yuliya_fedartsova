package org.training.issuetracker.model.impl.spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.training.issuetracker.model.beans.Issue;
import org.training.issuetracker.services.IssueService;

public class SpringIssueImpl {

	@Autowired
	private IssueService issueService;
	
	public List<Issue> getIssues() {
		return issueService.getIssues();
	}
	
	public Issue getIssueById(int id){
		return issueService.getById(id);
	}
	
	public void addIssue(Issue issue) {
		issueService.save(issue);
	}
	
	public void updateIssue(Issue issue) {
		issueService.update(issue);
	}


}
