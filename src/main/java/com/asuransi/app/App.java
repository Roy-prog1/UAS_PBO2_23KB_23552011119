/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// com.asuransi.app.App
package com.asuransi.app;

import com.asuransi.app.gui.LoginForm;

public class App {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new LoginForm().setVisible(true);
        });
    }
}