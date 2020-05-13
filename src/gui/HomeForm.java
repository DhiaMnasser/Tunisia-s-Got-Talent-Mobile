/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import utils.Recherche;
import utils.UserCourant;

/**
 *
 * @author bhk
 */
public class HomeForm extends Form{
Form current;
Resources res ;
private ImageViewer image,image2 ;
    public HomeForm(Resources theme) {
        current=this;
        
       /* setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnAddTask = new Button("Add Task");
        Button btnListTasks = new Button("List Tasks");
        
        
        btnListTasks.addActionListener(e-> new EvenementForm().show());
        addAll(btnAddTask,btnListTasks);*/
         setTitle("Tunisian Got Talent");
  Container c = new Container(BoxLayout.x());
         
        setLayout(BoxLayout.y());
     
        
        
      
        
        current.getToolbar().addCommandToOverflowMenu("Logout", null, new ActionListener() {
        
         @Override
         public void actionPerformed(ActionEvent evt) {
             UserCourant.ok=null;
             Recherche.username=false;
  Recherche.email=false;
     Recherche.connexion=false;
    Recherche.name=false;
     Recherche.mail=false;
                        new SignInForm(theme).show();
                        
         }
     });
        
                      current.getToolbar().addCommandToOverflowMenu("Exit",null,ev->{Display.getInstance().exitApplication();});

          current.getToolbar().addMaterialCommandToSideMenu("      ", FontImage.MATERIAL_ACCOUNT_CIRCLE, new ActionListener() {
             @Override
            public void actionPerformed(ActionEvent evt) {
          EvenementForm events = new EvenementForm(theme);
           events.getF().show();
            }
        });
      
          current.getToolbar().addMaterialCommandToSideMenu("       Evénements", FontImage.MATERIAL_PLACE, new ActionListener() {
           
              @Override
            public void actionPerformed(ActionEvent evt) {
          EvenementForm events = new EvenementForm(theme);
           events.getF().show();
            }
        });
           current.getToolbar().addMaterialCommandToSideMenu("       Régions", FontImage.MATERIAL_PLACE, new ActionListener() {
             @Override
            public void actionPerformed(ActionEvent evt) {
          RegionForm regions = new RegionForm(theme);
           regions.getF().show();
            }
        });
              
                         
             setUIID("SignIn");
                   image=new ImageViewer(theme.getImage("TGT logo.png"));
                   add(image);
                   Button btn = new Button("Bienvenue dans votre application");
                   add(btn) ;
        System.out.println("Bienvenue "+UserCourant.ok);
        
    }
    public Form getF() {
        return current;
    }
    
}
