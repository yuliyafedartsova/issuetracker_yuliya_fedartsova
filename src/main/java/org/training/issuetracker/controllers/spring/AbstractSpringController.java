package org.training.issuetracker.controllers.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.training.issuetracker.model.impl.spring.SpringIssueImpl;
import org.training.issuetracker.model.impl.spring.SpringPriorityImpl;
import org.training.issuetracker.model.impl.spring.SpringProjectImpl;
import org.training.issuetracker.model.impl.spring.SpringResolutionImpl;
import org.training.issuetracker.model.impl.spring.SpringRoleImpl;
import org.training.issuetracker.model.impl.spring.SpringStatusImpl;
import org.training.issuetracker.model.impl.spring.SpringTypeImpl;
import org.training.issuetracker.model.impl.spring.SpringUserImpl;

public abstract class AbstractSpringController {

	@Autowired
	SpringIssueImpl issueDao;
	@Autowired
	SpringPriorityImpl priorityDao;
	@Autowired
	SpringProjectImpl projectDao;
	@Autowired
	SpringResolutionImpl resolutionDao;
	@Autowired
	SpringRoleImpl roleDao;
	@Autowired
	SpringStatusImpl statusDao;
	@Autowired
	SpringTypeImpl typeDao;
	@Autowired
	SpringUserImpl userDao;

}
