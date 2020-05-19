/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities.dhia;

/**
 *
 * @author Klaizer
 */
public class Panier {

    int id = 0;
    int user_id;
    Double prixTotal = 0.0;
    Boolean etat;

    public Panier() {

    }

    public Panier(int id, int user_id, double prixTotal, boolean etat) {
        this.id = id;
        this.user_id = user_id;
        this.prixTotal = prixTotal;
        this.etat = etat;

    }

    public Panier(int user_id) {
        this.user_id = user_id;
        this.etat = true;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(Double prixTotal) {
        this.prixTotal = prixTotal;
    }

    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Panier{" + "id=" + id + ", user_id=" + user_id + ", prixTotal=" + prixTotal + ", etat=" + etat + '}';
    }

}
