package com.ewns.restoran;

import java.io.Serializable;

public class Yemekler implements Serializable {

    private String yemek_id;
    private String yemek_ismi;
    private int yemek_fiyat;

    public Yemekler(String yemek_id, String yemek_ismi, int yemek_fiyat) {
        this.yemek_id = yemek_id;
        this.yemek_ismi = yemek_ismi;
        this.yemek_fiyat = yemek_fiyat;
    }

    public Yemekler() {
    }

    public String getYemek_id() {
        return yemek_id;
    }

    public void setYemek_id(String yemek_id) {
        this.yemek_id = yemek_id;
    }

    public String getYemek_ismi() {
        return yemek_ismi;
    }

    public void setYemek_ismi(String yemek_ismi) {
        this.yemek_ismi = yemek_ismi;
    }

    public int getYemek_fiyat() {
        return yemek_fiyat;
    }

    public void setYemek_fiyat(int yemek_fiyat) {
        this.yemek_fiyat = yemek_fiyat;
    }
}