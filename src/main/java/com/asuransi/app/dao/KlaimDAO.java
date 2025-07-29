/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.asuransi.app.dao;

import com.asuransi.app.entity.Klaim;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class KlaimDAO {
    public boolean addKlaim(Klaim klaim) throws SQLException {
        String sql = "INSERT INTO klaim (polis_id, tanggal, status) VALUES (?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, klaim.getPolisId());
            stmt.setString(2, klaim.getTanggal());
            stmt.setString(3, klaim.getStatus());
            
            return stmt.executeUpdate() > 0;
        }
    }
}