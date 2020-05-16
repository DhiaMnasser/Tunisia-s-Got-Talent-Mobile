/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.Services.Stock;

import com.mycompany.myapp.Entities.Stock.Produit;
import com.mycompany.myapp.Utils.Statics;
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
/**
 *
 * @author Haddad
 */
public class ServiceProduct {
    public ArrayList<Produit> products;
    public static ServiceProduct instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    public ServiceProduct() {
         req = new ConnectionRequest();
    }

    public static ServiceProduct getInstance() {
        if (instance == null) {
            instance = new ServiceProduct();
        }
        return instance;
    }
public boolean addProduct(Produit p) {
    String url=Statics.BASE_URL+"/TGTMobile/AddProductDbMobile?nameproduct="+p.getNom_Produit()+"&qteproduct="+p.getQuantite_Totale()        
        +"&size="+p.getTaille_Produit()+"&prixproduct="+p.getPrix_Produit()+"&etatproduct="+p.getEtat_Produit()+
        "&id_admin="+p.getId_Categorie();
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
 public ArrayList<Produit> parseProducts(String jsonText){
 
        try {
            products=new ArrayList<>();
            JSONParser j=new JSONParser();
            Map<String,Object> productsListJson;
        
             productsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
             List <Map<String,Object>> list=(List<Map<String,Object>>)productsListJson.get("root");
             for (Map<String,Object> obj:list){
    
                Produit p=new Produit();
                float id=Float.parseFloat(obj.get("id").toString());
                //int idc=Integer.parseInt(obj.get("cat").toString());
                Map<String, Object> categorie = (Map) obj.get("cat");
               // Map<String, Object> cat = (Map) categorie.get("id");
                p.setId_Categorie((int) Float.parseFloat(categorie.get("id").toString()));
                p.setId_Produit((int) id);
               // p.setId_Categorie(idc);
                p.setNom_Produit(obj.get("nom").toString());
                p.setTaille_Produit(obj.get("size").toString());
                p.setEtat_Produit(obj.get("etat").toString());
                //p.setId_Categorie(((int)Integer.parseInt(obj.get("cat").toString())));
                p.setPrix_Produit(((int)Float.parseFloat(obj.get("prix").toString())));
                p.setQuantite_Totale(((int)Float.parseFloat(obj.get("qte").toString())));
                p.setUrl(obj.get("url").toString());
                products.add(p);

}
        } catch (IOException ex) {
        }
  return products;
}
   public ArrayList<Produit> getAllProducts(){
   String url=Statics.BASE_URL+"store/ApiProduit/all";
   req.setUrl(url);
   req.setPost(false);
   req.addResponseListener(new ActionListener<NetworkEvent>(){
   

    @Override
    public void actionPerformed(NetworkEvent evt) {
    products=parseProducts(new String(req.getResponseData()));
    req.removeResponseListener(this);

    }
});
NetworkManager.getInstance().addToQueueAndWait(req);
return products;
        }
   public ArrayList<Produit> getCProducts(int i){
   String url=Statics.BASE_URL+"store/ApiProduit/cat/"+i;
   req.setUrl(url);
   req.setPost(false);
   req.addResponseListener(new ActionListener<NetworkEvent>(){
   

    @Override
    public void actionPerformed(NetworkEvent evt) {
    products=parseProducts(new String(req.getResponseData()));
    req.removeResponseListener(this);

    }
});
NetworkManager.getInstance().addToQueueAndWait(req);
return products;
        }
   
   
   public ArrayList<Produit> getRProducts(String s){
   String url=Statics.BASE_URL+"store/ApiProduit/rech/"+s;
   req.setUrl(url);
   req.setPost(false);
   req.addResponseListener(new ActionListener<NetworkEvent>(){
   

    @Override
    public void actionPerformed(NetworkEvent evt) {
    products=parseProducts(new String(req.getResponseData()));
    req.removeResponseListener(this);

    }
});
NetworkManager.getInstance().addToQueueAndWait(req);
return products;
        }
   
      public ArrayList<Produit> getOProducts(int i){
   String url=Statics.BASE_URL+"store/ApiProduit/prod/"+i;
   req.setUrl(url);
   req.setPost(false);
   req.addResponseListener(new ActionListener<NetworkEvent>(){
   

    @Override
    public void actionPerformed(NetworkEvent evt) {
    products=parseProducts(new String(req.getResponseData()));
    req.removeResponseListener(this);

    }
});
NetworkManager.getInstance().addToQueueAndWait(req);
return products;
        }
}