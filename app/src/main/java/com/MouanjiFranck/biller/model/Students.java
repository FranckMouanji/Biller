package com.MouanjiFranck.biller.model;


import com.google.firebase.Timestamp;

import java.util.Comparator;

public class Students {
    private String id;
    private String nom;
    private String prenom;
    private String classe;
    private String email_prof;
    private Timestamp dateCreation;

    public Students() {
    }

    public Students(String id, String nom, String prenom, String classe) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.classe = classe;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getEmail_prof() {
        return email_prof;
    }

    public void setEmail_prof(String email_prof) {
        this.email_prof = email_prof;
    }

    public Timestamp getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Timestamp dateCreation) {
        this.dateCreation = dateCreation;
    }

    public static Comparator<Students> comparatorDate = (students, t1) -> students.getDateCreation().compareTo(t1.getDateCreation());
}
