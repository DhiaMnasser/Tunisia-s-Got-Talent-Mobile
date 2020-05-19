/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.achraf;


import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.achraf.Region;
import com.mycompany.myapp.gui.mohamed.NewsfeedForm;
import com.mycompany.myapp.services.achraf.RegionService;

/**
 *
 * @author Achraf
 */
public class AfficherRegion {
    Form f;
    int eventid;
   Region e = new Region(eventid);
    int user_id;
    public AfficherRegion(Region event , Resources theme) {
        f = new Form();


        this.e = event;
        
        
             
        f.setTitle(e.getNom());

        f.setLayout(BoxLayout.y());    
        f.setUIID("SignIn");
         
        Toolbar.setGlobalToolbar(true);
       /* f.getToolbar().addCommandToOverflowMenu("Logout", null, new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent evt) {
                         // new HomeForm().show();

         }
     });*/
       
         //f.getToolbar().addCommandToLeftBar("Back",null,ev->{new NewsfeedForm(theme).show();});
            
      
           f.getToolbar().addCommandToLeftBar("Back", null, (ev) -> {
            RegionForm sb = new RegionForm(theme);
            sb.getF().show();
            });
       
        Region ev = new Region(e.getId());
        RegionService es = new RegionService();

        ev = es.getregionbyid(event, e.getId());
Container c0 = new Container(BoxLayout.y());
  

        Container c = new Container(BoxLayout.x());
//NOM
        Container c4 = new Container(BoxLayout.x());
        final FontImage nom = FontImage.createMaterial(FontImage.MATERIAL_ROOM, "Label", 6);
        c4.add(nom);
        c4.add(new Label("Lieu : "));
        c4.add(new Label(e.getNom()));

             

        
        
        

       
        //nbVilles
        Container c7 = new Container(BoxLayout.x());
       
 final FontImage part = FontImage.createMaterial(FontImage.MATERIAL_PLACE, "Label", 6);
        c7.add(part);
        c7.add(new Label("Nombre de villes : "));
        int maxpart = e.getNb_villes();
        String s1 = Integer.toString(maxpart);
        c7.add(new Label(s1));
        c7.add(new Label("Villes")); 
        

           
        
        
                 
       

c0.addAll(c4,c7);
 /*c0.add(c4);
c0.add(c2);
        c0.add(c3);
        c0.add(c7);
        c0.add(c6);
*/
       
                
       f.add(c0);
            

        
        
        
        
        
}
public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
