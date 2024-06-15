package com.productapp.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlUtil {
	final static String DB_USER = "root";
	final static String DB_PASS = "root";
	final static String DB_NAME = "cdac";
	final static String DB_URL = "jdbc:mysql://localhost:3306/"+DB_NAME;
	final static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

	static Statement statement;
	static Connection connection;
	
	static public void connectDB() throws ClassNotFoundException, SQLException {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
			statement = connection.createStatement();		
	}
	
	static public int insert(String qry) throws SQLException {
		int result = -1;
		if(qry!="") {
			result = statement.executeUpdate(qry);
		}
		return result;
	}
	
	static public int update(String qry) throws SQLException {
		int result = -1;
		if(qry!="") {
			result = statement.executeUpdate(qry);
		}
		return result;
	}
	
	static public int delete(String qry) throws SQLException {
		int result = -1;
		if(qry!="") {
			result = statement.executeUpdate(qry);
		}
		return result;
	}
	
	static public ResultSet fetch(String qry) throws SQLException {
		ResultSet resultSet = null;
		if(qry!="") {
			resultSet = statement.executeQuery(qry);
		}
		return resultSet;
	}
	
	static public void close() throws SQLException {
		if(connection!=null && statement!=null) {
			statement.close();
			connection.close();
		}
	}
	
}
