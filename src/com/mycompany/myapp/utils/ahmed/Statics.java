/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.utils.ahmed;

import com.mycompany.myapp.entities.mohamed.Personne;
import com.mycompany.myapp.services.mohamed.UserCourant;

/**
 *
 * @author bhk
 */
public class Statics {
    
    public static final String BASE_URL="http://localhost:81/TGTOf/web/app_dev.php";
    public static final String IMAGE_URL="http://localhost:81/TGTOf/web/images";
    public static final String P_IMAGE_URL="http://localhost:81/TGTOf/web";
    public static Personne CurrentUser= UserCourant.ok;
    
}
