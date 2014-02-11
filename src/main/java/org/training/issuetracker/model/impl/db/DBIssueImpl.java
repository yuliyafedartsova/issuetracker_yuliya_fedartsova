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
		UserDAO userDAO = UserFactory.getClassFromFactory();
		StatusesDAO statusDAO = StatusFactory.getClassFromFactory();
		TypesDAO typesDAO = TypeFactory.getClassFromFactory();
		PrioritiesDAO priorityDAO = PriorityFactory.getClassFromFactory();
		ProjectDAO projectDAO = ProjectFactory.getClassFromFactory();
		ConnectionManager connectionMng = null;
		Connection connection = null;
		ResultSet rs = null;
		PreparedStatement ptmSelectIssue = null;
		try {
			connectionMng = new ConnectionManager();
			connection = connectionMng.getConnection();
			ptmSelectIssue = 
					connection.prepareStatement(ConstantsSQL.SELECT_ISSUE_BY_ID);
			ptmSelectIssue.setInt(1, id);
			rs = ptmSelectIssue.executeQuery();
			if(!rs.next()) {
				throw new ValidationException(Constants.SOME_PROBLEMS);
			}
			Priority priority = priorityDAO.getById(rs.getInt(ConstantsSQL.PRIORITY_ID_COLUMN));
			Type type = typesDAO.getById(rs.getInt(ConstantsSQL.TYPE_ID_COLUMN));
			String summary = rs.getString(ConstantsSQL.SUMMARY_COLUMN);
			String description = rs.getString(ConstantsSQL.DESCRIPTION_COLUMN);
			Status status = statusDAO.getById(rs.getInt(ConstantsSQL.STATUS_ID_COLUMN));
			Project project = 
					projectDAO.getProjectById(rs.getInt(ConstantsSQL.PRIORITY_ID_COLUMN));
			Version version = 
					projectDAO.getVersionById(rs.getInt(ConstantsSQL.VERSION_ID_COLUMN));
			Date createDate = rs.getDate(ConstantsSQL.CREATE_DATE_COLUMN);
			User author = userDAO.getUserById(rs.getInt(ConstantsSQL.AUTHOR_ID_COLUMN));
			issue = new Issue(id, priority, type, summary, description, status,
					project, version, createDate, author);
			if(rs.getInt(ConstantsSQL.ASSIGNEE_ID_COLUMN) != 0) {
				User assignee = userDAO.getUserById(rs.getInt(ConstantsSQL.ASSIGNEE_ID_COLUMN));
				issue.setAssignee(assignee);
			}
			if(rs.getDate(ConstantsSQL.MODIFY_DATE_COLUMN) != null) {
				User modifier = 
						userDAO.getUserById(rs.getInt(ConstantsSQL.MODIFIER_ID_COLUMN));
				issue.setModifier(modifier);
				issue.setModifyDate(rs.getDate(ConstantsSQL.MODIFY_DATE_COLUMN));
			}
			if(rs.getInt(ConstantsSQL.RESOLUTION_ID_COLUMN) != 0) {
				ResolutionDAO resolutionDAO = ResolutionFactory.getClassFromFactory();
				Resolution resolution = resolutionDAO.getById(rs.getInt(ConstantsSQL.RESOLUTION_ID_COLUMN));
				issue.setResolution(resolution);
			}
			return issue;
		}catch (SQLException e) {
			throw new DaoException(e);
		}finally {
			if(connectionMng != null) {
				connectionMng.closeStatements(ptmSelectIssue);
				connectionMng.closeResultSets(rs);
				connectionMng.closeConnection();
			}
		} 
	}
	
	public List<Issue> getNLastAddedIssues(int n, String sortingType) throws DaoException, ValidationException  {
		String SELECT_ISSUES = ConstantsSQL.SELECT_LAST_ADDED_ISSUES + getSortingPartOfSelectQuery(sortingType);
		List<Issue> issues = new ArrayList<Issue>();
		UserDAO userDAO = UserFactory.getClassFromFactory();
		StatusesDAO statusDAO = StatusFactory.getClassFromFactory();
		TypesDAO typesDAO = TypeFactory.getClassFromFactory();
		PrioritiesDAO priorityDAO = PriorityFactory.getClassFromFactory();
		ProjectDAO projectDAO = ProjectFactory.getClassFromFactory();
		ConnectionManager connectionMng = null;
		Connection connection = null;
		ResultSet rs = null;
		PreparedStatement ptmSelectIssues = null;
		try{
			connectionMng = new ConnectionManager();
			connection = connectionMng.getConnection();
			ptmSelectIssues = connection.prepareStatement(SELECT_ISSUES);
			ptmSelectIssues.setInt(1, n);
			rs = ptmSelectIssues.executeQuery();
			while (rs.next()){
				int id = rs.getInt(ConstantsSQL.ID_COLUMN);
				Priority priority = priorityDAO.getById(rs.getInt(ConstantsSQL.PRIORITY_ID_COLUMN));
				Type type = typesDAO.getById(rs.getInt(ConstantsSQL.TYPE_ID_COLUMN));
				String summary = rs.getString(ConstantsSQL.SUMMARY_COLUMN);
				String description = rs.getString(ConstantsSQL.DESCRIPTION_COLUMN);
				Status status = statusDAO.getById(rs.getInt(ConstantsSQL.STATUS_ID_COLUMN));
				Project project = 
						projectDAO.getProjectById(rs.getInt(ConstantsSQL.PROJECT_ID_COLUMN));
				Version version = 
						projectDAO.getVersionById(rs.getInt(ConstantsSQL.VERSION_ID_COLUMN));
				Date createDate = rs.getDate(ConstantsSQL.CREATE_DATE_COLUMN);
				User author = 
						userDAO.getUserById(rs.getInt(ConstantsSQL.AUTHOR_ID_COLUMN));
				Issue issue = new Issue(id, priority, type, summary, description, status,
						project, version, createDate, author);
				if(rs.getInt(ConstantsSQL.ASSIGNEE_ID_COLUMN) != 0) {
					User assignee = userDAO.getUserById(rs.getInt(ConstantsSQL.ASSIGNEE_ID_COLUMN));
					issue.setAssignee(assignee);
				}
				if(rs.getDate(ConstantsSQL.MODIFY_DATE_COLUMN) != null) {
					User modifier = 
							userDAO.getUserById(rs.getInt(ConstantsSQL.MODIFIER_ID_COLUMN));
					issue.setModifier(modifier);
					issue.setModifyDate(rs.getDate(ConstantsSQL.MODIFY_DATE_COLUMN));
				}
				if(rs.getInt(ConstantsSQL.RESOLUTION_ID_COLUMN) != 0) {
					ResolutionDAO resolutionDAO = ResolutionFactory.getClassFromFactory();
					Resolution resolution = resolutionDAO.getById(rs.getInt(ConstantsSQL.RESOLUTION_ID_COLUMN));
				    issue.setResolution(resolution);
				}
				issues.add(issue);
			}
			return issues;
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		}finally {
			if(connectionMng != null) {
				connectionMng.closeStatements(ptmSelectIssues);
				connectionMng.closeResultSets(rs);
				connectionMng.closeConnection();
			}
		} 
	}
	
	public List<Issue> getNAssignedIssues(int n, User user, String sortingType) throws DaoException, ValidationException {
		String SELECT_ISSUES = ConstantsSQL.SELECT_ASSIGNED_ISSUES + getSortingPartOfSelectQuery(sortingType);
		List<Issue> issues = new ArrayList<Issue>();
		UserDAO userDAO = UserFactory.getClassFromFactory();
		StatusesDAO statusDAO = StatusFactory.getClassFromFactory();
		TypesDAO typesDAO = TypeFactory.getClassFromFactory();
		PrioritiesDAO priorityDAO = PriorityFactory.getClassFromFactory();
		ProjectDAO projectDAO = ProjectFactory.getClassFromFactory();
		ConnectionManager connectionMng = null;
		Connection connection = null;
		ResultSet rs = null;
		PreparedStatement ptmSelectIssues = null;
		try{
			connectionMng = new ConnectionManager();
			connection = connectionMng.getConnection();
			ptmSelectIssues = 
					connection.prepareStatement(SELECT_ISSUES);
			ptmSelectIssues.setInt(1, n);
			ptmSelectIssues.setInt(2, user.getId());
			rs = ptmSelectIssues.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(ConstantsSQL.ID_COLUMN);
				Priority priority = priorityDAO.getById(rs.getInt(ConstantsSQL.PRIORITY_ID_COLUMN));
				Type type = typesDAO.getById(rs.getInt(ConstantsSQL.TYPE_ID_COLUMN));
				String summary = rs.getString(ConstantsSQL.SUMMARY_COLUMN);
				String description = rs.getString(ConstantsSQL.DESCRIPTION_COLUMN);
				Status status = statusDAO.getById(rs.getInt(ConstantsSQL.STATUS_ID_COLUMN));
				Project project = 
						projectDAO.getProjectById(rs.getInt(ConstantsSQL.PROJECT_ID_COLUMN));
				Version version = 
						projectDAO.getVersionById(rs.getInt(ConstantsSQL.VERSION_ID_COLUMN));
				Date createDate = rs.getDate(ConstantsSQL.CREATE_DATE_COLUMN);
				User author = 
						userDAO.getUserById(rs.getInt(ConstantsSQL.AUTHOR_ID_COLUMN));
				Issue issue = new Issue(id, priority, type, summary, description, status,
						project, version, createDate, author);
				issue.setAssignee(user);
				if(rs.getDate(ConstantsSQL.MODIFY_DATE_COLUMN) != null) {
					User modifier = 
							userDAO.getUserById(rs.getInt(ConstantsSQL.MODIFIER_ID_COLUMN));
					issue.setModifier(modifier);
					issue.setModifyDate(rs.getDate(ConstantsSQL.MODIFY_DATE_COLUMN));
				}
				if(rs.getInt(ConstantsSQL.RESOLUTION_ID_COLUMN) != Constants.NULL) {
					ResolutionDAO resolutionDAO = ResolutionFactory.getClassFromFactory();
					Resolution resolution = resolutionDAO.getById(rs.getInt(ConstantsSQL.RESOLUTION_ID_COLUMN));
					issue.setResolution(resolution);
				}
				issues.add(issue);
			}
			return issues;
		}catch (SQLException e) {
			throw new DaoException(e);
		}finally {
			if(connectionMng != null) {
				connectionMng.closeStatements(ptmSelectIssues);
				connectionMng.closeResultSets(rs);
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
			ptmInsertIssue.setInt(1, issue.getPriority().getId());	
			if(issue.getAssignee() != null) {
				ptmInsertIssue.setInt(2, issue.getAssignee().getId());
			} else {
				ptmInsertIssue.setInt(2, Constants.NULL);
			}
			ptmInsertIssue.setInt(3, issue.getType().getId());	
			ptmInsertIssue.setString(4, issue.getSummary());
			ptmInsertIssue.setString(5, issue.getDescription());
			ptmInsertIssue.setInt(6, issue.getStatus().getId());
			ptmInsertIssue.setInt(7, issue.getProject().getId());
			if(issue.getResolution() != null) {
				ptmInsertIssue.setInt(8, issue.getResolution().getId());
			} else {
				ptmInsertIssue.setInt(8, Constants.NULL);
			}
			ptmInsertIssue.setInt(9, issue.getBuildFound().getId());
			ptmInsertIssue.setDate(10, issue.getCreateDate());
			ptmInsertIssue.setInt(11, issue.getAuthor().getId());
			if(issue.getModifyDate() != null) {
				ptmInsertIssue.setDate(12, issue.getModifyDate());
				ptmInsertIssue.setInt(13, issue.getModifier().getId());
			} else {
				ptmInsertIssue.setDate(12, null);
				ptmInsertIssue.setInt(13, Constants.NULL);
			}
			ptmInsertIssue.executeUpdate();
		}catch (SQLException e) {
			throw new DaoException(e);
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
			ptmUpdateIssue.setInt(1, issue.getPriority().getId());
			if(issue.getAssignee() != null) {
				ptmUpdateIssue.setInt(2, issue.getAssignee().getId());
			} else {
				ptmUpdateIssue.setInt(2, Constants.NULL);
			}
			ptmUpdateIssue.setInt(3, issue.getType().getId());
			ptmUpdateIssue.setString(4, issue.getSummary());
			ptmUpdateIssue.setString(5, issue.getDescription());
			ptmUpdateIssue.setInt(6, issue.getStatus().getId());
			ptmUpdateIssue.setInt(7, issue.getProject().getId());
			if(issue.getResolution() != null) {
				ptmUpdateIssue.setInt(8, issue.getResolution().getId());
			} else {
				ptmUpdateIssue.setInt(8, Constants.NULL);
			}
			ptmUpdateIssue.setInt(9, issue.getBuildFound().getId());
			ptmUpdateIssue.setDate(10, issue.getCreateDate());
			ptmUpdateIssue.setInt(11, issue.getAuthor().getId());
			ptmUpdateIssue.setDate(12, issue.getModifyDate());
			ptmUpdateIssue.setInt(13, issue.getModifier().getId());
			ptmUpdateIssue.setInt(14, issue.getId());
		    ptmUpdateIssue.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
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
