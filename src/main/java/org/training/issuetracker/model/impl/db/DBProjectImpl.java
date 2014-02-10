package org.training.issuetracker.model.impl.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.training.issuetracker.constants.ConstantsSQL;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.exceptions.ValidationException;
import org.training.issuetracker.model.DAO.ProjectDAO;
import org.training.issuetracker.model.DAO.UserDAO;
import org.training.issuetracker.model.beans.Issue;
import org.training.issuetracker.model.beans.Project;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.model.beans.properties.Version;
import org.training.issuetracker.model.factories.ProjectFactory;
import org.training.issuetracker.model.factories.UserFactory;
import org.training.issuetracker.utils.ConnectionManager;
import org.training.issuetracker.utils.ValidationManagers.ProjectValidator;

public class DBProjectImpl implements ProjectDAO {
	public List<Project> getProjects() throws DaoException {
		List<Project> projects = new ArrayList<Project>();
		UserDAO userDAO = UserFactory.getClassFromFactory();
		ConnectionManager connectionMng = null;
		Connection connection = null;
		ResultSet rs = null;
		Statement ptmSelectProjects = null;
		try{
			connectionMng = new ConnectionManager();
			connection = connectionMng.getConnection();
			ptmSelectProjects = connection.createStatement();
			rs = ptmSelectProjects.executeQuery(ConstantsSQL.SELECT_PROJECTS);	
			while (rs.next()){
				int id = rs.getInt(ConstantsSQL.ID_COLUMN);
			    String name = rs.getString(ConstantsSQL.NAME_COLUMN);
			    String description  = rs.getString(ConstantsSQL.DESCRIPTION_COLUMN);
			    int managerId = rs.getInt(ConstantsSQL.MANAGER_ID_COLUMN);
			    List<Version> versions = getVersionsOfProject(id);
			    User manager = userDAO.getUserById(managerId);
			    projects.add(new Project(id, name, manager, versions, description));
			}
			return projects;
		}catch (SQLException e) {
			throw new DaoException(e);
		}finally {
			if(connectionMng != null) {
				connectionMng.closeStatements(ptmSelectProjects);
				connectionMng.closeResultSets(rs);
				connectionMng.closeConnection();
			}
		}
	}
	
	public Project getProjectById(int id) throws DaoException {
		Project project = null;
		UserDAO userDAO = UserFactory.getClassFromFactory();
		ConnectionManager connectionMng = null;
		Connection connection = null;
		ResultSet rs = null;
		PreparedStatement ptmSelectProject = null;
		try{
			connectionMng = new ConnectionManager();
			connection = connectionMng.getConnection();
			ptmSelectProject = 
				connection.prepareStatement(ConstantsSQL.SELECT_PROJECT_BY_ID);
			ptmSelectProject.setInt(1, id);
			rs = ptmSelectProject.executeQuery();
			rs.next();
			String name = rs.getString(ConstantsSQL.NAME_COLUMN);
			String description  = rs.getString(ConstantsSQL.DESCRIPTION_COLUMN);
			int managerId = rs.getInt(ConstantsSQL.MANAGER_ID_COLUMN);
			User manager = userDAO.getUserById(managerId);
			List<Version> versions = getVersionsOfProject(id);
			project = new Project(id, name, manager, versions, description);
			return project;
		}catch (SQLException e) {
			throw new DaoException(e);
		}finally {
			if(connectionMng != null) {
				connectionMng.closeStatements(ptmSelectProject);
				connectionMng.closeResultSets(rs);
				connectionMng.closeConnection();
			}
		}
	}
	
	public Version getVersionById(int id) throws DaoException {
		 Version version = null; 
		 ConnectionManager connectionMng = null;
		 Connection connection = null;
		 ResultSet rs = null;
		 PreparedStatement ptmSelectVersion = null;
		 try{
			 connectionMng = new ConnectionManager();
			 connection = connectionMng.getConnection();
			 ptmSelectVersion = 
				 connection.prepareStatement(ConstantsSQL.SELECT_VERSION_BY_ID);
			 ptmSelectVersion.setInt(1, id);
			 rs = ptmSelectVersion.executeQuery();
			 rs.next();
			 String name = rs.getString(ConstantsSQL.NAME_COLUMN);
			 version = new Version(id, name);
			 return version;
		 }catch (SQLException e) {
				throw new DaoException(e);
		 }finally {
			 if(connectionMng != null) {
				connectionMng.closeStatements(ptmSelectVersion);
				connectionMng.closeResultSets(rs);
				connectionMng.closeConnection();
			}
		}
	}
	 
	public List<Version> getVersionsOfProject(int projectId) throws DaoException {
		 List<Version> versions = new ArrayList<Version>();
		 ConnectionManager connectionMng = null;
		 Connection connection = null;
		 ResultSet rs = null;
		 PreparedStatement ptmSelectVersions = null;
		 try {
			 connectionMng = new ConnectionManager();
			 connection = connectionMng.getConnection();
			 ptmSelectVersions = 
					 connection.prepareStatement(ConstantsSQL.SELECT_VERSIONS_OF_PROJECT);
			 ptmSelectVersions.setInt(1, projectId);
			 rs = ptmSelectVersions.executeQuery();
			 while (rs.next()){
					int id = rs.getInt(1);
				    String name = rs.getString(ConstantsSQL.NAME_COLUMN);
				    versions.add(new Version(id, name));
				}
			 return versions;
		 }catch (SQLException e) {
				throw new DaoException(e);
		 }finally {
			 if(connectionMng != null) {
				connectionMng.closeStatements(ptmSelectVersions);
				connectionMng.closeResultSets(rs);
				connectionMng.closeConnection();
			}
		}
		 
	}
	
	public void addProject(Project project) throws DaoException, ValidationException {
		ConnectionManager connectionMng = null;
		Connection connection = null;
		ResultSet rs = null;
		PreparedStatement ptmInsertProject = null;
		ProjectValidator validator = new ProjectValidator();
		String errorMessage = validator.validateProject(project);
		if(!errorMessage.isEmpty()) {
			throw new ValidationException(errorMessage);
		}
		try {
			connectionMng = new ConnectionManager();
			connection = connectionMng.getConnection();
			ptmInsertProject = 
				connection.prepareStatement(ConstantsSQL.ADD_PROJECT, PreparedStatement.RETURN_GENERATED_KEYS);
			ptmInsertProject.setString(1, project.getName());	
			ptmInsertProject.setString(2, project.getDescription());	
			ptmInsertProject.setInt(3, project.getManager().getId());
			synchronized (DBProjectImpl.class) {
				if(whetherTheProjectExists(project, connection)) {
					return;
				}
				ptmInsertProject.executeUpdate();
			}
			rs = ptmInsertProject.getGeneratedKeys();
			rs.next();
			int projectId = rs.getInt(1);
			for(Version version : project.getBuildVersions()) {
		    	addVersion(version.getName(), projectId);
		    }
		
		}catch (SQLException e) {
			throw new DaoException(e);
		}finally {
			if(connectionMng != null) {
				connectionMng.closeStatements(ptmInsertProject);
				connectionMng.closeResultSets(rs);
				connectionMng.closeConnection();
		}
	}
	
	}
	
	public void addVersion(String version, int projectId) throws DaoException, ValidationException {
		ConnectionManager connectionMng = null;
		Connection connection = null;
		PreparedStatement ptmInsertVersion = null;
		ProjectValidator validator = new ProjectValidator();
		String errorMessage = validator.validateVersion(version);
		if(!errorMessage.isEmpty()) {
			throw new ValidationException(errorMessage);
		}
		try {
			connectionMng = new ConnectionManager();
			connection = connectionMng.getConnection();
			ptmInsertVersion = 
				connection.prepareStatement(ConstantsSQL.ADD_VERSION);
			ptmInsertVersion.setString(1, version);
			ptmInsertVersion.setInt(2, projectId);
			synchronized (DBProjectImpl.class) {
				if(whetherTheVersionExists(version, projectId, connection)) {
					return;
				}
				ptmInsertVersion.executeUpdate();
			}
		}catch (SQLException e) {
			throw new DaoException(e);
		}finally {
			if(connectionMng != null) {
				connectionMng.closeStatements(ptmInsertVersion);
				connectionMng.closeConnection();
			}
		}
	}
	
	private boolean whetherTheProjectExists(Project project, Connection connection) throws DaoException, SQLException {
		boolean exist = false;
		PreparedStatement ptmSelectProject = null;
		ResultSet rs = null;
		try {
			ptmSelectProject = 
					connection.prepareStatement(ConstantsSQL.SELECT_IF_PROJECT_EXISTS);
			ptmSelectProject.setString(1, project.getName());
			rs = ptmSelectProject.executeQuery();
		    if(rs.next()) {
		    	exist = true;
		    }
		    return exist;
		}catch (SQLException e) {
			throw new DaoException(e);
		}finally {
			rs.close();
			ptmSelectProject.close();
		}
	}
	
	private boolean whetherTheVersionExists(String version, int projectId, Connection connection) 
			throws DaoException, SQLException {
		boolean exist = false;
		ResultSet rs = null;
		PreparedStatement ptmSelectVersion = null;
		try {
			ptmSelectVersion = 
					connection.prepareStatement(ConstantsSQL.SELECT_IF_VERSION_EXISTS);
			ptmSelectVersion.setString(1, version);
			rs = ptmSelectVersion.executeQuery();
		    if(rs.next()) {
		    	exist = true;
		    }
		    return exist;
		}catch (SQLException e) {
			throw new DaoException(e);
		}finally {
			rs.close();
			ptmSelectVersion.close();
		}
	}
	
	public void updateProject(Project project) throws DaoException, ValidationException  {
		ConnectionManager connectionMng = null;
		Connection connection = null;
		PreparedStatement ptmUpdateProject = null;
		ProjectValidator validator = new ProjectValidator();
		String errorMessage = validator.validateProject(project);
		if(!errorMessage.isEmpty()) {
			throw new ValidationException(errorMessage);
		}
		try {
			connectionMng = new ConnectionManager();
			connection = connectionMng.getConnection();
			ptmUpdateProject = 
					connection.prepareStatement("UPDATE projects SET name = ?, description = ?, " +
							"managerId = ?   WHERE id = ?;");
			ptmUpdateProject.setString(1, project.getName());
			ptmUpdateProject.setString(2, project.getDescription());
			ptmUpdateProject.setInt(3, project.getManager().getId());
			ptmUpdateProject.setInt(4, project.getId());
			synchronized (DBProjectImpl.class) {
				if(whetherTheProjectExists(project, connection)) {
					return;
				}
				ptmUpdateProject.executeUpdate();
			}
		}catch (SQLException e) {
			throw new DaoException(e);
		}finally {
			if(connectionMng != null) {
				connectionMng.closeStatements(ptmUpdateProject);
				connectionMng.closeConnection();
			}
		}
	}
	

}
