package com.MouanjiFranck.biller.model;

import java.util.Objects;

public class Course implements java.io.Serializable{

    private String idCours;
    private String matiere;
    private String titreCours;
    private String niveau;
    private String classe;
    private String categorie;

    public Course() {
    }

    public Course(String idCours, String matiere, String titreCours, String niveau, String classe, String categorie) {
        this.idCours = idCours;
        this.matiere = matiere;
        this.titreCours = titreCours;
        this.niveau = niveau;
        this.classe = classe;
        this.categorie = categorie;
    }

    public String getIdCours() {
        return idCours;
    }

    public void setIdCours(String idCours) {
        this.idCours = idCours;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public String getTitreCours() {
        return titreCours;
    }

    public void setTitreCours(String titreCours) {
        this.titreCours = titreCours;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        return idCours.equals(course.idCours) && matiere.equals(course.matiere) && titreCours.equals(course.titreCours) && niveau.equals(course.niveau) && classe.equals(course.classe) && categorie.equals(course.categorie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCours, matiere, titreCours, niveau, classe, categorie);
    }
}
