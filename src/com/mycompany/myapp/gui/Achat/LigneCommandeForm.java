/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.Achat;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Entities.Achat.LigneCommande;
import com.mycompany.myapp.Services.Achat.LigneCommandeService;
import com.mycompany.myapp.Utils.Statics;
import com.mycompany.myapp.gui.BaseForm;

/**
 *
 * @author Klaizer
 */
public class LigneCommandeForm extends BaseForm {

    Form current, F2;

    public LigneCommandeForm(Resources res, LigneCommande lc) {
        super(BoxLayout.y());
        this.setLayout(new BorderLayout());
        current = this;
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ordre");
//        setTitleStyle(new Style(Color.TRANSPARENT,Color.WHITESMOKE, Font , 0));
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);

        tb.addSearchCommand(e -> {
        });

        Container ligneCmd = new Container(BoxLayout.y());

//        TODO replace url
        String urlImage = Statics.IMAGE_URL + "/dev-img.jpg";
//            Produit p = ServiceProduit.getInstance().getProduitById(lc.getIdproduit());
//            String urlImage = Statics.IMAGE_URL+p.getUrl();
        Image placeholder = Image.createImage(140, 50);
        EncodedImage enco = EncodedImage.createFromImage(placeholder, true);
//        TODO
//            URLImage imgser = URLImage.createToStorage(enco, "" + p.getNom(), urlImage);
        URLImage imgser = URLImage.createToStorage(enco, "" + "dev-img.jpg", urlImage);
        ImageViewer img = new ImageViewer(imgser);
        img.setWidth(500);
        SpanLabel nomProduit = new SpanLabel(lc.getNomProduit());

        TextField quantite = new TextField(lc.getQuantite());
        quantite.setHint("Quantite :" + lc.getQuantite());

//            TODO replace the product price 
//            Label prix = new Label("Prix :" + lc.getQuantite()*p.getPrix());
        SpanLabel prix = new SpanLabel("Prix :" + lc.getQuantite() * 12);
        ligneCmd.addAll(img, nomProduit, quantite, prix);
        Button checkoutBtn = new Button("Sauve guarder");
        checkoutBtn.addActionListener((evt) -> {
            int qte;
            String msg;
            if (quantite.getText() != "") {
                qte = Integer.parseInt(quantite.getText());
                msg = "La modification est enregistre";
            } else {
                qte = lc.getQuantite();
                msg = "Aucune Modification";
            }
            if (qte >= 1 && qte <= 3) {
                lc.setQuantite(qte);
                LigneCommandeService.getInstance().editLigneCommande(lc);
                Dialog.show("Succee", msg, "OK", null);

                new PanierForm(res).show();
            } else {
                Dialog.show("Erreur", "La Quantite Doit Etre Inferieur a 3", "OK", null);

            }

        });;
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());

        ligneCmd.getAllStyles().setPaddingTop(size / 2);
        add(CENTER, ligneCmd);
        add(BorderLayout.SOUTH, checkoutBtn);

        System.out.println("LigneCommandeForm");
    }

}
