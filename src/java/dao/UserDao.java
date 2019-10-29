/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.models.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ThinhNguyenCong
 */
public class UserDao {

    public UserDao() {
    }

    private String jdbcURL = "jdbc:mysql://localhost:3306/books";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";

    private static final String INSERT_USERS_SQL = "INSERT INTO user" + "  (name, email, phone, password, type) VALUES "
            + " (?, ?, ?, ?, ?);";

    private static final String SELECT_USER_BY_ID = "select id,name,email,phone,password,type from user where id =?";
    private static final String SELECT_ALL_USERS = "select * from user";
    private static final String DELETE_USERS_SQL = "delete from user where id = ?;";
    private static final String UPDATE_USERS_SQL = "update user set name = ?,email= ?, phone = ?, password = ?, type= ? where id = ?;";

    protected Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return con;
    }

    public void insertUser(User user) throws SQLException {
        System.out.println(INSERT_USERS_SQL);
        try (
                Connection con = getConnection();
                PreparedStatement prep = con.prepareStatement(INSERT_USERS_SQL);) {
            prep.setString(1, user.getName());
            prep.setString(2, user.getEmail());
            prep.setString(3, user.getPhone());
            prep.setString(4, user.getPassword());
            prep.setString(5, user.getType());
            System.out.println(prep);
            prep.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getListUser() {
        List<User> list = new ArrayList<>();
        try (
                Connection con = getConnection();
                PreparedStatement prep = con.prepareStatement(SELECT_ALL_USERS);) {
            System.out.println(prep);
            ResultSet rs = prep.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String password = rs.getString("password");
                String type = rs.getString("type");
                list.add(new User(id, name, email, phone, password, type));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public User selectUser(int id) {
        User user = null;
        try (
                Connection con = getConnection();
                PreparedStatement prep = con.prepareStatement(SELECT_USER_BY_ID);) {
            prep.setInt(1, id);
            System.out.println(prep);
            ResultSet rs = prep.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String password = rs.getString("password");
                String type = rs.getString("type");
                user = new User(id, name, email, phone, password, type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public boolean deleteUser(int id) throws SQLException {
        boolean rowDeleted;
        try (
                Connection con = getConnection();
                PreparedStatement prep = con.prepareStatement(DELETE_USERS_SQL);) {
                prep.setInt(1, id);
                rowDeleted = prep.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateUser(User user) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
                PreparedStatement prep = connection.prepareStatement(UPDATE_USERS_SQL);) {
            prep.setString(1, user.getName());
            prep.setString(2, user.getEmail());
            prep.setString(3, user.getPhone());
            prep.setString(4, user.getPassword());
            prep.setString(5, user.getType());
            prep.setInt(6, user.getId());

            rowUpdated = prep.executeUpdate() > 0;
        }
        return rowUpdated;
    }
}
