/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author bhk
 */
public class HomeForm extends BaseForm{
Form current;
    public HomeForm() {
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
     
        
        add(new Label("Bienvenue à TGT"));
      
        
        current.getToolbar().addCommandToOverflowMenu("Logout", null, new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent evt) {
                          new HomeForm().show();

         }
     });
        
                      current.getToolbar().addCommandToOverflowMenu("Exit",null,ev->{Display.getInstance().exitApplication();});

          current.getToolbar().addMaterialCommandToSideMenu("      ", FontImage.MATERIAL_ACCOUNT_CIRCLE, new ActionListener() {
             @Override
            public void actionPerformed(ActionEvent evt) {
          EvenementForm events = new EvenementForm();
           events.getF().show();
            }
        });
      
          current.getToolbar().addMaterialCommandToSideMenu("       Evénements", FontImage.MATERIAL_PLACE, new ActionListener() {
             @Override
            public void actionPerformed(ActionEvent evt) {
          EvenementForm events = new EvenementForm();
           events.getF().show();
            }
        });
           current.getToolbar().addMaterialCommandToSideMenu("       Régions", FontImage.MATERIAL_PLACE, new ActionListener() {
             @Override
            public void actionPerformed(ActionEvent evt) {
          RegionForm regions = new RegionForm();
           regions.getF().show();
            }
        });
              
                         
                
                   
        
        
    }
    public Form getF() {
        return current;
    }
    
}
