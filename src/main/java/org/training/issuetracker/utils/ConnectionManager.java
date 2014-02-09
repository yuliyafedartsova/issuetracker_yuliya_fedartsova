package org.training.issuetracker.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.exceptions.DaoException;


public class ConnectionManager {
	 
	 static{
	 try {
	   Class.forName("org.h2.Driver");
	 } catch (ClassNotFoundException e){
		 System.out.println("Driver ClassNotFoundException");
	 }}
	 
	 private Connection connection;
	 
	 public ConnectionManager() {
		 try {	
		 connection = DriverManager
					.getConnection("jdbc:h2:" + Constants.PATH + "WEB-INF\\classes\\db\\test", "sa", "");
		 }catch (SQLException e) {   
			  throw new RuntimeException(e.getMessage());
		 }
	 
	 }
	 
	 
	 public Connection getConnection() {
		 return connection;
	 }
	 
	 public void closeConnection() throws DaoException {
		 if (connection != null) {
				try{
					connection.close();
				}catch (SQLException e) {
					throw new DaoException(e);
				}
			}
	 }
	 
	 public void closePreparedStatements(PreparedStatement... preparedStatements) throws DaoException {
		 for (Statement preparedStatement : preparedStatements){
			 	if (preparedStatement != null) {
			 		try {
			 			preparedStatement.close();
			 		}catch (SQLException e) {
			 			throw new DaoException(e);
			 		}
			 	}
		 }
	 }
	 
	 
	 
	 public void closeResultSets(ResultSet... resultSet) throws DaoException {
			for (ResultSet rs : resultSet) {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						throw new DaoException(e);
					}
				}
			}
		}
	 
	 
	 public void closeStatements(Statement... statement) throws DaoException {
			for (Statement st : statement) {
				if (st != null) {
					try {
						st.close();
					} catch (SQLException e) {
						throw new DaoException(e);
					}
				}
			}
		}
	 

}
