/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services.gth;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.gth.Vote;
import com.mycompany.myapp.utils.gth.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author gth
 */
public class ServiceVote {
    public ArrayList<Vote> votes;
    public static ServiceVote instance;
    public boolean resultOk;
    private ConnectionRequest req;

    private ServiceVote(){
        req=new ConnectionRequest();
    }

    public static ServiceVote getInstance(){
        if(instance == null){
            instance=new ServiceVote();
        }
        return instance;
    }

    

    public ArrayList<Vote> parseVote(String jsonText){
        votes=new ArrayList<>();
        JSONParser j = new JSONParser();
        Map<String,Object> uploadListJson= null;

        try {
            uploadListJson=j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        } catch (IOException e) {

        }

        List<Map<String,Object>>  list= (List<Map<String,Object>>)uploadListJson.get("root");
        for(Map<String,Object>obj:list){
            Vote v=new Vote();
            float id= Float.parseFloat(obj.get("id").toString());
            v.setId(((int)Float.parseFloat(obj.get("id").toString())));
            v.setUserId(((int)Float.parseFloat(obj.get("user").toString())));
            v.setId(((int)Float.parseFloat(obj.get("publication").toString())));
            votes.add(v);
        }
        return votes;
    }
    
    
    public boolean dejaVote(int id){
        String url= Statics.BASE_URL+"/vote/api/verifVot/"+id;
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
    
    public boolean dejaVote1(int id){
        ArrayList<Vote> votes=ServiceVote.getInstance().getAllVotes();
        boolean dv=false;
        for(Vote u:votes){
            if(u.getUserId()==id){
                dv=true;
            }
        }
        
        return dv;
    }
    
    public ArrayList<Vote> getAllVotes(){
        String url = Statics.BASE_URL+"/vote/api/list";

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                votes=parseVote(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

        return votes;
    }
}
