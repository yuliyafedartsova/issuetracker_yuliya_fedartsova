package org.training.issuetracker.controllers.spring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.training.issuetracker.constants.Constants;

@Controller
public class PropertyFormSpringController extends AbstractSpringController {

	@RequestMapping("/property-form-update")
	String updateProperty(ModelMap model,
			@RequestParam("property") String propertyName,
			@RequestParam("id") int propertyId) {
		model.addAttribute(Constants.PROPERTY, propertyName);
		switch(propertyName) {
		case Constants.STATUS:
			model.addAttribute(Constants.PARAMETER, statusDao.getById(propertyId));
			break;
		case Constants.TYPE:
			model.addAttribute(Constants.PARAMETER, typeDao.getById(propertyId));
			break;
		case Constants.PRIORITY:
			model.addAttribute(Constants.PARAMETER, priorityDao.getById(propertyId));
			break;
		case Constants.RESOLUTION:
			model.addAttribute(Constants.PARAMETER, resolutionDao.getById(propertyId));
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
