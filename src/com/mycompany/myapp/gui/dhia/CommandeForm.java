/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.dhia;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.ahmed.Produit;
import com.mycompany.myapp.entities.dhia.Commande;
import com.mycompany.myapp.entities.dhia.LigneCommande;
import com.mycompany.myapp.gui.mohamed.BaseForm;
import com.mycompany.myapp.services.ahmed.ServiceProduct;
import com.mycompany.myapp.services.dhia.LigneCommandeService;
import com.mycompany.myapp.utils.ahmed.Statics;
import java.util.ArrayList;

/**
 *
 * @author Klaizer
 */
public class CommandeForm extends BaseForm {

    Form current;
    boolean show = true;
    Form ligneCommandeListCont = new Form(BoxLayout.y());

    public CommandeForm(Resources res, Commande cmd) {

        super(BoxLayout.y());
//        this.setLayout(new BorderLayout());
        current = this;

        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Commande " + cmd.getId());
        getContentPane().setScrollVisible(false);
        setUIID("SignIn");

        super.addSideMenu(res);

        tb.addSearchCommand(e -> {
        });

        Container detailsCmd = new Container(BoxLayout.y());
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());

        detailsCmd.getAllStyles().setPaddingTop(size / 5);

        getToolbar().addCommandToRightBar("Back", null, (ev) -> {
            new ListCommandesForm(res).show();

        });
//        SpanLabel numCmd = new SpanLabel("Numero Commande :" + cmd.getId());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SpanLabel date = new SpanLabel("Date :" + format.format(cmd.getDate()));
        FontImage.setMaterialIcon(date, FontImage.MATERIAL_DATE_RANGE, 6);

        String etatText = (cmd.getEtat() ? "Validee" : "En cours");

        SpanLabel etat = new SpanLabel("Etat :" + etatText);
        FontImage.setMaterialIcon(etat, FontImage.MATERIAL_INFO, 6);

        SpanLabel address = new SpanLabel("Adresse :" + cmd.getAddress());
        FontImage.setMaterialIcon(address, FontImage.MATERIAL_ROOM, 6);

        SpanLabel tel = new SpanLabel("Tel :" + cmd.getTel());
        FontImage.setMaterialIcon(tel, FontImage.MATERIAL_PHONE, 6);

        SpanLabel VotreProduits = new SpanLabel("Afficher List des Produits");
        FontImage.setMaterialIcon(VotreProduits, FontImage.MATERIAL_LIST, 6);
        Button showProduits = new Button();

        System.out.println("show etat" + show);
        VotreProduits.setLeadComponent(showProduits);
        detailsCmd.addAll(date, etat, address, tel, VotreProduits);
        add(detailsCmd);
        showProduits.addActionListener((evt) -> {
            if (!show) {
                removeComponent(ligneCommandeListCont);
                show = true;
                VotreProduits.setText("Afficher List des Produits");

            } else {
                add(ligneCommandeListCont);
                show = false;
                VotreProduits.setText("Masquer List des Produits");

            }
            revalidate();
        });

        ArrayList<LigneCommande> LigneCommandeListe = LigneCommandeService.getInstance().getLigneCommandesByPanier(cmd.getIdPanier());
        for (LigneCommande lc : LigneCommandeListe) {
    Container ligneCommande = new Container(BoxLayout.x(), "ligneCommande");

//            TODO 
            Produit p = ServiceProduct.getInstance().getOProducts(lc.getIdproduit());

//        TODO
//            String urlImage = Statics.IMAGE_URL + "/prd3.jpg";
            String urlImage = Statics.P_IMAGE_URL+p.getUrl();
            EncodedImage enco = EncodedImage.createFromImage(res.getImage("icon.png"), false);
            URLImage imgser = URLImage.createToStorage(enco, "" + p.getUrl(), urlImage);
//            URLImage imgser = URLImage.createToStorage(enco, "" + "prd3.jpg", urlImage);
            ImageViewer img = new ImageViewer(imgser);

            Container details = new Container(BoxLayout.y());

            SpanLabel nomProduit = new SpanLabel(lc.getNomProduit());
            SpanLabel qte = new SpanLabel("Quantite :" + lc.getQuantite());
//            TODO replace the product price 
            SpanLabel prix = new SpanLabel("Prix :" + lc.getQuantite()*p.getPrix_Produit());
//            SpanLabel prix = new SpanLabel("Prix :" + lc.getQuantite() * 12);
            details.addAll(nomProduit, qte, prix);

            ligneCommande.addAll(img, details);
            ligneCommandeListCont.add(ligneCommande);
        }

        System.out.println("CommandeForm");

    }
}
