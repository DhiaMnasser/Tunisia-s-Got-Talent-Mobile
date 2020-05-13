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
import com.mycompany.myapp.services.gth.ServiceUpload;

import java.io.IOException;

public class LecteurForm extends Form {

    public LecteurForm(Form previous,int id,String lien) {


        Container ctnBt=new Container(BoxLayout.x());
        setTitle("Lecteur");
        setLayout(new BorderLayout());

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


        getToolbar().addCommandToOverflowMenu("Voter",null,evt -> {
           ServiceUpload.getInstance().vote(id);
           if(ServiceUpload.getInstance().vote(id))
           Dialog.show("Succes","Vote Effectué","Ok",null);
        });

        Boolean jury=false;
        if(jury){
            getToolbar().addCommandToOverflowMenu("Buzzer",null,evt -> {

            });
        }







        getToolbar().addCommandToLeftBar("Retour",null,e->{
            previous.showBack();
        });
    }
}
