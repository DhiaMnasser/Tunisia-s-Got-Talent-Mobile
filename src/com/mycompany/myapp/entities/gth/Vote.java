/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities.gth;

/**
 *
 * @author gth
 */
public class Vote {
    private int id;
    private int userId;
    private int pubId;

    public Vote(int id, int userId, int pubId) {
        this.id = id;
        this.userId = userId;
        this.pubId = pubId;
    }

    public Vote() {
    }
    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPubId() {
        return pubId;
    }

    public void setPubId(int pubId) {
        this.pubId = pubId;
    }

    @Override
    public String toString() {
        return "Vote{" + "id=" + id + ", userId=" + userId + ", pubId=" + pubId + '}';
    }
    
    
    
    
    
}
