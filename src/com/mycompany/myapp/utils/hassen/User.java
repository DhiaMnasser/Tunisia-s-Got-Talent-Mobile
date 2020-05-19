/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.utils.hassen;

/**
 *
 * @author Klaizer
 */
public class User {
    private int id;
    String email;

    public User(int id) {
        this.id = id;
        this.email="mohameddhia.mnasser@esprit.tn";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
