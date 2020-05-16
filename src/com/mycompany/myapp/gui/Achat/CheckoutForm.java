/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.Achat;

import com.codename1.l10n.ParseException;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Entities.Achat.Commande;
import com.mycompany.myapp.Entities.Achat.Panier;
//import com.mycompany.myapp.Entities.Achat.SendEmailTLS;
import com.mycompany.myapp.Entities.Achat.StripePayment;
import com.mycompany.myapp.Services.Achat.CommandeService;
import com.mycompany.myapp.Services.Achat.ServicePanier;
import com.mycompany.myapp.Utils.Statics;
import com.stripe.model.Charge;
import com.stripe.model.Token;
import com.codename1.messaging.Message;

/**
 *
 * @author Klaizer
 */
public class CheckoutForm extends Form {

    Form current, F2;
    ServicePanier pans;

    public CheckoutForm(Resources res, Panier pan) {

        this.setLayout(new BorderLayout());

        TextField address = new TextField("", "adresse");
        TextField tel = new TextField("", "tel");
        TextField num = new TextField("", "Numero carte");
        TextField mois = new TextField("", "mois");
        TextField annee = new TextField("", "annee");
        TextField cvv = new TextField("", "cvv");
        Button payerBtn = new Button("Payer");
//        payerBtn.

   getToolbar().addCommandToRightBar("Back", null, (ev) -> {
            new PanierForm(res).show();

        });
        payerBtn.addActionListener((evt) -> {
            try {
                int tele = Integer.parseInt(mois.getText());
                if (num.getText() == "" || mois.getText() == "" || annee.getText() == "" || tel.getText().length() != 8 || address.getText().length() < 5) {
                    Dialog.show("Erreur", "Merci de vérifier vos informations", "OK", null);
                } else {

                    int mois0 = Integer.parseInt(mois.getText());
                    int annee0 = Integer.parseInt(annee.getText());

//TODO replace token
                    Token token = StripePayment.getToken("pk_test_VkxHIqxNUhztx7sLrBe14vNu00HVIf29N2", num.getText(), mois0, annee0, cvv.getText(), Statics.CurrentUser.getEmail());
//                    Token token = StripePayment.getToken("pk_test_VkxHIqxNUhztx7sLrBe14vNu00HVIf29N2", num.getText(), mois0, annee0, cvv.getText(), UserCourant.ok.getEmail());
                    if (token != null) {
                        Charge ch = StripePayment.ChargePayement("rk_test_oGfrFNOjpnRPklUVzjelPHgf", "usd", "tok_visa", pan.getPrixTotal() * 100, "sk_test_lE9pTHIXFMFcZr7CZvTS33wM00fIb8c2WL", num.getText(), mois0, annee0, cvv.getText(), Statics.CurrentUser.getEmail());
                        Commande cmd = new Commande(Statics.CurrentUser.getId(), pan.getId(), address.getText(), tel.getText());
                        Commande commande = CommandeService.getInstance().addCommande(cmd);

                        Message m = new Message("");
                        m.setMimeType(Message.MIME_TEXT);
//                        boolean success = m.sendMessageViaCloudSync("TGT", Statics.CurrentUser.getEmail(), "Name Of User","Votre Commande sur TGT" ,"le lien vers votre commande : \n "+Statics.BASE_URL+"/commande/"+commande.getId());
//                        new SendEmailTLS().sendCommandeConfrimationMail(Statics.CurrentUser.getEmail(), Statics.BASE_URL + "/commande/" + commande.getId());
                        new CommandeForm(res, commande).show();

                        Dialog.show("Succès", "Le paiement a été effectué avec succès", "OK", null);

                    } else {
                        Dialog.show("Erreur", "Merci de vérifier vos informations", "OK", null);
                    }
                }
            } catch (NumberFormatException ex) {

                Dialog.show("Erreur", "Merci de vérifier vos informations", "OK", null);

                System.out.println(ex);
            }
        });
        F2 = new Form();
        F2.addAll(address, tel, num, mois, annee, cvv);
        add(CENTER, F2);
        add(BorderLayout.SOUTH, payerBtn);

    }

}
