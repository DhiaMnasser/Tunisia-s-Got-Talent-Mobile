/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.Entities.Achat;

/**
 *
 * @author Klaizer
 */
public class LigneCommande {
    
    int id;
    int idproduit;
    String nomProduit;
    int quantite;
    int idPanier;

    public LigneCommande(int id, int idproduit, String nomProduit, int quantite, int idPanier) {
        this.id = id;
        this.idproduit = idproduit;
        this.nomProduit = nomProduit;
        this.quantite = quantite;
        this.idPanier = idPanier;
    }
    
    public LigneCommande( int idproduit, String nomProduit, int quantite, int idPanier) {
        this.idproduit = idproduit;
        this.nomProduit = nomProduit;
        this.quantite = quantite;
        this.idPanier = idPanier;
    }

    public LigneCommande() {
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdproduit() {
        return idproduit;
    }

    public void setIdproduit(int idproduit) {
        this.idproduit = idproduit;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getIdPanier() {
        return idPanier;
    }

    public void setIdPanier(int idPanier) {
        this.idPanier = idPanier;
    }

    @Override
    public String toString() {
        return "LigneCommande{" + "id=" + id + ", idproduit=" + idproduit + ", nomProduit=" + nomProduit + ", quantite=" + quantite + ", idPanier=" + idPanier + '}';
    }
    
    
}
