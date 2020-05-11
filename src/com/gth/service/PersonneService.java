/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gth.service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;

import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.gth.entities.BCrypt;

import java.util.List;

import net.sf.json.JSONSerializer;
import org.json.JSONObject;
import com.gth.entities.Personne;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import java.util.ArrayList;
import java.util.Map;


import org.json.JSONException;


/**
 *
 * @author mohamed khrouf
 */
public class PersonneService {
      public ArrayList<Personne> users;
       public void sendCode(Personne p){
       try {
           JavaMailUtil.sendMail(p.getEmail(),p.getConfirmation_token());
       } catch (Exception ex) {
          
       }
     }
       public static String hashPassword(String password_plaintext) {
        String salt = BCrypt.gensalt(13);
        System.out.println(salt);
        String hashed_password = BCrypt.hashpw(password_plaintext, salt);

        return (hashed_password);
    }
        public static boolean checkPassword(String password_plaintext, String stored_hash) {
        boolean password_verified = false;

        if (null == stored_hash || !stored_hash.startsWith("$2y$")) {
            throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");
        }

        password_verified = BCrypt.checkpw(password_plaintext, stored_hash);

        return (password_verified);
    }
 public void ajouterPersonne(Personne p){
     ConnectionRequest con = new ConnectionRequest();
     con.setPost(false);
        String Url = "http://localhost/TGTOf/web/app_dev.php/insert"+"?username="+p.getUsername()+"&email="+p.getEmail()+"&enabled="+p.getEnabled()+"&password="+p.getPassword()+"&confirmationtoken="+p.getConfirmation_token()+"&roles="+p.getRoles();
        con.setUrl(Url);

        System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    } 
 
     
     public void modPersonne(Personne p){
     ConnectionRequest con = new ConnectionRequest();
     con.setPost(false);
        String Url = "http://localhost/TGTOf/web/app_dev.php/mod"+"?id="+p.getId()+"&username="+p.getUsername()+"&email="+p.getEmail()+"&enabled="+p.getEnabled()+"&password="+p.getPassword()+"&confirmationtoken="+p.getConfirmation_token()+"&roles="+p.getRoles();
        con.setUrl(Url);

        System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }  


  public void getUser(String username,String email) {

       ConnectionRequest connectionRequest = new ConnectionRequest() {
            List<Personne> books = new ArrayList<>();
            Personne userkh = new Personne();
            Personne usercurrent = new Personne();

            @Override
            protected void readResponse(InputStream in) throws IOException {
                JSONParser json = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(in, "UTF-8");
                    Map<String, Object> data = json.parseJSON(reader);
                    List<Map<String, Object>> content = (List<Map<String, Object>>) data.get("root");
                    books.clear();
                    for (Map<String, Object> obj : content) {
                        System.out.println((String) obj.get("username"));
                        userkh = new Personne();
                               
                                userkh.setUsername((String) obj.get("username"));
                                 userkh.setUsername_canonical((String) obj.get("username"));
                                userkh.setEmail((String) obj.get("email"));
                                 userkh.setEmail_canonical((String) obj.get("email"));
                             userkh.setPassword((String) obj.get("password"));
                       
                        books.add(userkh);
                        
                    }
                } catch (IOException err) {
                    Log.e(err);
                }
            }

            @Override
            protected void postResponse() {
                for (Personne obj : books) {
                    if (username.equals(obj.getUsername())) {
                        System.out.println(obj.getId());
                         System.out.println(obj.getUsername());
                         Recherche.username=true;
                    }
                    if( email.equals(obj.getEmail()) ){
                        Recherche.email=true;
                    }
                }
            }
        };
        connectionRequest.setUrl("http://localhost/TGTOf/web/app_dev.php/get");
        NetworkManager.getInstance().addToQueueAndWait(connectionRequest);
    }

    /**
     *
     * @param username
     * @param password
     */
    public void checklog(String username,String password) {

       ConnectionRequest connectionRequest = new ConnectionRequest() {
            List<Personne> books = new ArrayList<>();
            Personne userkh = new Personne();
            Personne usercurrent = new Personne();

            @Override
            protected void readResponse(InputStream in) throws IOException {
                JSONParser json = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(in, "UTF-8");
                    Map<String, Object> data = json.parseJSON(reader);
                    List<Map<String, Object>> content = (List<Map<String, Object>>) data.get("root");
                    books.clear();
                    for (Map<String, Object> obj : content) {
                        System.out.println((String) obj.get("username"));
                        
                        userkh = new Personne();
                               userkh.setId(((Double)obj.get("id")).intValue());
                                userkh.setUsername((String) obj.get("username"));
                                 userkh.setUsername_canonical((String) obj.get("username"));
                                userkh.setEmail((String) obj.get("email"));
                                 userkh.setEmail_canonical((String) obj.get("email"));
                             userkh.setPassword((String) obj.get("password"));
                       
                        books.add(userkh);
                        
                    }
                } catch (IOException err) {
                    Log.e(err);
                }
            }

            @Override
            protected void postResponse() {
                for (Personne obj : books) {
                    if (username.equals(obj.getUsername()) && checkPassword(password, obj.getPassword())) {
                      Recherche.connexion=true;
                     
                      Personne p =new Personne(obj.getUsername(),obj.getEmail(),obj.getPassword());
                      p.setPassword(obj.getPassword());
                      p.setId(obj.getId());
                      System.out.println(obj.getId());
                      p.setEnabled(obj.getEnabled());
                      
                      UserCourant.ok=p;
                      
                    }
                   
                }
            }
        };
        connectionRequest.setUrl("http://localhost/TGTOf/web/app_dev.php/get");
        NetworkManager.getInstance().addToQueueAndWait(connectionRequest);
    }
 public void checklog(String username) {

       ConnectionRequest connectionRequest = new ConnectionRequest() {
            List<Personne> books = new ArrayList<>();
            Personne userkh = new Personne();
            Personne usercurrent = new Personne();

            @Override
            protected void readResponse(InputStream in) throws IOException {
                JSONParser json = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(in, "UTF-8");
                    Map<String, Object> data = json.parseJSON(reader);
                    List<Map<String, Object>> content = (List<Map<String, Object>>) data.get("root");
                    books.clear();
                    for (Map<String, Object> obj : content) {
                        System.out.println((String) obj.get("username"));
                        userkh = new Personne();
                               userkh.setId(((Double)obj.get("id")).intValue());
                                userkh.setUsername((String) obj.get("username"));
                                 userkh.setUsername_canonical((String) obj.get("username"));
                                userkh.setEmail((String) obj.get("email"));
                                 userkh.setEmail_canonical((String) obj.get("email"));
                             userkh.setPassword((String) obj.get("password"));
                       
                        books.add(userkh);
                        
                    }
                } catch (IOException err) {
                    Log.e(err);
                }
            }

            @Override
            protected void postResponse() {
                for (Personne obj : books) {
                    if (username.equals(obj.getUsername())) {
                      
                     
                      Personne p =new Personne(obj.getUsername(),obj.getEmail(),obj.getPassword());
                      p.setPassword(obj.getPassword());
                      p.setId(obj.getId());
                      p.setEnabled(obj.getEnabled());
                      
                      UserCourant.ok=p;
                    }
                   
                }
            }
        };
        connectionRequest.setUrl("http://localhost/TGTOf/web/app_dev.php/get");
        NetworkManager.getInstance().addToQueueAndWait(connectionRequest);
    }
  public void getPersonne(String username,String email) {

       ConnectionRequest connectionRequest = new ConnectionRequest() {
            List<Personne> books = new ArrayList<>();
            Personne userkh = new Personne();
            Personne usercurrent = new Personne();

            @Override
            protected void readResponse(InputStream in) throws IOException {
                JSONParser json = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(in, "UTF-8");
                    Map<String, Object> data = json.parseJSON(reader);
                    List<Map<String, Object>> content = (List<Map<String, Object>>) data.get("root");
                    books.clear();
                    for (Map<String, Object> obj : content) {
                        System.out.println((String) obj.get("username"));
                        userkh = new Personne();
                               
                                userkh.setUsername((String) obj.get("username"));
                                 userkh.setUsername_canonical((String) obj.get("username"));
                                userkh.setEmail((String) obj.get("email"));
                                 userkh.setEmail_canonical((String) obj.get("email"));
                             userkh.setPassword((String) obj.get("password"));
                       
                        books.add(userkh);
                        
                    }
                } catch (IOException err) {
                    Log.e(err);
                }
            }

            @Override
            protected void postResponse() {
                for (Personne obj : books) {
                    if (username.equals(obj.getUsername())) {
                        System.out.println(obj.getId());
                         System.out.println(obj.getUsername());
                         Recherche.name=true;
                    }
                    if( email.equals(obj.getEmail()) ){
                        Recherche.mail=true;
                    }
                }
            }
        };
        connectionRequest.setUrl("http://localhost/TGTOf/web/app_dev.php/get");
        NetworkManager.getInstance().addToQueueAndWait(connectionRequest);
    }
 }
   
    
    

