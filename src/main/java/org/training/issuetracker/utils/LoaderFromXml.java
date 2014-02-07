package org.training.issuetracker.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.training.issuetracker.constants.Pages;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.model.DAO.ProjectDAO;
import org.training.issuetracker.model.DAO.PropertyDAO;
import org.training.issuetracker.model.DAO.UserDAO;
import org.training.issuetracker.model.beans.Project;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.model.beans.properties.Priority;
import org.training.issuetracker.model.beans.properties.Resolution;
import org.training.issuetracker.model.beans.properties.Role;
import org.training.issuetracker.model.beans.properties.Status;
import org.training.issuetracker.model.beans.properties.Type;
import org.training.issuetracker.model.beans.properties.Version;
import org.training.issuetracker.model.factories.ProjectFactory;
import org.training.issuetracker.model.factories.PropertyFactory;
import org.training.issuetracker.model.factories.UserFactory;

public class LoaderFromXml {
	public  void loadUsers() {
		UserDAO userDAO = UserFactory.getClassFromFactory();
		try {
		List<User> users = userDAO.getUsers();
		for(User user : users) {
			addUser(user);
		}
		}catch (DaoException e) {
			System.out.println(e.getMessage());
		} 
	}
	
	public void loadPropertise() {
		PropertyDAO propertyDAO = PropertyFactory.getClassFromFactory();
		try {
			List<Type> types = propertyDAO.getTypes();
			List<Priority> priorities = propertyDAO.getPriorities();
			List<Resolution> resolutions = propertyDAO.getResolutions();
			List<Status> statuses = propertyDAO.getStatuses();
			List<Role> roles = propertyDAO.getRoles();
			
			for(Role parameter : roles) {
				addRole(parameter);
			}
			
			for(Status parameter : statuses) {
				addStatus(parameter);
			}
			
			for(Resolution parameter : resolutions) {
				addResolution(parameter);
			}
			
			
			for(Type parameter : types) {
				addType(parameter);
			}
			
			for(Priority parameter : priorities) {
				addPriority(parameter);
			}
			
			}catch (DaoException e) {
				System.out.println(e.getMessage());
			} 
		
	}
	
	public void loadProjects() {
		ProjectDAO propertyDAO = ProjectFactory.getClassFromFactory();
	    try {
	    	List<Project> projects = propertyDAO.getProjects();
	    	for(Project project : projects) {
	    		addProject(project);
	    	}
	    
	    }catch (DaoException e) {
			System.out.println(e.getMessage());
		} 
	
	
	}
	
	
	
	public void addUser(User user) {
		ConnectionManager manager = new ConnectionManager();
		Connection connection = manager.getConnection();
		ResultSet rs = null;
		
		try {
		PreparedStatement ptmSelectUser = connection.prepareStatement("SELECT id FROM users WHERE firstName = ? " +
				"AND lastName = ?;");
		PreparedStatement ptmSelectRole = connection.prepareStatement("SELECT id FROM roles WHERE name = ?;");
		PreparedStatement ptmInsertRole = connection.prepareStatement("INSERT INTO roles(name) VALUES (?);");     
		PreparedStatement ptmInsertUser = connection.prepareStatement("INSERT INTO users(firstName, lastName," +
				" email, roleId, password) VALUES (?,?,?,?,?);");		
				
	    ptmSelectUser.setString(1, user.getFirstName()); //проверка по всем параметрам
		ptmSelectUser.setString(2, user.getLastName());
		rs = ptmSelectUser.executeQuery();
		if(rs.next()) {
			return;
		}
		int roleId = getId(user.getRole().getName(), ptmSelectRole,
				ptmInsertRole);
		ptmInsertUser.setString(1, user.getFirstName());
		ptmInsertUser.setString(2, user.getLastName());
		ptmInsertUser.setString(3, user.getEmail());
		ptmInsertUser.setInt(4, roleId);
		ptmInsertUser.setString(5, user.getPassword());
		ptmInsertUser.executeUpdate();
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
		
	}
	
	public void addType(Type type) {
		ConnectionManager manager = new ConnectionManager();
		Connection connection = manager.getConnection();
		ResultSet rs = null;
		try {
			PreparedStatement ptmSelectType = connection.prepareStatement("SELECT id FROM types WHERE name = ?;");
			PreparedStatement ptmInsertType = connection.prepareStatement("INSERT INTO types(name) VALUES (?);"); 
			ptmSelectType.setString(1, type.getName());
			rs = ptmSelectType.executeQuery();
			if(rs.next()) {
				return;
			}
			ptmInsertType.setString(1, type.getName());
			ptmInsertType.executeUpdate();
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
	
	
	}
	
	public void addPriority(Priority priority) {
		ConnectionManager manager = new ConnectionManager();
		Connection connection = manager.getConnection();
		ResultSet rs = null;
		try {
			PreparedStatement ptmSelectPriority = connection.prepareStatement("SELECT id FROM priorities WHERE name = ?;");
			PreparedStatement ptmInsertPriority = connection.prepareStatement("INSERT INTO priorities(name) VALUES (?);"); 
			ptmSelectPriority.setString(1, priority.getName());
			rs = ptmSelectPriority.executeQuery();
			if(rs.next()) {
				return;
			}
			ptmInsertPriority.setString(1, priority.getName());
			ptmInsertPriority.executeUpdate();
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
	
	
	}
	
	public void addResolution(Resolution parameter) {
		ConnectionManager manager = new ConnectionManager();
		Connection connection = manager.getConnection();
		ResultSet rs = null;
		try {
			PreparedStatement ptmSelectResolution = connection.prepareStatement("SELECT id FROM resolutions WHERE name = ?;");
			PreparedStatement ptmInsertResolution = connection.prepareStatement("INSERT INTO resolutions(name) VALUES (?);"); 
			ptmSelectResolution.setString(1, parameter.getName());
			rs = ptmSelectResolution.executeQuery();
			if(rs.next()) {
				return;
			}
			ptmInsertResolution.setString(1, parameter.getName());
			ptmInsertResolution.executeUpdate();
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
		
	}
	
	
	public void addStatus(Status parameter) {
		ConnectionManager manager = new ConnectionManager();
		Connection connection = manager.getConnection();
		ResultSet rs = null;
		try {
			PreparedStatement ptmSelectStatus = connection.prepareStatement("SELECT id FROM statuses WHERE name = ?;");
			PreparedStatement ptmInsertStatus = connection.prepareStatement("INSERT INTO statuses(name) VALUES (?);"); 
			ptmSelectStatus.setString(1, parameter.getName());
			rs = ptmSelectStatus.executeQuery();
			if(rs.next()) {
				return;
			}
			ptmInsertStatus.setString(1, parameter.getName());
			ptmInsertStatus.executeUpdate();
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
	}
	
	private void addRole(Role parameter) {
		ConnectionManager manager = new ConnectionManager();
		Connection connection = manager.getConnection();
		ResultSet rs = null;
		try {
			PreparedStatement ptmSelectRole = connection.prepareStatement("SELECT id FROM roles WHERE name = ?;");
			PreparedStatement ptmInsertRole = connection.prepareStatement("INSERT INTO roles(name) VALUES (?);"); 
			ptmSelectRole.setString(1, parameter.getName());
			rs = ptmSelectRole.executeQuery();
			if(rs.next()) {
				return;
			}
			ptmInsertRole.setString(1, parameter.getName());
			ptmInsertRole.executeUpdate();
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
	}
	/////
	
	public void addProject(Project project) {
		ConnectionManager manager = new ConnectionManager();
		Connection connection = manager.getConnection();
		ResultSet rs = null;
		try {
			PreparedStatement ptmSelectProject = connection.prepareStatement("SELECT id FROM projects WHERE name = ?;");
			PreparedStatement ptmInsertProject = connection.prepareStatement("INSERT INTO projects(name, managerId, description) VALUES (?,?,?);"); 
			ptmSelectProject.setString(1, project.getName());
			rs = ptmSelectProject.executeQuery();
			if(rs.next()) {
				return;
			}
			ptmInsertProject.setString(1, project.getName());
			ptmInsertProject.setInt(2, project.getManager().getId()); //
			ptmInsertProject.setString(3, project.getDescription());
			ptmInsertProject.executeUpdate();
		
		    for(Version version : project.getBuildVersions()) {
		    	addVersion(project.getId(), version);
		    }
		
		
		
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
	
	}
	
	public void addVersion(int projectId, Version version) {
		ConnectionManager manager = new ConnectionManager();
		Connection connection = manager.getConnection();
		ResultSet rs = null;
		try {
			PreparedStatement ptmSelectVersion = connection.prepareStatement("SELECT id FROM versions WHERE name = ? " +
					"AND projectId = ?;");
			PreparedStatement ptmInsertVersion = connection.prepareStatement("INSERT INTO versions(name, projectId) VALUES (?,?);"); 
			ptmSelectVersion.setString(1, version.getName());
			ptmSelectVersion.setInt(2, projectId);
			rs = ptmSelectVersion.executeQuery();
			if(rs.next()) {
				return;
			}
			ptmInsertVersion.setString(1, version.getName());
			ptmInsertVersion.setInt(2, projectId);
			ptmInsertVersion.executeUpdate();
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
	
	}
	
	
	private int getId(String name, PreparedStatement Select,
			PreparedStatement Insert) throws SQLException {
		int id;
		ResultSet rs = null;
		Select.setString(1, name);
		try {
		rs = Select.executeQuery();
		if (rs.next()) {
			id = rs.getInt(1);
			return id;
		} else {
			Insert.setString(1, name);
			Insert.executeUpdate();
			return getId(name, Select, Insert);
		}
		}finally {
			if (rs != null) {
				rs.close();
			}
		}
	}
	
	
	

}
