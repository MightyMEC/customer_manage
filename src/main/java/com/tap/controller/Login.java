package com.tap.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // SQL query to check login credentials
    String fetch = "SELECT * FROM `userss` WHERE login_id = ? AND password = ?";
    
    private Connection connection;
    private PreparedStatement prepareStatement;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String loginId = request.getParameter("login_id");
        String password = request.getParameter("password");
        
        PrintWriter writer = response.getWriter();
        
        try {
            // Load the database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish a connection to the database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "Passcode@123");
            
            // Prepare the SQL statement
            prepareStatement = connection.prepareStatement(fetch);
            prepareStatement.setString(1, loginId);
            prepareStatement.setString(2, password);
            
            // Execute the query
            ResultSet res = prepareStatement.executeQuery();
            
            // Check if the user exists
            if (res.next()) {
                // Redirect to a successful page if the user is found
                response.sendRedirect("successful.html");
            } else {
                // Print error message if credentials are invalid
                writer.print("<h1>Invalid login ID or password</h1>");
            }
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
