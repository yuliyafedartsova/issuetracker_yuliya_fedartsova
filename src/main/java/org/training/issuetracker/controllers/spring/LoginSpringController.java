package org.training.issuetracker.controllers.spring;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.exceptions.ValidationException;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.utils.SessionFactoryManager;


@Controller

public class LoginSpringController extends AbstractSpringController {

	@RequestMapping("/login")
	public String registrate(ModelMap model, @RequestParam("email") String email, 
			@RequestParam("password") String password, HttpServletRequest request) {
		final String ERROR_MESSAGE = "Wrong email or password";
		try {
		checkData(email, password);
		}catch (ValidationException e) {
			model.addAttribute(Constants.ERROR_MESSAGE, e.getMessage());
			return Constants.MAIN;
		}
		SessionFactory sessionFactory = new SessionFactoryManager().getSessionFactory();
	    Session session = sessionFactory.openSession();
	    session.beginTransaction();
	    String query = "from User where password=:password";
	    Query q = session.createQuery(query);
	    q.setParameter("password", password);
	    User user = (User) q.uniqueResult();
	    session.getTransaction().commit();
	    if(user != null) {
	    	model.addAttribute(Constants.USER, user);
	    	request.getSession().setAttribute(Constants.USER, user);
	    
	    } else {
	    	model.addAttribute(Constants.ERROR_MESSAGE, ERROR_MESSAGE);
	    }
	  //  return Constants.MAIN;
	    return "forward:/";
	} 
	
	private void checkData(String email, String password) throws ValidationException {
    	if (Constants.EMPTY.equals(email) || Constants.EMPTY.equals(password)) {
			throw new ValidationException(Constants.LOGIN_EMPTY);
		}
    	
    	if (email == null || password == null) {
			throw new ValidationException(Constants.LOGIN_EMPTY);
		}
    	
    	
    	
    	
    	
    	
	}
	
	


}
