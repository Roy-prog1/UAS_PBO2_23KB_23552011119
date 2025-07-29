/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.asuransi.app.entity;

public class Klaim {
    private int id;
    private int polisId;
    private String tanggal;
    private String status; // Pending/Disetujui/Ditolak

    // Getter & Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getPolisId() { return polisId; }
    public void setPolisId(int polisId) { this.polisId = polisId; }
    public String getTanggal() { return tanggal; }
    public void setTanggal(String tanggal) { this.tanggal = tanggal; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}