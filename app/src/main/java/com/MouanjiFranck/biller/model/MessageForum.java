package com.MouanjiFranck.biller.model;

public class MessageForum {
    private String email_destinataire;
    private String id_forum;
    private String date_envoie;
    private String media;

    public MessageForum() {
    }

    public MessageForum(String email_destinataire, String id_forum, String date_envoie, String media) {
        this.email_destinataire = email_destinataire;
        this.id_forum = id_forum;
        this.date_envoie = date_envoie;
        this.media = media;
    }

    public String getEmail_destinataire() {
        return email_destinataire;
    }

    public void setEmail_destinataire(String email_destinataire) {
        this.email_destinataire = email_destinataire;
    }

    public String getId_forum() {
        return id_forum;
    }

    public void setId_forum(String id_forum) {
        this.id_forum = id_forum;
    }

    public String getDate_envoie() {
        return date_envoie;
    }

    public void setDate_envoie(String date_envoie) {
        this.date_envoie = date_envoie;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }
}
