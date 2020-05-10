/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.Achat;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
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

    Form current;
    ArrayList<Commande> CommandeListe = new ArrayList<>();

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
        Container list = new Container(BoxLayout.y());
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());

        list.getAllStyles().setPaddingTop(size / 4);
        CommandeListe = CommandeService.getInstance().getCommandesByUser();
        for (Commande cmd : CommandeListe) {
            Button detailBtn = new Button();
            detailBtn.addActionListener((evt) -> {
                System.out.println("PointerPressedListener");

                new CommandeForm(res, cmd).show();
            });
//            TODO 

            Container Commande = new Container(BoxLayout.x(), "Commande");
//            IconHolder icon = IconHolder.cas(FontImage.MATERIAL_10K);
                    
            SpanLabel nomCmd = new SpanLabel("Commande " + cmd.getId());
            SpanLabel date = new SpanLabel("" + cmd.getDate());
            
            Commande.addAll(nomCmd, date);
            Commande.getAllStyles().setMargin(size / 8,0,0,0);

            Commande.setLeadComponent(detailBtn);
            list.add(Commande);
        }
        add(list);

    }
}
