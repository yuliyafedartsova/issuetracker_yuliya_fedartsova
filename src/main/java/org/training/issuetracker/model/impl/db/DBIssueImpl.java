package org.training.issuetracker.model.impl.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.constants.ConstantsSQL;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.exceptions.ValidationException;
import org.training.issuetracker.model.DAO.IssueDAO;
import org.training.issuetracker.model.DAO.PrioritiesDAO;
import org.training.issuetracker.model.DAO.ProjectDAO;
import org.training.issuetracker.model.DAO.ResolutionDAO;
import org.training.issuetracker.model.DAO.StatusesDAO;
import org.training.issuetracker.model.DAO.TypesDAO;
import org.training.issuetracker.model.DAO.UserDAO;
import org.training.issuetracker.model.beans.Issue;
import org.training.issuetracker.model.beans.Project;
import org.training.issuetracker.model.beans.User;
import org.training.issuetracker.model.beans.properties.Priority;
import org.training.issuetracker.model.beans.properties.Resolution;
import org.training.issuetracker.model.beans.properties.Role;
import org.training.issuetracker.model.beans.properties.Status;
import org.training.issuetracker.model.beans.properties.Type;
import org.training.issuetracker.model.beans.properties.Version;
import org.training.issuetracker.model.factories.PriorityFactory;
import org.training.issuetracker.model.factories.ProjectFactory;
import org.training.issuetracker.model.factories.ResolutionFactory;
import org.training.issuetracker.model.factories.StatusFactory;
import org.training.issuetracker.model.factories.TypeFactory;
import org.training.issuetracker.model.factories.UserFactory;
import org.training.issuetracker.utils.ConnectionManager;
import org.training.issuetracker.utils.ValidationManagers.IssueValidator;

public class DBIssueImpl implements IssueDAO {

   public Issue getIssueById(int id) throws DaoException, ValidationException {
	   	Issue issue = null;
	    ConnectionManager connectionMng = null;
		Connection connection = null;
		ResultSet rs = null;
		ResultSet resultSet = null;
		PreparedStatement ptmSelectIssue = null;
		PreparedStatement ptmSelectUsers = null;
		PreparedStatement ptmSelectProject = null;
		PreparedStatement ptmSelectVersions = null;
		try {
			connectionMng = new ConnectionManager();
			connection = connectionMng.getConnection();
			ptmSelectIssue = 
					connection.prepareStatement(ConstantsSQL.SELECT_ISSUE_BY_ID);
			ptmSelectUsers = connection.prepareStatement(ConstantsSQL.SELECT_USER_BY_ID);
			ptmSelectProject = connection.prepareStatement(ConstantsSQL.SELECT_PROJECT_BY_ID);
			ptmSelectVersions = connection.prepareStatement(ConstantsSQL.SELECT_VERSIONS_OF_PROJECT);
			ptmSelectIssue.setInt(Constants.INDEX_1, id);
			rs = ptmSelectIssue.executeQuery();
			if(!rs.next()) {
				throw new ValidationException(Constants.SOME_PROBLEMS);
			}
			Priority priority = new Priority(rs.getInt(Constants.INDEX_1), rs.getString(Constants.INDEX_2));
			User assignee = (rs.getInt(Constants.INDEX_3) != Constants.NULL) ? 
					getUserById(rs.getInt(Constants.INDEX_3), connection, ptmSelectUsers, 
							resultSet) : null;
			Type type = new Type(rs.getInt(Constants.INDEX_4), rs.getString(Constants.INDEX_5));    
			String summary = rs.getString(Constants.INDEX_6);
			String description = rs.getString(Constants.INDEX_7);
			Status status = new Status(rs.getInt(Constants.INDEX_8), rs.getString(Constants.INDEX_9));   
			Project project = getProjectById(rs.getInt(Constants.INDEX_10), connection, ptmSelectProject, 
					ptmSelectVersions, resultSet);
			Resolution resolution = (rs.getInt(Constants.INDEX_11) != Constants.NULL) ? 
					new Resolution(rs.getInt(Constants.INDEX_11), rs.getString(Constants.INDEX_12)) : null;
			Version version = new Version(rs.getInt(Constants.INDEX_13), rs.getString(Constants.INDEX_14));
			Date createDate = rs.getDate(Constants.INDEX_15);
			User author = getUserById(rs.getInt(Constants.INDEX_16), connection, ptmSelectUsers, resultSet);
			Date modifyDate = rs.getDate(Constants.INDEX_17);
			User modifier = (rs.getInt(Constants.INDEX_18) != Constants.NULL) ? getUserById(rs.getInt(Constants.INDEX_18), connection, 
					ptmSelectUsers, resultSet) : null;
			issue = new Issue(id, priority, assignee,  type, summary, description, status,
					project, resolution, version, createDate, modifyDate, author, modifier);
			return issue;
		}catch (SQLException e) {
			throw new DaoException(Constants.DB_PROBLEM);
		}finally {
			if(connectionMng != null) {
				connectionMng.closeStatements(ptmSelectIssue, ptmSelectUsers, 
						ptmSelectProject, ptmSelectVersions);
				connectionMng.closeResultSets(rs, resultSet);
				connectionMng.closeConnection();
			}
		} 
	}
	
	public List<Issue> getNLastAddedIssues(int n, String sortingType) throws DaoException, ValidationException  {
		String SELECT_ISSUES = ConstantsSQL.SELECT_LAST_ADDED_ISSUES + getSortingPartOfSelectQuery(sortingType);
		List<Issue> issues = new ArrayList<Issue>();
		ConnectionManager connectionMng = null;
		Connection connection = null;
		ResultSet rs = null;
		ResultSet resultSet = null;
		PreparedStatement ptmSelectIssues = null;
		PreparedStatement ptmSelectUsers = null;
		PreparedStatement ptmSelectProject = null;
		PreparedStatement ptmSelectVersions = null;
		try{
			connectionMng = new ConnectionManager();
			connection = connectionMng.getConnection();
			ptmSelectIssues = connection.prepareStatement(SELECT_ISSUES);
			ptmSelectUsers = connection.prepareStatement(ConstantsSQL.SELECT_USER_BY_ID);
			ptmSelectProject = connection.prepareStatement(ConstantsSQL.SELECT_PROJECT_BY_ID);
			ptmSelectVersions = connection.prepareStatement(ConstantsSQL.SELECT_VERSIONS_OF_PROJECT);
			ptmSelectIssues.setInt(Constants.INDEX_1, n);
			rs = ptmSelectIssues.executeQuery();
			while (rs.next()){
				int id = rs.getInt(Constants.INDEX_1);
				Priority priority = new Priority(rs.getInt(Constants.INDEX_2), rs.getString(Constants.INDEX_3));
				User assignee = (rs.getInt(Constants.INDEX_4) != Constants.NULL) ? getUserById(rs.getInt(Constants.INDEX_4), connection, 
						ptmSelectUsers, resultSet) : null;
				Type type = new Type(rs.getInt(Constants.INDEX_5), rs.getString(Constants.INDEX_6));
				String summary = rs.getString(Constants.INDEX_7);
				String description = rs.getString(Constants.INDEX_8);
				Status status = new Status(rs.getInt(Constants.INDEX_9), rs.getString(Constants.INDEX_10));
				Project project = getProjectById(rs.getInt(Constants.INDEX_11), connection, ptmSelectProject, 
						ptmSelectVersions, resultSet);
				Resolution resolution = (rs.getInt(Constants.INDEX_12) != Constants.NULL) ? 
						new Resolution(rs.getInt(Constants.INDEX_12), rs.getString(Constants.INDEX_13)) : null;
				Version version = new Version(rs.getInt(Constants.INDEX_14), rs.getString(Constants.INDEX_15));
				Date createDate = rs.getDate(Constants.INDEX_16);
				User author = getUserById(rs.getInt(Constants.INDEX_17), connection, ptmSelectUsers, resultSet);
				Date modifyDate = rs.getDate(Constants.INDEX_18);
				User modifier = (rs.getInt(Constants.INDEX_19) != Constants.NULL) ? getUserById(rs.getInt(Constants.INDEX_19), connection, 
						ptmSelectUsers, resultSet) : null;
				Issue issue = new Issue(id, priority, assignee,  type, summary, description, status,
						project, resolution, version, createDate, modifyDate, author, modifier);
				issues.add(issue);
		}
			return issues;
		}catch (SQLException e) {
			throw new DaoException(Constants.DB_PROBLEM);
		}finally {
			if(connectionMng != null) {
				connectionMng.closeStatements(ptmSelectIssues, ptmSelectUsers, 
						ptmSelectProject, ptmSelectVersions);
				connectionMng.closeResultSets(rs, resultSet);
				connectionMng.closeConnection();
			}
		} 
	}
	
	private User getUserById(int id, Connection connection, PreparedStatement ptmSelectUsers, 
			ResultSet userRs) throws DaoException, ValidationException {
		User user = null;
		try{
	    ptmSelectUsers.setInt(Constants.INDEX_1, id);
		userRs = ptmSelectUsers.executeQuery();
    	if(!userRs.next()) {
    		throw new ValidationException(Constants.SOME_PROBLEMS);
    	}
    	user = new User(id, userRs.getString(Constants.INDEX_1), 
    		userRs.getString(Constants.INDEX_2), userRs.getString(Constants.INDEX_3), 
    			new Role(userRs.getInt(Constants.INDEX_4), userRs.getString(Constants.INDEX_5)),
    				userRs.getString(Constants.INDEX_6));
    	
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
    	return user;
	}
	
	private Project getProjectById(int id, Connection connection, PreparedStatement ptmSelectProject, 
			PreparedStatement ptmSelectVersions, ResultSet projectRs) throws DaoException, ValidationException {
		Project project = null;
		List<Version> versions = new ArrayList<Version>();
		try {
			ptmSelectVersions.setInt(Constants.INDEX_1, id);
			projectRs = ptmSelectVersions.executeQuery();
			while(projectRs.next()) {
				versions.add(new Version(projectRs.getInt(Constants.INDEX_1), projectRs.getString(2)));
			}
			ptmSelectProject.setInt(Constants.INDEX_1, id);
			projectRs = ptmSelectProject.executeQuery();
			if(!projectRs.next()) {
	    		throw new ValidationException(Constants.SOME_PROBLEMS);
	    	}
			String name = projectRs.getString(Constants.INDEX_1);
			String description = projectRs.getString(Constants.INDEX_2);
			User manager = new User(projectRs.getInt(Constants.INDEX_3), projectRs.getString(Constants.INDEX_4), 
				projectRs.getString(Constants.INDEX_5), projectRs.getString(Constants.INDEX_6), 
					new Role(projectRs.getInt(Constants.INDEX_7), projectRs.getString(Constants.INDEX_8)), 
						projectRs.getString(Constants.INDEX_9));
			project = new Project(id, name, manager, versions, description);
			
		}catch (SQLException e) {
			throw new DaoException(Constants.DB_PROBLEM);
		}
		return project;
		
	}
	
	public List<Issue> getNAssignedIssues(int n, User user, String sortingType) throws DaoException, ValidationException {
		String SELECT_ISSUES = ConstantsSQL.SELECT_ASSIGNED_ISSUES + getSortingPartOfSelectQuery(sortingType);
		List<Issue> issues = new ArrayList<Issue>();
		PreparedStatement ptmSelectUsers = null;
		PreparedStatement ptmSelectProject = null;
		PreparedStatement ptmSelectVersions = null;
		ConnectionManager connectionMng = null;
		Connection connection = null;
		ResultSet rs = null;
		ResultSet resultSet = null;
		PreparedStatement ptmSelectIssues = null;
		try{
			connectionMng = new ConnectionManager();
			connection = connectionMng.getConnection();
			ptmSelectIssues = 
					connection.prepareStatement(SELECT_ISSUES);
			ptmSelectUsers = connection.prepareStatement(ConstantsSQL.SELECT_USER_BY_ID);
			ptmSelectProject = connection.prepareStatement(ConstantsSQL.SELECT_PROJECT_BY_ID);
			ptmSelectVersions = connection.prepareStatement(ConstantsSQL.SELECT_VERSIONS_OF_PROJECT);
			ptmSelectIssues.setInt(Constants.INDEX_1, n);
			ptmSelectIssues.setInt(Constants.INDEX_2, user.getId());
			rs = ptmSelectIssues.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(Constants.INDEX_1);
				Priority priority = new Priority(rs.getInt(Constants.INDEX_2), 
						rs.getString(Constants.INDEX_3));
				Type type = new Type(rs.getInt(Constants.INDEX_4), rs.getString(Constants.INDEX_5));
				String summary = rs.getString(Constants.INDEX_6);
				String description = rs.getString(Constants.INDEX_7);
				Status status = new Status(rs.getInt(Constants.INDEX_8), 
						rs.getString(Constants.INDEX_9));
				Project project = getProjectById(rs.getInt(Constants.INDEX_10), connection, ptmSelectProject, 
						ptmSelectVersions, resultSet);
				Resolution resolution = (rs.getInt(Constants.INDEX_11) != Constants.NULL) ? 
						new Resolution(rs.getInt(Constants.INDEX_11), rs.getString(Constants.INDEX_12)) 
							: null;
				Version version = new Version(rs.getInt(Constants.INDEX_13), 
						rs.getString(Constants.INDEX_14));
				Date createDate = rs.getDate(Constants.INDEX_15);
				User author = getUserById(rs.getInt(Constants.INDEX_16), connection, ptmSelectUsers, resultSet);
				Date modifyDate = rs.getDate(Constants.INDEX_17);
				User modifier = (rs.getInt(Constants.INDEX_18) != Constants.NULL) ? 
						getUserById(rs.getInt(Constants.INDEX_18), connection, 
								ptmSelectUsers, resultSet) : null;
				Issue issue = new Issue(id, priority, user,  type, summary, description, status,
						project, resolution, version, createDate, modifyDate, author, modifier);
				issues.add(issue);
			}
			return issues;
		}catch (SQLException e) {
			throw new DaoException(Constants.DB_PROBLEM);
		}finally {
			if(connectionMng != null) {
				connectionMng.closeStatements(ptmSelectIssues, ptmSelectUsers, 
						ptmSelectProject, ptmSelectVersions);
				connectionMng.closeResultSets(rs, resultSet);
				connectionMng.closeConnection();
			}
		} 
	}
	
	public void addIssue(Issue issue) throws DaoException, ValidationException {
		ConnectionManager connectionMng = null;
		IssueValidator validator = new IssueValidator();
		String errorMessage = validator.validateIssue(issue);
		if(!errorMessage.isEmpty()) {
			throw new ValidationException(errorMessage);
		}
		Connection connection = null;
		PreparedStatement ptmInsertIssue = null;
		try {
			connectionMng = new ConnectionManager();
			connection = connectionMng.getConnection();
			ptmInsertIssue = 
				connection.prepareStatement(ConstantsSQL.ADD_ISSUE);
			ptmInsertIssue.setInt(Constants.INDEX_1, issue.getPriority().getId());	
			if(issue.getAssignee() != null) {
				ptmInsertIssue.setInt(Constants.INDEX_2, issue.getAssignee().getId());
			} else {
				ptmInsertIssue.setInt(Constants.INDEX_2, Constants.NULL);
			}
			ptmInsertIssue.setInt(Constants.INDEX_3, issue.getType().getId());	
			ptmInsertIssue.setString(Constants.INDEX_4, issue.getSummary());
			ptmInsertIssue.setString(Constants.INDEX_5, issue.getDescription());
			ptmInsertIssue.setInt(Constants.INDEX_6, issue.getStatus().getId());
			ptmInsertIssue.setInt(Constants.INDEX_7, issue.getProject().getId());
			if(issue.getResolution() != null) {
				ptmInsertIssue.setInt(Constants.INDEX_8, issue.getResolution().getId());
			} else {
				ptmInsertIssue.setInt(Constants.INDEX_8, Constants.NULL);
			}
			ptmInsertIssue.setInt(Constants.INDEX_9, issue.getBuildFound().getId());
			ptmInsertIssue.setDate(Constants.INDEX_10, issue.getCreateDate());
			ptmInsertIssue.setInt(Constants.INDEX_11, issue.getAuthor().getId());
			if(issue.getModifyDate() != null) {
				ptmInsertIssue.setDate(Constants.INDEX_12, issue.getModifyDate());
				ptmInsertIssue.setInt(Constants.INDEX_13, issue.getModifier().getId());
			} else {
				ptmInsertIssue.setDate(Constants.INDEX_12, null);
				ptmInsertIssue.setInt(Constants.INDEX_13, Constants.NULL);
			}
			ptmInsertIssue.executeUpdate();
		}catch (SQLException e) {
			throw new DaoException(Constants.DB_PROBLEM);
		}finally {
			if(connectionMng != null) {
				connectionMng.closeStatements(ptmInsertIssue);
			    connectionMng.closeConnection();
			}
		} 
	}
	
    public void updateIssue(Issue issue) throws DaoException, ValidationException {
    	ConnectionManager connectionMng = null;
		Connection connection = null;
		PreparedStatement ptmUpdateIssue = null;
		IssueValidator validator = new IssueValidator();
		String errorMessage = validator.validateIssue(issue);
		if(!errorMessage.isEmpty()) {
			throw new ValidationException(errorMessage);
		}
		try {
			connectionMng = new ConnectionManager();
			connection = connectionMng.getConnection();
		    ptmUpdateIssue = 
					connection.prepareStatement(ConstantsSQL.UPDATE_ISSUE);
			ptmUpdateIssue.setInt(Constants.INDEX_1, issue.getPriority().getId());
			if(issue.getAssignee() != null) {
				ptmUpdateIssue.setInt(Constants.INDEX_2, issue.getAssignee().getId());
			} else {
				ptmUpdateIssue.setInt(Constants.INDEX_2, Constants.NULL);
			}
			ptmUpdateIssue.setInt(Constants.INDEX_3, issue.getType().getId());
			ptmUpdateIssue.setString(Constants.INDEX_4, issue.getSummary());
			ptmUpdateIssue.setString(Constants.INDEX_5, issue.getDescription());
			ptmUpdateIssue.setInt(Constants.INDEX_6, issue.getStatus().getId());
			ptmUpdateIssue.setInt(Constants.INDEX_7, issue.getProject().getId());
			if(issue.getResolution() != null) {
				ptmUpdateIssue.setInt(Constants.INDEX_8, issue.getResolution().getId());
			} else {
				ptmUpdateIssue.setInt(Constants.INDEX_8, Constants.NULL);
			}
			ptmUpdateIssue.setInt(Constants.INDEX_9, issue.getBuildFound().getId());
			ptmUpdateIssue.setDate(Constants.INDEX_10, issue.getCreateDate());
			ptmUpdateIssue.setInt(Constants.INDEX_11, issue.getAuthor().getId());
			ptmUpdateIssue.setDate(Constants.INDEX_12, issue.getModifyDate());
			ptmUpdateIssue.setInt(Constants.INDEX_13, issue.getModifier().getId());
			ptmUpdateIssue.setInt(Constants.INDEX_14, issue.getId());
		    ptmUpdateIssue.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(Constants.DB_PROBLEM);
		}finally {
			if(connectionMng != null) {
				connectionMng.closeStatements(ptmUpdateIssue);
			    connectionMng.closeConnection();
			}
		} 
	}
    
    private String getSortingPartOfSelectQuery(String sortingType) {
		final String BY_ID_DESC = "order by id desc";
    	final String BY_ID = "order by id";
    	final String BY_TYPE = "order by typeId";
    	final String BY_PRIORITY = "order by priorityId";
    	final String BY_ASSIGNEE = "order by assigneeId";
    	final String BY_STATUS = "order by statusId";
    	String sorting = null;
		switch(sortingType) {
			case Constants.DEFAULT :
				sorting = BY_ID_DESC;
				break;
			case Constants.ID:
				sorting = BY_ID;
				break;
			case Constants.TYPE:
				sorting = BY_TYPE;
				break;
			case Constants.PRIORITY:
				sorting = BY_PRIORITY;
				break;
			case Constants.ASSIGNEE:
				sorting = BY_ASSIGNEE;
				break;
			case Constants.STATUS:
				sorting = BY_STATUS;
				break;
		}
		return sorting;
	}
}
