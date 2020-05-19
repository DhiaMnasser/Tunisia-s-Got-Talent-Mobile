/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.mohamed;

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
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.mohamed.Personne;
import com.mycompany.myapp.services.mohamed.PersonneService;
import com.mycompany.myapp.services.mohamed.Recherche;
import com.mycompany.myapp.services.mohamed.UserCourant;

/**
 *
 * @author mohamed khrouf
 */
public class ModForm extends BaseForm{
   public ModForm(Resources res) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Profile");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        
        tb.addSearchCommand(e -> {});
        
        
        Image img = res.getImage("profile-background.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

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
                                new Label(res.getImage("profile-pic.jpg"), "PictureWhiteBackgrond")),
                            twitter
                    )
                )
        ));

        TextField username = new TextField(UserCourant.ok.getUsername());
        username.setUIID("TextFieldBlack");
        addStringValue("Username", username);

        TextField email = new TextField(UserCourant.ok.getEmail(), "E-Mail", 20, TextField.EMAILADDR);
        email.setUIID("TextFieldBlack");
        addStringValue("E-Mail", email);
        
        TextField password = new TextField("sandeep", "Password", 20, TextField.PASSWORD);
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
            ps.getPersonne(username.getText(), password.getText());
           p.setId(UserCourant.ok.getId());
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
