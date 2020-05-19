/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.achraf;


import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.achraf.Region;
import com.mycompany.myapp.gui.dhia.ListCommandesForm;
import com.mycompany.myapp.gui.dhia.PanierForm;
import com.mycompany.myapp.gui.gth.AddPubForm;
import com.mycompany.myapp.gui.mohamed.BaseForm;
import com.mycompany.myapp.gui.mohamed.ModForm;
import com.mycompany.myapp.gui.mohamed.NewsfeedForm;
import com.mycompany.myapp.gui.mohamed.ProfileForm;
import com.mycompany.myapp.gui.mohamed.SignInForm;
import com.mycompany.myapp.gui.mohamed.WalkthruForm;
import com.mycompany.myapp.services.achraf.RegionService;
import com.mycompany.myapp.services.mohamed.UserCourant;
import com.mycompany.myapp.utils.achraf.Recherche;
import java.util.ArrayList;

/**
 *
 * @author Achraf
 */
public class RegionForm extends BaseForm{
    Region e = new Region();
Form f;
//private Resources theme;
public RegionForm(Resources theme) {
    //f = new Form();
f = this ;
        RegionService es = new RegionService();
        ArrayList<Region> listevents = new ArrayList<>();
        listevents = es.getAllregions();
         f.setTitle("Régions");

        f.setLayout(BoxLayout.y());
        f.setUIID("SignIn");

        f.getToolbar().addCommandToOverflowMenu("Exit",null,ev->{Display.getInstance().exitApplication();});
        
        Toolbar tb = getToolbar();
        Image img = theme.getImage("profile-background.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        tb.addComponentToSideMenu(LayeredLayout.encloseIn(
                sl,
                FlowLayout.encloseCenterBottom(
                        new Label(theme.getImage("profile-pic.jpg"), "PictureWhiteBackgrond"))
        ));
       tb.addMaterialCommandToSideMenu("Newsfeed", FontImage.MATERIAL_UPDATE, e -> new NewsfeedForm(theme).show());
        tb.addMaterialCommandToSideMenu("Profile", FontImage.MATERIAL_SETTINGS, e -> new ProfileForm(theme).show());
        tb.addMaterialCommandToSideMenu("edit profile", FontImage.MATERIAL_SETTINGS, e -> new ModForm(theme).show());
        tb.addMaterialCommandToSideMenu("Evenements", FontImage.MATERIAL_PLACE, e -> new EvenementForm(theme).show());
        tb.addMaterialCommandToSideMenu("Ajouter une Publication", FontImage.MATERIAL_ADD_TO_PHOTOS, e -> new AddPubForm(theme).show());
        tb.addMaterialCommandToSideMenu("Profile", FontImage.MATERIAL_PERSON, e -> new ProfileForm(theme).show());
        tb.addMaterialCommandToSideMenu("Store", FontImage.MATERIAL_EXIT_TO_APP, e -> new com.mycompany.myapp.gui.ahmed.HomeForm(theme).show());
        tb.addMaterialCommandToSideMenu("Panier", FontImage.MATERIAL_SHOPPING_CART, e -> new PanierForm(theme).show());
        tb.addMaterialCommandToSideMenu("Commande", FontImage.MATERIAL_PAGES, e -> new ListCommandesForm(theme).show());
        tb.addMaterialCommandToSideMenu("Régions", FontImage.MATERIAL_PLACE, e -> new  RegionForm(theme).show());
        tb.addMaterialCommandToSideMenu("Logout", FontImage.MATERIAL_EXIT_TO_APP, e -> {
            UserCourant.ok=null;
             com.mycompany.myapp.services.mohamed.Recherche.username=false;
            com.mycompany.myapp.services.mohamed.Recherche.email=false;
            com.mycompany.myapp.services.mohamed.Recherche.connexion=false;
           com.mycompany.myapp.services.mohamed.Recherche.name=false;
            com.mycompany.myapp.services.mohamed.Recherche.mail=false; 
            new WalkthruForm(theme).show();});

        f.getToolbar().addCommandToLeftBar("Back",null,ev->{new NewsfeedForm(theme).show();});
         

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
        c7.add(new Label("Nombre de villes : "));
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
            AfficherRegion ev = new AfficherRegion(e,theme);
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
