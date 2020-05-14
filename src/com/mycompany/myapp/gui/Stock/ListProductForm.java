/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.Stock;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.table.TableLayout;
import com.mycompany.myapp.Entities.Stock.Categorie;
import com.mycompany.myapp.Services.Stock.ServiceProduct;
import com.mycompany.myapp.Entities.Stock.Produit;
import com.mycompany.myapp.Services.Stock.ServiceCategorie;
import com.mycompany.myapp.gui.BaseForm;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Haddad
 */
public class ListProductForm extends BaseForm{
      public ListProductForm(Form previous) {
        
          
        super(BoxLayout.y());
        ServiceProduct serv = new ServiceProduct();
        setTitle("Store");
        
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
           //toolbar
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        Form current = this;
        getTitleArea().setUIID("Container");
        
        getContentPane().setScrollVisible(true);
        
        
        ServiceCategorie Catserv = new ServiceCategorie();

        ComboBox<String> comboCat = new ComboBox<String>();
        for (Categorie item : Catserv.getAllCats()) {
            comboCat.addItem(item.getNom_Categorie());
        }
        this.add(comboCat);
        
        comboCat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                System.out.println("--------------------------------------------");
                String c = comboCat.getSelectedItem();
                int i=0;
                switch(c)
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
                System.out.println(serv.getCProducts(i));
                ArrayList<Produit> form = null;
                form = serv.getCProducts(i);
                ListProductCForm ap = new ListProductCForm(c,form,getF());
                ap.getF().show();
                System.out.println("*******************************************");
                System.out.println(i);
                System.out.println(form);
                System.out.println("*****************************************");

            }
        });
        TextField tf_recherche = new TextField("", "Rechercher");

        Button recherche = new Button("Rech");

        //LIKE
        recherche.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                ServiceProduct formServ = new ServiceProduct();
                System.out.println("--------------------------------------------");
                System.out.println(formServ.getRProducts(tf_recherche.getText()));
                System.out.println("--------------------------------------------");
                ArrayList<Produit> form = null;
                form = formServ.getRProducts(tf_recherche.getText());
                ListProductCForm ap = new ListProductCForm("Resultats",form,getF());
                ap.getF().show();
                System.out.println("*******************************************");
                System.out.println(form);
                System.out.println("*******************************************");
            }
        });

        Container rechCont = new Container(new TableLayout(1, 2));

        rechCont.add(recherche);
        rechCont.add(tf_recherche);

        this.add(rechCont);

        int i = 0;   
        Container list = new Container(BoxLayout.y());
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        list.getAllStyles().setPaddingTop(size / 4);
        if (i!=0)
        {
        ArrayList<Produit> productlist = ServiceProduct.getInstance().getCProducts(i);
        for (Produit p : productlist) {
            Button detailBtn = new Button();
            detailBtn.addActionListener((evt) -> {
                System.out.println("PointerPressedListener");
                new ShowForm(current,p).show();
            });
            Container Prod = new Container(BoxLayout.x(), "Produit");
            SpanLabel nomP = new SpanLabel("Produit:" + p.getNom_Produit());
            SpanLabel etatP = new SpanLabel("Etat:" + p.getEtat_Produit());
            SpanLabel prixP = new SpanLabel("Prix:" + p.getPrix_Produit()); 
            Prod.addAll(nomP,prixP,etatP);
            Prod.getAllStyles().setMargin(size / 8,0,0,0);      
            Prod.setLeadComponent(detailBtn);
            list.add(Prod);
        }
        add(list);
        }
        else
        {
        ArrayList<Produit> productlist = ServiceProduct.getInstance().getAllProducts();
        for (Produit p : productlist) {
            Button detailBtn = new Button();
            detailBtn.addActionListener((evt) -> {
                System.out.println("PointerPressedListener");
                new ShowForm(current,p).show();
            });
//            TODO 
          //  if p.getNcat(p.getId_Categorie()) == d.getTextSelection()
                    
            Container Prod = new Container(BoxLayout.x(), "Produit");
//            IconHolder icon = IconHolder.cas(FontImage.MATERIAL_10K);
                    
            SpanLabel nomP = new SpanLabel("Produit:" + p.getNom_Produit());
            SpanLabel etatP = new SpanLabel("Etat:" + p.getEtat_Produit());
            SpanLabel prixP = new SpanLabel("Prix:" + p.getPrix_Produit());
           
            
            
            Prod.addAll(nomP,prixP,etatP);
            Prod.getAllStyles().setMargin(size / 8,0,0,0);
            
            Prod.setLeadComponent(detailBtn);
            list.add(Prod);
        }
        add(list);
        }
        
      
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



