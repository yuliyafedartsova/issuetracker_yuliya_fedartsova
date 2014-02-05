package org.training.issuetracker.model.factories;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.constants.Pages;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.model.DAO.UserDAO;
import org.training.issuetracker.model.beans.Parameter;
import org.training.issuetracker.model.beans.Project;
import org.training.issuetracker.model.beans.User;

public class ProjectInstanceFactory {

	public static Project getProject(HttpServletRequest request) throws DaoException {
		UserDAO userDAO = UserFactory.getClassFromFactory();
		String name = request.getParameter(Constants.NAME).trim();
        String description = request.getParameter(Constants.DESCRIPTION).trim();
        String build = request.getParameter(Constants.BUILD).trim();
        int managerId = Integer.parseInt(request.getParameter(Constants.MANAGER)); 
        Parameter version = new Parameter(0, build);
        List<Parameter> versions = new ArrayList<Parameter>();
        versions.add(version);
        User manager = userDAO.getUserById(managerId);
        Project project = new Project(name, manager, versions, description);
        return project;
    }







}
