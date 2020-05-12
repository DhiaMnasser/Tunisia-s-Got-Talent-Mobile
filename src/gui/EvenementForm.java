/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;
import Entities.Evenement;
import Services.EvenementService;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.IconHolder;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;


import gui.*;
import java.util.ArrayList;
import javafx.scene.paint.Color;
/**
 *
 * @author Achraf
 */
public class EvenementForm extends BaseForm{
   Evenement e = new Evenement();
Form f;
private Resources theme;
public EvenementForm() {
    //f = new Form();
f = this ;
        EvenementService es = new EvenementService();
        ArrayList<Evenement> listevents = new ArrayList<>();
        listevents = es.getAllevents();
         f.setTitle("Evenements");

        f.setLayout(BoxLayout.y());
        f.getToolbar().addCommandToOverflowMenu("Logout", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                //new HomeForm().show();
                new SignInForm(theme).show();

            }
        });
         f.getToolbar().addCommandToSideMenu("Evenements", null, new ActionListener() {
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
    
        TextField search = new TextField("","Rechercher");
        FloatingActionButton searchbtn = FloatingActionButton.createFAB(FontImage.MATERIAL_SEARCH);
       
        System.out.println("test");
        //RECHERCHE
        
        // LISTE
        
        for (Evenement e : listevents) {

           Container c0 = new Container(BoxLayout.y());
  

        Container c = new Container(BoxLayout.x());
//NOM
        Container c4 = new Container(BoxLayout.x());
        final FontImage nom = FontImage.createMaterial(FontImage.MATERIAL_ROOM, "Label", 6);
        c4.add(nom);
        c4.add(new Label("Lieu : "));
        c4.add(new Label(e.getNomevent()));

              //IMAGE 
        Image imgUrl;
        Image placeholder = Image.createImage(600, 600);
        EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
        imgUrl = URLImage.createToStorage(encImage, e.getImage(), e.getImage());
        ImageViewer img1 = new ImageViewer(imgUrl);
        c0.add(img1);

        
        //DATE Début
        
        Container c2 = new Container(BoxLayout.x());
        final FontImage time = FontImage.createMaterial(FontImage.MATERIAL_DATE_RANGE, "Label", 6);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        c2.add(time);

      

        c2.add(new Label("Date : "));
        c2.add(new Label(formatter.format(e.getDate_d())));
        // Date fin
        Container c3 = new Container(BoxLayout.x());
        final FontImage time2 = FontImage.createMaterial(FontImage.MATERIAL_DATE_RANGE, "Label", 6);
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
        c3.add(time2);

      

        c3.add(new Label("Date : "));
        c3.add(new Label(formatter2.format(e.getDate_f())));
        //Duree
        Container c5 = new Container(BoxLayout.x());
        final FontImage duree = FontImage.createMaterial(FontImage.MATERIAL_ROOM, "Label", 6);
        c5.add(duree);
        c5.add(new Label("Duree : "));
        c5.add(new Label(e.getDuree()));
        //Gagnant
        Container c6 = new Container(BoxLayout.x());
        final FontImage gagnant = FontImage.createMaterial(FontImage.MATERIAL_ROOM, "Label", 6);
        c6.add(gagnant);
        c6.add(new Label("Gagnant : "));
        c6.add(new Label(e.getGagnant()));

       
        //maxparticipants
        Container c7 = new Container(BoxLayout.x());
       
 final FontImage part = FontImage.createMaterial(FontImage.MATERIAL_ATTACH_MONEY, "Label", 6);
        c7.add(part);
        c7.add(new Label("Max Participants : "));
        int maxpart = e.getMaxParticipants();
        String s1 = Integer.toString(maxpart);
        c7.add(new Label(s1));
        c7.add(new Label("Participants")); 
        //Etat
        Container c8 = new Container(BoxLayout.x());
       
 final FontImage etat = FontImage.createMaterial(FontImage.MATERIAL_ALARM, "Label", 6);
        c8.add(etat);
        c8.add(new Label("Etat : "));
        int etati = e.getEtat();
        String s2 = Integer.toString(etati);
        c8.add(new Label(s2));
        if (etati==0)
        {
        c8.add(new Label("Terminé")); 
        }
        else
        {
            c8.add(new Label("En cours"));
        }
        //region
        Container c9 = new Container(BoxLayout.x());
       
 final FontImage region = FontImage.createMaterial(FontImage.MATERIAL_ATTACH_MONEY, "Label", 6);
        c9.add(region);
        c9.add(new Label("Region : "));
        int reg = e.getRegion_id();
        String s3 = Integer.toString(reg);
        c9.add(new Label(s3));
        
        //
           Container c10 = new Container(BoxLayout.x());
Button b = new Button("Afficher Evénement"); 
           
b.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

            
            }  });
       
           c10.add(b);

           
        
        
                 
       

c0.addAll(c2,c3,c4,c5,c6,c7,c8,c9,c10);
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

