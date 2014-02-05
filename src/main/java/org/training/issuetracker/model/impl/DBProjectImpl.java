package org.training.issuetracker.model.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.training.issuetracker.constants.ConstantsSQL;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.model.DAO.ProjectDAO;
import org.training.issuetracker.model.DAO.PropertyDAO;
import org.training.issuetracker.model.DAO.UserDAO;
import org.training.issuetracker.model.beans.Issue;
import org.training.issuetracker.model.beans.Parameter;
import org.training.issuetracker.model.beans.Project;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.model.factories.ProjectFactory;
import org.training.issuetracker.model.factories.PropertyFactory;
import org.training.issuetracker.model.factories.UserFactory;
import org.training.issuetracker.utils.ConnectionManager;

public class DBProjectImpl implements ProjectDAO {
	public List<Project> getProjects() throws DaoException {
		List<Project> projects = new ArrayList<Project>();
		UserDAO userDAO = UserFactory.getClassFromFactory();
		ConnectionManager connectionMng = new ConnectionManager();
		Connection connection = connectionMng.getConnection();
		ResultSet rs = null;
		try{
			Statement ptmSelectProjects = connection.createStatement();
			rs = ptmSelectProjects.executeQuery(ConstantsSQL.SELECT_PROJECTS);	
			while (rs.next()){
				int id = rs.getInt(ConstantsSQL.ID_COLUMN);
			    String name = rs.getString(ConstantsSQL.NAME_COLUMN);
			    String description  = rs.getString(ConstantsSQL.DESCRIPTION_COLUMN);
			    int managerId = rs.getInt(ConstantsSQL.MANAGER_ID_COLUMN);
			    List<Parameter> versions = getVersionsOfProject(id);
			    User manager = userDAO.getUserById(managerId);
			    projects.add(new Project(id, name, manager, versions, description));
			}
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
		return projects;
	}
	
	public Project getProjectById(int id) throws DaoException {
		Project project = null;
		UserDAO userDAO = UserFactory.getClassFromFactory();
		ConnectionManager connectionMng = new ConnectionManager();
		Connection connection = connectionMng.getConnection();
		ResultSet rs = null;
		try{
		PreparedStatement ptmSelectProject = 
				connection.prepareStatement(ConstantsSQL.SELECT_PROJECT_BY_ID);
		ptmSelectProject.setInt(1, id);
		rs = ptmSelectProject.executeQuery();
		rs.next();
		String name = rs.getString(ConstantsSQL.NAME_COLUMN);
		String description  = rs.getString(ConstantsSQL.DESCRIPTION_COLUMN);
		int managerId = rs.getInt(ConstantsSQL.MANAGER_ID_COLUMN);
		User manager = userDAO.getUserById(managerId);
		List<Parameter> versions = getVersionsOfProject(id);
		project = new Project(id, name, manager, versions, description);
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
		return project;
	}
	
	public Parameter getVersionById(int id) throws DaoException {
		 Parameter version = null; 
		 ConnectionManager connectionMng = new ConnectionManager();
		 Connection connection = connectionMng.getConnection();
		 ResultSet rs = null;
		 try{
		 PreparedStatement ptmSelectVersion = 
				 connection.prepareStatement(ConstantsSQL.SELECT_VERSION_BY_ID);
		 ptmSelectVersion.setInt(1, id);
		 rs = ptmSelectVersion.executeQuery();
		 rs.next();
		 String name = rs.getString(ConstantsSQL.NAME_COLUMN);
		 version = new Parameter(id, name);
		 }catch (SQLException e) {
		 	 System.out.println(e.getMessage());
		 } 
		 return version;
	 }
	 
	public List<Parameter> getVersionsOfProject(int projectId) throws DaoException {
		 List<Parameter> versions = new ArrayList<Parameter>();
		 ConnectionManager connectionMng = new ConnectionManager();
		 Connection connection = connectionMng.getConnection();
		 ResultSet rs = null;
		 try {
			 PreparedStatement ptmSelectVersions = 
					 connection.prepareStatement(ConstantsSQL.SELECT_VERSIONS_OF_PROJECT);
			 ptmSelectVersions.setInt(1, projectId);
			 rs = ptmSelectVersions.executeQuery();
			 while (rs.next()){
					int id = rs.getInt(1);
				    String name = rs.getString(ConstantsSQL.NAME_COLUMN);
				    versions.add(new Parameter (id, name));
				}
		 
		 }catch (SQLException e) {
		 	 System.out.println(e.getMessage());
		 } 
		 return versions;
	}
	
	public void addProject(Project project) throws DaoException {
		if(whetherTheProjectExists(project)) {
			return;
		}
		ConnectionManager connectionMng = new ConnectionManager();
		Connection connection = connectionMng.getConnection();
		ResultSet rs = null;
		try {
			PreparedStatement ptmInsertProject = 
				connection.prepareStatement(ConstantsSQL.ADD_PROJECT, PreparedStatement.RETURN_GENERATED_KEYS);
			ptmInsertProject.setString(1, project.getName());	
			ptmInsertProject.setString(2, project.getDescription());	
			ptmInsertProject.setInt(3, project.getManager().getId());
			ptmInsertProject.executeUpdate();
			rs = ptmInsertProject.getGeneratedKeys();
			rs.next();
			int projectId = rs.getInt(1);
			for(Parameter version : project.getBuildVersions()) {
		    	addVersion(version, projectId);
		    }
		
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
	
	}
	
	public void addVersion(Parameter version, int projectId) throws DaoException {
		if(whetherTheVersionExists(version, projectId)) {
			return;
		}
		ConnectionManager connectionMng = new ConnectionManager();
		Connection connection = connectionMng.getConnection();
		try {
			PreparedStatement ptmInsertVersion = 
				connection.prepareStatement(ConstantsSQL.ADD_VERSION);
			ptmInsertVersion.setString(1, version.getName());
			ptmInsertVersion.setInt(2, projectId);
			ptmInsertVersion.executeUpdate();
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
	}
	
	private boolean whetherTheProjectExists(Project project) {
		boolean exist = false;
		ConnectionManager connectionMng = new ConnectionManager();
		Connection connection = connectionMng.getConnection();
		ResultSet rs = null;
		try {
			PreparedStatement ptmSelectProject = 
					connection.prepareStatement(ConstantsSQL.SELECT_IF_PROJECT_EXISTS);
			ptmSelectProject.setString(1, project.getName());
			rs = ptmSelectProject.executeQuery();
		    if(rs.next()) {
		    	exist = true;
		    }
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
		return exist;
	}
	
	private boolean whetherTheVersionExists(Parameter version, int projectId) {
		boolean exist = false;
		ConnectionManager connectionMng = new ConnectionManager();
		Connection connection = connectionMng.getConnection();
		ResultSet rs = null;
		try {
			PreparedStatement ptmSelectVersion = 
					connection.prepareStatement(ConstantsSQL.SELECT_IF_VERSION_EXISTS);
			ptmSelectVersion.setString(1, version.getName());
			rs = ptmSelectVersion.executeQuery();
		    if(rs.next()) {
		    	exist = true;
		    }
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
		return exist;
	}

}
