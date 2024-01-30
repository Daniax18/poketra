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
import java.util.ArrayList;
import java.util.List;
import mapping.BddObject;
import utilities.Ordering;

/**
 *
 * @author rango
 */
@TableAnnotation(nameTable = "look_sac", sequence = "look_seq", prefix = "LOOK_")
public class Look_sac {
    @ColumnField(column = "id_look_sac", primary_key = true, is_increment = true)
    private String idlook;
    
     @ColumnField(column = "nom_look")
    private String nom_look;
     
     @ColumnField(column = "valeur")
    private Integer valeur;
     
     
     // GET THE HEURE NEED
     public double getHeureNeed(Connection connection) throws Exception{
        boolean isOpen = false;
         ConnectionBase cb = new ConnectionBase();
        if(connection == null){
            connection = cb.dbConnect();     // If it is null, creating connection
        }else{
            isOpen = true;
        }
        
        try {
          List<Look_sac_heure> ls = BddObject.findByOrder(new Look_sac_heure(), "date", Ordering.DESC, connection);
          if(ls.size()>0){
              return this.getValeur() * ls.get(0).getMinValue();
          }
          return 0;
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
            throw new Exception("Error on getting the hour number of looc_sac");
        } finally{
            if(isOpen == false) connection.close();
        }
     }

    // PRENDRE LES MATIERES D'UN LOOK
     public List<MatierePremiere> getAllMatierePremiere(Connection connection) throws Exception{
          boolean isOpen = false;
         ConnectionBase cb = new ConnectionBase();
        if(connection == null){
            connection = cb.dbConnect();     // If it is null, creating connection
        }else{
            isOpen = true;
        }
        Look_matiere lk = new Look_matiere();
        lk.setIdlook(this.getIdlook());
        try {
           List<MatierePremiere> result = new ArrayList<>();
           List<Look_matiere> temp = BddObject.find(lk, connection);
           for(Look_matiere lm : temp){
               MatierePremiere mp = new MatierePremiere();
               mp.setIdmatiere(lm.getIdmatiere());
               mp = BddObject.findById(mp, connection);
               result.add(mp);
           }
           return result;
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
            throw new Exception("Error on inserting all inventory of all products");
        } finally{
            if(isOpen == false) connection.close();
        }
     }
    
    // GETTERS AND SETTERS
    public String getIdlook() {
        return idlook;
    }

    public void setIdlook(String idlook) {
        this.idlook = idlook;
    }

    public String getNom_look() {
        return nom_look;
    }

    public void setNom_look(String nom_look) {
        this.nom_look = nom_look;
    }

    public Integer getValeur() {
        return valeur;
    }

    public void setValeur(Integer valeur) {
        this.valeur = valeur;
    }
    
    
    
    
}
