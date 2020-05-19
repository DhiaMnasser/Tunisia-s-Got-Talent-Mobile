/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services.hassen;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
//import com.codename1.io.Properties;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.hassen.Avis;
import com.mycompany.myapp.utils.hassen.User;
import com.mycompany.myapp.utils.hassen.UserCourant;
import com.mycompany.myapp.utils.hassen.Statics1;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
//import java.sql.SQLException;

import java.util.List;

//import com.codename1.messaging.Message;
import com.codename1.ui.Dialog;

import java.util.ArrayList;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;
import static jdk.nashorn.internal.runtime.PropertyMap.diff;

/**
 *
 * @author frauDEee
 */
public class AvisServices {
    
    private Avis oneAvis;
public ArrayList<Avis> AvisL;
    public static AvisServices instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public AvisServices() {
        req = new ConnectionRequest();
    }

    public static AvisServices getInstance() {
        if (instance == null) {
            instance = new AvisServices();
        }
        return instance;
    }

    public Avis addAvis(Avis av) {

//        TODO
//        ServicePanier span = ServicePanier.getInstance();
//        Panier pan = span.getPanierByUser();
//Calendar calendar = Calendar.getInstance();       
//java.util.Date currentDate = calendar.getTime();
       // java.sql.Date date = new java.sql.Date(currentDate.getTime());
       // av.setDate(date);
        String url = Statics1.BASE_URL + "/ApiAvis/new?user_id="+UserCourant.ok.getId()+"&texte="+av.getTexte();
//          String url = "";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                if(req.getResponseCode() == 200){ //Code HTTP 200 OK
                oneAvis = parseOneAvis(new String(req.getResponseData()));

                req.removeResponseListener(this);
                sendmail();
                Dialog.show("Review submitted.", "Thank you for you review", "OK", "Cancel");
                }
            }

            

           
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        return oneAvis;
    }
    
    
    public ArrayList<Avis> parseAvis(String jsonText) {
        try {
            AvisL = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> AvisListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) AvisListJson.get("root");
            
            for (Map<String, Object> obj : list) {
                Avis av = new Avis();
              float id = Float.parseFloat(obj.get("id").toString());
              av.setId((int) id);
                
                av.setTexte((String) obj.get("texte"));
                av.setUser_id(1);
             //   av.setDate((Date) );
             //   Map<String, Object> User = (Map) obj.get("user_id");

             //   cmd.setUser_id((int) Float.parseFloat(user.get("id").toString()));

              //  Map<String, Object> panier = (Map) obj.get("idPanier");

           //     cmd.setIdPanier((int) Float.parseFloat(panier.get("id").toString()));
            //    av.setAddress((String) obj.get("texte"));
            //    cmd.setTel((String) obj.get("tel"));
             //   cmd.setEtat((Boolean) Boolean.parseBoolean(obj.get("etat").toString()));

//                
           //     Map<String, Object> date = (Map<String, Object>) obj.get("date");
            //    float time = Float.parseFloat(date.get("timestamp").toString());
//              DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            //    cmd.setDate(new Date((long) time * 1000));

                AvisL.add(av);
            }

        } catch (IOException ex) {

        }
        return AvisL;
    }
    
     public ArrayList<Avis> getAllAvis()
     {
         String url = Statics1.BASE_URL + "/ApiAvis/index";
//          String url = "";
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                if(req.getResponseCode() == 200){ //Code HTTP 200 OK
                AvisL = parseAvis(new String(req.getResponseData()));

                req.removeResponseListener(this);
                
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return AvisL;
     }
    
    public Avis parseOneAvis(String jsonText) {
        Avis av = new Avis();
        try {

            System.out.println("parseOneAvis :   :");
            
            JSONParser j = new JSONParser();
            Map<String, Object> AvisListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            System.out.println(AvisListJson);
            float id = Float.parseFloat(AvisListJson.get("id").toString());
            av.setId((int) id);
           // Map<String, Object> User = (Map) AvisListJson.get("userId");

//                av.setUser_id((int) Float.parseFloat(user.get("id").toString()));

               // Map<String, Object> panier = (Map) CommandesListJson.get("idPanier");

              //  cmd.setIdPanier((int) Float.parseFloat(panier.get("id").toString()));
                av.setTexte((String) AvisListJson.get("texte"));
                av.setUser_id(UserCourant.ok.getId());
               // cmd.setTel((String) CommandesListJson.get("tel"));
               // cmd.setEtat((Boolean) Boolean.parseBoolean(CommandesListJson.get("etat").toString()));

//                
                Map<String, Object> date = (Map<String, Object>) AvisListJson.get("date");
                float time = Float.parseFloat(date.get("timestamp").toString());
//              DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                av.setDate(new Date((long) time * 1000));

            
//            }

        } catch (IOException ex) {
            System.out.println(ex);

        }
        return av;
    }
    
   public void sendmail() {

      //  String diff = "hassen.benabid@esprit.tn";
        final String username = "tunisiangt@gmail.com";
        final String password = "Gamer4ever";

        Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("tunisiangt@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("hassen.benabid@esprit.tn")
            );
            message.setSubject("Review");
            message.setText("We received your review! Thank you for helping our community.");

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
   }
}
