/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.Stock;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.myapp.Entities.Stock.Produit;
import com.mycompany.myapp.gui.BaseForm;

/**
 *
 * @author Haddad
 */
public class ShowForm extends BaseForm{
      public ShowForm(Form c,Produit p) {
        
          
            super(BoxLayout.y());
        Button btnBuy=new Button("Buy");
        Form current = this;
        Form hi = new Form("Show", new FlowLayout(Container.CENTER, Container.CENTER));
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Produit");
        getContentPane().setScrollVisible(false);

        //super.addSideMenu(res);

        tb.addSearchCommand(e -> {
        });
        Container list = new Container(BoxLayout.x());
       // Container list1 = new Container(BoxLayout.x());
       // Container list2 = new Container(BoxLayout.x());
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());

        list.getAllStyles().setPaddingTop(size / 4);
            
//            TODO 

            Container Prod = new Container(BoxLayout.y(), "Produit");
           //  Container Prod2 = new Container(BoxLayout.x(), "Produit");
           // Container Prod3 = new Container(BoxLayout.x(), "Produit");
            
//            IconHolder icon = IconHolder.cas(FontImage.MATERIAL_10K);
                    
            SpanLabel idP = new SpanLabel("Id:" +p.getId_Produit());
            SpanLabel nomP = new SpanLabel("Produit:" + p.getNom_Produit());
            SpanLabel catP = new SpanLabel("Categorie:" + p.getNcat(p.getId_Categorie()));
            SpanLabel etatP = new SpanLabel("Etat:" + p.getEtat_Produit());
            SpanLabel prixP = new SpanLabel("Prix:" + p.getPrix_Produit());
            SpanLabel sizeP = new SpanLabel("Taille:" + p.getTaille_Produit());
            Image taille = Image.createImage(100, 100, 0xffff0000);
            ImageViewer imgv1 = new ImageViewer(taille);
            btnBuy.addActionListener(e-> new AddProductForm(current).show());
            EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(current.getWidth(), current.getWidth() / 5, 0xffff0000), false);
            URLImage img = URLImage.createToStorage(placeholder, p.getUrl(),"file:///C:/wamp64/www/BitDevTGT/web"+p.getUrl());
            //img.fetch();
            imgv1.setImage(img);
            Prod.addAll(idP,nomP,catP,prixP,etatP,sizeP,imgv1);
            // Prod.addAll(idP,nomP,catP);
            // Prod2.add(etatP,prixP);
            // Prod3.addAll(sizeP,qteP,urlP);
            Prod.getAllStyles().setMargin(size / 8,0,0,0);
            
           /*ImageViewer imgv1 = new ImageViewer();
            EncodedImage enc = EncodedImage.createFromImage("", false);
            Image img = URLImage.createToStorage(enc, "http://127.0.0.1/mobile/" + p.getUrl());
            imgv1.setImage(img);*/
            
            Prod.setLeadComponent(btnBuy);
            list.add(Prod);
           // list1.add(Prod2);
           // list2.add(Prod3);
        
        addAll(list);
        
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> c.showBack());
    }
    
}



