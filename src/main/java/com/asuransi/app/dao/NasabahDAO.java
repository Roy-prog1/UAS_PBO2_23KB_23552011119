/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// com.asuransi.app.dao.NasabahDAO
package com.asuransi.app.dao;

import com.asuransi.app.entity.Nasabah;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NasabahDAO {
    public List<Nasabah> getAllNasabah() {
        List<Nasabah> list = new ArrayList<>();
        String sql = "SELECT * FROM nasabah";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Nasabah nasabah = new Nasabah();
                nasabah.setId(rs.getInt("id"));
                nasabah.setNama(rs.getString("nama"));
                nasabah.setUmur(rs.getInt("umur"));
                list.add(nasabah);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public boolean addNasabah(Nasabah nasabah) {
        String sql = "INSERT INTO nasabah(nama, umur) VALUES(?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, nasabah.getNama());
            stmt.setInt(2, nasabah.getUmur());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Metode lainnya (update, delete, getById) bisa ditambahkan sesuai kebutuhan
}