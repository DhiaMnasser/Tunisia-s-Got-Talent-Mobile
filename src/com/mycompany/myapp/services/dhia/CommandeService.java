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
import com.mycompany.myapp.entities.dhia.Commande;
import com.mycompany.myapp.utils.ahmed.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Klaizer
 */
public class CommandeService {

    public ArrayList<Commande> Commandes;
    private Commande oneCommande;

    public static CommandeService instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private CommandeService() {
        req = new ConnectionRequest();
    }

    public static CommandeService getInstance() {
        if (instance == null) {
            instance = new CommandeService();
        }
        return instance;
    }

    public Commande addCommande(Commande cmd) {

//        TODO
//        ServicePanier span = ServicePanier.getInstance();
//        Panier pan = span.getPanierByUser();
//        String url = Statics.BASE_URL + "/Apicommande/new?user=" + UserCourant.ok.getId() + "&adress=" + cmd.getAddress() + "&tel=" + cmd.getTel();
        String url = Statics.BASE_URL + "/Apicommande/new?user=" + Statics.CurrentUser.getId() + "&adress=" + cmd.getAddress() + "&tel=" + cmd.getTel();
//          String url = "";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                if (req.getResponseCode() == 200) { //Code HTTP 200 OK
                    oneCommande = parseOneCommande(new String(req.getResponseData()));

                    req.removeResponseListener(this);

                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return oneCommande;
    }

    public ArrayList<Commande> parseCommandes(String jsonText) {
        try {
            Commandes = new ArrayList<>();

            JSONParser j = new JSONParser();
            Map<String, Object> CommandesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            System.out.println("list commandesjson" + CommandesListJson);

            List<Map<String, Object>> list = (List<Map<String, Object>>) CommandesListJson.get("root");
            System.out.println("list commandes " + list);
            for (Map<String, Object> obj : list) {
                Commande cmd = new Commande();
                float id = Float.parseFloat(obj.get("id").toString());
                cmd.setId((int) id);
                Map<String, Object> user = (Map) obj.get("userId");

                cmd.setUser_id((int) Float.parseFloat(user.get("id").toString()));

                Map<String, Object> panier = (Map) obj.get("idPanier");

                cmd.setIdPanier((int) Float.parseFloat(panier.get("id").toString()));
                cmd.setAddress((String) obj.get("address"));
                cmd.setTel((String) obj.get("tel"));
                cmd.setEtat((Boolean) Boolean.parseBoolean(obj.get("etat").toString()));

//                
                Map<String, Object> date = (Map<String, Object>) obj.get("date");
                float time = Float.parseFloat(date.get("timestamp").toString());

                cmd.setDate(new Date((long) time * 1000));
                Commandes.add(cmd);
            }

        } catch (IOException ex) {
        }

        return Commandes;
    }

    public ArrayList<Commande> getCommandesByUser() {
//        TODO
        String url = Statics.BASE_URL + "/Apicommande/index?user=" + Statics.CurrentUser.getId();
//        String url = Statics.BASE_URL + "/Apicommande/index?user=" + UserCourant.ok.getId();

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Commandes = parseCommandes(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Commandes;
    }

    public Commande parseOneCommande(String jsonText) {
        Commande cmd = new Commande();
        try {

            System.out.println("parseOneCommande :   :");

            JSONParser j = new JSONParser();
            Map<String, Object> CommandesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            System.out.println("one commande" + CommandesListJson);
            float id = Float.parseFloat(CommandesListJson.get("id").toString());
            cmd.setId((int) id);
            Map<String, Object> user = (Map) CommandesListJson.get("userId");

            cmd.setUser_id((int) Float.parseFloat(user.get("id").toString()));

            Map<String, Object> panier = (Map) CommandesListJson.get("idPanier");

            cmd.setIdPanier((int) Float.parseFloat(panier.get("id").toString()));
            cmd.setAddress((String) CommandesListJson.get("address"));
            cmd.setTel((String) CommandesListJson.get("tel"));
            cmd.setEtat((Boolean) Boolean.parseBoolean(CommandesListJson.get("etat").toString()));

//                
            Map<String, Object> date = (Map<String, Object>) CommandesListJson.get("date");
            float time = Float.parseFloat(date.get("timestamp").toString());
//              DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            cmd.setDate(new Date((long) time * 1000));

//            }
        } catch (IOException ex) {
            System.out.println(ex);

        }
        return cmd;
    }

    public Commande getCommandesById(Commande cmd) {
//        TODO replace url and lc in parameters
//        String url = Statics.BASE_URL + "/Apicommande/410/show";
        String url = Statics.BASE_URL+"/Apicommande/"+cmd.getId()+"/show";
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                oneCommande = parseOneCommande(new String(req.getResponseData()));
//                System.out.println("get Ligne Commande bt panier " + lignesCommandes);
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return oneCommande;
    }
}
