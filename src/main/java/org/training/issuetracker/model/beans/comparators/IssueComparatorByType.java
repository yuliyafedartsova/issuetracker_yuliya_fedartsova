package org.training.issuetracker.model.beans.comparators;

import java.util.Comparator;

import org.training.issuetracker.model.beans.Issue;

public class IssueComparatorByType implements Comparator <Issue> {
	
	@Override
	public int compare(Issue ob1, Issue ob2) {
		return ob1.getType().compareTo(ob2.getType());
	}
}
