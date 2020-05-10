/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.Utils;

/**
 *
 * @author Klaizer
 */
public class User {
    private int id;
    String email;

    public User(int id) {
        this.id = id;
        this.email="esprit.bitdev@gmail.com";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
