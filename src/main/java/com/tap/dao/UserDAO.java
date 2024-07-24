package com.tap.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO {
    
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/jdbc";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "Passcode@123";
    
    private static final String INSERT_USER_QUERY = 
        "INSERT INTO userss (login_id, password) VALUES (?, ?)";
    
    public void saveUser(String loginId, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establish a connection to the database
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            
            // Prepare the SQL statement
            preparedStatement = connection.prepareStatement(INSERT_USER_QUERY);
            preparedStatement.setString(1, loginId);
            preparedStatement.setString(2, password);
            
            // Execute the SQL statement
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User saved successfully.");
            } else {
                System.out.println("Failed to save user.");
            }
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

