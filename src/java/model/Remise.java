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
import java.util.List;
import mapping.BddObject;
import utilities.Ordering;

/**
 *
 * @author aram
 */
@TableAnnotation(nameTable = "remise", sequence = "remise_seq", prefix = "REM_")
public class Remise {
    @ColumnField(column = "id_remise", primary_key = true, is_increment = true)
    private String idRemise;
        
    @ColumnField(column = "nom_remise")
    private String nomRemise;

    
    // GET MY VALUE REMISE
    public double myValueRemise(Connection connection) throws Exception{
        double result = 0;
        boolean isOpen = false;
        ConnectionBase cb = new ConnectionBase();
        if(connection == null){
            connection = cb.dbConnect();     // If it is null, creating connection
        }else{
            isOpen = true;
        }
       
        try {
           RemiseValue rm = new RemiseValue();
           rm.setIdRemise(this.getIdRemise());
           List<RemiseValue> temp = BddObject.findByOrder(rm, "date", Ordering.DESC, connection);
           if(temp.size() > 0){
               return temp.get(0).getValue();
           }
           
           return result;
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
            throw new Exception("Error on getting thye value of remise. "+ e.getMessage());
        } finally{
            if(isOpen == false) connection.close();
        }
    }
    
    // Insert a new remise with default value
    public void insertNewRemise(Connection connection) throws Exception{

        boolean isOpen = false;
        ConnectionBase cb = new ConnectionBase();
        if(connection == null){
            connection = cb.dbConnect();     // If it is null, creating connection
        }else{
            isOpen = true;
        }
       
        try {
           String renise = BddObject.insertInDatabase(this, connection);
           
           RemiseValue rm = new RemiseValue(renise, "0");
           BddObject.insertInDatabase(rm, connection);
           if(isOpen == false) connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
            throw new Exception("Error on inserting a new remise. "+ e.getMessage());
        } finally{
            if(isOpen == false) connection.close();
        }
    }
    
    
    // CS
    public Remise() {
    }

    public Remise(String nomRemise) {
        this.setNomRemise(nomRemise);
    }

    // getters and setters
    public String getIdRemise() {
        return idRemise;
    }

    public void setIdRemise(String idRemise) {
        this.idRemise = idRemise;
    }

    public String getNomRemise() {
        return nomRemise;
    }

    public void setNomRemise(String nomRemise) {
        this.nomRemise = nomRemise;
    }
    
    
    
}
