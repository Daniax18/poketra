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
@TableAnnotation(nameTable = "taux_horaire", sequence = "taux_seq", prefix = "TAX_")
public class Taux_horaire {
    @ColumnField(column = "id_taux", primary_key = true, is_increment = true)
    private String idTaux;
    
    @ColumnField(column = "salaire")
    private Double salaire;
    
    @ColumnField(column = "id_poste")
    private String idPoste;
    
    private Poste myPoste;
    
    @ColumnField(column = "date_attribution")
    private java.sql.Date date;

    // GET LAST TAUX
    public static double getTaux(Connection connection) throws Exception{
        boolean isOpen = false;
         ConnectionBase cb = new ConnectionBase();
        if(connection == null){
            connection = cb.dbConnect();     // If it is null, creating connection
        }else{
            isOpen = true;
        }
        
        try {
          List<Taux_horaire> ls = BddObject.find(new Taux_horaire(), connection);
          if(ls.size()>0){
              return ls.get(0).getSalaire();
          }
          return 0;
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
            throw new Exception("Error on getting the saliare");
        } finally{
            if(isOpen == false) connection.close();
        }
    }
    
    public Taux_horaire() {
    }
    
    // CS
    public Taux_horaire(String idPoste, String salaire) {
        try {
            this.setIdPoste(idPoste);
            this.setSalaire(salaire);
            this.setDate(new Date(Calendar.getInstance().getTimeInMillis()));
        } catch (Exception e) {
        }
    }
    
    
    public Taux_horaire(String salaire) {
        try {
            this.setSalaire(salaire);
            this.setDate(new Date(Calendar.getInstance().getTimeInMillis()));
        } catch (Exception e) {
        }
    }
    
    // Getters and asee
    public String getIdTaux() {
        return idTaux;
    }

    public void setIdTaux(String idTaux) {
        this.idTaux = idTaux;
    }

    public Double getSalaire() {
        return salaire;
    }

    public void setSalaire(Double salaire) {
        this.salaire = salaire;
    }
    
    public void setSalaire(String salairehtml){
        try {
            Double t = Double.valueOf(salairehtml);
            this.setSalaire(t);
        } catch (Exception e) {
        }
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
        public String getIdPoste() {
        return idPoste;
    }

    public void setIdPoste(String idPoste) {
        this.idPoste = idPoste;
    }
    
    public Poste getMyPoste() throws Exception{
        if(this.getIdPoste()== null) return null;
        try {
            Poste ls = new Poste();
            ls.setIdPoste(this.getIdPoste());
            return BddObject.findById(ls, null);
        } catch (Exception e) {
            throw new Exception("Error on getting the poste of the taux horaire. "+e.getMessage());
        }
    }

    public void setMyPoste(Poste myPoste) {
        this.myPoste = myPoste;
    }
    
}
