package org.training.issuetracker.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.training.issuetracker.constants.Configurations;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.exceptions.DaoException;


public class ConnectionManager {
	 
	 static{
	 try {
	   Class.forName(Configurations.DB_DRIVER_NAME);
	 } catch (ClassNotFoundException e){
		 throw new RuntimeException();
	 }}
	 
	 private Connection connection;
	 
	 public ConnectionManager() {
		 
		 try {	
		 connection = DriverManager
					.getConnection(Constants.DRIVER_TYPE + Configurations.DB + ":" + Configurations.PATH + Constants.LOCAL_PASS_TO_DB 
							+ Configurations.DB_NAME, Configurations.DB_USER, Configurations.DB_PASSWORD);
		
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
