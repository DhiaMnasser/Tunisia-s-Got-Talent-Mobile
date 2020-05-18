/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.Avis;

import Chat.ChatController;
import com.codename1.components.SpanLabel;
import static com.codename1.rad.schemas.Thing.url;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Entities.Avis.Avis;
import com.mycompany.myapp.MyApplication;
import com.mycompany.myapp.Services.AvisServices;
import java.io.IOException;

import java.util.ArrayList;



/**
 *
 * @author frauDEee
 */
public class ListeAvisForm extends Form {

    Form current;
    ArrayList<Avis> AvisListe = new ArrayList<>();
     private Resources theme;

    public ListeAvisForm() {
      
      //  super(BoxLayout.y());
        theme = UIManager.initFirstTheme("/theme");
        current = this;
        setLayout(BoxLayout.y());
        
        setTitle("Liste des avis");
        add (new Label ("See what people say about our app ! "));
        SpanLabel sp = new SpanLabel();
        sp.setText(AvisServices.getInstance().getAllAvis().toString());
        add(sp);
        Label i = new Label();
       // Image img = theme.getImage("testmail.png");
     //   i.setIcon(fetchResourceFile().getimage("testmail.png"));
       // add(i);
  //     Resource r = (Resource) Resources.open("theme.res");

//Image mg = r.
          Image icon = FontImage.createMaterial(FontImage.MATERIAL_CAKE, "TitleCommand", 5).toImage();
       Label tittleButton = new Label("Reviews", icon, "Label");
       current.getToolbar().setTitleComponent(tittleButton);
       current.getToolbar().setTitleCentered(true);
       
     
        
        Button btnAddAvis = new Button ("Write a review!");
        Button btnChat = new Button ("Enter Chat Room !");
        btnAddAvis.addActionListener(e-> new EnvoyerAvisForm(current).show());
        add(btnAddAvis);
        btnChat.addActionListener(e-> new ChatController()); 
        add (btnChat);
        
}
}