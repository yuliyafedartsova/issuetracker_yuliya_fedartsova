package org.training.issuetracker.controllers.spring;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.exceptions.ValidationException;
import org.training.issuetracker.model.DAO.PrioritiesDAO;
import org.training.issuetracker.model.DAO.ResolutionDAO;
import org.training.issuetracker.model.DAO.StatusesDAO;
import org.training.issuetracker.model.DAO.TypesDAO;
import org.training.issuetracker.model.beans.Property;
import org.training.issuetracker.model.beans.properties.Priority;
import org.training.issuetracker.model.beans.properties.Resolution;
import org.training.issuetracker.model.beans.properties.Status;
import org.training.issuetracker.model.beans.properties.Type;
import org.training.issuetracker.model.factories.PriorityFactory;
import org.training.issuetracker.model.factories.ResolutionFactory;
import org.training.issuetracker.model.factories.StatusFactory;
import org.training.issuetracker.model.factories.TypeFactory;
import org.training.issuetracker.services.PropertyService;
import org.training.issuetracker.utils.HibernateSessionFactory;


@Controller
public class PropertySpringController {
	
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
		 PropertyService service = new PropertyService();
		 switch(property) {
    		case Constants.STATUS:
    			service.update(new Status(id, name));
    			break;
    		case Constants.TYPE:
    			service.update(new Type(id, name));
    			break;
    		case Constants.PRIORITY:
    			service.update(new Priority(id, name));
    			break;
    		case Constants.RESOLUTION:
    			service.update(new Resolution(id, name));
    			break;
        }
   }

	private void addParameter(String property, String name)  {
		 PropertyService service = new PropertyService();
		 switch(property) {
     		case Constants.STATUS:
     			service.save(new Status(name));
     			break;
     		case Constants.TYPE:
     			service.save(new Type(name));
     			break;
     		case Constants.PRIORITY:
     			service.save(new Priority(name));
     			break;
     		case Constants.RESOLUTION:
     			service.save(new Resolution(name));
     			break;
         }
   }




}
