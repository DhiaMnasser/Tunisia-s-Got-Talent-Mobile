/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.ahmed;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.gui.mohamed.BaseForm;

/**
 *
 * @author DELL
 */
public class HomeForm extends BaseForm{
Form current;
    public HomeForm(Resources res) {
        
        super(BoxLayout.y());
        current=this;
        super.addSideMenu(res);
        setTitle("Home");
        setLayout(BoxLayout.y());
        setUIID("SignIn");
        
        add(new Label("Choose an option"));
        Button btnAddProduct=new Button("Add");
        Button btnListProduct=new Button("liste des produits");
        Button btnChart=new Button("Chart");
        btnAddProduct.addActionListener(e-> new AddProductForm(current).show());
        btnListProduct.addActionListener(e-> new ListProductForm(res,current).show());
        btnChart.addActionListener(e-> new ChartForm(current).show());
        addAll(btnAddProduct,btnListProduct,btnChart);
     
    }
    
    
}
