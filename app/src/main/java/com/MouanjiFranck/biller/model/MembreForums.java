package com.MouanjiFranck.biller.model;

public class MembreForums {
    private String email_user;
    private String id_forum;
    private String statut_membre;
    private String date_admission;

    public MembreForums() {
    }

    public MembreForums(String email_user, String id_forum, String statut_membre, String date_admission) {
        this.email_user = email_user;
        this.id_forum = id_forum;
        this.statut_membre = statut_membre;
        this.date_admission = date_admission;
    }

    public String getEmail_user() {
        return email_user;
    }

    public void setEmail_user(String email_user) {
        this.email_user = email_user;
    }

    public String getId_forum() {
        return id_forum;
    }

    public void setId_forum(String id_forum) {
        this.id_forum = id_forum;
    }

    public String getStatut_membre() {
        return statut_membre;
    }

    public void setStatut_membre(String statut_membre) {
        this.statut_membre = statut_membre;
    }

    public String getDate_admission() {
        return date_admission;
    }

    public void setDate_admission(String date_admission) {
        this.date_admission = date_admission;
    }
}
