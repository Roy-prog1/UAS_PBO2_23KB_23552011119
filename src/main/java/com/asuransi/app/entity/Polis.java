/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.asuransi.app.entity;

import java.util.Date;

public class Polis {
    private int id;
    private String nomorPolis;
    private Date tanggalMulai;
    private boolean sudahKlaim;
    
    public Polis() {}

    // Getter dan Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getNomorPolis() { return nomorPolis; }
    public void setNomorPolis(String nomorPolis) { this.nomorPolis = nomorPolis; }
    
    public Date getTanggalMulai() { return tanggalMulai; }
    public void setTanggalMulai(Date tanggalMulai) { this.tanggalMulai = tanggalMulai; }
    
    public boolean isSudahKlaim() { return sudahKlaim; }
    public void setSudahKlaim(boolean sudahKlaim) { this.sudahKlaim = sudahKlaim; }
    
    @Override
    public String toString() {
        return nomorPolis + (sudahKlaim ? " ✓" : "");
    }
}