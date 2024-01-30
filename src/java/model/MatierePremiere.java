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
@TableAnnotation(nameTable = "matiere_premiere", sequence = "matiere_seq", prefix = "MAT_")
public class MatierePremiere {
    @ColumnField(column = "id_matiere_premiere", primary_key = true, is_increment = true)
    private String idmatiere;
    
    @ColumnField(column = "nom_matiere")
    private String nom_matiere;
    
    // GET MY STOCK
    public Double myStock(Connection connection) throws Exception{
        double result = 0;
        boolean isOpen = false;
        ConnectionBase cb = new ConnectionBase();
        if(connection == null){
            connection = cb.dbConnect();     // If it is null, creating connection
        }else{
            isOpen = true;
        }
       
        try {
            Mouvement mv = new Mouvement();
            mv.setIdMatierePremiere(this.getIdmatiere());
            
            double sortie = mv.getTotalMouvement(1, connection);
            double entree = mv.getTotalMouvement(0, connection);
            result = entree - sortie;
            
           return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error on getting my stock. "+ e.getMessage());
        } finally{
            if(isOpen == false) connection.close();
        }
    }
    
    // GET MY UNIT PRICE
    public double myUnitPrice(Connection connection) throws Exception{
        double result = 0;
        boolean isOpen = false;
        ConnectionBase cb = new ConnectionBase();
        if(connection == null){
            connection = cb.dbConnect();     // If it is null, creating connection
        }else{
            isOpen = true;
        }
       
        try {
            AchatMatiere am = new AchatMatiere();
            am.setIdmatiere(this.getIdmatiere());
           List<AchatMatiere> my_achat = BddObject.findByOrder(am, "dateAchat", Ordering.DESC, connection);
           
           if(my_achat.size() > 0){
               return my_achat.get(0).getPriceUnit();
           }
         
           return result;
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
            throw new Exception("Error on getting my price unit. "+ e.getMessage());
        } finally{
            if(isOpen == false) connection.close();
        }
    }

    // GET POKETRA WHICH HAS THIS MATIERE
    public List<Poketra_matiere> getPoketraByMatiere() throws Exception{
        Connection connection = null;
        
        try {
            ConnectionBase cb = new ConnectionBase();
            connection = cb.dbConnect();
            Poketra_matiere pm = new Poketra_matiere();
            pm.setIdMatierePremiere(this.getIdmatiere());
            return BddObject.find(pm, connection);
            
        } catch (Exception e) {
            throw new Exception("Error on getting poketra by matiere. " + e.getMessage());
        } finally{
            connection.close();
        }
    }
    
    // GETTER AND SETTER
    public String getIdmatiere() {
        return idmatiere;
    }

    public void setIdmatiere(String idmatiere) {
        this.idmatiere = idmatiere;
    }

    public String getNom_matiere() {
        return nom_matiere;
    }

    public void setNom_matiere(String nom_matiere) {
        this.nom_matiere = nom_matiere;
    }
    
    
}
