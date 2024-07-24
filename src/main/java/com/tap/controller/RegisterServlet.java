package com.tap.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.tap.dao.UserDAO;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form parameters
        String loginId = request.getParameter("login_id");
        String password = request.getParameter("password");
        
        // Create UserDAO instance
        UserDAO userDAO = new UserDAO();
        
        // Save user to database
        userDAO.saveUser(loginId, password);
        
        // Redirect to a success page or show a success message
        response.sendRedirect("Login.html");
    }
}
