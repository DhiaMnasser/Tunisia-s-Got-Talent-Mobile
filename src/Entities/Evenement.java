/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;




import java.util.Date;

/**
 *
 * @author Achraf
 */
public class Evenement {
     private int id;
    private String Duree, Gagnant ,nomevent , image;
    private Date Date_d,Date_f;
    private int MaxParticipants, Etat,region_id;


    
    /****************************************************/
    
        public Evenement() {
    }
public Evenement (String nomevent, String Duree,int etat)
{
    this.nomevent=nomevent;
    this.Duree=Duree;
    this.Etat=etat;
}
    public Evenement(int id, String Duree, String Gagnant, String nomevent, String image, Date Date_d, Date Date_f, int MaxParticipants, int Etat,int region_id) {
        this.id = id;
        this.Duree = Duree;
        this.Gagnant = Gagnant;
        this.nomevent = nomevent;
        this.image = image;
        this.Date_d = Date_d;
        this.Date_f = Date_f;
        this.MaxParticipants = MaxParticipants;
        this.Etat = Etat;
        this.region_id=region_id;
    }
    public Evenement(int id, String Duree, String Gagnant, String nomevent, String image, Date Date_d, Date Date_f, int MaxParticipants, int Etat) {
        this.id = id;
        this.Duree = Duree;
        this.Gagnant = Gagnant;
        this.nomevent = nomevent;
        this.image = image;
        this.Date_d = Date_d;
        this.Date_f = Date_f;
        this.MaxParticipants = MaxParticipants;
        this.Etat = Etat;
        
    }
    public Evenement (String nomevent,String Duree, String Gagnant, String image, Date Date_d, Date Date_f, int MaxParticipants, int Etat)
    {
        
    this.nomevent=nomevent ;
    this.Duree = Duree;
        this.Gagnant = Gagnant;
        
        this.image = image;
        this.Date_d = Date_d;
        this.Date_f = Date_f;
        this.MaxParticipants = MaxParticipants;
        this.Etat = Etat;
    }
public Evenement (String nomevent,String Duree, String Gagnant, String image, Date Date_d, Date Date_f, int MaxParticipants, int Etat,int region_id)
    {
        
    this.nomevent=nomevent ;
    this.Duree = Duree;
        this.Gagnant = Gagnant;
        
        this.image = image;
        this.Date_d = Date_d;
        this.Date_f = Date_f;
        this.MaxParticipants = MaxParticipants;
        this.Etat = Etat;
        this.region_id = region_id;
    }
    public Evenement(String Duree, String Gagnant,String nomevent, String image) {
        this.Duree = Duree;
        this.Gagnant = Gagnant;
        this.nomevent=nomevent;
        this.image = image;
    }

    public Evenement(int id) {
        this.id=id;
    }

    

    public int getRegion_id() {
        return region_id;
    }

    public void setRegion_id(int region_id) {
        this.region_id = region_id;
    }
    
    
    
    /**************************************************/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDuree() {
        return Duree;
    }

    public void setDuree(String Duree) {
        this.Duree = Duree;
    }

    public String getGagnant() {
        return Gagnant;
    }

    public void setGagnant(String Gagnant) {
        this.Gagnant = Gagnant;
    }

    public String getNomevent() {
        return nomevent;
    }

    public void setNomevent(String nomevent) {
        this.nomevent = nomevent;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDate_d() {
        return Date_d;
    }

    public Date setDate_d(Date Date_d) {
        return this.Date_d = Date_d;
    }

    public Date getDate_f() {
        return Date_f;
    }

    public Date setDate_f(Date Date_f) {
        return this.Date_f = Date_f;
    }

    public int getMaxParticipants() {
        return MaxParticipants;
    }

    public void setMaxParticipants(int MaxParticipants) {
        this.MaxParticipants = MaxParticipants;
    }

    public int getEtat() {
        return Etat;
    }

    public void setEtat(int Etat) {
        this.Etat = Etat;
    }

    
    
    /***************************************************************/
    
    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", Duree=" + Duree + ", Gagnant=" + Gagnant + ", nomevent=" + nomevent + ", image=" + image + ", Date_d=" + Date_d + ", Date_f=" + Date_f + ", MaxParticipants=" + MaxParticipants + ", Etat=" + Etat +", Region= "+ region_id+'}';
    }
     
        
    
}
