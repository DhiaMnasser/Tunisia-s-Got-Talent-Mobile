/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entities.Region;
import Services.RegionService;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;

/**
 *
 * @author Achraf
 */
public class RegionForm extends Form{
     Region e = new Region();
Form f;
private Resources theme;
public RegionForm() {
    //f = new Form();
f = this ;
        RegionService es = new RegionService();
        ArrayList<Region> listevents = new ArrayList<>();
        listevents = es.getAllregions();
         f.setTitle("Régions");

        f.setLayout(BoxLayout.y());
        f.getToolbar().addCommandToOverflowMenu("Logout", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                new HomeForm().show();
                

            }
        });

        f.getToolbar().addCommandToOverflowMenu("Exit",null,ev->{Display.getInstance().exitApplication();});

  f.getToolbar().addMaterialCommandToSideMenu("      ", FontImage.MATERIAL_ACCOUNT_CIRCLE, new ActionListener() {
             @Override
            public void actionPerformed(ActionEvent evt) {
          EvenementForm events = new EvenementForm();
           events.getF().show();
            }
        });
          
          f.getToolbar().addMaterialCommandToSideMenu("       Evenements", FontImage.MATERIAL_PLACE, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                EvenementForm events = new EvenementForm();
                events.getF().show();
            }
        });
          f.getToolbar().addMaterialCommandToSideMenu("       Régions", FontImage.MATERIAL_PLACE, new ActionListener() {
             @Override
            public void actionPerformed(ActionEvent evt) {
          EvenementForm events = new EvenementForm();
           events.getF().show();
            }
        });
         f.getToolbar().addCommandToLeftBar("back", null, (ev) -> {
            HomeForm sb = new HomeForm();
            sb.show();
        });

for (Region e : listevents) {

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
        c7.add(new Label("Max Participants : "));
        int maxpart = e.getNb_villes();
        String s1 = Integer.toString(maxpart);
        c7.add(new Label(s1));
        c7.add(new Label("Villes")); 
        
       
        
        //
           Container c10 = new Container(BoxLayout.x());
Button b = new Button("Afficher Region"); 
           
b.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
AfficherRegion ev = new AfficherRegion(e);
                    ev.getF().show();
            
            }  });
       
           c10.add(b);

           
        
        
                 
       

c0.addAll(c4,c7,c10);
 /*c0.add(c4);
c0.add(c2);
        c0.add(c3);
        c0.add(c7);
        c0.add(c6);
*/
       
                
       f.add(c0);
            

        }
        
        
        
        
}
public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
