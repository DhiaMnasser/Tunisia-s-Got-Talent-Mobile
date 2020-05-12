/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Region;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import utils.Statics;




/**
 *
 * @author Achraf
 */
public class RegionService {
    public static RegionService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    public ArrayList<Region> regions;
   public RegionService() {
         req = new ConnectionRequest();
    }

    public static RegionService getInstance() {
        if (instance == null) {
            instance = new RegionService();
        }
        return instance;
    }
    public ArrayList<Region> getAllregions(){
        String url = Statics.BASE_URL+"api/region";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                regions = parseregions(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return regions;
    }
    public ArrayList<Region> parseregions(String jsonText){
         
        try {
            regions=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> eventsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
          
            List<Map<String,Object>> list = (List<Map<String,Object>>)eventsListJson.get("root");
            for(Map<String,Object> obj : list){
                Region t = new Region();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                t.setNb_villes(((int)Float.parseFloat(obj.get("nbVilles").toString())));
                t.setNom(obj.get("nom").toString());
                
                 
                 
               
                 
                 if (obj.get("evenement") != null )
                 {
                     Map<String, Object> r = (Map<String, Object>) obj.get("evenement");
                  t.setEvent_id(((int)Float.parseFloat(r.get("id").toString())));
                 
                 }
                
                
                regions.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return regions;
    }
    public Region getregionbyid(Region e ,int id ){
        String url = Statics.BASE_URL+"api/region/show/"+id;
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
}
