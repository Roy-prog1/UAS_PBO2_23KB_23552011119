/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.asuransi.app.gui;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

public class KelolaNasabahForm extends JDialog {
    private JTable tblNasabah;
    private JButton btnTambah;
    private JButton btnEdit;
    private JButton btnHapus;
    private JButton btnRefresh;
    private Connection conn;
    
    public KelolaNasabahForm(JFrame parent, Connection conn) {
        super(parent, "Kelola Data Nasabah", true);
        this.conn = conn;
        initComponents();
        loadData();
    }
    
    private void initComponents() {
        setSize(800, 600);
        setLayout(new BorderLayout());
        setLocationRelativeTo(getParent());
        
        // Tabel dengan scroll pane
        tblNasabah = new JTable();
        JScrollPane scrollPane = new JScrollPane(tblNasabah);
        add(scrollPane, BorderLayout.CENTER);
        
        // Panel tombol
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        
        btnTambah = new JButton("Tambah");
        btnTambah.addActionListener(this::btnTambahActionPerformed);
        buttonPanel.add(btnTambah);
        
        btnEdit = new JButton("Edit");
        btnEdit.addActionListener(this::btnEditActionPerformed);
        buttonPanel.add(btnEdit);
        
        btnHapus = new JButton("Hapus");
        btnHapus.addActionListener(this::btnHapusActionPerformed);
        buttonPanel.add(btnHapus);
        
        btnRefresh = new JButton("Refresh");
        btnRefresh.addActionListener(e -> loadData());
        buttonPanel.add(btnRefresh);
        
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void loadData() {
        try {
            DefaultTableModel model = new DefaultTableModel(
                new Object[]{"ID", "Nama", "Alamat"}, 0
            ) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    // Semua sel tidak bisa diedit
                    return false;
                }
            };
            
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM nasabah")) {
                
                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("nama"),
                        rs.getString("alamat")
                    });
                }
            }
            
            tblNasabah.setModel(model);
            System.out.println("Data kelola nasabah dimuat: " + model.getRowCount() + " baris");
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Error: " + ex.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void btnTambahActionPerformed(ActionEvent evt) {
        new TambahNasabahForm(this, conn).setVisible(true);
        loadData(); // Refresh data setelah form ditutup
    }
    
    private void btnEditActionPerformed(ActionEvent evt) {
        int selectedRow = tblNasabah.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                "Pilih nasabah yang akan diedit",
                "Peringatan",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int id = (int) tblNasabah.getValueAt(selectedRow, 0);
        String nama = (String) tblNasabah.getValueAt(selectedRow, 1);
        String alamat = (String) tblNasabah.getValueAt(selectedRow, 2);
        
        new EditNasabahForm(this, conn, id, nama, alamat).setVisible(true);
        loadData(); // Refresh data setelah form ditutup
    }
    
    private void btnHapusActionPerformed(ActionEvent evt) {
        int selectedRow = tblNasabah.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                "Pilih nasabah yang akan dihapus",
                "Peringatan",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int id = (int) tblNasabah.getValueAt(selectedRow, 0);
        String nama = (String) tblNasabah.getValueAt(selectedRow, 1);
        
        int confirm = JOptionPane.showConfirmDialog(this,
            "Hapus nasabah: " + nama + "?",
            "Konfirmasi Hapus",
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            try (PreparedStatement stmt = conn.prepareStatement(
                    "DELETE FROM nasabah WHERE id = ?")) {
                stmt.setInt(1, id);
                int affectedRows = stmt.executeUpdate();
                
                if (affectedRows > 0) {
                    JOptionPane.showMessageDialog(this,
                        "Nasabah berhasil dihapus",
                        "Sukses",
                        JOptionPane.INFORMATION_MESSAGE);
                    loadData();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this,
                    "Error: " + ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}