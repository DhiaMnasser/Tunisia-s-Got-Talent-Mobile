/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.Achat;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.IconHolder;
import com.codename1.ui.Image;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Entities.Achat.Commande;
import com.mycompany.myapp.Entities.Achat.LigneCommande;
import com.mycompany.myapp.Services.Achat.CommandeService;
import com.mycompany.myapp.Services.Achat.LigneCommandeService;
import com.mycompany.myapp.Utils.Statics;
import com.mycompany.myapp.gui.BaseForm;
import java.util.ArrayList;

/**
 *
 * @author Klaizer
 */
public class ListCommandesForm extends BaseForm {

    Form current, listForm;
    ArrayList<Commande> list = new ArrayList<>();

    public ListCommandesForm(Resources res) {

        super(BoxLayout.y());

        current = this;
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Mes Commandes");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);

        tb.addSearchCommand(e -> {
        });

        String[] characters = {"All", "en cours", "validee"};
        int[] actors = {0, 1, 2};
        MultiButton b = new MultiButton("All");
        FontImage.setMaterialIcon(b, FontImage.MATERIAL_SORT, 6);

        b.getAllStyles().setPaddingTop(12);
        b.addActionListener(e -> {
            Dialog d = new Dialog();
            d.setLayout(BoxLayout.y());
            d.getContentPane().setScrollableY(false);
            for (int iter = 0; iter < characters.length; iter++) {
                MultiButton mb = new MultiButton(characters[iter]);
                mb.setName(characters[iter]);
//        mb.setIcon(pictures[iter]);
                d.add(mb);
                mb.addActionListener(ee -> {
//                    revalidate();
                    b.setTextLine1(mb.getTextLine1());
//                    b.setTextLine2(mb.getTextLine2());
//                    b.setIcon(mb.getIcon());
                    d.dispose();
                    listForm.removeAll();

//                    list = filterCmds(Integer.parseInt(mb.getTextLine2()));
                    System.out.println(mb);
                    list = filterCmds(mb.getName());
                    ajouterCmds(res);
                    b.revalidate();
                });
            }
            d.showPopupDialog(b);
        });
        add(b);

        listForm = new Form(BoxLayout.y());
        add(listForm);
        list = filterCmds("All");
        ajouterCmds(res);

    }

    public ArrayList<Commande> filterCmds(String state) {

        ArrayList<Commande> filtered = new ArrayList<>();
        ArrayList<Commande> CommandeListe = CommandeService.getInstance().getCommandesByUser();

        for (Commande cmd : CommandeListe) {
            if (state == "en cours") {
                if (!cmd.getEtat()) {
                    filtered.add(cmd);
                }
            } else if (state == "validee") {
                if (cmd.getEtat()) {
                    filtered.add(cmd);
                }
            } else if (state == "All") {
                filtered.add(cmd);
            }

        }
        System.out.println(filtered);
        return filtered;

    }

    public void ajouterCmds(Resources res) {
        Container filtered = new Container(BoxLayout.y());
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        removeComponent(filtered);
        filtered.getAllStyles().setPaddingTop(size / 4);
        System.out.println("filtered list " + list);

        for (Commande cmd : list) {
            Button detailBtn = new Button();

            detailBtn.addActionListener((evt) -> {
                System.out.println("PointerPressedListener");

                new CommandeForm(res, cmd).show();
            });


            Container Commande = new Container(BoxLayout.x(), "Commande" + cmd.getId());
//            IconHolder icon = IconHolder.cas(FontImage.MATERIAL_10K);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            SpanLabel nomCmd = new SpanLabel("Cmd " + cmd.getId());
            FontImage.setMaterialIcon(nomCmd, FontImage.MATERIAL_DATE_RANGE, 6);
            SpanLabel date = new SpanLabel("" + format.format(cmd.getDate()));
            SpanLabel etat = new SpanLabel(cmd.getEtat() ? "Validee" : "En cours");

            Commande.addAll(nomCmd, date, etat);
            Commande.getAllStyles().setMargin(size / 8, 0, 0, 0);

            Commande.setLeadComponent(detailBtn);
            filtered.add(Commande);
            filtered.add(createLineSeparator(0xeeeeee));

        }
        listForm.add(filtered);
    }

}
