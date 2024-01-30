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

/**
 *
 * @author rango
 */
@TableAnnotation(nameTable = "poketra_matiere", sequence = "poketra_matiere_seq", prefix = "POK_MAT_")
public class Poketra_matiere {
    @ColumnField(column = "id_poketra_matiere", primary_key = true, is_increment = true)
    private String idPoketraMatiere;
    
    @ColumnField(column = "id_poketra")
    private String idPoketra;
    
    @ColumnField(column = "id_matiere_premiere")
    private String idMatierePremiere;
    
    @ColumnField(column = "qty")
    private Double qty;
    
    private MatierePremiere my_mp;
    
    private Poketra my_poketra;
    
    // Update a liste of Poketra matiere
    public static void updatePms(List<Poketra_matiere> pms) throws Exception{
        Connection connection = null;
        
        try {
            ConnectionBase cb = new ConnectionBase();
            connection = cb.dbConnect();
            for(Poketra_matiere pm : pms){
                BddObject.updatingObject(pm, connection);
            }
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            throw new Exception("Error on updating matiere of Poketra. " + e.getMessage());
        } finally{
            connection.close();
        }
    }
    
    // CONSTUCTEURS

    public Poketra_matiere(String idPoketra, String idMatierePremiere, Double qty) {
        this.setIdPoketra(idPoketra);
        this.setIdMatierePremiere(idMatierePremiere);
        this.setQty(qty);
    }
    
    

    public Poketra_matiere() {
    }
    
    
    // GETTERS AND SETTERS

    public String getIdPoketraMatiere() {
        return idPoketraMatiere;
    }

    public void setIdPoketraMatiere(String idPoketraMatiere) {
        this.idPoketraMatiere = idPoketraMatiere;
    }

    public String getIdPoketra() {
        return idPoketra;
    }

    public void setIdPoketra(String idPoketra) {
        this.idPoketra = idPoketra;
    }

    public String getIdMatierePremiere() {
        return idMatierePremiere;
    }

    public void setIdMatierePremiere(String idMatierePremiere) {
        this.idMatierePremiere = idMatierePremiere;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }
    
    public void setQty(String qtyHtml) throws Exception{
        try {
            Double value = Double.valueOf(qtyHtml);
            this.setQty(value);
        } catch (Exception e) {
            throw new Exception("Error on parsing the value of the qty materiel" + e.getMessage());
        }
    }

    public MatierePremiere getMy_mp() throws Exception{
         if(this.getIdMatierePremiere()== null) return null;
        try {
            MatierePremiere mp = new MatierePremiere();
            mp.setIdmatiere(this.getIdMatierePremiere());
            return BddObject.findById(mp, null);
        } catch (Exception e) {
            throw new Exception("Error on getting the matiere of the poketra. "+e.getMessage());
        }
    }

    public void setMy_mp(MatierePremiere my_mp) {
        this.my_mp = my_mp;
    }

    public Poketra getMy_poketra() throws Exception{
         if(this.getIdPoketra()== null) return null;
        try {
            Poketra mp = new Poketra();
            mp.setIdPoketra(this.getIdPoketra());
            return BddObject.findById(mp, null);
        } catch (Exception e) {
            throw new Exception("Error on getting the poketra of poketra_matiere. "+e.getMessage());
        }
    }

    public void setMy_poketra(Poketra my_poketra) {
        this.my_poketra = my_poketra;
    }
    

}
