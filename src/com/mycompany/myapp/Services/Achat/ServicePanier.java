/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.Services.Achat;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.Entities.Achat.Panier;
import com.mycompany.myapp.Utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Klaizer
 */
public class ServicePanier {

    public Panier panier;

    public static ServicePanier instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServicePanier() {
        req = new ConnectionRequest();
    }

    public static ServicePanier getInstance() {
        if (instance == null) {
            instance = new ServicePanier();
        }
        return instance;
    }

    public Panier parsePanier(String jsonText) {
        Panier pan = new Panier();
        try {
            System.out.println("parsePanier :   :");

            JSONParser j = new JSONParser();
            Map<String, Object> panierListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            System.out.println(panierListJson);
            Map<String, Object> user = (Map) panierListJson.get("userId");
            float id = Float.parseFloat(panierListJson.get("id").toString());
            pan.setId((int) id);
            pan.setUser_id((int) Double.parseDouble(user.get("id").toString()));
            pan.setEtat((boolean) Boolean.parseBoolean(panierListJson.get("etat").toString()));
            pan.setPrixTotal(((Double) (panierListJson.get("prixtotal"))));

        } catch (IOException ex) {

        }
        return pan;
    }

    public Panier getPanierByUser() {
//        TODO
        String url = Statics.BASE_URL + "/Apipanier/show?user=" + Statics.CurrentUser.getId();
//        String url = Statics.BASE_URL + "/Apipanier/show?user=" + UserCourant.ok.getId();

//String url = "";
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                panier = parsePanier(new String(req.getResponseData()));
                
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return panier;
    }

}
