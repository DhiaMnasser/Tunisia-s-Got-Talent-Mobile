package com.mycompany.myapp.gui.gth;

import com.codename1.components.MediaPlayer;
import com.codename1.io.Log;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.gui.mohamed.NewsfeedForm;
import com.mycompany.myapp.services.gth.ServiceUpload;
import com.mycompany.myapp.services.mohamed.UserCourant;

import java.io.IOException;

public class LecteurForm extends Form {

    public LecteurForm(Resources res,int id,String lien) {


        Container ctnBt=new Container(BoxLayout.x());
        setTitle("Lecteur");
        setLayout(new BorderLayout());
        //setUIID("SignIn");

        setToolbar(new Toolbar());
        Style s = UIManager.getInstance().getComponentStyle("Title");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_VIDEO_LIBRARY, s);
        if(lien != null) {
            String file = lien;
            try {
                System.out.println(lien);
                Media video = MediaManager.createMedia(file, true);
                removeAll();
                MediaPlayer m=new MediaPlayer(video);
                m.setAutoplay(true);
                m.showControls();
                add(BorderLayout.CENTER, m);
                revalidate();
            } catch(IOException err) {
                Log.e(err);
            }
        }

        add(BorderLayout.NORTH, new Label(res.getImage("tgt300.png"), "LogoLabel"));
        getToolbar().addCommandToOverflowMenu("Voter",null,evt -> {
           ServiceUpload.getInstance().vote(id);
           if(ServiceUpload.getInstance().vote(id))
           Dialog.show("Succes","Vote Effectué","Ok",null);
        });

        
        if(UserCourant.ok.getJury()==1){
            getToolbar().addCommandToOverflowMenu("Buzzer",null,evt -> {

            });
        }







        getToolbar().addCommandToLeftBar("Retour",null,e->{
            new NewsfeedForm(res).show();
        });
    }
}
