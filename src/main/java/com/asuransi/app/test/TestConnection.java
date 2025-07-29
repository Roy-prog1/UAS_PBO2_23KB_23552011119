/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.asuransi.app.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestConnection {
    public static void main(String[] args) {
        testConnection("asuransi_admin", "admin123");
    }
    
    private static void testConnection(String user, String password) {
        String url = "jdbc:mysql://localhost:3306/db_asuransi?" +
                   "useSSL=false&" +
                   "allowPublicKeyRetrieval=true&" +
                   "serverTimezone=UTC";
        
        System.out.println("Testing connection for: " + user);
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("→ Connection SUCCESS");
            
            // Test query
            try (Statement stmt = conn.createStatement()) {
                // Cek tabel users
                ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM users");
                if (rs.next()) {
                    System.out.println("→ Total users: " + rs.getInt(1));
                }
                
                // Cek tabel nasabah
                rs = stmt.executeQuery("SELECT COUNT(*) FROM nasabah");
                if (rs.next()) {
                    System.out.println("→ Total nasabah: " + rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            System.out.println("→ Connection FAILED");
            System.out.println("  Error Code: " + e.getErrorCode());
            System.out.println("  SQL State: " + e.getSQLState());
            System.out.println("  Message: " + e.getMessage());
        }
    }
}