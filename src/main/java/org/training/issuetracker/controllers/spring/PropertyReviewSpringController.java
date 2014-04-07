package org.training.issuetracker.controllers.spring;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.training.issuetracker.constants.Constants;


@Controller
public class PropertyReviewSpringController extends AbstractSpringController {

	@RequestMapping("/property-review")
	public String reviewProperty(ModelMap model,
			@RequestParam("property") String propertyName) {
		
		model.addAttribute(Constants.PROPERTY, propertyName);
		switch(propertyName) {
		case Constants.STATUS:
			model.addAttribute(Constants.PARAMETRES, statusDao.getAll());
			break;
		case Constants.TYPE:
			model.addAttribute(Constants.PARAMETRES, typeDao.getAll());
			break;
		case Constants.PRIORITY:
			model.addAttribute(Constants.PARAMETRES, priorityDao.getAll());
			break;
		case Constants.RESOLUTION:
		    model.addAttribute(Constants.PARAMETRES, resolutionDao.getAll());
			break;
    	}
		return "parameters";
	}






}
