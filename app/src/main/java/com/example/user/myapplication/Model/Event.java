package com.example.user.myapplication.Model;

import android.widget.ImageView;

/**
 * Created by user on 22-Mar-15.
 */
public class Event {

    private String nama;
    private String tanggal;
    private int gambar;

    public Event() {
    }

    public Event(int gambar, String nama, String tanggal) {
        this.gambar = gambar;
        this.nama = nama;
        this.tanggal = tanggal;
    }

    public Event(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public int getGambar() {
        return gambar;
    }

    public void setGambar(int gambar) {
        this.gambar = gambar;
    }
}
