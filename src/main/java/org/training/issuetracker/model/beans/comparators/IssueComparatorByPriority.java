package org.training.issuetracker.model.beans.comparators;

import java.util.Comparator;

import org.training.issuetracker.model.beans.Issue;

public class IssueComparatorByPriority implements Comparator <Issue> {
	
	@Override
	public int compare(Issue ob1, Issue ob2) {
		return ob1.getPriority().compareTo(ob2.getPriority());
	}
}