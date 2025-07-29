/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// com.asuransi.app.dao.DBConnection
package com.asuransi.app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/db_asuransi?"
            + "useSSL=false&"
            + "allowPublicKeyRetrieval=true&"
            + "serverTimezone=UTC&"
            + "useLegacyDatetimeCode=false";
    
    private static final String USER = "asuransi_admin";
    private static final String PASSWORD = "admin123";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    private static final String BASE_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "db_asuransi";
    private static final String PARAMS = "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    
    public static Connection getRootConnection() throws SQLException {
        return DriverManager.getConnection(BASE_URL + DB_NAME + PARAMS, "root", "");
    }
    
    public static Connection getAdminConnection(String password) throws SQLException {
        return DriverManager.getConnection(BASE_URL + DB_NAME + PARAMS, "admin", password);
    }
    
    public static boolean testAdminConnection(String password) {
        try (Connection conn = getAdminConnection(password)) {
            return conn.isValid(2); // Timeout 2 detik
        } catch (SQLException e) {
            return false;
        }
    }
}