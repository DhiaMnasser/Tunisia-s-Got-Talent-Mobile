/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stock.Gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import Stock.Services.ServiceProduct;
import Stock.Entities.Produit;
import Stock.Utils.Statics;

/**
 *
 * @author DELL
 */
public class ListProductForm extends Form{
      public ListProductForm(Form previous) {
        setTitle("List products");
        
        SpanLabel sp = new SpanLabel();
        sp.setText(ServiceProduct.getInstance().getAllProducts().toString());
        add(sp);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    
}


