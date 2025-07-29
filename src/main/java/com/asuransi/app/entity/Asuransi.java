/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// com.asuransi.app.entity.Asuransi
package com.asuransi.app.entity;

public abstract class Asuransi {
    protected String jenis;
    protected double premiDasar;

    public Asuransi(String jenis, double premiDasar) {
        this.jenis = jenis;
        this.premiDasar = premiDasar;
    }

    // Polymorphic method
    public abstract double hitungPremi();
    
    public String getJenis() {
        return jenis;
    }
}