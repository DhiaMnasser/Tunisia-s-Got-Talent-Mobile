/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Achraf
 */
public class User {
    private int id ;
   private String name ;
   private int event_id ;

    

    

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public User(int id, String name, int event_id) {
        this.id = id;
        this.name = name;
        this.event_id = event_id;
    }
public User( String name, int event_id) {
        
        this.name = name;
        this.event_id = event_id;
    }
    public User() {
    }

    @Override
    public String toString() {
        return  name  ;
    }
    
}
