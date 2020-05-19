/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.hassen;

//import Chat.ChatController;
import com.codename1.components.SpanLabel;
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
        this.setLayout(new BorderLayout());
        current = this;
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        setTitle("Liste des avis");
        getTitleArea().setUIID("Container");
        getContentPane().setScrollVisible(false);
        super.addSideMenu(res);
        
        
        //this.add (BorderLayout.NORTH, new Label ("See what people say about our app ! "));
        SpanLabel sp = new SpanLabel();
        sp.setText(AvisServices.getInstance().getAllAvis().toString());
        this.add(BorderLayout.CENTER, sp);
        
       // Image img = theme.getImage("testmail.png");
     //   i.setIcon(fetchResourceFile().getimage("testmail.png"));
       // add(i);
  //     Resource r = (Resource) Resources.open("theme.res");

//Image mg = r.
       Image icon = FontImage.createMaterial(FontImage.MATERIAL_FEEDBACK, "TitleCommand", 5).toImage();
       Label tittleButton = new Label("Reviews", icon, "Label");
       current.getToolbar().setTitleComponent(tittleButton);
       current.getToolbar().setTitleCentered(true);
       
     
        
        Button btnAddAvis = new Button ("Write a review!");
        Button btnChat = new Button ("Enter Chat Room !");
        btnAddAvis.addActionListener(e-> new EnvoyerAvisForm(current).show());
        this.add(BorderLayout.SOUTH, btnAddAvis);
     //   btnChat.addActionListener(e-> new ChatController()); 
      //  this.add (BorderLayout.SOUTH, btnChat);
        
}
}