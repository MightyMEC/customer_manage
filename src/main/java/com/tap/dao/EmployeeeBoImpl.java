package com.tap.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.tap.models.Employeee;

public class EmployeeeBoImpl implements EmployeeeBo {
    public static Connection connection;
    public static PreparedStatement prepareStatement;
    public static ResultSet res;
    
    private final static String UPDATE_QUERY = "UPDATE `users` SET first_name=?, last_name=?, street=?, address=?, city=?, state=?, email=?, phone=? WHERE id=?";
    private final static String SELECT_QUERY = "SELECT * from `users`";
    private final static String DELETE_QUERY = "DELETE from `users` WHERE `id`=?";
    private final static String INSERT_QUERY = "INSERT into `users` (`first_name`, `last_name`, `street`, `address`, `city`, `state`, `email`, `phone`) values (?,?,?,?,?,?,?,?)";

    @Override
    public int save(Employeee user) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc","root","Passcode@123");
            prepareStatement = connection.prepareStatement(INSERT_QUERY);
            
            prepareStatement.setString(1, user.getFirstName());
            prepareStatement.setString(2, user.getLastName());
            prepareStatement.setString(3, user.getStreet());
            prepareStatement.setString(4, user.getAddress());
            prepareStatement.setString(5, user.getCity());
            prepareStatement.setString(6, user.getState());
            prepareStatement.setString(7, user.getEmail());
            prepareStatement.setString(8, user.getPhone());
                
            return prepareStatement.executeUpdate();
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

   
}
