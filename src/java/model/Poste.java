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
@TableAnnotation(nameTable = "poste", sequence = "poste_seq", prefix = "POSTE_")
public class Poste {
    @ColumnField(column = "id_poste", primary_key = true, is_increment = true)
    private String idPoste;
    
    @ColumnField(column = "nom")
    private String nomPoste;
    
    // GET MY TAUX HORAIRE
    public double getMyTauxHoraire(Connection connection) throws Exception{
         boolean isOpen = false;
         ConnectionBase cb = new ConnectionBase();
        if(connection == null){
            connection = cb.dbConnect();     // If it is null, creating connection
        }else{
            isOpen = true;
        }
        
        try {
            double taux = MinTaux.getTaux(connection);
            CoefficientPoste temp = new CoefficientPoste();
            temp.setIdPoste(this.getIdPoste());
            List<CoefficientPoste> cp = BddObject.findByOrder(temp, "date", Ordering.DESC, connection);
            
            if(cp.size() > 0){
                return taux * cp.get(0).getCoefficient();
            }
            
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
            throw new Exception("Error on getting the taux horaire of poste");
        } finally{
            if(isOpen == false) connection.close();
        }
    }
    
//    // GET MY TAUX HORAIRE -- AVANT ----
//    public double getMyTauxHoraire(Connection connection) throws Exception{
//         boolean isOpen = false;
//         ConnectionBase cb = new ConnectionBase();
//        if(connection == null){
//            connection = cb.dbConnect();     // If it is null, creating connection
//        }else{
//            isOpen = true;
//        }
//        
//        try {
//            Taux_horaire mytaux = new Taux_horaire();
//            mytaux.setIdPoste(this.getIdPoste());
//            List<Taux_horaire> ls = BddObject.findByOrder(mytaux, "date", Ordering.DESC, connection);
//            if(ls.size()>0){
//                return ls.get(0).getSalaire();
//            }
//            return 0;
//        } catch (Exception e) {
//            e.printStackTrace();
//            connection.rollback();
//            throw new Exception("Error on getting the taux horaire of poste");
//        } finally{
//            if(isOpen == false) connection.close();
//        }
//    }
    
    // GETTERS AND SETTERS
    public String getIdPoste() {
        return idPoste;
    }

    public void setIdPoste(String idPoste) {
        this.idPoste = idPoste;
    }

    public String getNomPoste() {
        return nomPoste;
    }

    public void setNomPoste(String nomPoste) {
        this.nomPoste = nomPoste;
    }
    
    
}
