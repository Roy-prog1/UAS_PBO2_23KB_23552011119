/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.asuransi.app.dao;

import com.asuransi.app.entity.Polis;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PolisDAO {
    
    public List<Polis> getAllPolisWithKlaimStatus() throws SQLException {
        List<Polis> listPolis = new ArrayList<>();
        String sql = "SELECT id, nomor_polis, tanggal_mulai, sudah_klaim FROM polis";
        
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Polis polis = new Polis();
                polis.setId(rs.getInt("id"));
                polis.setNomorPolis(rs.getString("nomor_polis"));
                polis.setTanggalMulai(rs.getDate("tanggal_mulai"));
                polis.setSudahKlaim(rs.getBoolean("sudah_klaim"));
                listPolis.add(polis);
            }
        }
        return listPolis;
    }
    
    public void updateStatusKlaim(int polisId, boolean status) throws SQLException {
        String sql = "UPDATE polis SET sudah_klaim = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, status);
            stmt.setInt(2, polisId);
            int rowsUpdated = stmt.executeUpdate();
            
            if (rowsUpdated > 0) {
                System.out.println("Berhasil update status klaim untuk polis ID: " + polisId);
            } else {
                System.out.println("Tidak ditemukan polis dengan ID: " + polisId);
            }
        }
    }
}