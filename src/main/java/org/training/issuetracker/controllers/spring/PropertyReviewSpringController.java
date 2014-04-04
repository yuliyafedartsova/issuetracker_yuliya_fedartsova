package org.training.issuetracker.controllers.spring;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.model.DAO.PrioritiesDAO;
import org.training.issuetracker.model.DAO.ResolutionDAO;
import org.training.issuetracker.model.DAO.StatusesDAO;
import org.training.issuetracker.model.DAO.TypesDAO;
import org.training.issuetracker.model.beans.properties.Priority;
import org.training.issuetracker.model.beans.properties.Resolution;
import org.training.issuetracker.model.beans.properties.Status;
import org.training.issuetracker.model.beans.properties.Type;
import org.training.issuetracker.model.factories.PriorityFactory;
import org.training.issuetracker.model.factories.ResolutionFactory;
import org.training.issuetracker.model.factories.StatusFactory;
import org.training.issuetracker.model.factories.TypeFactory;
import org.training.issuetracker.services.PriorityService;
import org.training.issuetracker.services.ResolutionService;
import org.training.issuetracker.services.StatusService;
import org.training.issuetracker.services.TypeService;

@Controller
public class PropertyReviewSpringController {

	@RequestMapping("/property-review")
	public String reviewProperty(ModelMap model,
			@RequestParam("property") String propertyName) {
		
		model.addAttribute(Constants.PROPERTY, propertyName);
		switch(propertyName) {
		case Constants.STATUS:
			List<Status> statuses = new StatusService().getStatuses();
			model.addAttribute(Constants.PARAMETRES, statuses);
			break;
		case Constants.TYPE:
			List<Type> types = new TypeService().getTypes();
			model.addAttribute(Constants.PARAMETRES, types);
			break;
		case Constants.PRIORITY:
			List<Priority> priorities = new PriorityService().getPriorities();
			model.addAttribute(Constants.PARAMETRES, priorities);
			break;
		case Constants.RESOLUTION:
		    List<Resolution> resolutions = new ResolutionService().getResolutions();
			model.addAttribute(Constants.PARAMETRES, resolutions);
			break;
    	}
		return "parameters";
	}






}
