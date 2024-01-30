/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import annoted.ColumnField;
import annoted.TableAnnotation;
import database.ConnectionBase;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import mapping.BddObject;

/**
 *
 * @author aram
 */
@TableAnnotation(nameTable = "achat_matiere", sequence = "achat_seq", prefix = "ACH_")
public class AchatMatiere {
    @ColumnField(column = "id_achat_matiere", primary_key = true, is_increment = true)
    private String idAchatMatiere;
    
    @ColumnField(column = "id_matiere_premiere")
    private String idmatiere;
    
    @ColumnField(column = "id_fournisseur")
    private String idFournisseur;
    
    @ColumnField(column = "price_unit")
    private Double priceUnit;
    
    @ColumnField(column = "date_achat")
    private java.sql.Date dateAchat;
    
    private MatierePremiere my_matiere;
    
    
    // Constructors
    public AchatMatiere() {
    }

    public AchatMatiere(String idmatiere, String idFournisseur, String priceUnit) throws Exception{
        try {
            this.setIdmatiere(idmatiere);
            this.setIdFournisseur(idFournisseur);
            this.setPriceUnit(priceUnit);
            Calendar calendar = Calendar.getInstance();
            java.sql.Date now = new Date(calendar.getTimeInMillis());
            this.setDateAchat(now);
        } catch (Exception e) {
            throw new Exception("Error on setting constructing the achat");
        }
    }
    
    
    // GETTER AND SETTERS

    public Date getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(Date dateAchat) {
        this.dateAchat = dateAchat;
    }
    
    
    public String getIdAchatMatiere() {
        return idAchatMatiere;
    }

    public void setIdAchatMatiere(String idAchatMatiere) {
        this.idAchatMatiere = idAchatMatiere;
    }

    public String getIdmatiere() {
        return idmatiere;
    }

    public void setIdmatiere(String idmatiere) {
        this.idmatiere = idmatiere;
    }

    public String getIdFournisseur() {
        return idFournisseur;
    }

    public void setIdFournisseur(String idFournisseur) {
        this.idFournisseur = idFournisseur;
    }

    public Double getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(Double priceUnit) {
        this.priceUnit = priceUnit;
    }
    
    public void setPriceUnit(String priceHtml) throws Exception{
        try {
            Double myValue = Double.valueOf(priceHtml);
            this.setPriceUnit(myValue);
        } catch (Exception e) {
            throw new Exception("Error on setting the price of the matiere"+ e.getMessage());
        }
    }

    public MatierePremiere getMy_matiere() throws Exception{
        if(this.getIdmatiere()== null) return null;
        try {
            MatierePremiere mp = new MatierePremiere();
            mp.setIdmatiere(this.getIdmatiere());
            return BddObject.findById(mp, null);
        } catch (Exception e) {
            throw new Exception("Error on getting the matiere of the achat. "+e.getMessage());
        }
    }

    public void setMy_matiere(MatierePremiere my_matiere) {
        this.my_matiere = my_matiere;
    }
    
    
    
}
