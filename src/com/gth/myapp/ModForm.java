/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gth.myapp;

import com.codename1.capture.Capture;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.gth.entities.Personne;
import com.gth.service.PersonneService;
import com.gth.service.Recherche;
import com.gth.service.UserCourant;
import java.io.IOException;

/**
 *
 * @author mohamed khrouf
 */
public class ModForm extends BaseForm{
    private Label pic;
    String imgPath;
   public ModForm(Resources res) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Profile");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        
        tb.addSearchCommand(e -> {});
        
 if (UserCourant.ok.getConfirmation_token().length()==3){
        Image img = res.getImage("profile-background.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
       try{
         pic= new Label(res.getImage("profile-pic.jpg"), "PictureWhiteBackgrond");
  
 pic.addPointerReleasedListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                  try {
                         imgPath = Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);
                        System.out.println(imgPath);
                        Image oui = Image.createImage(imgPath);                      
                          pic= new Label(oui.scaled(200,200 ), "PictureWhiteBackgrond");
                           
                    } catch (IOException ex) {
                        System.out.println(ex);
                    }
                }
                
            });
        Label facebook = new Label("786 followers", res.getImage("facebook-logo.png"), "BottomPad");
        Label twitter = new Label("486 followers", res.getImage("twitter-logo.png"), "BottomPad");
        facebook.setTextPosition(BOTTOM);
        twitter.setTextPosition(BOTTOM);
        
      
           add(LayeredLayout.encloseIn(
                   sl,
                   BorderLayout.south(
                           GridLayout.encloseIn(3,
                                   facebook,
                                   FlowLayout.encloseCenter(
                                        pic ),
                                   twitter
                           )
                   )
           ));
       }catch(Exception ex){
           
       }

 }else{
       Image img = res.getImage("profile-background.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
       try{
           pic=  new Label(Image.createImage(UserCourant.ok.getConfirmation_token()).scaled(200,200 ), "PictureWhiteBackgrond");
  
 pic.addPointerReleasedListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                  try {
                         imgPath = Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);
                        System.out.println(imgPath);
                        Image oui = Image.createImage(imgPath);                      
                          pic= new Label(oui.scaled(200,200 ), "PictureWhiteBackgrond");
                           
                    } catch (IOException ex) {
                        System.out.println(ex);
                    }
                }
                
            });
        Label facebook = new Label("786 followers", res.getImage("facebook-logo.png"), "BottomPad");
        Label twitter = new Label("486 followers", res.getImage("twitter-logo.png"), "BottomPad");
        facebook.setTextPosition(BOTTOM);
        twitter.setTextPosition(BOTTOM);
        
      
           add(LayeredLayout.encloseIn(
                   sl,
                   BorderLayout.south(
                           GridLayout.encloseIn(3,
                                   facebook,
                                   FlowLayout.encloseCenter(
                                        pic ),
                                   twitter
                           )
                   )
           ));
       }catch(Exception ex){
           
       }
 }
        TextField username = new TextField(UserCourant.ok.getUsername());
        username.setUIID("TextFieldBlack");
        addStringValue("Username", username);

        TextField email = new TextField(UserCourant.ok.getEmail(), "E-Mail", 20, TextField.EMAILADDR);
        email.setUIID("TextFieldBlack");
        addStringValue("E-Mail", email);
        
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        password.setUIID("TextFieldBlack");
        addStringValue("Password", password);
        
        CheckBox cb1 = CheckBox.createToggle(res.getImage("on-off-off.png"));
        cb1.setUIID("Label");
        cb1.setPressedIcon(res.getImage("on-off-on.png"));
        CheckBox cb2 = CheckBox.createToggle(res.getImage("on-off-off.png"));
        cb2.setUIID("Label");
        cb2.setPressedIcon(res.getImage("on-off-on.png"));
        Button b = new Button("edit");
        addStringValue("Facebook", FlowLayout.encloseRightMiddle(cb1));
        addStringValue("Twitter", FlowLayout.encloseRightMiddle(cb2));
        addStringValue("",FlowLayout.encloseCenter(b));
       b.addActionListener(e->{
           Personne p = new Personne(username.getText(),email.getText(),password.getText());
                   PersonneService ps= new PersonneService();
            ps.getPersonne(username.getText(), email.getText());
           p.setId(UserCourant.ok.getId());
           p.setConfirmation_token(imgPath);
          
            if(!(Recherche.mail) && !(Recherche.name)){
                
                   ps.modPersonne(p);
                   UserCourant.ok=p;
                
                }
           
                if(Recherche.mail){
                    Dialog.show("email existe", "saisir un autre email "  , "OK", null);
                    Recherche.mail=false;
                }
           
               
                if(Recherche.name){
                    Dialog.show("username existe", "saisir un autre username "  , "OK", null);
                     Recherche.name=false;
                }
              
       
       });
    }
    
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }   
}
