package org.pahappa.system.userregistration.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
    public static Connection makeConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/user_registration_system","root","");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
