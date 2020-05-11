/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stock.Entities;
import java.util.*;


/**
 *
 * @author Haddad
 */

public class Photo {
    private int Id_Photo;
    private int Id_Product;
    private String URL;
    private String Nom;

    public Photo() {
    }

    public Photo(int Id_Photo, int Id_Product, String URL, String Nom) {
        this.Id_Photo = Id_Photo;
        this.Id_Product = Id_Product;
        this.URL = URL;
        this.Nom = Nom;
    }

    public int getId_Photo() {
        return Id_Photo;
    }

    public void setId_Photo(int Id_Photo) {
        this.Id_Photo = Id_Photo;
    }

    public int getId_Product() {
        return Id_Product;
    }

    public void setId_Product(int Id_Product) {
        this.Id_Product = Id_Product;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    @Override
    public String toString() {
        return "Photo{" + "Id_Photo=" + Id_Photo + ", Id_Product=" + Id_Product + ", URL=" + URL + ", Nom=" + Nom + '}';
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Photo other = (Photo) obj;
        if (this.Id_Photo != other.Id_Photo) {
            return false;
        }
        if (this.Id_Product != other.Id_Product) {
            return false;
        }
        if (!Objects.equals(this.URL, other.URL)) {
            return false;
        }
        if (!Objects.equals(this.Nom, other.Nom)) {
            return false;
        }
        return true;
    }
    
    
}
