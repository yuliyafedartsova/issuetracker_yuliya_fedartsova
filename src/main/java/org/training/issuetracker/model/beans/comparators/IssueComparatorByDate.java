package org.training.issuetracker.model.beans.comparators;

import java.util.Comparator;

import org.training.issuetracker.model.beans.Issue;

public class IssueComparatorByDate  implements Comparator <Issue> {
	
	@Override
	public int compare(Issue ob1, Issue ob2) {
	//	return ob1.getCreateDate().compareTo(ob2.getCreateDate());
		return ob2.getCreateDate().compareTo(ob1.getCreateDate());
	
	}
}
 


