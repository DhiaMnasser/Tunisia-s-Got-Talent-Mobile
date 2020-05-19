/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities.ahmed;


import java.util.*;


/**
 *
 * @author Haddad
 */
public class Produit {
    private int Id_Produit;
    private String Nom_Produit;
    private int Id_Categorie;
    private int Quantite_Totale ;
    private float Prix_Produit;
    private String Etat_Produit;
    private String Taille_Produit;
    private String Url;
    
    public Produit(int Id_Produit, String Nom_Produit,int Id_Categorie, int Quantite_Totale, float Prix_Produit, String Etat_Produit, String Taille_Produit,String Url) {
        this.Id_Produit = Id_Produit;
        this.Nom_Produit = Nom_Produit;
        this.Id_Categorie = Id_Categorie;
        this.Quantite_Totale = Quantite_Totale;
        this.Prix_Produit = Prix_Produit;
        this.Etat_Produit = Etat_Produit;
        this.Taille_Produit = Taille_Produit;
        this.Url = Url;
    }
    
    

    

    public Produit() {
    }

    public Produit(String Nom_Produit, int Id_Categorie, int Quantite_Totale, float Prix_Produit, String Etat_Produit, String Taille_Produit, String Url) {
        this.Nom_Produit = Nom_Produit;
        this.Id_Categorie = Id_Categorie;
        this.Quantite_Totale = Quantite_Totale;
        this.Prix_Produit = Prix_Produit;
        this.Etat_Produit = Etat_Produit;
        this.Taille_Produit = Taille_Produit;
        this.Url = Url;
    }

    

    public int getId_Produit() {
        return Id_Produit;
    }

    public void setId_Produit(int Id_Produit) {
        this.Id_Produit = Id_Produit;
    }

    public String getNom_Produit() {
        return Nom_Produit;
    }

    public void setNom_Produit(String Nom_Produit) {
        this.Nom_Produit = Nom_Produit;
    }

    public int getQuantite_Totale() {
        return Quantite_Totale;
    }

    public void setQuantite_Totale(int Quantite_Totale) {
        this.Quantite_Totale = Quantite_Totale;
    }

    public float getPrix_Produit() {
        return Prix_Produit;
    }

    public void setPrix_Produit(float Prix_Produit) {
        this.Prix_Produit = Prix_Produit;
    }

    public String getEtat_Produit() {
        return Etat_Produit;
    }

    public void setEtat_Produit(String Etat_Produit) {
        this.Etat_Produit = Etat_Produit;
    }

    public String getTaille_Produit() {
        return Taille_Produit;
    }

    public void setTaille_Produit(String Taille_Produit) {
        this.Taille_Produit = Taille_Produit;
    }

    

    public int getId_Categorie() {
        return Id_Categorie;
    }

    public void setId_Categorie(int Id_Categorie) {
        this.Id_Categorie = Id_Categorie;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String Url) {
        this.Url = Url;
    }
    
    public String getNcat(int i)
    {
    if (i==1)
        return "Tshirt";
    else if (i==2)
        return "Acc";
    else if (i==3)
        return "Tickets";
    else if (i==4)
        return "Autres";
    else if (i==5)
        return "VIP";
    else
        return "Erreur";
    }
    
    /*public String getNCat(int id) throws SQLException {
          String p = null;
          Statement stm = cnx.createStatement();
          String requete = " SELECT nomc FROM `categorie` WHERE `id`= '"+id+"'" ;
          ResultSet rst = stm.executeQuery(requete);
            if (rst.next())
            {p=rst.getString("nomc");
            }
        return p ;
            }*/
    
    
    
    
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
        final Produit other = (Produit) obj;
        if (this.Id_Produit != other.Id_Produit) {
            return false;
        }
        if (this.Quantite_Totale != other.Quantite_Totale) {
            return false;
        }
        if (Float.floatToIntBits(this.Prix_Produit) != Float.floatToIntBits(other.Prix_Produit)) {
            return false;
        }
        if (this.Taille_Produit != other.Taille_Produit) {
            return false;
        }
        if (this.Id_Categorie != other.Id_Categorie) {
            return false;
        }
        if (!Objects.equals(this.Nom_Produit, other.Nom_Produit)) {
            return false;
        }
        if (!Objects.equals(this.Etat_Produit, other.Etat_Produit)) {
            return false;
        }
        return true;
    }
   
    

    @Override
    public String toString() {
        return "Produit : {" + "Id_Produit=" + Id_Produit + ", Id_Categorie=" + Id_Categorie+ ", Name_Produit=" + Nom_Produit + ", Quantite_Totale=" + Quantite_Totale + ", Prix_Produit=" + Prix_Produit + ", Etat_Product=" + Etat_Produit + ", Taille_Porduit=" + Taille_Produit + ", Url=" + Url+ '}' + "\n";
    }



}
