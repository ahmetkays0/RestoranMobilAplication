package com.ewns.restoran;

import java.io.Serializable;

public class Hesap implements Serializable {

    private String hesap_yemek_id;
    private String hesap_yemek_ismi;
    private int hesap_yemek_adet;
    private int hesap_tutar;

    public Hesap(String hesap_yemek_id, String hesap_yemek_ismi, int hesap_yemek_adet, int hesap_tutar) {
        this.hesap_yemek_id = hesap_yemek_id;
        this.hesap_yemek_ismi = hesap_yemek_ismi;
        this.hesap_yemek_adet = hesap_yemek_adet;
        this.hesap_tutar = hesap_tutar;
    }

    public Hesap() {
    }

    public String getHesap_yemek_id() {
        return hesap_yemek_id;
    }

    public void setHesap_yemek_id(String hesap_yemek_id) {
        this.hesap_yemek_id = hesap_yemek_id;
    }

    public String getHesap_yemek_ismi() {
        return hesap_yemek_ismi;
    }

    public void setHesap_yemek_ismi(String hesap_yemek_ismi) {
        this.hesap_yemek_ismi = hesap_yemek_ismi;
    }

    public int getHesap_yemek_adet() {
        return hesap_yemek_adet;
    }

    public void setHesap_yemek_adet(int hesap_yemek_adet) {
        this.hesap_yemek_adet = hesap_yemek_adet;
    }

    public int getHesap_tutar() {
        return hesap_tutar;
    }

    public void setHesap_tutar(int hesap_tutar) {
        this.hesap_tutar = hesap_tutar;
    }
}
