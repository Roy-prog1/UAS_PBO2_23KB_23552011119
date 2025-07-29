/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// com.asuransi.app.gui.LoginForm
package com.asuransi.app.gui;

import java.awt.EventQueue;
import java.sql.*;
import java.util.Arrays;
import javax.swing.JOptionPane;

public class LoginForm extends javax.swing.JFrame {
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnRegister;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;

    public LoginForm() {
        initComponents();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();
        btnRegister = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login - Aplikasi Asuransi");

        jLabel1.setText("Username:");

        jLabel2.setText("Password:");

        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        btnRegister.setText("Register");
        btnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtUsername)
                    .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(87, Short.MAX_VALUE)
                .addComponent(btnLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRegister)
                .addGap(82, 82, 82))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLogin)
                    .addComponent(btnRegister))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {
        String username = txtUsername.getText();
        char[] password = txtPassword.getPassword();
        
        // Gunakan user baru yang telah dibuat
        String dbUser = "asuransi_admin";
        String dbPassword = "admin123";
        
        String url = "jdbc:mysql://localhost:3306/db_asuransi?" +
                   "useSSL=false&" +
                   "allowPublicKeyRetrieval=true&" +
                   "serverTimezone=UTC&" +
                   "useLegacyDatetimeCode=false";

        try {
            // 1. Tes koneksi dengan credential baru
            System.out.println("Testing connection for: " + dbUser);
            Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
            System.out.println("Database connection successful!");
            
            // 2. Verifikasi login di tabel users
            String sql = "SELECT * FROM users WHERE username=? AND password=?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, username);
                stmt.setString(2, new String(password));
                
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    System.out.println("Login successful!");
                    
                    // Tutup login form sebelum buka beranda
                    this.setVisible(false);
                    this.dispose();
                    
                    // Buka BerandaForm dengan koneksi yang valid
                    EventQueue.invokeLater(() -> {
                        new BerandaForm(conn).setVisible(true);
                        this.dispose();
                    });
                } else {
                    JOptionPane.showMessageDialog(this,
                        "Username/password salah",
                        "Login Failed",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException e) {
            System.err.println("Database error details:");
            System.err.println("Message: " + e.getMessage());
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
            
            String errorMsg = "Terjadi kesalahan database:\n" + e.getMessage();
            JOptionPane.showMessageDialog(this,
                errorMsg,
                "Database Error",
                JOptionPane.ERROR_MESSAGE);
        } finally {
            // Clear password dari memory
            Arrays.fill(password, '\0');
        }
    }
    
    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println("Membuka RegisterForm...");
        this.dispose(); // Tutup login form
        new RegisterForm().setVisible(true); // Buka register form
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginForm().setVisible(true);
            }
        });
    }
}