package com.mycompany.myapp.gui.gth;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.gth.Upload;
import com.mycompany.myapp.services.gth.ServiceUpload;

import java.util.ArrayList;
import java.util.List;

public class ListPubForm extends Form {
    Form current;

    public ListPubForm(Resources res) {
        this.current = this;

        this.setLayout(BoxLayout.y());

        setTitle("La Liste des Prestations");
        setUIID("SignIn");

        ArrayList<Upload> uploads=ServiceUpload.getInstance().getAllUploads();
        for(Upload u:uploads){
            Button btn=new Button(u.getTitre());
            Label lb= new Label("Auteur:"+u.getAuteur()+"  Catégorie:"+u.getCategorie());
            Container cnt=new Container(BoxLayout.y());
            btn.addActionListener(evt -> {
                new LecteurForm(res,u.getId(),u.getSource()).show();
            });
            cnt.addAll(btn,lb);
            add(cnt);
        }


        getToolbar().addCommandToSideMenu("Liste des Prestations",null,ev->{this.show();});
        getToolbar().addCommandToSideMenu("Ajouter une Prestation",null,ev->{
            new AddPubForm(res).show();
        });
    }
}
