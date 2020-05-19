/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.achraf;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.achraf.Evenement;
import com.mycompany.myapp.entities.mohamed.Personne;
import com.mycompany.myapp.gui.dhia.ListCommandesForm;
import com.mycompany.myapp.gui.dhia.PanierForm;
import com.mycompany.myapp.gui.gth.AddPubForm;
import com.mycompany.myapp.gui.mohamed.ModForm;
import com.mycompany.myapp.gui.mohamed.NewsfeedForm;
import com.mycompany.myapp.gui.mohamed.ProfileForm;
import com.mycompany.myapp.gui.mohamed.WalkthruForm;
import com.mycompany.myapp.services.achraf.EvenementService;
import com.mycompany.myapp.services.achraf.JavaMailUtilAchraf;
import com.mycompany.myapp.services.mohamed.Recherche;
import com.mycompany.myapp.services.mohamed.UserCourant;
import java.util.ArrayList;
import com.teknikindustries.bulksms.SMS;

/**
 *
 * @author Achraf
 */
public class AfficherEvent {
     Form f;
    int eventid;
        Evenement e = new Evenement(eventid);
         int user_id;
     Container c11 ;
     Label s ;
    //int SelectedID;
    public AfficherEvent(Evenement event,Resources theme) {
        f = new Form();


        this.e = event;
        
        
             
        f.setTitle(e.getNomevent());

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
          
       
        f.getToolbar().addCommandToLeftBar("back", null, (ev) -> {
            EvenementForm sb = new EvenementForm(theme);
            sb.getF().show();
        });

       Evenement ev = new Evenement(e.getId());
        EvenementService es = new EvenementService();

        ev = es.geteventbyid(event, e.getId());

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
        final FontImage duree = FontImage.createMaterial(FontImage.MATERIAL_ALARM, "Label", 6);
        c5.add(duree);
        c5.add(new Label("Duree : "));
        c5.add(new Label(e.getDuree()));
        c5.add(new Label("Jours"));
        //Gagnant
        Container c6 = new Container(BoxLayout.x());
        final FontImage gagnant = FontImage.createMaterial(FontImage.MATERIAL_PERSON, "Label", 6);
        c6.add(gagnant);
        c6.add(new Label("Gagnant : "));
        c6.add(new Label(e.getGagnant()));

       
        //maxparticipants
        Container c7 = new Container(BoxLayout.x());
       
 final FontImage part = FontImage.createMaterial(FontImage.MATERIAL_PERSON, "Label", 6);
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
       
 final FontImage region = FontImage.createMaterial(FontImage.MATERIAL_PLACE, "Label", 6);
        c9.add(region);
        c9.add(new Label("Region : "));
        int reg = e.getRegion_id();
        String s3 = Integer.toString(reg);
        c9.add(new Label(s3));
        
        //
           Container c10 = new Container(BoxLayout.x());
Button b = new Button("S'inscrire à l'Evénement"); 
           
b.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
        
        Personne p = UserCourant.ok ;
        
        EvenementService es = new EvenementService();
        if (p.getEvent_id()==e.getId())
                {
                   Dialog.show(":(((","Vous êtes déjà inscrit à cet événement", "ok", "cancel");
                   
                   
                }
                else
                {
                    Dialog.show(":)))","Inscription prise en considération : Un EMAIL ainsi qu'un SMS vous ont été envoyés !!", "ok", "cancel");
                }
        
       
         p.setEvent_id(e.getId());
         
         es.modPersonne(p);
         SMS sms=new SMS();
String nt= "+21655886985";
sms.SendSMS("achraftest","Ab123456", "Votre Inscription à l'événement a été effectuée", nt ,"https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0");
try {
              
JavaMailUtilAchraf.sendMail("mohamedachraf.chourabi@esprit.tn","Inscription à l'événement "+e.getNomevent()+" a été prise en considération");
                } catch (Exception ex) {
                    
                }
                EvenementForm events = new EvenementForm(theme);
                events.getF().show();
            }  
            

});
       
           c10.add(b);
           c0.addAll(c2,c3,c4,c5,c6,c7,c8,c9);
// liste des participants 
int nbr =0 ;
 c11 = new Container(BoxLayout.x());

        final FontImage liste = FontImage.createMaterial(FontImage.MATERIAL_PERSON, "Label", 6);
        c11.add(liste);
        c11.add(new Label("Participants : "));
        
        
EvenementService es3 = new EvenementService();
        ArrayList<Personne> listevents3 = new ArrayList<>();
        listevents3 = es3.getusers();
                for (Personne u : listevents3) {
                    
       
        
               if (e.getId()==u.getEvent_id()){
                 
        /*c11 = new Container(BoxLayout.x());

        final FontImage liste = FontImage.createMaterial(FontImage.MATERIAL_PERSON, "Label", 6);
        c11.add(liste);
        c11.add(new Label("Participants : "));*/
        c11.add(new SpanLabel(u.getUsername()));
        
                       //c0.add(c11);
                       nbr++ ;
               }
                }
                 
       c11.add(new SpanLabel("( "+nbr+" Participants )"));
       
c0.add(c11);
c0.add(c10);
 /*c0.add(c4);

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
