package org.training.issuetracker.model.impl.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.constants.ConstantsSQL;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.exceptions.ValidationException;
import org.training.issuetracker.model.DAO.ProjectDAO;
import org.training.issuetracker.model.beans.Project;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.model.beans.properties.Role;
import org.training.issuetracker.model.beans.properties.Version;
import org.training.issuetracker.utils.ConnectionManager;
import org.training.issuetracker.utils.ValidationManagers.ProjectValidator;

public class DBProjectImpl implements ProjectDAO {
	public List<Project> getProjects() throws DaoException, ValidationException {
		List<Project> projects = new ArrayList<Project>();
		ConnectionManager connectionMng = null;
		Connection connection = null;
		ResultSet rs = null;
		ResultSet resultSet = null;
		PreparedStatement ptmSelectProjects = null;
		PreparedStatement ptmSelectVersions = null;
		try{
			connectionMng = new ConnectionManager();
			connection = connectionMng.getConnection();
			ptmSelectProjects = connection.prepareStatement(ConstantsSQL.SELECT_PROJECTS);
			ptmSelectVersions = connection.prepareStatement(ConstantsSQL.SELECT_VERSIONS_OF_PROJECT);
			rs = ptmSelectProjects.executeQuery();	
			while (rs.next()){
				int id = rs.getInt(1);
			    String name = rs.getString(2);
			    String description  = rs.getString(3);
			    User manager = new User(rs.getInt(4), rs.getString(5), rs.getString(6), 
			    		rs.getString(7), new Role(rs.getInt(8), rs.getString(9)), rs.getString(10));
			    ptmSelectVersions.setInt(1, id);
			    resultSet = ptmSelectVersions.executeQuery();
			    List<Version> versions = new ArrayList<Version>();
			    while(resultSet.next()) {
					versions.add(new Version(resultSet.getInt(1), resultSet.getString(2)));
				}
			    projects.add(new Project(id, name, manager, versions, description));
			}
			return projects;
		}catch (SQLException e) {
			throw new DaoException(Constants.DB_PROBLEM);
		}finally {
			if(connectionMng != null) {
				connectionMng.closeStatements(ptmSelectProjects, ptmSelectVersions);
				connectionMng.closeResultSets(rs, resultSet);
				connectionMng.closeConnection();
			}
		}
	}
	
	public Project getProjectById(int id) throws DaoException, ValidationException {
		Project project = null;
		ConnectionManager connectionMng = null;
		Connection connection = null;
		ResultSet rs = null;
		ResultSet resultSet = null;
		PreparedStatement ptmSelectProject = null;
		PreparedStatement ptmSelectVersions = null;
		try{
			connectionMng = new ConnectionManager();
			connection = connectionMng.getConnection();
			ptmSelectProject = 
				connection.prepareStatement(ConstantsSQL.SELECT_PROJECT_BY_ID);
			ptmSelectVersions = connection.prepareStatement(ConstantsSQL.SELECT_VERSIONS_OF_PROJECT);
			ptmSelectProject.setInt(1, id);
			rs = ptmSelectProject.executeQuery();
			if(!rs.next()){
				throw new ValidationException(Constants.SOME_PROBLEMS);
			}
			String name = rs.getString(1);
			String description  = rs.getString(2);
			User manager = new User(rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6),
					new Role(rs.getInt(7), rs.getString(8)), rs.getString(9));
			ptmSelectVersions.setInt(1, id);
		    resultSet = ptmSelectVersions.executeQuery();
		    List<Version> versions = new ArrayList<Version>();
		    while(resultSet.next()) {
				versions.add(new Version(resultSet.getInt(1), resultSet.getString(2)));
			}
			project = new Project(id, name, manager, versions, description);
			return project;
		}catch (SQLException e) {
			throw new DaoException(Constants.DB_PROBLEM);
		}finally {
			if(connectionMng != null) {
				connectionMng.closeStatements(ptmSelectProject, ptmSelectVersions);
				connectionMng.closeResultSets(rs, resultSet);
				connectionMng.closeConnection();
			}
		}
	}
	
	public Version getVersionById(int id) throws DaoException, ValidationException {
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
			 if(!rs.next()){
					throw new ValidationException(Constants.SOME_PROBLEMS);
			 }
			 String name = rs.getString(ConstantsSQL.NAME_COLUMN);
			 version = new Version(id, name);
			 return version;
		 }catch (SQLException e) {
				throw new DaoException(Constants.DB_PROBLEM);
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
				throw new DaoException(Constants.DB_PROBLEM);
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
			ptmInsertProject.executeUpdate();
			rs = ptmInsertProject.getGeneratedKeys();
			if(!rs.next()){
				throw new ValidationException(Constants.SOME_PROBLEMS);
		    }
			int projectId = rs.getInt(1);
			for(Version version : project.getBuildVersions()) {
		    	addVersion(version.getName(), projectId);
		    }
		
		}catch (SQLException e) {
			throw new DaoException(Constants.DB_PROBLEM);
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
		PreparedStatement ptmSelectVersion = null;
		ResultSet rs = null;
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
			ptmSelectVersion = connection.prepareStatement(ConstantsSQL.SELECT_IF_VERSION_EXISTS);
			ptmSelectVersion.setString(1, version);
			ptmSelectVersion.setInt(2, projectId);
			rs = ptmSelectVersion.executeQuery();
			ptmInsertVersion.setString(1, version);
			ptmInsertVersion.setInt(2, projectId);
			synchronized (DBProjectImpl.class) {
				if(rs.next()) {
					throw new ValidationException(Constants.VERSION_EXIST);
				}
				ptmInsertVersion.executeUpdate();
			}
		}catch (SQLException e) {
			throw new DaoException(Constants.DB_PROBLEM);
		}finally {
			if(connectionMng != null) {
				connectionMng.closeStatements(ptmInsertVersion, ptmSelectVersion);
				connectionMng.closeResultSets(rs);
				connectionMng.closeConnection();
			}
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
					connection.prepareStatement(ConstantsSQL.UPDATE_PROJECT);
			ptmUpdateProject.setString(1, project.getName());
			ptmUpdateProject.setString(2, project.getDescription());
			ptmUpdateProject.setInt(3, project.getManager().getId());
			ptmUpdateProject.setInt(4, project.getId());
			ptmUpdateProject.executeUpdate();
		}catch (SQLException e) {
			throw new DaoException(Constants.DB_PROBLEM);
		}finally {
			if(connectionMng != null) {
				connectionMng.closeStatements(ptmUpdateProject);
				connectionMng.closeConnection();
			}
		}
	}
	

}
