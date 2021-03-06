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


@Controller
public class PropertySpringController extends AbstractSpringController {
	
	@RequestMapping("/update-parameter")
	public String updateParameter(ModelMap model, 
			@RequestParam("id") int id, 
			@RequestParam("property") String propertyName,
			@RequestParam("parameter") String parameter
			) {

	 updateParameter(propertyName, id, parameter);	
	 model.addAttribute(Constants.MESSAGE, Constants.SUCCESSFULLY_UPDATE_PARAMETER);
	 return Constants.MAIN;
	}
	
	@RequestMapping("/add-parameter")
	public String addParameter(ModelMap model, 
			@RequestParam("property") String propertyName,
			@RequestParam("parameter") String parameter
			) {

	 addParameter(propertyName, parameter);	
	 model.addAttribute(Constants.MESSAGE, Constants.SUCCESSFULLY_ADD_PARAMETER);
	 return Constants.MAIN;
	}
	
	private void updateParameter(String property, int id, String name)  {
		 
		 switch(property) {
    		case Constants.STATUS:
    			statusDao.update(new Status(id, name));
    			break;
    		case Constants.TYPE:
    			typeDao.update(new Type(id, name));
    			break;
    		case Constants.PRIORITY:
    			priorityDao.update(new Priority(id, name));
    			break;
    		case Constants.RESOLUTION:
    			resolutionDao.update(new Resolution(id, name));
    			break;
        }
   }

	private void addParameter(String property, String name)  {
		 switch(property) {
     		case Constants.TYPE:
     			typeDao.add(new Type(name));
     			break;
     		case Constants.PRIORITY:
     			priorityDao.add(new Priority(name));
     			break;
     		case Constants.RESOLUTION:
     			resolutionDao.add(new Resolution(name));
     			break;
         }
   }




}
