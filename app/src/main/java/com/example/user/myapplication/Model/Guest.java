package com.example.user.myapplication.Model;

/**
 * Created by user on 23-Mar-15.
 */
public class Guest {

    private int id;
    private String nama;
    private String birthdate;
    private int image;

    public Guest() {
    }

    public Guest(int id, String nama, String birthdate, int image) {
        this.id =id;
        this.nama = nama;
        this.birthdate = birthdate;
        this.image = image;
    }

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

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
