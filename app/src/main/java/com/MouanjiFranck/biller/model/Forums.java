package com.MouanjiFranck.biller.model;

public class Forums {
    private String id_forum;
    private String name_forum;
    private String statut_forum;
    private String logo_forum;
    private String date_creaction;

    public Forums() {
    }

    public Forums(String id_forum, String name_forum, String statut_forum, String logo_forum, String date_creaction) {
        this.id_forum = id_forum;
        this.name_forum = name_forum;
        this.statut_forum = statut_forum;
        this.logo_forum = logo_forum;
        this.date_creaction = date_creaction;
    }

    public String getId_forum() {
        return id_forum;
    }

    public void setId_forum(String id_forum) {
        this.id_forum = id_forum;
    }

    public String getName_forum() {
        return name_forum;
    }

    public void setName_forum(String name_forum) {
        this.name_forum = name_forum;
    }

    public String getStatut_forum() {
        return statut_forum;
    }

    public void setStatut_forum(String statut_forum) {
        this.statut_forum = statut_forum;
    }

    public String getLogo_forum() {
        return logo_forum;
    }

    public void setLogo_forum(String logo_forum) {
        this.logo_forum = logo_forum;
    }

    public String getDate_creaction() {
        return date_creaction;
    }

    public void setDate_creaction(String date_creaction) {
        this.date_creaction = date_creaction;
    }
}
