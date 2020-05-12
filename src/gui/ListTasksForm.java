/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entities.Evenement;
import Services.EvenementService;
import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;


/**
 *
 * @author bhk
 */
public class ListTasksForm extends Form{

    public ListTasksForm(Form previous) {
        setTitle("List tasks");
        
        /*SpanLabel sp = new SpanLabel();
        sp.setText(EvenementService.getInstance().getAllevents().toString());
        add(sp);
*/Evenement s = new Evenement();
        SpanLabel sp = new SpanLabel();
        sp.setText(EvenementService.getInstance().geteventbyid(s,4).toString());
        
        add(sp);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
  
    
}
