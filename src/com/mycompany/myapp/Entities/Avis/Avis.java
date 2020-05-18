/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.Entities.Avis;

import java.util.Date;

/**
 *
 * @author frauDEee
 */
public class Avis {
    int id;
    int user_id;
    String texte;
    Date date;
    int Rating;
    String Email;

    public Avis(String text) {
        this.texte=text;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public Avis(int user_id, String texte, Date date, int Rating, String Email) {
        this.user_id = user_id;
        this.texte = texte;
        this.date = date;
        this.Rating = Rating;
        this.Email = Email;
    }

    public Avis(int user_id, String texte, Date date, int Rating) {
        this.user_id = user_id;
        this.texte = texte;
        this.date = date;
        this.Rating = Rating;
    }

    public int getRating() {
        return Rating;
    }

    public void setRating(int Rating) {
        this.Rating = Rating;
    }

    public Avis(int user_id, String texte, Date date) {
        this.user_id = user_id;
        this.texte = texte;
        this.date = date;
    }

    public Avis(int user_id, String texte) {
        this.user_id = user_id;
        this.texte = texte;
    }
    
    
    public Avis(String texte, Date date) {
        this.texte = texte;
        this.date = date;
    }

    public Avis() {
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

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    @Override
    public String toString() {
        return "Avis{" + "id=" + id + ", user_id=" + user_id + ", texte=" + texte + ", date=" + date + '}';
    }

    
    
}
