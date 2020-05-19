/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services.dhia;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.ahmed.Produit;
import com.mycompany.myapp.entities.dhia.LigneCommande;
import com.mycompany.myapp.services.mohamed.UserCourant;
import com.mycompany.myapp.utils.ahmed.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Klaizer
 */
public class LigneCommandeService {

    public ArrayList<LigneCommande> lignesCommandes;
    private LigneCommande OneligneCommande;

    public static LigneCommandeService instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private LigneCommandeService() {
        req = new ConnectionRequest();
    }

    public static LigneCommandeService getInstance() {
        if (instance == null) {
            instance = new LigneCommandeService();
        }
        return instance;
    }

//    TODO
    public boolean addLigneCommande(Produit p) {

//        TODO replace url's
//        String url = Statics.BASE_URL+"/Apilignecommande/new?idProduit=" + p.getId_Produit() + "&user="+Statics.CurrentUser.getId();
       String url = Statics.BASE_URL+"/Apilignecommande/new?idProduit=" + p.getId_Produit() + "&user="+UserCourant.ok.getId();

        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
        public boolean deleteLigneCommande(LigneCommande lc) {


        String url = Statics.BASE_URL+"/Apilignecommande/"+lc.getId()+"/supprimer";

        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
        
        public boolean editLigneCommande(LigneCommande lc) {


        String url = Statics.BASE_URL+"/Apilignecommande/"+lc.getId()+"/edit?qte="+lc.getQuantite();

        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<LigneCommande> parseLigneCommandes(String jsonText) {
        try {
            System.out.println("parseLigneCommandes :   :");
            lignesCommandes = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> lignesCommandesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            System.out.println("lignesCommandesListJson :  " + lignesCommandesListJson);
//            System.out.println("CharArrayReader :  "+(new CharArrayReader(jsonText.toCharArray())).toString());

            List<Map<String, Object>> list = (List<Map<String, Object>>) lignesCommandesListJson.get("root");
//            System.out.println("list :  " + lignesCommandesListJson);

            for (Map<String, Object> obj : list) {
                LigneCommande lg = new LigneCommande();
                float id = Float.parseFloat(obj.get("id").toString());
                lg.setId((int) id);
                Map<String, Object> produit = (Map) obj.get("idproduit");
                lg.setIdproduit((int) Float.parseFloat(produit.get("id").toString()));
                Map<String, Object> panier = (Map) obj.get("idPanier");
                lg.setIdPanier((int) Float.parseFloat(panier.get("id").toString()));
                lg.setNomProduit((String) obj.get("nomproduit"));
                lg.setQuantite((int) Float.parseFloat(obj.get("quantite").toString()));

                lignesCommandes.add(lg);
            }

        } catch (IOException ex) {

        }
        return lignesCommandes;
    }

    public ArrayList<LigneCommande> getLigneCommandesByCurrentPanier() {
//        TODO
//        String url = Statics.BASE_URL+"/Apilignecommande/index?user="+Statics.CurrentUser.getId();
           String url = Statics.BASE_URL+"/Apilignecommande/index?user="+UserCourant.ok.getId();
//String url = "";
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                lignesCommandes = parseLigneCommandes(new String(req.getResponseData()));
                System.out.println("get Ligne Commande by panier " + lignesCommandes);
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return lignesCommandes;
    }

     public ArrayList<LigneCommande> getLigneCommandesByPanier(int idPanier) {

        String url = Statics.BASE_URL+"/Apilignecommande/getLigneCommandesByPanier?panier="+idPanier;

        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                lignesCommandes = parseLigneCommandes(new String(req.getResponseData()));
                System.out.println("get Ligne Commande by panier " + lignesCommandes);
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return lignesCommandes;
    }
    
    public LigneCommande parseOneLigneCommande(String jsonText) {
        LigneCommande lg = new LigneCommande();
        try {

            System.out.println("parseOneLigneCommande :   :");
            JSONParser j = new JSONParser();
            Map<String, Object> lignesCommandesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            float id = Float.parseFloat(lignesCommandesListJson.get("id").toString());
            lg.setId((int) id);
            Map<String, Object> produit = (Map) lignesCommandesListJson.get("idproduit");
            lg.setIdproduit((int) Float.parseFloat(produit.get("id").toString()));
            Map<String, Object> panier = (Map) lignesCommandesListJson.get("idPanier");
            lg.setIdPanier((int) Float.parseFloat(panier.get("id").toString()));
            lg.setNomProduit((String) lignesCommandesListJson.get("nomproduit"));
            lg.setQuantite((int) Float.parseFloat(lignesCommandesListJson.get("quantite").toString()));

            
//            }

        } catch (IOException ex) {
            System.out.println(ex);

        }
        return lg;
    }

    public LigneCommande getLigneCommandesById(LigneCommande lc) {
//        TODO replace url and lc in parameters
//        String url = Statics.BASE_URL+"/Apilignecommande/255/show";
        String url = Statics.BASE_URL+"/Apilignecommande/"+lc.getId()+"/show";
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                OneligneCommande = parseOneLigneCommande(new String(req.getResponseData()));
//                System.out.println("get Ligne Commande bt panier " + lignesCommandes);
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return OneligneCommande;
    }
}
