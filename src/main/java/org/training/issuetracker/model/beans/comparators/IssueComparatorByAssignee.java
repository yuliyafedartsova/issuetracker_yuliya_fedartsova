package org.training.issuetracker.model.beans.comparators;

import java.util.Comparator;

import org.training.issuetracker.model.beans.Issue;

public class IssueComparatorByAssignee implements Comparator <Issue> {
	
	@Override
	public int compare(Issue ob1, Issue ob2) {
		if(ob1.getAssignee() == null) {
	    	return 1;
	    } else if (ob2.getAssignee() == null) {
	    	return -1;
	    }
		if (ob1.getAssignee().getFirstName().compareTo(ob2.getAssignee().getFirstName()) == 1)
		    return ob1.getAssignee().getLastName().compareTo(ob2.getAssignee().getLastName());
		else {
			return ob1.getAssignee().getFirstName().compareTo(ob2.getAssignee().getFirstName());
		}
	}
}