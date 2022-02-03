package com.MouanjiFranck.biller.model;

import java.io.Serializable;

public class Users implements Serializable {
    private String image_profile;
    private String name;
    private String surname;
    private String email;
    private String password;
    private int statut;
    private String niveau;

    public Users(){

    }

    public Users(String image_profile, String name, String surname, String email, String password, int statut, String niveau) {
        this.image_profile = image_profile;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.statut = statut;
        this.niveau = niveau;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage_profile() {
        return image_profile;
    }

    public void setImage_profile(String image_profile) {
        this.image_profile = image_profile;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }
}
