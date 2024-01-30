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
 * @author rango
 */
@TableAnnotation(nameTable = "taille_sac", sequence = "taille_sac_seq", prefix = "TAILLE_")
public class Taille_sac {
    @ColumnField(column = "id_taille_sac", primary_key = true, is_increment = true)
    private String id;
    
    @ColumnField(column = "nom")
    private String nom;
    
    @ColumnField(column = "valeur")
    private Integer valeur;
    
      // GET THE EMP NEED FOR EACH POSTE
    public List<Taille_sac_emp> getEmployeeNeed(Connection connection) throws Exception{
         boolean isOpen = false;
         ConnectionBase cb = new ConnectionBase();
        if(connection == null){
            connection = cb.dbConnect();     // If it is null, creating connection
        }else{
            isOpen = true;
        }
        
        try {
           
          List<Taille_sac_emp> ls = BddObject.findByOrder(new Taille_sac_emp(), "date", Ordering.DESC, connection);
          for(Taille_sac_emp ts : ls){
              ts.setMinValue((int) Math.pow(2, (this.getValeur()-1)));
          }
            
          return ls;
          
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
            throw new Exception("Error on getting the emp number of taille_sac");
        } finally{
            if(isOpen == false) connection.close();
        }
    }
    
    // GET THE EMP NEED
     public double getEmpNeed(Connection connection) throws Exception{
        boolean isOpen = false;
         ConnectionBase cb = new ConnectionBase();
        if(connection == null){
            connection = cb.dbConnect();     // If it is null, creating connection
        }else{
            isOpen = true;
        }
        
        try {
          List<Taille_sac_emp> ls = BddObject.findByOrder(new Taille_sac_emp(), "date", Ordering.DESC, connection);
          if(ls.size()>0){
              return this.getValeur() * ls.get(0).getMinValue();
          }
          return 0;
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
            throw new Exception("Error on getting the emp number of taille_sac");
        } finally{
            if(isOpen == false) connection.close();
        }
     }
    
    // g and s

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getValeur() {
        return valeur;
    }

    public void setValeur(Integer valeur) {
        this.valeur = valeur;
    }

    
   

}
