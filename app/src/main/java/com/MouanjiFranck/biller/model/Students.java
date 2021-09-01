package com.MouanjiFranck.biller.model;

public class Students {
    private String id;
    private String nom;
    private String prenom;
    private String classe;

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
}
