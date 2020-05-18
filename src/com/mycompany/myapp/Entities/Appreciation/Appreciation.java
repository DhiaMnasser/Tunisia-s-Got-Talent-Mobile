/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.Entities.Appreciation;

/**
 *
 * @author frauDEee
 */
public class Appreciation {
    int id;
    int user_id;
    int dislike;
    int likes;
    int publication_id;

    public Appreciation() {
    }
    
    
    public Appreciation(int user_id, int dislike, int likes, int publication_id) {
        this.user_id = user_id;
        this.dislike = dislike;
        this.likes = likes;
        this.publication_id = publication_id;
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

    public int getDislike() {
        return dislike;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getPublication_id() {
        return publication_id;
    }

    public void setPublication_id(int publication_id) {
        this.publication_id = publication_id;
    }
    
    @Override
    public String toString() {
        return "Appreciation{" + "id=" + id + ", user_id=" + user_id + ", dislike=" + dislike + ", likes=" + likes + ", publication_id=" + publication_id + '}';
    }
    
}
