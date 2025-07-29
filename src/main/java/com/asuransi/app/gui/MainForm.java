/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.asuransi.app.gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class MainForm extends JFrame {
    
    public MainForm() {
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void initComponents() {
        setTitle("Aplikasi Asuransi - Dashboard");
        setSize(600, 400);
        
        // Panel untuk header
        JPanel headerPanel = new JPanel();
        headerPanel.add(new JLabel("Selamat datang di Sistem Asuransi"));
        
        // Panel untuk tombol-tombol utama
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        
        // Tombol untuk mengajukan klaim
        JButton btnKlaim = new JButton("Ajukan Klaim");
        btnKlaim.setPreferredSize(new Dimension(150, 40));
        btnKlaim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    bukaKlaimForm();
                } catch (SQLException ex) {
                    System.getLogger(MainForm.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
            }
        });
        
        // Tombol lainnya (contoh)
        JButton btnNasabah = new JButton("Kelola Nasabah");
        JButton btnPolis = new JButton("Kelola Polis");
        JButton btnLaporan = new JButton("Laporan");
        
        // Tambahkan tombol ke panel
        buttonPanel.add(btnNasabah);
        buttonPanel.add(btnPolis);
        buttonPanel.add(btnKlaim);
        buttonPanel.add(btnLaporan);
        
        // Panel footer
        JPanel footerPanel = new JPanel();
        footerPanel.add(new JLabel("© 2025 Aplikasi Asuransi"));
        
        // Atur layout frame
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(headerPanel, BorderLayout.NORTH);
        getContentPane().add(buttonPanel, BorderLayout.CENTER);
        getContentPane().add(footerPanel, BorderLayout.SOUTH);
    }
    
    private void bukaKlaimForm() throws SQLException {
        KlaimForm klaimForm = new KlaimForm();
        klaimForm.setVisible(true);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }
}