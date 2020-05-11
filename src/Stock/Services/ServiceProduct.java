/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stock.Services;

import Stock.Entities.Produit;
import Stock.Utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.*;
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
 p.setId_Produit((int) id);
 p.setNom_Produit(obj.get("Name_Product").toString());
p.setTaille_Produit(obj.get("Taille_Produit").toString());
p.setEtat_Produit(obj.get("Etat_Produit").toString());
p.setId_Categorie(((int)Float.parseFloat(obj.get("Id_Categorie").toString())));
p.setPrix_Produit(((int)Float.parseFloat(obj.get("Price_Product").toString())));
p.setQuantite_Totale(((int)Float.parseFloat(obj.get("Quantity_Tota").toString())));
p.setUrl(obj.get("URL").toString());
products.add(p);

}
        } catch (IOException ex) {
        }
  return products;
}
public ArrayList<Produit> getAllProducts(){
String url=Statics.BASE_URL+"/TGTMobile/ListeProductsall";
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