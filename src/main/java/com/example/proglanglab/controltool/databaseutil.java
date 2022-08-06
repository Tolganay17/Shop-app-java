package com.example.proglanglab.controltool;

import com.example.proglanglab.classes.Manga;
import com.example.proglanglab.classes.Order;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class databaseutil {
    public static Connection connectToDb() throws ClassNotFoundException {
        Connection conn = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        String DB_URL = "jdbc:mysql://localhost:3306/database1";
        String USER = "root";
        String PASS = "";
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return conn;

    }

    public static void disconnectfromdatabase(Connection connection, Statement statement) {
        try {
            if (connection != null && statement != null) {
                connection.close();
                statement.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateField(String dbColName, String value, String name, String tabname, String attname) throws ClassNotFoundException {
        Connection connection = connectToDb();
        String sql = "UPDATE " + tabname + " SET " + dbColName + " = ? where " + attname + " = ? ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, value);
            preparedStatement.setString(2, name);
            preparedStatement.execute();
            disconnectfromdatabase(connection, preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateFieldforUser(String dbColName, String value, int userId) throws ClassNotFoundException {
        Connection connection = connectToDb();
        String sql = "UPDATE users SET " + dbColName + " = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, value);
            preparedStatement.setInt(2, userId);
            preparedStatement.execute();
            disconnectfromdatabase(connection, preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteUser(int userId) throws ClassNotFoundException {
        Connection connection = connectToDb();
        String sql = "DELETE FROM users where id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            preparedStatement.execute();
            disconnectfromdatabase(connection, preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteUserByName(String tabname, String colname, String xname) throws ClassNotFoundException {
        Connection connection = connectToDb();
        String sql = "DELETE FROM " + tabname + " where " + colname + " = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, xname);
            preparedStatement.execute();
            disconnectfromdatabase(connection, preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int createNewOrder(String manganame) {
        int key = 0;
        try {
            Connection connection = connectToDb();

            String sql = "INSERT INTO orders (`Date`) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // preparedStatement.setInt(1, order_id);
            preparedStatement.setDate(1, Date.valueOf(LocalDate.now()));
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) key = rs.getInt(1);

            String sql2 = "INSERT INTO ordered_manga (`Manga_name`, `order_id`) VALUES (?,?)";
            preparedStatement = connection.prepareStatement(sql2);
            preparedStatement.setString(1, manganame);
            preparedStatement.setInt(2, key);
            preparedStatement.execute();
            disconnectfromdatabase(connection, preparedStatement);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return key;
    }

    public static void addNewtoOrder(int order_id, String manganame) {
        try {
            Connection connection = connectToDb();


            String sql = "INSERT INTO ordered_manga (`Manga_name`, `order_id`) VALUES (?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, manganame);
            preparedStatement.setInt(2, order_id);
            preparedStatement.execute();
            disconnectfromdatabase(connection, preparedStatement);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static List<Manga> getMangaByOrder(int orderid) throws ClassNotFoundException {
        Connection connection = connectToDb();
        List<Manga> mangalist = new ArrayList<>();
        String sql = "SELECT * FROM manga,ordered_manga,orders WHERE ordered_manga.Manga_name = manga.Manga_name AND ordered_manga.order_id =orders.order_id AND orders.order_id= ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, orderid);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
               // mangalist.add(new Manga(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            disconnectfromdatabase(connection, preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mangalist;
    }

    public static ResultSet getOrders(Statement statement) throws SQLException {

        String sql = " SELECT manga.* FROM orders o, ordered_manga om, manga where o.order_id = om.order_id AND manga.Manga_name = om.Manga_name";
        return statement.executeQuery(sql);
    }
    public static ResultSet getALLOrders(Statement statement) throws SQLException {

        String sql = " SELECT order_id, Manga_name FROM ordered_manga";
        return statement.executeQuery(sql);
    }

}
