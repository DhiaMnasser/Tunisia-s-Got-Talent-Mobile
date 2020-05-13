package com.gthcompagny.myapp.entities.gth;

public class Upload {
    private int id;
    private String titre;
    private String description;
    private String categorie;
    private String auteur;
    private String source;
    private String evenement;


    public Upload(int id, String titre, String auteur,String categorie, String source) {
        this.id = id;
        this.titre = titre;
        this.categorie = categorie;
        this.auteur = auteur;
        this.source=source;
    }

    public Upload(int id, String titre, String auteur,String categorie, String description,String source) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.categorie = categorie;
        this.auteur = auteur;
        this.source=source;
    }
    
    public Upload(String titre, String auteur,String categorie, String description,String source) {
        this.titre = titre;
        this.description = description;
        this.categorie = categorie;
        this.auteur = auteur;
        this.source=source;
    }

    public Upload() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getEvenement() {
        return evenement;
    }

    public void setEvenement(String evenement) {
        this.evenement = evenement;
    }

    @Override
    public String toString() {
        return "Upload{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                ", categorie='" + categorie + '\'' +
                ", auteur='" + auteur + '\'' +
                ", source='" + source + '\'' +
                ", evenement='" + evenement + '\'' +
                '}';
    }
}
