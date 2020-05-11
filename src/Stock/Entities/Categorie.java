/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stock.Entities;


import java.util.*;
import Stock.Services.ServiceCategorie;

/**
 *
 * @author Haddad
 */

public class Categorie {

    private int Id_Categorie;
    private String Nom_Categorie;
    

    public Categorie(String Name_Categorie) {
        this.Nom_Categorie = Name_Categorie; 
    }

    public Categorie(int Id_Categorie, String Nom_Categorie) {
        this.Id_Categorie = Id_Categorie;
        this.Nom_Categorie = Nom_Categorie;
    }

    public Categorie() {
    }

    public int getId_Categorie() {
        return Id_Categorie;
    }

    public void setId_Categorie(int Id_Product) {
        this.Id_Categorie = Id_Product;
    }

    public String getNom_Categorie() {
        return Nom_Categorie;
    }

    public void setNom_Categorie(String Nom_Categorie) {
        this.Nom_Categorie = Nom_Categorie;
    }

   
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Categorie other = (Categorie) obj;
        
        return true;
    }

    @Override
    public String toString() {
        return "Categorie : {" + "Id_Categorie=" + Id_Categorie + ", Name_Categorie=" + Nom_Categorie +'}'+ "\n";
    }



}
