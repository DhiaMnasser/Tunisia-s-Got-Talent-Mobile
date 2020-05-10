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
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Entities.Achat.Commande;
import com.mycompany.myapp.Entities.Achat.LigneCommande;
import com.mycompany.myapp.Services.Achat.LigneCommandeService;
import com.mycompany.myapp.Utils.Statics;
import com.mycompany.myapp.gui.BaseForm;
import java.util.ArrayList;

/**
 *
 * @author Klaizer
 */
public class CommandeForm extends BaseForm {

    Form current;

    public CommandeForm(Resources res, Commande cmd) {

        super(BoxLayout.y());
//        this.setLayout(new BorderLayout());
        current = this;

        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Commande " + cmd.getId());
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);

        tb.addSearchCommand(e -> {
        });

        Container detailsCmd = new Container(BoxLayout.y());
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());

        detailsCmd.getAllStyles().setPaddingTop(size / 4);
        SpanLabel numCmd = new SpanLabel("Numero Commande :" + cmd.getId());
        SpanLabel date = new SpanLabel("Date :" + cmd.getDate());
        String etatText = (cmd.getEtat() ? "Validee" : "En cour");

        SpanLabel etat = new SpanLabel("Etat :" + etatText);
        SpanLabel address = new SpanLabel("Adresse :" + cmd.getAddress());
        SpanLabel tel = new SpanLabel("Tel :" + cmd.getTel());
        SpanLabel VotreProduits = new SpanLabel(" Votre Produits :");
        detailsCmd.addAll(numCmd, date, etat, address, tel, VotreProduits);
        add(detailsCmd);

        ArrayList<LigneCommande> LigneCommandeListe = LigneCommandeService.getInstance().getLigneCommandesByPanier(cmd.getIdPanier());
        for (LigneCommande lc : LigneCommandeListe) {

//            TODO 
//            Produit p = ServiceProduit.getInstance().getProduitById(lc.getIdproduit())
            String urlImage = Statics.IMAGE_URL + "/dev-img.jpg";

//        TODO
//            String urlImage = Statics.IMAGE_URL+p.getUrl();
            EncodedImage enco = EncodedImage.createFromImage(res.getImage("icon.png"), false);
//            URLImage imgser = URLImage.createToStorage(enco, "" + p.getNom(), urlImage);
            URLImage imgser = URLImage.createToStorage(enco, "" + "dev-img.jpg", urlImage);
            ImageViewer img = new ImageViewer(imgser);
            Container ligneCommande = new Container(BoxLayout.x(), "ligneCommande");
            Container details = new Container(BoxLayout.y());

            SpanLabel nomProduit = new SpanLabel(lc.getNomProduit());
            SpanLabel qte = new SpanLabel("Quantite :" + lc.getQuantite());
//            TODO replace the product price 
//            Label prix = new Label("Prix :" + lc.getQuantite()*p.getPrix());
            SpanLabel prix = new SpanLabel("Prix :" + lc.getQuantite() * 12);
            details.addAll(nomProduit, qte, prix);

            ligneCommande.addAll(img, details);
            add(ligneCommande);
        }
        System.out.println("CommandeForm");

    }
}
