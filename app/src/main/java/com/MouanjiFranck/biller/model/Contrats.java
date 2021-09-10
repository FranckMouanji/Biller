package com.MouanjiFranck.biller.model;

public class Contrats {

    private String id_contrat;
    private String email_repetiteur;
    private String id_student;
    private String nom_pere;
    private String prenom_pere;
    private String numero_pere;
    private String second_numero;
    private String salaire;
    private String date_payement;
    private String sign;

    public Contrats() {
    }

    public Contrats(String id_contrat, String email_repetiteur, String id_student, String nom_pere, String prenom_pere, String numero_pere, String second_numero, String salaire, String date_payement, String sign) {
        this.id_contrat = id_contrat;
        this.email_repetiteur = email_repetiteur;
        this.id_student = id_student;
        this.nom_pere = nom_pere;
        this.prenom_pere = prenom_pere;
        this.numero_pere = numero_pere;
        this.second_numero = second_numero;
        this.salaire = salaire;
        this.date_payement = date_payement;
        this.sign = sign;
    }

    public String getId_contrat() {
        return id_contrat;
    }

    public void setId_contrat(String id_contrat) {
        this.id_contrat = id_contrat;
    }

    public String getEmail_repetiteur() {
        return email_repetiteur;
    }

    public void setEmail_repetiteur(String email_repetiteur) {
        this.email_repetiteur = email_repetiteur;
    }

    public String getId_student() {
        return id_student;
    }

    public void setId_student(String id_student) {
        this.id_student = id_student;
    }

    public String getNom_pere() {
        return nom_pere;
    }

    public void setNom_pere(String nom_pere) {
        this.nom_pere = nom_pere;
    }

    public String getPrenom_pere() {
        return prenom_pere;
    }

    public void setPrenom_pere(String prenom_pere) {
        this.prenom_pere = prenom_pere;
    }

    public String getNumero_pere() {
        return numero_pere;
    }

    public void setNumero_pere(String numero_pere) {
        this.numero_pere = numero_pere;
    }

    public String getSecond_numero() {
        return second_numero;
    }

    public void setSecond_numero(String second_numero) {
        this.second_numero = second_numero;
    }

    public String getSalaire() {
        return salaire;
    }

    public void setSalaire(String salaire) {
        this.salaire = salaire;
    }

    public String getDate_payement() {
        return date_payement;
    }

    public void setDate_payement(String date_payement) {
        this.date_payement = date_payement;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
