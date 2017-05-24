/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package services;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Fredy
 */
public class DBManager {
    private static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static String JDBC_URL = "jdbc:mysql://localhost:3306/";
    private static String JDBC_DB = "torneotenismesa";
    private static String JDBC_USER = "root";
    private static String JDBC_PASSWORD = "root";
    private static Driver driver = null;
    
    public static synchronized Connection getConnection() throws SQLException{
        if (driver == null) {
            try {
                Class jdbcDriverClass = Class.forName(JDBC_DRIVER);
                driver = (Driver) jdbcDriverClass.newInstance();
                DriverManager.registerDriver(driver);
            } catch (Exception e) {
                System.out.println("Fallo en cargar el driver JDBC");
                e.printStackTrace();
            }
        }
        return DriverManager.getConnection(JDBC_URL + JDBC_DB, JDBC_USER, JDBC_PASSWORD);
    }
    
    public static void close(ResultSet rs){
        try {
            if (rs != null) rs.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
    public static void close(PreparedStatement statement){
        try {
            if (statement != null) statement.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
    
    public static void close(Connection connection){
        try {
            if (connection != null) connection.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
}
