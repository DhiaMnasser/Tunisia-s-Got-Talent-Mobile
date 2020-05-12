/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Evenement;
import com.codename1.components.ImageViewer;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import utils.Statics;


/**
 *
 * @author Achraf
 */
public class EvenementService {
    
     public ArrayList<Evenement> events;
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
        String url = Statics.BASE_URL+"api";
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
    public Evenement geteventbyid(Evenement e ,int id ){
        String url = Statics.BASE_URL+"api/show/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener((NetworkEvent evt) -> {
            byte[] data = (byte[]) evt.getMetaData();
            String s = new String(data);
           System.out.println(data);
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
    
}
