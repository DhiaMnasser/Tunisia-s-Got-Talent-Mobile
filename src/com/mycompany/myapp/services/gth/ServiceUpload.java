package com.mycompany.myapp.services.gth;

import com.codename1.io.*;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.gth.Upload;
import com.mycompany.myapp.gui.gth.LecteurForm;
import com.mycompany.myapp.services.mohamed.UserCourant;
import com.mycompany.myapp.utils.gth.Statics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServiceUpload {
    public ArrayList<Upload> uploads;
    public static ServiceUpload instance;
    public boolean resultOk;
    private ConnectionRequest req;

    private ServiceUpload(){
        req=new ConnectionRequest();
    }

    public static ServiceUpload getInstance(){
        if(instance == null){
            instance=new ServiceUpload();
        }
        return instance;
    }

    public ArrayList<Upload> parseUpload(String jsonText){
        uploads=new ArrayList<>();
        JSONParser j = new JSONParser();
        Map<String,Object> uploadListJson= null;

        try {
            uploadListJson=j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        } catch (IOException e) {

        }

        List<Map<String,Object>>  list= (List<Map<String,Object>>)uploadListJson.get("root");
        for(Map<String,Object>obj:list){
            Upload u=new Upload();
            float id= Float.parseFloat(obj.get("id").toString());
            u.setId(((int)Float.parseFloat(obj.get("id").toString())));
            u.setTitre(obj.get("titre").toString());
            u.setDescription(obj.get("description").toString());
            u.setAuteur(obj.get("author").toString());
            u.setSource(obj.get("videoFile").toString());
            u.setCategorie(obj.get("categorie").toString());
            u.setVote(((int)Float.parseFloat(obj.get("nbrVote").toString())));
            u.setEvenement("");
            uploads.add(u);
        }
        return uploads;
    }
    
    

    public boolean vote(int id){
        String url= Statics.BASE_URL+"/publication/api/vote/"+id+"/"+UserCourant.ok.getId();
        ConnectionRequest req= new ConnectionRequest(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk=req.getResposeCode()==200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);


        return resultOk;
    }
    
    public boolean addUpload(Upload u){
        String url= Statics.BASE_URL+"/publication/api/new?auteur="+u.getAuteur()+"&desc="+u.getDescription()+"&titre="+u.getTitre()+"&lien="+u.getSource()+"&cat="+u.getCategorie();
        ConnectionRequest req= new ConnectionRequest(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk=req.getResposeCode()==200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);


        return resultOk;
    }

    public ArrayList<Upload> getAllUploads(){
        String url = Statics.BASE_URL+"/publication/api/list";

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                uploads=parseUpload(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

        return uploads;
    }
    
    public boolean dejaUpload(){
        boolean du=false;
        
        ArrayList<Upload> uploads=ServiceUpload.getInstance().getAllUploads();
        for(Upload u:uploads){
            if(u.getAuteur().equals(UserCourant.ok.getUsername())){
                du=true;
            }
        }
        return du;
    }


}
