/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import annoted.ColumnField;
import annoted.TableAnnotation;
import java.sql.Date;
import mapping.BddObject;
import utilities.DatePattern;
import utilities.DateUtil;

/**
 *
 * @author aram
 */
@TableAnnotation(nameTable = "paiement", sequence = "paiement_seq", prefix = "PAY_")
public class Paiement {
    @ColumnField(column = "id_paiement", primary_key = true, is_increment = true)
    private String idPaiement;
    
    @ColumnField(column = "id_facture")
    private String idFacture;
    
    @ColumnField(column = "date_paiement")
    private java.sql.Date datePaiement;
    
    @ColumnField(column = "montant")
    private Double montant;
    
    private Facture motherFacture;
    
    
    
    
    // CS
    public Paiement() {
    }

    public Paiement(String idFacture, String datePaiement, String montant) throws Exception{
        try {
            this.setIdFacture(idFacture);
            this.setDatePaiement(datePaiement);
            this.setMontant(montant);
        } catch (Exception e) {
            throw new Exception("Error on constructing the payment. " + e.getMessage());
        }   
    }
    
    
    
    
    // GETTERS AND SETTERS
    public String getIdPaiement() {
        return idPaiement;
    }

    public void setIdPaiement(String idPaiement) {
        this.idPaiement = idPaiement;
    }

    public String getIdFacture() {
        return idFacture;
    }

    public void setIdFacture(String idFacture) {
        this.idFacture = idFacture;
    }

    public Date getDatePaiement() {
        return datePaiement;
    }

    public void setDatePaiement(Date datePaiement) {
        this.datePaiement = datePaiement;
    }
    
    public void setDatePaiement(String dtnHml) throws Exception{
        try {
            java.sql.Date date = DateUtil.stringToSqlDate(java.sql.Date.class, dtnHml, DatePattern.YYYY_MM_DD);
            this.setDatePaiement(date);
        } catch (Exception e) {
            throw new Exception("Error on setting the date of payment. "+e.getMessage());
        }
    } 

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }
    
   public void setMontant(String montantHtml) throws Exception{
       try {
            Double value = Double.valueOf(montantHtml);
            this.setMontant(value);
        } catch (Exception e) {
            throw new Exception("Error on setting the montant of the payment. "+e.getMessage());
        }
    }

    public Facture getMotherFacture() throws Exception{
        
        if(this.getIdFacture()== null) return null;
        try {
            Facture mp = new Facture();
            mp.setIdFacture(this.getIdFacture());
            return BddObject.findById(mp, null);
        } catch (Exception e) {
            throw new Exception("Error on getting the facture of payment. "+e.getMessage());
        }
    }

    public void setMotherFacture(Facture motherFacture) {
        this.motherFacture = motherFacture;
    }
    
    
    
}
