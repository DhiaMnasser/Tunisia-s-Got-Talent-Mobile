/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.Entities.Achat;

import java.util.Date;

/**
 *
 * @author Klaizer
 */
public class Commande {
    int id;
    int user_id;
    Date date;
    Boolean etat = false;
    int idPanier;
    String address;
    String tel;

    public Commande(int user_id, int idPanier, String address, String tel) {
        this.user_id = user_id;
//        this.date = new java.sql.Date(System.currentTimeMillis());
        this.idPanier = idPanier;
        this.address = address;
        this.tel = tel;
    }

    public Commande(int id, int user_id, Date date,boolean etat, int idPanier, String address, String tel) {
        this.id = id;        
        this.user_id = user_id;
        this.date = date;
        this.etat = etat;
        this.idPanier = idPanier;
        this.address = address;
        this.tel = tel;
    }

    public Commande() {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }

    public int getIdPanier() {
        return idPanier;
    }

    public void setIdPanier(int idPanier) {
        this.idPanier = idPanier;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", user_id=" + user_id + ", date=" + date + ", etat=" + etat + ", idPanier=" + idPanier + ", address=" + address + ", tel=" + tel + '}';
    }
    
    
    public void validerCommande(){
        this.etat = true;
    }
    
}
