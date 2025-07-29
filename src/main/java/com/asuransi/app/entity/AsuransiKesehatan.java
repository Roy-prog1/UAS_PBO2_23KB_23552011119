/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// com.asuransi.app.entity.AsuransiKesehatan
package com.asuransi.app.entity;

public class AsuransiKesehatan extends Asuransi {
    private double faktorRisiko;

    public AsuransiKesehatan(double premiDasar, double faktorRisiko) {
        super("Kesehatan", premiDasar);
        this.faktorRisiko = faktorRisiko;
    }

    @Override
    public double hitungPremi() {
        return premiDasar * faktorRisiko;
    }
}