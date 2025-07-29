/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.asuransi.app.gui;

import com.asuransi.app.dao.KlaimDAO;
import com.asuransi.app.dao.PolisDAO;
import com.asuransi.app.entity.Klaim;
import com.asuransi.app.entity.Polis;
import java.awt.Color;
import java.awt.Component;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class KlaimForm extends javax.swing.JFrame {
    
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JComboBox<Polis> cbPolis;
    private com.toedter.calendar.JDateChooser dateChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    
    public KlaimForm() {
        initComponents();
        setLocationRelativeTo(null);
        dateChooser.setDate(new Date());
        try {
            loadDataPolis();
            setupComboBoxRenderer();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, 
                "Error memuat data polis: " + ex.getMessage(),
                "Database Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbPolis = new javax.swing.JComboBox<>();
        dateChooser = new com.toedter.calendar.JDateChooser();
        btnSubmit = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ajukan Klaim");

        jLabel1.setText("Polis:");

        jLabel2.setText("Tanggal Klaim:");

        btnSubmit.setText("Submit");
        btnSubmit.addActionListener(e -> btnSubmitActionPerformed(e));

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(e -> btnCancelActionPerformed(e));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbPolis, 0, 200, Short.MAX_VALUE)
                    .addComponent(dateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(87, Short.MAX_VALUE)
                .addComponent(btnSubmit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancel)
                .addGap(82, 82, 82))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbPolis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSubmit)
                    .addComponent(btnCancel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    private void loadDataPolis() throws SQLException {
        PolisDAO polisDAO = new PolisDAO();
        List<Polis> listPolis = polisDAO.getAllPolisWithKlaimStatus();
        
        DefaultComboBoxModel<Polis> model = new DefaultComboBoxModel<>();
        
        for (Polis polis : listPolis) {
            if (!polis.isSudahKlaim()) {
                model.addElement(polis);
            }
        }
        
        if (model.getSize() == 0) {
            JOptionPane.showMessageDialog(this,
                "Semua polis sudah mengajukan klaim",
                "Informasi",
                JOptionPane.INFORMATION_MESSAGE);
        }
        
        cbPolis.setModel(model);
    }
    
    private void setupComboBoxRenderer() {
        cbPolis.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, 
                    int index, boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Polis) {
                    Polis p = (Polis) value;
                    setText(p.getNomorPolis() + (p.isSudahKlaim() ? " ✓" : ""));
                    if (p.isSudahKlaim()) {
                        setForeground(Color.GRAY);
                    }
                }
                return c;
            }
        });
    }

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {
        Polis selectedPolis = (Polis) cbPolis.getSelectedItem();
        Date tanggal = dateChooser.getDate();
        
        if (selectedPolis == null || tanggal == null) {
            JOptionPane.showMessageDialog(this, "Semua field harus diisi!");
            return;
        }
        
        if (selectedPolis.getTanggalMulai() != null && 
            tanggal.before(selectedPolis.getTanggalMulai())) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            JOptionPane.showMessageDialog(this, 
                "Tanggal klaim tidak boleh sebelum tanggal aktif polis: " 
                + sdf.format(selectedPolis.getTanggalMulai()));
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String tanggalStr = sdf.format(tanggal);
        
        Klaim klaim = new Klaim();
        klaim.setPolisId(selectedPolis.getId());
        klaim.setTanggal(tanggalStr);
        klaim.setStatus("Pending");
        
        try {
            KlaimDAO klaimDAO = new KlaimDAO();
            if (klaimDAO.addKlaim(klaim)) {
                // UPDATE STATUS POLIS
                updateStatusPolis(selectedPolis.getId());
                
                JOptionPane.showMessageDialog(this, 
                    "Klaim berhasil diajukan!\nStatus polis diperbarui",
                    "Sukses",
                    JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Gagal mengajukan klaim",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, 
                "Database Error: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    private void updateStatusPolis(int polisId) {
        try {
            PolisDAO polisDAO = new PolisDAO();
            polisDAO.updateStatusKlaim(polisId, true);
            System.out.println("Status klaim diperbarui untuk polis ID: " + polisId);
        } catch (SQLException e) {
            System.err.println("Gagal update status polis: " + e.getMessage());
            JOptionPane.showMessageDialog(this,
                "Klaim berhasil, tetapi gagal update status polis: " + e.getMessage(),
                "Warning",
                JOptionPane.WARNING_MESSAGE);
        }
    }

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }
}