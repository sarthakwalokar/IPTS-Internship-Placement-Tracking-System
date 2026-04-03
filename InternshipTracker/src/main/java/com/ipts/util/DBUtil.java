package com.ipts.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DBUtil - Handles MySQL database connection
 * Update DB_URL, USER, PASS to match your local setup
 */
public class DBUtil {

	 private static final String URL = "jdbc:mysql://localhost:3306/ipts_db";
	    private static final String USER = "root";
	    private static final String PASSWORD = "Sarthak@2006"; // change as per your MySQL

	    public static Connection getConnection() {
	        Connection con = null;

	        try {
	            // Load Driver
	            Class.forName("com.mysql.cj.jdbc.Driver");

	            // Create Connection
	            con = DriverManager.getConnection(URL, USER, PASSWORD);
  
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return con;
	    }
	  
}