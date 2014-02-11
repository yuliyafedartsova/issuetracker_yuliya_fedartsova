package org.training.issuetracker.constants;

public class ConstantsSQL {
	public static final String PRIORITY_ID_COLUMN = "priorityId";
	public static final String TYPE_ID_COLUMN = "typeId";
	public static final String STATUS_ID_COLUMN = "statusId";
	public static final String PROJECT_ID_COLUMN = "projectId";
	public static final String VERSION_ID_COLUMN = "versionId";
	public static final String AUTHOR_ID_COLUMN = "authorId";
	public static final String ASSIGNEE_ID_COLUMN = "assigneeId";
	public static final String MODIFIER_ID_COLUMN = "modifierId";
	public static final String RESOLUTION_ID_COLUMN = "resolutionId";
	public static final String SUMMARY_COLUMN = "summary";
	public static final String DESCRIPTION_COLUMN = "description";
	public static final String CREATE_DATE_COLUMN = "createDate";
	public static final String MODIFY_DATE_COLUMN = "modifyDate";
	public static final String ID_COLUMN = "id";
	public static final String MANAGER_ID_COLUMN = "managerId";
	public static final String NAME_COLUMN = "name";
	public static final String FIRST_NAME_COLUMN = "firstName";
	public static final String LAST_NAME_COLUMN = "lastName";
	public static final String EMAIL_COLUMN = "email";
	public static final String ROLE_ID_COLUMN = "roleId";
	public static final String PASSWORD = "password";
	public static final String SELECT_PROJECTS = "SELECT * FROM projects;";
	public static final String SELECT_PROJECT_BY_ID = "SELECT name, " +
				"description, managerId FROM projects WHERE id = ?;";
	public static final String SELECT_VERSION_BY_ID = "SELECT name " +
					"FROM versions WHERE id = ?;";
	public static final String SELECT_VERSIONS_OF_PROJECT = "SELECT id, name " +
						"FROM versions WHERE projectId = ?;";
	public static final String SELECT_ISSUE_BY_ID = "SELECT " +
					"* FROM issues WHERE id = ?;";
	public static final String SELECT_USER_BY_ID = "SELECT firstName, " +
				"lastName, email, roleId, password FROM users WHERE id = ?;";
	public static final String SELECT_USER_BY_EMAIL_AND_PASSWORD = 
			"SELECT id, firstName, lastName, roleId FROM users WHERE email = ? AND password = ?;";
	public static final String SELECT_USERS = "SELECT * FROM users;";
    public static final String SELECT_ASSIGNED_ISSUES = "SELECT TOP ? * FROM issues " +
			"WHERE assigneeId = ? ";
    public static final String SELECT_LAST_ADDED_ISSUES = 
			"SELECT TOP ? * FROM issues ";
    public static final String ADD_USER = "INSERT INTO users(firstName, lastName, " +
				"email, roleId, password) VALUES (?,?,?,?,?);";
    public static final String SELECT_IF_USER_EXISTS = "SELECT id " +
					"FROM users where email = ? AND password = ?;";
    public static final String ADD_ISSUE = "INSERT INTO issues(priorityId, assigneeId, " +
				"typeId, summary, description, statusId, projectId, resolutionId, " +
					"versionId, createDate, authorId, modifyDate, modifierId) " +
						"VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);";
    public static final String SELECT_IF_ISSUE_EXISTS = "SELECT id " +
					"FROM issues where summary = ? AND description = ? AND projectId = ? " +
					"AND versionId = ?;";
    public static final String ADD_PROJECT = "INSERT INTO projects(name, description, " +
				"managerId) VALUES (?,?,?);";
    public static final String ADD_VERSION = "INSERT INTO versions(name, projectId) " +
						"VALUES (?,?);";
    public static final String SELECT_IF_PROJECT_EXISTS = "SELECT id " +
					"FROM projects where name = ?;";
    public static final String SELECT_IF_VERSION_EXISTS = "SELECT id " +
					"FROM versions where name = ? AND projectId = ?;";
    public static final String UPDATE_ISSUE = "UPDATE issues SET priorityId = ?, assigneeId = ?, " +
			"typeId = ?, summary = ?, description = ?, statusId = ?, " +
			"projectId = ?, resolutionId = ?, versionId = ?, createDate = ?, " +
			"authorId = ?, modifyDate = ?, modifierId = ?   WHERE id = ?;";
    public static final String UPDATE_PROJECT = "UPDATE projects SET name = ?, description = ?, " +
			"managerId = ?   WHERE id = ?;";
    public static final String UPDATE_USER = "UPDATE users SET firstName = ?, lastName = ?, " +
			"email = ?, roleId = ?, password = ?  WHERE id = ?;";

}
