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
@TableAnnotation(nameTable = "type_sac", sequence = "type_sac_seq", prefix = "TYPE_")
public class Type_sac {
    
    @ColumnField(column = "id_type_sac", primary_key = true, is_increment = true)
    private String id;
    
    @ColumnField(column = "nom")
    private String nom;
    
    @ColumnField(column = "valeur")
    private Integer valeur;

    // prendre le voume horaire pour chaque type de sac
    public double getMyVolumeHoraire(Connection connection) throws Exception{
        boolean isOpen = false;
        ConnectionBase cb = new ConnectionBase();
        if(connection == null){
            connection = cb.dbConnect();     // If it is null, creating connection
        }else{
            isOpen = true;
        }
        
        try {
          Type_sac_emp temp = new Type_sac_emp();
          temp.setIdTypeSac(this.getId());
          List<Type_sac_emp> ls = BddObject.findByOrder(temp, "date", Ordering.DESC, connection);
          if(ls.size()>0){
              return ls.get(0).getValue();
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
    
    
    // G AND S
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
