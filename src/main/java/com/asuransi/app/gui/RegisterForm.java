/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// com.asuransi.app.gui.RegisterForm
package com.asuransi.app.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterForm extends javax.swing.JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JPasswordField txtConfirmPassword;
    private JButton btnRegister;
    private JButton btnBack;

    public RegisterForm() {
        initComponents();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        setTitle("Registrasi Pengguna Baru");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        
        // Panel utama dengan layout BoxLayout vertikal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Panel untuk input fields
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        
        formPanel.add(new JLabel("Username:"));
        txtUsername = new JTextField();
        formPanel.add(txtUsername);

        formPanel.add(new JLabel("Password:"));
        txtPassword = new JPasswordField();
        formPanel.add(txtPassword);

        formPanel.add(new JLabel("Konfirmasi Password:"));
        txtConfirmPassword = new JPasswordField();
        formPanel.add(txtConfirmPassword);
        
        mainPanel.add(formPanel);

        // Panel untuk tombol
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        btnRegister = new JButton("Daftar");
        btnRegister.setPreferredSize(new Dimension(120, 30));
        btnRegister.addActionListener(this::btnRegisterActionPerformed);
        buttonPanel.add(btnRegister);

        btnBack = new JButton("Kembali");
        btnBack.setPreferredSize(new Dimension(120, 30));
        btnBack.addActionListener(e -> {
            this.dispose();
            new LoginForm().setVisible(true);
        });
        buttonPanel.add(btnBack);
        
        mainPanel.add(buttonPanel);

        // Tambahkan ke frame
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        
        // Tampilkan form
        setVisible(true);
    }

    private void btnRegisterActionPerformed(ActionEvent evt) {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());
        String confirmPassword = new String(txtConfirmPassword.getPassword());

        // Validasi input
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Username dan password harus diisi",
                "Error Registrasi", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, 
                "Password dan konfirmasi password tidak cocok",
                "Error Registrasi", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        System.out.println("Mencoba registrasi user: " + username);
        
        // Simpan ke database
        try {
            String url = "jdbc:mysql://localhost:3306/db_asuransi?" +
                       "useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
            
            try (Connection conn = DriverManager.getConnection(url, "asuransi_admin", "admin123")) {
                String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
                
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, username);
                    stmt.setString(2, password);
                    
                    int rowsAffected = stmt.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("Registrasi berhasil untuk user: " + username);
                        JOptionPane.showMessageDialog(this, 
                            "Registrasi berhasil! Silakan login",
                            "Sukses", 
                            JOptionPane.INFORMATION_MESSAGE);
                        this.dispose();
                        new LoginForm().setVisible(true);
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error registrasi: " + ex.getMessage());
            
            String errorMsg = "Error registrasi: ";
            if (ex.getMessage().contains("Duplicate entry")) {
                errorMsg += "Username sudah digunakan";
            } else {
                errorMsg += ex.getMessage();
            }
            
            JOptionPane.showMessageDialog(this, 
                errorMsg,
                "Database Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new RegisterForm().setVisible(true);
        });
    }
}