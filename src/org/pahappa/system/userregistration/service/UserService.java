package org.pahappa.system.userregistration.service;

import org.pahappa.system.userregistration.model.JDBCConnection;
import org.pahappa.system.userregistration.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    public boolean addUser(User user) {
        Connection conn= JDBCConnection.makeConnection();
        String sql="INSERT INTO users(username, firstName, lastName, dateOfBirth) VALUES (?,?,?,?)";
        try {
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getFirstName());
            pstmt.setString(3, user.getLastName());
            pstmt.setString(4, user.getDateOfBirth());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return false;
    }
    public boolean deleteAllUsers() {
        Connection conn= JDBCConnection.makeConnection();
        String sql="DELETE FROM users";
        Statement stmt= null;
        try {
            stmt = conn.createStatement();
            stmt.execute(sql);
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return false;
    }
    public List<User> getUsers() {
        List<User> users=new ArrayList<User>();
        Connection conn = JDBCConnection.makeConnection();
        String sql="SELECT * FROM users";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            if(rs.next()) {
                while(rs.next()) {
                    User user=new User();
                    user.setUsername(rs.getString("username"));
                    user.setFirstName(rs.getString("firstName"));
                    user.setLastName(rs.getString("lastName"));
                    user.setDateOfBirth(rs.getString("dateOfBirth"));
                    users.add(user);
                }
                return users;
            }else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }
    public User getUser(String username) {
        Connection conn = JDBCConnection.makeConnection();
        String sql="SELECT * FROM users WHERE username=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                User user=new User();
                user.setUsername(rs.getString("username"));
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setDateOfBirth(rs.getString("dateOfBirth"));
                return user;
            }else return null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }
    public boolean deleteUser(String username) {
        Connection conn = JDBCConnection.makeConnection();
        String sql="DELETE FROM users WHERE username=?";
        try {
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return false;
    }
    public boolean updateUser(User user) {
        Connection conn = JDBCConnection.makeConnection();
        String sql = "UPDATE users SET firstName=?,lastName=?,dateOfBirth=? WHERE username=?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, user.getFirstName());
            pst.setString(2, user.getLastName());
            pst.setString(3, user.getDateOfBirth());
            pst.setString(4, user.getUsername());
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return false;
    }
}
