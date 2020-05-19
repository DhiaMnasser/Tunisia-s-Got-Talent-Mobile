/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services.ahmed;


import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.ahmed.Categorie;
import com.mycompany.myapp.utils.ahmed.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 *
 * @author Haddad
 */
public class ServiceCategorie {
    public ArrayList<Categorie> Cats;
    public static ServiceCategorie instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    public ServiceCategorie() {
         req = new ConnectionRequest();
    }

    public static ServiceCategorie getInstance() {
        if (instance == null) {
            instance = new ServiceCategorie();
        }
        return instance;
    }
public boolean addCat(Categorie p) {
String url="http://localhost:81/TGTOf/web/app_dev.php"+"/TGTMobile/AddCatDbMobile?namecat="+p.getNom_Categorie()+"&idcat="+p.getId_Categorie();
req.setUrl(url);
req.addResponseListener(new ActionListener<NetworkEvent>(){
    @Override
    public void actionPerformed(NetworkEvent evt) {
       resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
}
 public ArrayList<Categorie> parseCategorie(String jsonText){
 
        try {
            Cats=new ArrayList<>();
  JSONParser j=new JSONParser();
  Map<String,Object> catsListJson;
        
            catsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
             List <Map<String,Object>> list=(List<Map<String,Object>>)catsListJson.get("root");
for (Map<String,Object> obj:list){
    
 Categorie p=new Categorie();
 float id=Float.parseFloat(obj.get("id").toString());
 p.setId_Categorie((int) id);
 p.setNom_Categorie(obj.get("nomc").toString());
 Cats.add(p);

}
        } catch (IOException ex) {
        }
  return Cats;
}
public ArrayList<Categorie> getAllCats(){
String url="http://localhost:81/TGTOf/web/app_dev.php"+"/store/ApiProduit/allC";
req.setUrl(url);
req.setPost(false);
req.addResponseListener(new ActionListener<NetworkEvent>(){
   

    @Override
    public void actionPerformed(NetworkEvent evt) {
    Cats=parseCategorie(new String(req.getResponseData()));
req.removeResponseListener(this);

    }
});
NetworkManager.getInstance().addToQueueAndWait(req);
return Cats;
        }
}