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

@WebServlet("/search")
public class Search extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String employeeId = request.getParameter("employeeId");

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc","root","Passcode@123");

            String sql = "SELECT * FROM `users` WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(employeeId));
            resultSet = statement.executeQuery();

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<title>Employee Data</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px; }");
            out.println("table { width: 100%; border-collapse: collapse; margin-top: 20px; }");
            out.println("table, th, td { border: 1px solid #ccc; }");
            out.println("th, td { padding: 10px; text-align: left; }");
            out.println("th { background-color: #007bff; color: white; }");
            out.println("tr:nth-child(even) { background-color: #f2f2f2; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h2>Employee Data</h2>");
            out.println("<table>");
            out.println("<thead><tr><th>ID</th><th>First Name</th><th>Last Name</th><th>Street</th><th>Address</th><th>City</th><th>State</th><th>Email</th><th>Phone</th></tr></thead>");
            out.println("<tbody>");

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String street = resultSet.getString("street");
                String address = resultSet.getString("address");
                String city = resultSet.getString("city");
                String state = resultSet.getString("state");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");

                out.println("<tr>");
                out.println("<td>" + id + "</td>");
                out.println("<td>" + firstName + "</td>");
                out.println("<td>" + lastName + "</td>");
                out.println("<td>" + street + "</td>");
                out.println("<td>" + address + "</td>");
                out.println("<td>" + city + "</td>");
                out.println("<td>" + state + "</td>");
                out.println("<td>" + email + "</td>");
                out.println("<td>" + phone + "</td>");
                out.println("</tr>");
            } else {
                out.println("<tr><td colspan='9'>Employee not found!</td></tr>");
            }

            out.println("</tbody>");
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            out.println("<p>Error occurred: " + e.getMessage() + "</p>");
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
