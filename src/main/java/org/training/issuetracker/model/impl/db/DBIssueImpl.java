package org.training.issuetracker.model.impl.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.training.issuetracker.constants.ConstantsSQL;
import org.training.issuetracker.exceptions.DaoException;
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

public class DBIssueImpl implements IssueDAO {

	public List<Issue> getIssues() throws DaoException {
		List<Issue> issues = new ArrayList<Issue>();
		UserDAO userDAO = UserFactory.getClassFromFactory();
		StatusesDAO statusDAO = StatusFactory.getClassFromFactory();
		TypesDAO typesDAO = TypeFactory.getClassFromFactory();
		PrioritiesDAO priorityDAO = PriorityFactory.getClassFromFactory();
		ProjectDAO projectDAO = ProjectFactory.getClassFromFactory();
		ConnectionManager connectionMng = null;
		Connection connection = null;
		ResultSet rs = null;
		Statement stSelectIssues = null;
		try{
		connectionMng = new ConnectionManager();
		connection = connectionMng.getConnection();
		stSelectIssues = connection.createStatement();
		rs = stSelectIssues.executeQuery("SELECT id, priorityId, assigneeId, " +
					"typeId, summary, description, statusId, projectId, " +
					"resolutionId, versionId, createDate, authorId, modifyDate, " +
					"modifierId FROM issues;");	
			while (rs.next()){
				String summary = rs.getString(5);
				String description = rs.getString(6);
				Date createDate = rs.getDate(11);
				Date modifyDate = rs.getDate(13);
				Priority priority = priorityDAO.getById(rs.getInt(2));
				Type type = typesDAO.getById(rs.getInt(4));
				Status status = statusDAO.getById(rs.getInt(7));
				Project project = projectDAO.getProjectById(rs.getInt(8));
				Version version = projectDAO.getVersionById(rs.getInt(10)); 
				User author = userDAO.getUserById(rs.getInt(12));
				Issue issue = new Issue(rs.getInt(1), priority, type, summary, description, status,
						project, version, createDate, author);
				if(rs.getInt(3) != 0) {
					User assignee = userDAO.getUserById(rs.getInt(3));
					issue.setAssignee(assignee);
				}
				if(modifyDate != null) {
					User modifier = userDAO.getUserById(rs.getInt(14));
					issue.setModifier(modifier);
					issue.setModifyDate(modifyDate);
				}
				if(rs.getInt(9) != 0) {
					ResolutionDAO resolutionDAO = ResolutionFactory.getClassFromFactory();
					Resolution resolution = resolutionDAO.getById(rs.getInt(9));
					issue.setResolution(resolution);
				}
				issues.add(issue);
			}
			return issues;
		}catch (SQLException e) {
			throw new DaoException(e);
		}finally {
			if(connectionMng != null) {
				connectionMng.closeStatements(stSelectIssues);
				connectionMng.closeResultSets(rs);
				connectionMng.closeConnection();
			}
		}
	}
	
	public Issue getIssueById(int id) throws DaoException {
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
			rs.next();
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
	
	public List<Issue> getNLastAddedIssues(int n, String sortingType) throws DaoException {
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
					connection.prepareStatement(ConstantsSQL.SELECT_LAST_ADDED_ISSUES);
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
			throw new DaoException(e);
		}finally {
			if(connectionMng != null) {
				connectionMng.closeStatements(ptmSelectIssues);
				connectionMng.closeResultSets(rs);
				connectionMng.closeConnection();
			}
		} 
	}
	
	public List<Issue> getNAssignedIssues(int n, User user, String sortingType) throws DaoException {
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
					connection.prepareStatement(ConstantsSQL.SELECT_ASSIGNED_ISSUES);
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
				if(rs.getInt(ConstantsSQL.RESOLUTION_ID_COLUMN) != 0) {
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
	
	public void addIssue(Issue issue) throws DaoException {
		ConnectionManager connectionMng = null;
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
				ptmInsertIssue.setInt(2, 0);
			}
			ptmInsertIssue.setInt(3, issue.getType().getId());	
			ptmInsertIssue.setString(4, issue.getSummary());
			ptmInsertIssue.setString(5, issue.getDescription());
			ptmInsertIssue.setInt(6, issue.getStatus().getId());
			ptmInsertIssue.setInt(7, issue.getProject().getId());
			if(issue.getResolution() != null) {
				ptmInsertIssue.setInt(8, issue.getResolution().getId());
			} else {
				ptmInsertIssue.setInt(8, 0);
			}
			ptmInsertIssue.setInt(9, issue.getBuildFound().getId());
			ptmInsertIssue.setDate(10, issue.getCreateDate());
			ptmInsertIssue.setInt(11, issue.getAuthor().getId());
			if(issue.getModifyDate() != null) {
				ptmInsertIssue.setDate(12, issue.getModifyDate());
				ptmInsertIssue.setInt(13, issue.getModifier().getId());
			} else {
				ptmInsertIssue.setDate(12, null);
				ptmInsertIssue.setInt(13, 0);
			}
			synchronized (DBIssueImpl.class) {
				if(whetherTheIssueExists(issue, connection)) {
					return;
				}
				ptmInsertIssue.executeUpdate();
			}
		}catch (SQLException e) {
			throw new DaoException(e);
		}finally {
			if(connectionMng != null) {
				connectionMng.closeStatements(ptmInsertIssue);
			    connectionMng.closeConnection();
			}
		} 
	}
	
	private boolean whetherTheIssueExists(Issue issue, Connection connection) throws DaoException, SQLException  {
		boolean exist = false;
		ResultSet rs = null;
		PreparedStatement ptmSelectIssue = null;
		try {
			ptmSelectIssue = 
					connection.prepareStatement(ConstantsSQL.SELECT_IF_ISSUE_EXISTS);
			ptmSelectIssue.setString(1, issue.getSummary());
			ptmSelectIssue.setString(2, issue.getDescription());
			ptmSelectIssue.setInt(3, issue.getProject().getId());
			ptmSelectIssue.setInt(4, issue.getBuildFound().getId());
			rs = ptmSelectIssue.executeQuery();
			    if(rs.next()) {
			    	exist = true;
			    }
			return exist;
		}catch (SQLException e) {
			throw new DaoException(e);
		}finally {
			rs.close();
			ptmSelectIssue.close();
		}
		
	}
	
    public void updateIssue(Issue issue) throws DaoException {
    	ConnectionManager connectionMng = null;
		Connection connection = null;
		PreparedStatement ptmUpdateIssue = null;
		try {
			connectionMng = new ConnectionManager();
			connection = connectionMng.getConnection();
		    ptmUpdateIssue = 
					connection.prepareStatement("UPDATE issues SET priorityId = ?, assigneeId = ?, " +
							"typeId = ?, summary = ?, description = ?, statusId = ?, " +
							"projectId = ?, resolutionId = ?, versionId = ?, createDate = ?, " +
							"authorId = ?, modifyDate = ?, modifierId = ?   WHERE id = ?;");
			ptmUpdateIssue.setInt(1, issue.getPriority().getId());
			if(issue.getAssignee() != null) {
				ptmUpdateIssue.setInt(2, issue.getAssignee().getId());
			} else {
				ptmUpdateIssue.setInt(2, 0);
			}
			ptmUpdateIssue.setInt(3, issue.getType().getId());
			ptmUpdateIssue.setString(4, issue.getSummary());
			ptmUpdateIssue.setString(5, issue.getDescription());
			ptmUpdateIssue.setInt(6, issue.getStatus().getId());
			ptmUpdateIssue.setInt(7, issue.getProject().getId());
			if(issue.getResolution() != null) {
				ptmUpdateIssue.setInt(8, issue.getResolution().getId());
			} else {
				ptmUpdateIssue.setInt(8, 0);
			}
			ptmUpdateIssue.setInt(9, issue.getBuildFound().getId());
			ptmUpdateIssue.setDate(10, issue.getCreateDate());
			ptmUpdateIssue.setInt(11, issue.getAuthor().getId());
			ptmUpdateIssue.setDate(12, issue.getModifyDate());
			ptmUpdateIssue.setInt(13, issue.getModifier().getId());
			ptmUpdateIssue.setInt(14, issue.getId());
			synchronized (DBIssueImpl.class) {
				if(whetherTheIssueExists(issue, connection)) {
					return;
				}
				ptmUpdateIssue.executeUpdate();
			}
		}catch (SQLException e) {
			throw new DaoException(e);
		}finally {
			if(connectionMng != null) {
				connectionMng.closeStatements(ptmUpdateIssue);
			    connectionMng.closeConnection();
			}
		} 
	}
}
