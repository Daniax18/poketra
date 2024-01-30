/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import annoted.ColumnField;
import annoted.TableAnnotation;
import java.sql.Date;
import java.util.Calendar;
import mapping.BddObject;

/**
 *
 * @author aram
 */
@TableAnnotation(nameTable = "prix_vente", sequence = "pv_seq", prefix = "PV_")
public class PrixVentePoketra {
    @ColumnField(column = "id_pv", primary_key = true, is_increment = true)
    private String idPv;
    
    @ColumnField(column = "id_poketra")
    private String idPoketra;
    
    @ColumnField(column = "price")
    private Double price;
    
    @ColumnField(column = "date_prix")
    private java.sql.Date date;
    
    private Poketra my_poketra;

    
    // CS
    public PrixVentePoketra() {
    }
    
    public PrixVentePoketra(String idPoketra, String price) throws Exception{
        try {
            this.setIdPoketra(idPoketra);
            this.setPrice(price);
            this.setDate(new Date(Calendar.getInstance().getTimeInMillis()));
        } catch (Exception e) {
            throw new Exception("Error on setting constructing the pv. " + e.getMessage());
        }
        
    }
    
    // GETTER

    public String getIdPv() {
        return idPv;
    }

    public void setIdPv(String idPv) {
        this.idPv = idPv;
    }

    public String getIdPoketra() {
        return idPoketra;
    }

    public void setIdPoketra(String idPoketra) {
        this.idPoketra = idPoketra;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    
    public void setPrice(String priceHtml) throws Exception{
        try {
             Double myValue = Double.valueOf(priceHtml);
            this.setPrice(myValue);
        } catch (Exception e) {
             throw new Exception("Error on setting the price of the poetra "+ e.getMessage());
        }
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
