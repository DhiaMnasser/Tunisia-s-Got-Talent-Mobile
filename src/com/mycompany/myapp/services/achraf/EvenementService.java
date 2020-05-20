/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services.achraf;


import com.codename1.components.ImageViewer;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.achraf.Evenement;
import com.mycompany.myapp.entities.mohamed.BCrypt;
import com.mycompany.myapp.entities.mohamed.Personne;
import com.mycompany.myapp.services.mohamed.UserCourant;
import com.mycompany.myapp.utils.achraf.Recherche;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Achraf
 */
public class EvenementService {
    
     public ArrayList<Evenement> events;
     public ArrayList<Personne> users;
    private ImageViewer image ;
    private Resources theme ;
    public static EvenementService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public EvenementService() {
         req = new ConnectionRequest();
    }

    public static EvenementService getInstance() {
        if (instance == null) {
            instance = new EvenementService();
        }
        return instance;
    }
    public ArrayList<Evenement> getAllevents(){
        String url = "http://localhost:81/TGTOf/web/app_dev.php/test/"+"api";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                events = parseEvents(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return events;
    }
    public ArrayList<Personne> getusers(){
        String url = "http://localhost:81/TGTOf/web/app_dev.php/test/"+"api/users";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                users = parseUsers(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return users;
    }
    public ArrayList<Personne> parseUsers(String jsonText){
         
        try {
            users=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> eventsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
          
            List<Map<String,Object>> list = (List<Map<String,Object>>)eventsListJson.get("root");
            for(Map<String,Object> obj : list){
                Personne t = new Personne();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setEvent_id((int)id);
                
                t.setUsername(obj.get("username").toString());
                
                 
                 
               
                 
                 
                
                
                users.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return users;
    }
    public Evenement geteventbyid(Evenement e ,int id ){
        String url = "http://localhost:81/TGTOf/web/app_dev.php/test/"+"api/show/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener((NetworkEvent evt) -> {
            byte[] data = (byte[]) evt.getMetaData();
            String s = new String(data);
           
          });
       NetworkManager.getInstance().addToQueue(req);
       return e;
    }
    public ArrayList<Evenement> parseEvents(String jsonText){
         
        try {
            events=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> eventsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
          
            List<Map<String,Object>> list = (List<Map<String,Object>>)eventsListJson.get("root");
            for(Map<String,Object> obj : list){
                Evenement t = new Evenement();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                t.setMaxParticipants(((int)Float.parseFloat(obj.get("maxParticipants").toString())));
                t.setEtat(((int)Float.parseFloat(obj.get("etat").toString())));
                t.setNomevent(obj.get("nomevent").toString());
                t.setDuree(obj.get("duree").toString());
                t.setImage(obj.get("image").toString());
                 t.setGagnant(obj.get("gagnant").toString());
                 // Image
                /* if (obj.get("image").toString() != null)
                 {String urlImage = obj.get("image").toString();
                System.out.println(urlImage);
                 EncodedImage enco = EncodedImage.createFromImage(theme.getImage("Logo.png"), true);
                 
                 URLImage imgser = URLImage.createToStorage(enco, "" + "Logo.png", urlImage);
                 ImageViewer img = new ImageViewer(imgser);
                 }*/
                 //
                 
                if (obj.get("dateD") !=null)
                {
                    Map<String, Object> date = (Map<String, Object>) obj.get("dateD");
                    float time = Float.parseFloat(date.get("timestamp").toString());
            
                t.setDate_d(new Date((long) time * 1000));
                }
                if (obj.get("dateF") !=null)
                {
                    Map<String, Object> date = (Map<String, Object>) obj.get("dateF");
                    float time = Float.parseFloat(date.get("timestamp").toString());
            
                t.setDate_f(new Date((long) time * 1000));
                }
                 
                 if (obj.get("region") != null )
                 {
                     Map<String, Object> r = (Map<String, Object>) obj.get("region");
                  t.setRegion_id(((int)Float.parseFloat(r.get("id").toString())));
                 
                 }
                
                
                events.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return events;
    }
    public ArrayList<Evenement> getthevent(String c) {
                

          String url ="http://localhost:81/TGTOf/web/app_dev.php/test/"+"api/search/"+c;
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                events = parseEvents(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return events;
    }
    ///////////
    //////////////////////
    /////////////////////////// USER !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    
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
                        
                        
                        userkh = new Personne();
                               userkh.setId(((Double)obj.get("id")).intValue());
                                userkh.setUsername((String) obj.get("username"));
                                 
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
                     
                      Personne p =new Personne(obj.getUsername(),obj.getPassword());
                      p.setPassword(obj.getPassword());
                      p.setId(obj.getId());
                      System.out.println(obj.getId());
                      
                      
                      UserCourant.ok=p;
                      
                    }
                   
                }
            }
        };
        connectionRequest.setUrl("http://localhost:81/TGTOf/web/app_dev.php/test/"+"api/usersall");
        NetworkManager.getInstance().addToQueueAndWait(connectionRequest);
    }
    public static boolean checkPassword(String password_plaintext, String stored_hash) {
        boolean password_verified = false;

        if (null == stored_hash || !stored_hash.startsWith("$2y$")) {
            throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");
        }

        password_verified = BCrypt.checkpw(password_plaintext, stored_hash);

        return (password_verified);
    }
     public void modPersonne(Personne p){
     ConnectionRequest con = new ConnectionRequest();
     con.setPost(false);
     
     con.setFailSilently(true);
     Evenement test = new Evenement() ;
     //if(con.getResponseCode() == 200 || con.getResponseCode() == 202) {
    // success code here
    String Url = "http://localhost:81/TGTOf/web/test/"+"api/modif"+"?id="+p.getEvent_id()+"&iduser="+p.getId();
        con.setUrl(Url);

        

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            
        });
        NetworkManager.getInstance().addToQueueAndWait(con);

     
         
         
     
        
        
    }
    
}
