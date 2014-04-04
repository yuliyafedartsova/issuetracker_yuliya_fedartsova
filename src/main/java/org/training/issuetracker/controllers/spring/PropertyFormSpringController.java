package org.training.issuetracker.controllers.spring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.model.beans.properties.Priority;
import org.training.issuetracker.model.beans.properties.Resolution;
import org.training.issuetracker.model.beans.properties.Status;
import org.training.issuetracker.model.beans.properties.Type;
import org.training.issuetracker.services.PriorityService;
import org.training.issuetracker.services.ResolutionService;
import org.training.issuetracker.services.StatusService;
import org.training.issuetracker.services.TypeService;

@Controller
public class PropertyFormSpringController {

	@RequestMapping("/property-form-update")
	String updateProperty(ModelMap model,
			@RequestParam("property") String propertyName,
			@RequestParam("id") int propertyId) {
		model.addAttribute(Constants.PROPERTY, propertyName);
		switch(propertyName) {
		case Constants.STATUS:
			Status statuse = new StatusService().getById(propertyId);
			model.addAttribute(Constants.PARAMETER, statuse);
			break;
		case Constants.TYPE:
			Type type = new TypeService().getById(propertyId);
			model.addAttribute(Constants.PARAMETER, type);
			break;
		case Constants.PRIORITY:
			Priority priority = new PriorityService().getById(propertyId);
			model.addAttribute(Constants.PARAMETER, priority);
			break;
		case Constants.RESOLUTION:
			Resolution resolution = new ResolutionService().getById(propertyId);
			model.addAttribute(Constants.PARAMETER, resolution);
			break;
    	}
	
	    return "update_parameter";
	
	
	}

	@RequestMapping("/property-form-add")
	String addProperty(ModelMap model, 
			@RequestParam("property") String propertyName) {
		model.addAttribute(Constants.PROPERTY, propertyName);
		return "add_parameter";
	}

}
