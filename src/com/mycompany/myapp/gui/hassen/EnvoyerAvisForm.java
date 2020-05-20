/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.hassen;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.hassen.Avis;
import com.mycompany.myapp.services.hassen.AvisServices;

/**
 *
 * @author frauDEee
 */
public class EnvoyerAvisForm extends Form{
    Form current;
   public EnvoyerAvisForm (Form previous) {
      
       setTitle("Send review");
       getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
       setLayout(BoxLayout.y());
       setUIID("SignIn");
       add (new Label ("Tell us what you think about our mobile app!"));
       TextField Texte = new TextField("", "Write your review here");
       addAll(Texte);
       Button btnEnvAv = new Button ("Submit review");
       add(btnEnvAv);
       btnEnvAv.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent evt) {
              Avis av = new Avis (Texte.getText());
              AvisServices as = new AvisServices();
              as.addAvis(av);
              
       } 
       
   });
       
               }

    
   
    
    
}
