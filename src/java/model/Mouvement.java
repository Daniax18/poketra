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

/**
 *
 * @author aram
 */
@TableAnnotation(nameTable = "mouvement", sequence = "mouvement_mp_seq", prefix = "MV_")
public class Mouvement {
    @ColumnField(column = "id_mouvement", primary_key = true, is_increment = true)
    private String idMouvement;
    
    @ColumnField(column = "id_matiere_premiere")
    private String idMatierePremiere;
    
    @ColumnField(column = "date_mouvement")
    private java.sql.Date dateMouvement;
    
    @ColumnField(column = "qty_mouvement")
    private Double qty;
    
    @ColumnField(column = "status")
    private Integer status;
    
    private MatierePremiere my_matiere;
    
    
    // SI ON PEUT FAIRE UNE SORTIE
    public double[] canMakeSortie(Connection connection) throws Exception{
        double[] result = new double[2]; 
        boolean isOpen = false;
        ConnectionBase cb = new ConnectionBase();
        if(connection == null){
            connection = cb.dbConnect();     // If it is null, creating connection
        }else{
            isOpen = true;
        }
       
        try {
            double qty_stock = this.getMy_matiere().myStock(connection);
            if(qty_stock < this.getQty()){
                result[0] = 0;
                result[1] = -1 * (qty_stock - this.getQty());
            } else {
                result[0] = 1;
                result[1] = 0;
                this.setStatus(1);  // 1 mivoka
                BddObject.insertInDatabase(this, connection);
            }
            if(isOpen == false) connection.commit();
            return result;
        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
            throw new Exception("Error on knowing if we can make a sortie. "+ e.getMessage());
        } finally{
            if(isOpen == false) connection.close();
        }
    }
    
    // VOIR MOUVEMENT D'UN PRODUIT
    public Double getTotalMouvement(Integer mouvement_status, Connection connection) throws Exception{
        double result = 0;
        boolean isOpen = false;
        ConnectionBase cb = new ConnectionBase();
        if(connection == null){
            connection = cb.dbConnect();     // If it is null, creating connection
        }else{
            isOpen = true;
        }
       
        try {
            this.setStatus(mouvement_status);
            List<Mouvement> mvt = BddObject.find(this, connection);
            for(Mouvement mv : mvt){
                result += mv.getQty();
            }
           return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error on getting total mouvement. "+ e.getMessage());
        } finally{
            if(isOpen == false) connection.close();
        }
    }
    
    
    // CONSTRUCTORS

    public Mouvement() {
    }
    
   
    public Mouvement(String idMatierePremiere, Double qty, Integer status) throws Exception{
        try {
             this.setIdMatierePremiere(idMatierePremiere);
            this.setQty(qty);
            this.setStatus(status);
            Calendar calendar = Calendar.getInstance();
            java.sql.Date now = new Date(calendar.getTimeInMillis());
            this.setDateMouvement(now);
        } catch (Exception e) {
            throw new Exception("Error on  constructing the mouvement");
        }
       
    }

    
    
    // GETTERS AND SETTERS
    public String getIdMouvement() {
        return idMouvement;
    }

    public void setIdMouvement(String idMouvement) {
        this.idMouvement = idMouvement;
    }

    public String getIdMatierePremiere() {
        return idMatierePremiere;
    }

    public void setIdMatierePremiere(String idMatierePremiere) {
        this.idMatierePremiere = idMatierePremiere;
    }

    public Date getDateMouvement() {
        return dateMouvement;
    }

    public void setDateMouvement(Date dateMouvement) {
        this.dateMouvement = dateMouvement;
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
            throw new Exception("Error on setting the qty of the mouvement"+ e.getMessage());
        }
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    
    public MatierePremiere getMy_matiere() throws Exception{
        if(this.getIdMatierePremiere()== null) return null;
        try {
            MatierePremiere mp = new MatierePremiere();
            mp.setIdmatiere(this.getIdMatierePremiere());
            return BddObject.findById(mp, null);
        } catch (Exception e) {
            throw new Exception("Error on getting the matiere of the mouvement. "+e.getMessage());
        }
    }

    public void setMy_matiere(MatierePremiere my_matiere) {
        this.my_matiere = my_matiere;
    }
    
}
