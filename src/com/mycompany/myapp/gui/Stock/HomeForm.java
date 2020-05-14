/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.Stock;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.gui.BaseForm;

/**
 *
 * @author DELL
 */
public class HomeForm extends BaseForm{
Form current;
    //public HomeForm(Resources res) {
       public HomeForm() {  
        super(BoxLayout.y());
        current=this;
        //super.addSideMenu(res);
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnAddProduct=new Button("Add");
        Button btnListProduct=new Button("liste des produits");
        Button btnChart=new Button("Chart");
        btnAddProduct.addActionListener(e-> new AddProductForm(current).show());
        btnListProduct.addActionListener(e-> new ListProductForm(current).show());
        btnChart.addActionListener(e-> new ChartForm(current).show());
        addAll(btnAddProduct,btnListProduct,btnChart);
     
    }
    
    
}
