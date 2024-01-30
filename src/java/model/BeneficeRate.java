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
@TableAnnotation(nameTable = "benefice_rate", sequence = "rate_benefice_seq", prefix = "BENEF_")
public class BeneficeRate {
    @ColumnField(column = "id_benefice", primary_key = true, is_increment = true)
    private String idBenefice;
    
    @ColumnField(column = "id_poketra")
    private String idPoketra;
    
    @ColumnField(column = "rate")
    private Double rate;
    
    @ColumnField(column = "date_rate")
    private java.sql.Date dateRate;
    
    private Poketra my_poketra;
    
    // CS

    public BeneficeRate() {
    }

    public BeneficeRate(String idPoketra, String rate) throws Exception{
        try {
            this.setIdPoketra(idPoketra);
            this.setRate(rate);
            Calendar calendar = Calendar.getInstance();
            java.sql.Date now = new Date(calendar.getTimeInMillis());
            this.setDateRate(now);
        } catch (Exception e) {
            throw new Exception("Error on setting constructing the BENEFICE rate. " + e.getMessage());
        }
    }
    

    
    // GETTERS AND SETTERS
    public String getIdBenefice() {
        return idBenefice;
    }

    public void setIdBenefice(String idBenefice) {
        this.idBenefice = idBenefice;
    }

    public String getIdPoketra() {
        return idPoketra;
    }

    public void setIdPoketra(String idPoketra) {
        this.idPoketra = idPoketra;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
    
    
    public void setRate(String rateHtml) throws Exception{
        try {
            Double value = Double.valueOf(rateHtml);
            this.setRate(value);
        } catch (Exception e) {
            throw new Exception("Error on setting the rate of the benefice" + e.getMessage());
        }
    }

    public Date getDateRate() {
        return dateRate;
    }

    public void setDateRate(Date dateRate) {
        this.dateRate = dateRate;
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
