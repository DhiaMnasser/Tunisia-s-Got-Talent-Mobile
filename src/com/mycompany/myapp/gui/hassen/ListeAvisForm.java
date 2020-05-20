/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.hassen;

//import Chat.ChatController;
import com.codename1.components.SpanLabel;
import com.codename1.l10n.SimpleDateFormat;
//import static com.codename1.rad.schemas.Thing.url;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.hassen.Avis;
import com.mycompany.myapp.MyApplication;
import com.mycompany.myapp.services.hassen.AvisServices;
import com.mycompany.myapp.gui.mohamed.BaseForm;
import java.io.IOException;

import java.util.ArrayList;



/**
 *
 * @author frauDEee
 */
public class ListeAvisForm extends BaseForm {

    Form current;
    ArrayList<Avis> AvisListe = new ArrayList<>();
     private Resources theme;

    public ListeAvisForm(Resources res) {
        super(BoxLayout.y());
        current = this;
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        setTitle("Liste des avis");
        current.setLayout(BoxLayout.y());
        getTitleArea().setUIID("Container");
        getContentPane().setScrollVisible(false);
        setUIID("SignIn");
        super.addSideMenu(res);
        
        
        //this.add (BorderLayout.NORTH, new Label ("See what people say about our app ! "));
        /*SpanLabel sp = new SpanLabel();
        sp.setText(AvisServices.getInstance().getAllAvis().toString());
        this.add(BorderLayout.CENTER, sp);
        
       
       Image icon = FontImage.createMaterial(FontImage.MATERIAL_FEEDBACK, "TitleCommand", 5).toImage();
       Label tittleButton = new Label("Reviews", icon, "Label");
       current.getToolbar().setTitleComponent(tittleButton);
       current.getToolbar().setTitleCentered(true);*/
        
        AvisServices as = new AvisServices();
      
        ArrayList<Avis> ass = new ArrayList<>();
        ass = as.getAllAvis();
                for (Avis e : ass) {
                    Container c0 = new Container(BoxLayout.y());
                     Container c4 = new Container(BoxLayout.x());
        final FontImage nom = FontImage.createMaterial(FontImage.MATERIAL_ROOM, "Label", 6);
        c4.add(nom);
        c4.add(new Label("Review : "));
        c4.add(new Label(e.getTexte()));
        
        Container c3 = new Container(BoxLayout.x());
        final FontImage time2 = FontImage.createMaterial(FontImage.MATERIAL_DATE_RANGE, "Label", 6);
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
        c3.add(time2);
        
        c3.add(new Label("Date : "));
        c3.add(new Label(formatter2.format(e.getDate())));
        
        c0.addAll(c4,c3);
        current.add(c0);
        
        }
         Button btnAddAvis = new Button ("Write a review!");
        btnAddAvis.addActionListener(ev-> new EnvoyerAvisForm(current).show());
        //this.add(btnAddAvis);
        current.add(btnAddAvis);
        current.show();
    }
}