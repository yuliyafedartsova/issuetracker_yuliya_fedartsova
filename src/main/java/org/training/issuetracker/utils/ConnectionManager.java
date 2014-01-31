package org.training.issuetracker.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.training.issuetracker.constants.Constants;


public class ConnectionManager {
	 
	 static{
	 try {
	   Class.forName("org.h2.Driver");
	 } catch (ClassNotFoundException e){
		 System.out.println("Driver ClassNotFoundException");
	 }}
	 
	 public Connection getConnection() {
	 try {
	   return DriverManager.getConnection("jdbc:h2:" + Constants.PATH + "WEB-INF\\classes\\db\\test); // + ");
	  } catch (SQLException e) {   
		  throw new RuntimeException("getConnection()");
	  }
	 }
	 
	 public void closeConnections(Connection connection) {
	  try {
	   if (connection != null ) {
	    if (!connection.getAutoCommit()){
	     connection.setAutoCommit(true);
	    }
	    connection.close();
	   }
	  } catch (SQLException e) {
	    }
	 }
	 
	 public void closePreparedStatements(PreparedStatement... preparedStatements) {
	  try {
	   for (Statement preparedStatement : preparedStatements){
	    if (preparedStatement != null) {
	     preparedStatement.close();
	    }
	   }
	  } catch (SQLException e) {
	  
	  }
	 }
	 
	 public void closeStatements(Statement... statements) {
	  try {
	   for (Statement statement : statements){
	    if (statement != null) {
	     statement.close();
	    }
	   }
	  } catch (SQLException e) {
	   
	  }
	 }
	 
	 public void closeResultSet(ResultSet... resultSets) {
	  try {
	   for (ResultSet resultSet : resultSets){
	    if (resultSet != null) {
	     resultSet.close();
	    }
	   }
	  } catch (SQLException e) {
	   
	  }
	 }
	 

}
