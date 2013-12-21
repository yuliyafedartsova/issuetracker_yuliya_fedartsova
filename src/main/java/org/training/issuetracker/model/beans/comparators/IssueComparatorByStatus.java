package org.training.issuetracker.model.beans.comparators;

import java.util.Comparator;

import org.training.issuetracker.model.beans.Issue;

public class IssueComparatorByStatus implements Comparator <Issue> {
	
	@Override
	public int compare(Issue ob1, Issue ob2) {
		return ob1.getStatus().compareTo(ob2.getStatus());
	}
}