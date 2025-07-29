/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// com.asuransi.app.entity.AsuransiJiwa
package com.asuransi.app.entity;

public class AsuransiJiwa extends Asuransi {
    private double faktorUsia;

    public AsuransiJiwa(double premiDasar, double faktorUsia) {
        super("Jiwa", premiDasar);
        this.faktorUsia = faktorUsia;
    }

    @Override
    public double hitungPremi() {
        return premiDasar * faktorUsia;
    }
}