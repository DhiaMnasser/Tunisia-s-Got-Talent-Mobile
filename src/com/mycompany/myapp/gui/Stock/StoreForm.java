/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.Stock;

import com.codename1.ui.Form;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Klaizer
 */
public class StoreForm extends Form{
    Form current, F2;
    Resources theme = UIManager.initFirstTheme("/theme");

    public StoreForm(Form previous) {
        
        current = this ;
    }
    
    
}
