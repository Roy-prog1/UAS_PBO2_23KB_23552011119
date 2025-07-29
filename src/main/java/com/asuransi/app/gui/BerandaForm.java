/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// com.asuransi.app.gui.BerandaForm
package com.asuransi.app.gui;

import com.asuransi.app.dao.PolisDAO;
import com.asuransi.app.entity.Polis;
import java.awt.*;
import java.sql.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class BerandaForm extends javax.swing.JFrame {
    
    private final Connection conn;
    private javax.swing.JTable tblNasabah;
    private javax.swing.JTable tblPolis;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnKelola;
    private javax.swing.JButton btnKlaim;
    private javax.swing.JTabbedPane tabbedPane;

    public BerandaForm(Connection conn) {
        this.conn = conn;
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 700);
        setTitle("Dashboard Aplikasi Asuransi");
        
        try {
            loadDataNasabah();
            loadDataPolis();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
    
    private void initComponents() {
        tabbedPane = new JTabbedPane();
        
        // Tab 1: Data Nasabah
        JPanel nasabahPanel = new JPanel(new BorderLayout());
        tblNasabah = new javax.swing.JTable();
        JScrollPane nasabahScroll = new JScrollPane(tblNasabah);
        nasabahPanel.add(nasabahScroll, BorderLayout.CENTER);
        tabbedPane.addTab("Data Nasabah", nasabahPanel);
        
        // Tab 2: Data Polis
        JPanel polisPanel = new JPanel(new BorderLayout());
        tblPolis = new javax.swing.JTable();
        JScrollPane polisScroll = new JScrollPane(tblPolis);
        polisPanel.add(polisScroll, BorderLayout.CENTER);
        tabbedPane.addTab("Data Polis", polisPanel);
        
        // Panel tombol
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        
        btnKelola = new JButton("Kelola Nasabah");
        btnKlaim = new JButton("Ajukan Klaim");
        btnLogout = new JButton("Logout");
        
        // Action listeners
        btnKelola.addActionListener(e -> {
            new KelolaNasabahForm(this, conn).setVisible(true);
        });
        
        btnKlaim.addActionListener(e -> {
            try {
                KlaimForm klaimForm = new KlaimForm();
                klaimForm.setVisible(true);
                
                // Refresh data setelah form klaim ditutup
                SwingUtilities.invokeLater(() -> {
                    try {
                        loadDataPolis();
                        System.out.println("Data polis direfresh setelah pengajuan klaim");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                });
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });
        
        btnLogout.addActionListener(e -> logout());
        
        buttonPanel.add(btnKelola);
        buttonPanel.add(btnKlaim);
        buttonPanel.add(btnLogout);
        
        // Layout utama
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(tabbedPane, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void loadDataNasabah() throws SQLException {
        DefaultTableModel model = new DefaultTableModel(
            new Object[]{"ID", "Nama", "Alamat"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Semua sel tidak bisa diedit
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
            System.out.println("Data nasabah dimuat: " + model.getRowCount() + " baris");
        }
        
        tblNasabah.setModel(model);
    }
    
    private void loadDataPolis() throws SQLException {
        DefaultTableModel model = new DefaultTableModel(
            new Object[]{"Nomor Polis", "Tanggal Mulai", "Status Klaim"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Semua sel tidak bisa diedit
            }
        };
        
        PolisDAO polisDAO = new PolisDAO();
        List<Polis> listPolis = polisDAO.getAllPolisWithKlaimStatus();
        
        for (Polis polis : listPolis) {
            model.addRow(new Object[]{
                polis.getNomorPolis(),
                polis.getTanggalMulai(),
                polis.isSudahKlaim() ? "Sudah Klaim" : "Belum Klaim"
            });
        }
        
        System.out.println("Data polis dimuat: " + model.getRowCount() + " baris");
        tblPolis.setModel(model);
    }
    
    private void logout() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Koneksi database ditutup");
            }
            this.dispose();
            new LoginForm().setVisible(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}