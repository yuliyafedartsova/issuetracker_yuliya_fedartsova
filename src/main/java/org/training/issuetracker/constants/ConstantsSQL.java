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
	public static final String SELECT_PROJECTS = "SELECT projects.id, projects.name, projects.description, users.id, " +
			"users.firstName, users.lastName, users.email, roles.id, roles.name, users.password " +
			"FROM projects LEFT JOIN users ON projects.managerId = users.id LEFT JOIN " +
			"roles ON users.roleId = roles.id";
	public static final String SELECT_PROJECT_BY_ID = "SELECT projects.name, projects.description, users.id, " +
			"users.firstName, users.lastName, users.email, roles.id, roles.name, users.password " +
			"FROM projects LEFT JOIN users ON projects.managerId = users.id LEFT JOIN " +
			"roles ON users.roleId = roles.id WHERE projects.id = ?";
	public static final String SELECT_VERSION_BY_ID = "SELECT name " +
					"FROM versions WHERE id = ?;";
	public static final String SELECT_VERSIONS_OF_PROJECT = "SELECT id, name " +
						"FROM versions WHERE projectId = ?;";
	public static final String SELECT_ISSUE_BY_ID = "SELECT priorities.id, priorities.name, " +
    		"issues.assigneeId, types.id, types.name, issues.summary, issues.description, statuses.id, statuses.name, " +
    		"issues.projectId, resolutions.id, resolutions.name, versions.id, versions.name, issues.createdate, issues.authorId, " +
    		"issues.modifydate, issues.modifierId FROM issues  LEFT JOIN priorities ON issues.priorityId = priorities.id " +
    		"LEFT JOIN types ON issues.typeId = types.id LEFT JOIN statuses ON issues.statusId = statuses.id " +
    		"LEFT JOIN resolutions ON issues.resolutionId = resolutions.id  LEFT JOIN versions ON " +
    		"issues.versionId = versions.Id WHERE issues.id = ?";
	public static final String SELECT_USER_BY_ID = "SELECT users.firstName, users.lastName, users.email, " +
			"roles.id, roles.name, users.password FROM users LEFT JOIN roles ON users.roleId = roles.id " +
			"WHERE users.id = ?";
	public static final String SELECT_USER_BY_EMAIL_AND_PASSWORD = 
			"SELECT users.id, users.firstName, users.lastName, roles.id, roles.name FROM users " +
			"LEFT JOIN roles ON users.roleId = roles.id WHERE users.email = ? AND users.password = ?;";
	public static final String SELECT_USERS = "SELECT users.id, users.firstName, users.lastName, users.email, " +
			"roles.id, roles.name, users.password FROM users LEFT JOIN roles ON users.roleId = roles.id";
    public static final String SELECT_ASSIGNED_ISSUES = "SELECT TOP ? issues.id, priorities.id, priorities.name, " +
    		"types.id, types.name, issues.summary, issues.description, statuses.id, statuses.name, " +
    		"issues.projectId, resolutions.id, resolutions.name, versions.id, versions.name, issues.createdate, issues.authorId, " +
    		"issues.modifydate, issues.modifierId FROM issues  LEFT JOIN priorities ON issues.priorityId = priorities.id " +
    		"LEFT JOIN types ON issues.typeId = types.id LEFT JOIN statuses ON issues.statusId = statuses.id " +
    		"LEFT JOIN resolutions ON issues.resolutionId = resolutions.id  LEFT JOIN versions ON " +
    		"issues.versionId = versions.Id WHERE issues.assigneeId = ? ";
  public static final String SELECT_LAST_ADDED_ISSUES = "SELECT TOP ? issues.id, priorities.id, priorities.name, " +
    		"issues.assigneeId, types.id, types.name, issues.summary, issues.description, statuses.id, statuses.name, " +
    		"issues.projectId, resolutions.id, resolutions.name, versions.id, versions.name, issues.createdate, issues.authorId, " +
    		"issues.modifydate, issues.modifierId FROM issues  LEFT JOIN priorities ON issues.priorityId = priorities.id " +
    		"LEFT JOIN types ON issues.typeId = types.id LEFT JOIN statuses ON issues.statusId = statuses.id " +
    		"LEFT JOIN resolutions ON issues.resolutionId = resolutions.id  LEFT JOIN versions ON " +
    		"issues.versionId = versions.Id ";
    public static final String ADD_USER = "INSERT INTO users(firstName, lastName, " +
				"email, roleId, password) VALUES (?,?,?,?,?);";
    public static final String SELECT_IF_USER_EXISTS = "SELECT id " +
					"FROM users where email = ? AND password = ?;";
    public static final String ADD_ISSUE = "INSERT INTO issues(priorityId, assigneeId, " +
				"typeId, summary, description, statusId, projectId, resolutionId, " +
					"versionId, createDate, authorId, modifyDate, modifierId) " +
						"VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);";
    public static final String ADD_PROJECT = "INSERT INTO projects(name, description, " +
				"managerId) VALUES (?,?,?);";
    public static final String ADD_VERSION = "INSERT INTO versions(name, projectId) " +
						"VALUES (?,?);";
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
