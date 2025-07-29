/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// com.asuransi.app.entity.Nasabah
package com.asuransi.app.entity;

public class Nasabah {
    private int id;
    private String nama;
    private int umur;

    public Nasabah() {}

    public Nasabah(int id, String nama, int umur) {
        this.id = id;
        this.nama = nama;
        this.umur = umur;
    }

    // Getter dan Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getUmur() {
        return umur;
    }

    public void setUmur(int umur) {
        this.umur = umur;
    }

    @Override
    public String toString() {
        return nama;
    }
}