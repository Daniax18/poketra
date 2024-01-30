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
import java.util.Calendar;
import java.util.List;
import mapping.BddObject;
import utilities.Ordering;

/**
 *
 * @author aram
 */
@TableAnnotation(nameTable = "achat_mp", sequence = "achat_mp_seq", prefix = "BUY_")
public class BuyMatiere {
    @ColumnField(column = "id_achat_mp", primary_key = true, is_increment = true)
    private String idBuy;
    
    @ColumnField(column = "id_matiere_premiere")
    private String idMatierePremiere;
    
    @ColumnField(column = "id_fournisseur")
    private String idFournisseur;
    
    @ColumnField(column = "date_achat_mp")
    private java.sql.Date dateAchat;
    
    @ColumnField(column = "qty")
    private Double qty;
    
    private MatierePremiere my_matiere;

    // MAKE AN ACHAT
    public void makeAchatMatiere(Connection connection) throws Exception{
        double result = 0;
        boolean isOpen = false;
        ConnectionBase cb = new ConnectionBase();
        if(connection == null){
            connection = cb.dbConnect();     // If it is null, creating connection
        }else{
            isOpen = true;
        }
       
        try {
            Mouvement mvn = new Mouvement(this.getIdMatierePremiere(), this.getQty(), 0);
            
            BddObject.insertInDatabase(mvn, connection);
            BddObject.insertInDatabase(this, connection);
            if(isOpen == false) connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
            throw new Exception("Error on saving the achat FRN. "+ e.getMessage());
        } finally{
            if(isOpen == false) connection.close();
        }
    }
    
    
    // CONSTRUCTORS
    
    
    public BuyMatiere(){
    }

    public BuyMatiere(String idMatierePremiere, String idFournisseur, String qty) throws Exception {
        try {
            this.setIdMatierePremiere(idMatierePremiere);
            this.setIdFournisseur(idFournisseur);
            this.setQty(qty);
            Calendar calendar = Calendar.getInstance();
            java.sql.Date now = new Date(calendar.getTimeInMillis());
            this.setDateAchat(now);
        } catch (Exception e) {
            throw new Exception("Error on setting constructing the ACHAT");
        }               
    }
    
    
    
    // GETTERS AND SETTERS

    public String getIdBuy() {
        return idBuy;
    }

    public void setIdBuy(String idBuy) {
        this.idBuy = idBuy;
    }

    public String getIdMatierePremiere() {
        return idMatierePremiere;
    }

    public void setIdMatierePremiere(String idMatierePremiere) {
        this.idMatierePremiere = idMatierePremiere;
    }

    public String getIdFournisseur() {
        return idFournisseur;
    }

    public void setIdFournisseur(String idFournisseur) {
        this.idFournisseur = idFournisseur;
    }

    public Date getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(Date dateAchat) {
        this.dateAchat = dateAchat;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }
    
    public void setQty(String qtyHtml) throws Exception{
        try {
            Double myValue = Double.valueOf(qtyHtml);
            this.setQty(myValue);
        } catch (Exception e) {
            throw new Exception("Error on setting the qty of the ACHAT "+ e.getMessage());
        }
    }
    
    
    public MatierePremiere getMy_matiere() throws Exception{
        if(this.getIdMatierePremiere()== null) return null;
        try {
            MatierePremiere mp = new MatierePremiere();
            mp.setIdmatiere(this.getIdMatierePremiere());
            return BddObject.findById(mp, null);
        } catch (Exception e) {
            throw new Exception("Error on getting the matiere of the ACHAT. "+e.getMessage());
        }
    }

    public void setMy_matiere(MatierePremiere my_matiere) {
        this.my_matiere = my_matiere;
    }
}
