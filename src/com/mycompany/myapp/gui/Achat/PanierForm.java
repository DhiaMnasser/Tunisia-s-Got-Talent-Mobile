/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.Achat;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.IconHolder;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Entities.Achat.LigneCommande;
import com.mycompany.myapp.Entities.Achat.Panier;
import com.mycompany.myapp.Entities.Stock.Produit;
import com.mycompany.myapp.Services.Achat.LigneCommandeService;
import com.mycompany.myapp.Services.Achat.ServicePanier;
import com.mycompany.myapp.Utils.Statics;
import com.mycompany.myapp.gui.*;
import java.util.ArrayList;
import javafx.scene.paint.Color;

/**
 *
 * @author Klaizer
 */
public class PanierForm extends BaseForm {

    Form current, listLC;
    ArrayList<LigneCommande> LigneCommandeListe = new ArrayList<>();

//    Resources res = UIManager.initFirstTheme("/theme");
    public PanierForm(Resources res) {
        super(BoxLayout.y());
        this.setLayout(new BorderLayout());
        current = this;

        listLC = new Form(BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Panier");
//        setTitleStyle(new Style(Color.TRANSPARENT,Color.WHITESMOKE, Font , 0));
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);

        tb.addSearchCommand(e -> {
        });

        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, res.getImage("city.jpg"), spacer1, "tshirt  ");
        addTab(swipe, res.getImage("city.jpg"), spacer2, "VR");

        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }

        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

        Component.setSameSize(radioContainer, spacer1, spacer2);
        add(BorderLayout.NORTH, LayeredLayout.encloseIn(swipe, radioContainer));

        LigneCommandeListe = LigneCommandeService.getInstance().getLigneCommandesByCurrentPanier();
        for (LigneCommande lc : LigneCommandeListe) {
            Button detailBtn = new Button();
            detailBtn.addActionListener((evt) -> {
                System.out.println("PointerPressedListener");

                new LigneCommandeForm(res, lc).show();
            });
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
            FloatingActionButton delete = FloatingActionButton.createFAB(FontImage.MATERIAL_DELETE_OUTLINE);
            delete.addActionListener((evt) -> {
                LigneCommandeService.getInstance().deleteLigneCommande(lc);
                new PanierForm(res).show();

            });

            Container del = new Container(new BorderLayout());
            del.add(BorderLayout.SOUTH, delete);
            Container detai = new Container(BoxLayout.x());
            detai.addAll(img, details);
            detai.setLeadComponent(detailBtn);
            ligneCommande.addAll(detai, del);

            listLC.add(ligneCommande);
        }

        add(CENTER, listLC);

        Panier pan = ServicePanier.getInstance().getPanierByUser();
        Label prixTotal = new Label("Prix Total : " + pan.getPrixTotal() + "DT");
        prixTotal.getAllStyles().setAlignment(CENTER);
        prixTotal.getAllStyles().setMarginBottom(10);
//        prixTotal.getAllStyles().;
        Button checkoutBtn = new Button("Checkout");
        checkoutBtn.addActionListener((ActionEvent evt) -> {
            if (!LigneCommandeListe.isEmpty()) {
                new CheckoutForm(res, pan).show();
            } else {
                Dialog.show("Erreur", "Panier Vide !", "OK", null);

            }
        });
        Container bottom = new Container(BoxLayout.y());
        bottom.addAll(prixTotal, checkoutBtn);
        add(BorderLayout.SOUTH, bottom);
//        FloatingActionButton floating = FloatingActionButton.createBadge("checkout");
//        floating.setHeight(getHeight());
//        floating.getAllStyles().setPadding(3, 3, 3, 3);
//        floating.setSize(new Dimension(12, 12));
//        Image img = res.getImage("profile-background.jpg");
//        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
//            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
//        }
    }

    private void addTab(Tabs swipe, Image img, Label spacer, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if (img.getHeight() < size) {
            img = img.scaledHeight(size);
        }

        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");

        Container page1
                = LayeredLayout.encloseIn(
                        image,
                        overlay,
                        BorderLayout.south(
                                BoxLayout.encloseY(
                                        new SpanLabel(text, "LargeWhiteText"),
                                        spacer
                                )
                        )
                );

        swipe.addTab("", page1);
    }

}
