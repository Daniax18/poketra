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
import java.util.HashMap;
import java.util.List;
import mapping.BddObject;

/**
 *
 * @author aram
 */
@TableAnnotation(nameTable = "fabrication", sequence = "fabrication_seq", prefix = "PROD_")
public class Fabrication {
    @ColumnField(column = "id_fabrication", primary_key = true, is_increment = true)
    private String idFabrication;
    
    @ColumnField(column = "id_poketra")
    private String idPoketra;
    
    @ColumnField(column = "date_fabrication")
    private java.sql.Date dateFabrication;
    
    @ColumnField(column = "qty_fabrication")
    private Double qty;
    
    private Poketra my_poketra;

    // MAKE A FABRICATION
    public HashMap<String, Double> makeFabrication(Connection connection) throws Exception{
        HashMap<String, Double> result = new HashMap<>();
        boolean isOpen = false;
        ConnectionBase cb = new ConnectionBase();
        if(connection == null){
            connection = cb.dbConnect();     // If it is null, creating connection
        }else{
            isOpen = true;
        }
       
        try {
            List<Poketra_matiere> mps = this.getMy_poketra().getMy_matiere();
            for(Poketra_matiere pm : mps){
                Mouvement mouvement = new Mouvement(pm.getIdMatierePremiere(), this.getQty() * pm.getQty(), 1);
                double[] temp = mouvement.canMakeSortie(connection);
                if(temp[0] == 0){
                    result.put(pm.getMy_mp().getNom_matiere(), temp[1]);
                }
            }
            
            if(result.size() > 0){
                connection.rollback();
            } else{
                BddObject.insertInDatabase(this, connection);
                MouvementPoketra mp = new MouvementPoketra(this.getIdPoketra(), this.getDateFabrication(), this.getQty(), 0.0);
                BddObject.insertInDatabase(mp, connection);
                if(isOpen == false) connection.commit();
            }
            return result;
        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
            throw new Exception("Error on making fabrication. "+ e.getMessage());
        } finally{
            if(isOpen == false) connection.close();
        }
    }
    
    
    // CONSTRUCTORS

    public Fabrication(String idPoketra, String qty) throws Exception{
        try {
            this.setIdPoketra(idPoketra);
            this.setQty(qty);
            Calendar calendar = Calendar.getInstance();
            java.sql.Date now = new Date(calendar.getTimeInMillis());
            this.setDateFabrication(now);
        } catch (Exception e) {
            throw new Exception("Error on setting constructing the FABRICATION. " + e.getMessage());
        }
    }
    
    public Fabrication() {
    }
    
    
    // GETTERS AND SETTERS
    public String getIdFabrication() {
        return idFabrication;
    }

    public void setIdFabrication(String idFabrication) {
        this.idFabrication = idFabrication;
    }

    public String getIdPoketra() {
        return idPoketra;
    }

    public void setIdPoketra(String idPoketra) {
        this.idPoketra = idPoketra;
    }

    public Date getDateFabrication() {
        return dateFabrication;
    }

    public void setDateFabrication(Date dateFabrication) {
        this.dateFabrication = dateFabrication;
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
    
    public Poketra getMy_poketra() throws Exception{
        if(this.getIdPoketra()== null) return null;
        try {
            Poketra mp = new Poketra();
            mp.setIdPoketra(this.getIdPoketra());
            return BddObject.findById(mp, null);
        } catch (Exception e) {
            throw new Exception("Error on getting the poketra of fabrication. "+e.getMessage());
        }
    }

    public void setMy_poketra(Poketra my_poketra) {
        this.my_poketra = my_poketra;
    }
}
