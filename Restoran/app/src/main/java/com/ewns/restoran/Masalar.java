package com.ewns.restoran;

import java.io.Serializable;

public class Masalar implements Serializable {
    private String masa_id;
    private String masa_ismi;
    private String masa_durum;

    public Masalar(String masa_id, String masa_ismi, String masa_durum) {
        this.masa_id = masa_id;
        this.masa_ismi = masa_ismi;
        this.masa_durum = masa_durum;
    }

    public Masalar() {
    }

    public String getMasa_id() {
        return masa_id;
    }

    public void setMasa_id(String masa_id) {
        this.masa_id = masa_id;
    }

    public String getMasa_ismi() {
        return masa_ismi;
    }

    public void setMasa_ismi(String masa_ismi) {
        this.masa_ismi = masa_ismi;
    }

    public String getMasa_durum() {
        return masa_durum;
    }

    public void setMasa_durum(String masa_durum) {
        this.masa_durum = masa_durum;
    }
}
