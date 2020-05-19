/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.ahmed;

import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.ahmed.Produit;
import com.mycompany.myapp.gui.mohamed.BaseForm;
import com.mycompany.myapp.services.ahmed.ServiceProduct;
import com.mycompany.myapp.utils.ahmed.Statics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Haddad
 */
public class ListProductCForm extends BaseForm{
      public ListProductCForm(Resources res,String c,ArrayList<Produit> form,Form previous) {
        
          
            super(BoxLayout.y());
             super.addSideMenu(res);
            
            ServiceProduct serv = new ServiceProduct();
       
        setTitle(c);
        Form current = this;
        setUIID("SignIn");
        
        
        getTitleArea().setUIID("Container");
        
        getContentPane().setScrollVisible(true);
        
        Style s = UIManager.getInstance().getComponentStyle("");
        TextField tf_recherche = new TextField("", "Rechercher");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_SEARCH, s);
        Button recherche = new Button(icon);

        //LIKE
        recherche.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                if (tf_recherche.getText() != "")
                {
                ServiceProduct formServ = new ServiceProduct();
                System.out.println("--------------------------------------------");
                System.out.println(formServ.getRProducts(tf_recherche.getText()));
                System.out.println("--------------------------------------------");
                ArrayList<Produit> form = null;
                form = formServ.getRProducts(tf_recherche.getText());
                ListProductCForm ap = new ListProductCForm(res,"Resultats",form,previous);
                ap.getF().show();
                System.out.println("*******************************************");
                System.out.println(form);
                System.out.println("*******************************************");
               }
                else
                {
                   Dialog.show("Recherche", "Recherche vide", "OK", "Cancel"); 
                }}
        });

        Container rechCont = new Container(new TableLayout(1, 2));
        rechCont.add(recherche);
        rechCont.add(tf_recherche);
        this.add(rechCont);

       MultiButton b = new MultiButton("Filter par categorie");
    b.addActionListener(e -> {
    Dialog d = new Dialog();
    d.setLayout(BoxLayout.y());
    d.getContentPane().setScrollableY(true);
    String[] characters = { "All", "T-Shirt", "Acc","Tickets","Autres","VIP"};
     for(int iter = 0 ; iter < characters.length ; iter++) {
            MultiButton mb = new MultiButton(characters[iter]);
            d.add(mb);
            mb.addActionListener(ee -> {
            b.setTextLine1(mb.getTextLine1());
            b.setIcon(mb.getIcon());
            d.dispose();
            b.revalidate();
            int i;
            switch(mb.getTextLine1())
                {
                    case "T-Shirt":
                        i=1;
                        break;
                    case "Acc":
                        i=2;
                        break;
                    case "Tickets":
                        i=3;
                        break;
                    case "Autres":
                        i=4;
                        break;
                    case "VIP":
                        i=5;
                        break;
                    default:
                        i=0;
                        break;
                }
                ArrayList<Produit> form1 = null;
                if (i!=0)
                {
                form1 = serv.getCProducts(i);
                ListProductCForm ap = new ListProductCForm(res,mb.getTextLine1(),form1,getF());
                ap.getF().show();
                }
                else
                {
                ListProductForm ap = new ListProductForm(res,this);
                ap.show();
                }   
                
        });
    }
    d.showPopupDialog(b);
});
    add(b);
        
         
        Container list = new Container(new FlowLayout(Component.CENTER));
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        list.getAllStyles().setPaddingTop(size / 4);
        
      
        
        ArrayList<Produit> productlist = form;
        for (Produit p : productlist) {
            Button detailBtn = new Button();
            detailBtn.addActionListener((evt) -> {
                System.out.println("PointerPressedListener");
                new ShowForm(current,p,res).show();
            });

           Container Prod = new Container(BoxLayout.y(), "Produit");
             Container ProdF = new Container(BoxLayout.x());
//            IconHolder icon = IconHolder.cas(FontImage.MATERIAL_10K);
                    
            Label nomP = new Label("Produit : " + p.getNom_Produit());
            Label etatP = new Label("Etat : " + p.getEtat_Produit());
            etatP.getAllStyles().setFgColor(0xff000);
            Label prixP = new Label("Prix : " + p.getPrix_Produit()+" TD");
            etatP.getAllStyles().setBgColor(BOTTOM);
           
       
            Container im = new Container(BoxLayout.y());
            String urlImage = Statics.P_IMAGE_URL +p.getUrl();
               Image placeholder = Image.createImage(222, 287);
               EncodedImage enco = EncodedImage.createFromImage(placeholder, true);
               Image imgser = URLImage.createToStorage(enco, "" + p.getUrl(), urlImage , URLImage.RESIZE_SCALE);
               imgser = imgser.scaled(350, 350);
               ImageViewer img = new ImageViewer(imgser);
            im.add(img);
            Prod.addAll(nomP,prixP,etatP);                 
            Prod.setLeadComponent(detailBtn);          
            ProdF.addAll(im,Prod);
            list.add(ProdF);
         
                   }
        add(list);
        
        
         //toolbar
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }

      private Map<String, Object> createListEntry(String name) {
    Map<String, Object> entry = new HashMap<>();
    entry.put("Line1", name);
    return entry;
      }
    public Form getF() {
        return this;
    }

}



