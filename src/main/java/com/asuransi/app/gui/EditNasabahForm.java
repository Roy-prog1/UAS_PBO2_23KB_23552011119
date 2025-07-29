/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// EditNasabahForm.java
package com.asuransi.app.gui;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class EditNasabahForm extends JDialog {
    private JTextField txtNama;
    private JTextField txtAlamat;
    private JButton btnSimpan;
    private Connection conn;
    private int id;
    
    public EditNasabahForm(JDialog parent, Connection conn, int id, String nama, String alamat) {
        super(parent, "Edit Data Nasabah", true);
        this.conn = conn;
        this.id = id;
        initComponents();
        
        // Isi data awal
        txtNama.setText(nama);
        txtAlamat.setText(alamat);
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
        
        btnSimpan = new JButton("Simpan Perubahan");
        btnSimpan.addActionListener(e -> simpanPerubahan());
        add(btnSimpan);
    }
    
    private void simpanPerubahan() {
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
                "UPDATE nasabah SET nama = ?, alamat = ? WHERE id = ?")) {
            stmt.setString(1, nama);
            stmt.setString(2, alamat);
            stmt.setInt(3, id);
            
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(this,
                    "Data nasabah berhasil diperbarui",
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