package org.training.issuetracker.controllers.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.training.issuetracker.binders.IssueBinder;
import org.training.issuetracker.binders.PriorityBinder;
import org.training.issuetracker.binders.ProjectBinder;
import org.training.issuetracker.binders.PropertyBinder;
import org.training.issuetracker.binders.ResolutionBinder;
import org.training.issuetracker.binders.StatusBinder;
import org.training.issuetracker.binders.TypeBinder;
import org.training.issuetracker.binders.UserBinder;
import org.training.issuetracker.binders.VersionBinder;
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
	
	@Autowired
	IssueBinder issueBinder;
	@Autowired
	ProjectBinder projectBinder;
	@Autowired
	ResolutionBinder resolutionBinder;
	@Autowired
	StatusBinder statusBinder;
	@Autowired
	TypeBinder typeBinder;
	@Autowired
	UserBinder userBinder;
	@Autowired
	VersionBinder versionBinder;
	@Autowired
	PropertyBinder propertyBinder;
	@Autowired
	PriorityBinder priorityBinder;

}
