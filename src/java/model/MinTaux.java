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
import java.util.List;
import mapping.BddObject;
import utilities.Ordering;

/**
 *
 * @author aram
 */
@TableAnnotation(nameTable = "taux_min", sequence = "min_seq", prefix = "TX_")
public class MinTaux {
    @ColumnField(column = "id_taux", primary_key = true, is_increment = true)
    private String idTaux;
    
    @ColumnField(column = "taux")
    private Double value;
    
    @ColumnField(column = "date_taux")
    private java.sql.Date date; 

    // LAST TAUX
    public static double getTaux(Connection connection) throws Exception{
    
        boolean isOpen = false;
        ConnectionBase cb = new ConnectionBase();
        if(connection == null){
            connection = cb.dbConnect();     // If it is null, creating connection
        }else{
            isOpen = true;
        }
      
        try {
           
            List<MinTaux> temp = BddObject.findByOrder(new MinTaux(), "date", Ordering.DESC, connection);
            if(temp.size() > 0) return temp.get(0).getValue();
            return 0.0;
            
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
            throw new Exception("Error on getting the last taux. "+ e.getMessage());
        } finally{
            if(isOpen == false) connection.close();
        }
    }
    
    // getters and setters
    public String getIdTaux() {
        return idTaux;
    }

    public void setIdTaux(String idTaux) {
        this.idTaux = idTaux;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
    
}
