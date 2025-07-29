/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// TambahNasabahForm.java
package com.asuransi.app.gui;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class TambahNasabahForm extends JDialog {
    private JTextField txtNama;
    private JTextField txtAlamat;
    private JButton btnSimpan;
    private Connection conn;
    
    public TambahNasabahForm(JDialog parent, Connection conn) {
        super(parent, "Tambah Nasabah Baru", true);
        this.conn = conn;
        initComponents();
    }
    
    private void initComponents() {
        setSize(400, 200);
        setLayout(new GridLayout(3, 2, 10, 10));
        setLocationRelativeTo(getParent());
        
        add(new JLabel("Nama:"));
        txtNama = new JTextField();
        add(txtNama);
        
        add(new JLabel("Alamat:"));
        txtAlamat = new JTextField();
        add(txtAlamat);
        
        btnSimpan = new JButton("Simpan");
        btnSimpan.addActionListener(e -> simpanData());
        add(btnSimpan);
    }
    
    private void simpanData() {
        String nama = txtNama.getText();
        String alamat = txtAlamat.getText();
        
        if (nama.isEmpty() || alamat.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Nama dan alamat harus diisi",
                "Peringatan",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try (PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO nasabah (nama, alamat) VALUES (?, ?)")) {
            stmt.setString(1, nama);
            stmt.setString(2, alamat);
            
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(this,
                    "Nasabah berhasil ditambahkan",
                    "Sukses",
                    JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                "Error: " + ex.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
}
