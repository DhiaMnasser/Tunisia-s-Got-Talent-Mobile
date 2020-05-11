/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stock.Gui;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import Stock.Services.ServiceProduct;
import Stock.Entities.Produit;
import Stock.Utils.Statics;
/**
 *
 * @author Haddad
 */
public class AddProductForm extends Form {
public AddProductForm(Form previous) {
        setTitle("Add a new product");
        setLayout(BoxLayout.y());    
        TextField tfName = new TextField("","ProductName");
        TextField tfQuantity_Total= new TextField("", "Quantity_Total");
        TextField tfPrice_Product=new TextField("","Price_Product");
        TextField tfSize_Product=new TextField("","Size_Product");
        TextField tfEtat=new TextField("","Etat");
        TextField tfId_Cat=new TextField("","Id_Cat");
        TextField tfURL=new TextField("","Url");
        Button btnValider = new Button("Add product");
     btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfName.getText().length()==0)||(tfQuantity_Total.getText().length()==0))
                     Dialog.show("Alert", "Please fill all the fields", "ok","cancel");
                else
                {                  
                        Produit p = new Produit(
                                 tfName.getText(),
                                 Integer.parseInt(tfId_Cat.getText()),
                                 Integer.parseInt(tfQuantity_Total.getText()),
                                 Float.parseFloat(tfPrice_Product.getText()),
                                 tfEtat.getText(),
                                 tfURL.getText(),
                                 tfSize_Product.getText());
                            if (ServiceProduct.getInstance().addProduct(p))
                            { Dialog.show("success", "connection accepted", "ok", "cancel");
                            System.out.println("😈😈😈😃 ajout avec succes 😃😈😈😈");
                            } else
                            Dialog.show("ERROR", "Server error", "ok", "cancel");
                    } 
                    
                }
                
                
            });
      addAll(tfName,tfQuantity_Total,tfPrice_Product,tfEtat,tfId_Cat,tfSize_Product,tfURL,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
}}
