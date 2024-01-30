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

/**
 *
 * @author aram
 */
@TableAnnotation(nameTable = "look_sac_min_heure", sequence = "look_min_seq", prefix = "LOOKH_")
public class Look_sac_heure {
    @ColumnField(column = "id_look_sac_min", primary_key = true, is_increment = true)
    private String idLookHeure;
    
    @ColumnField(column = "valeur")
    private Integer minValue;
    
    @ColumnField(column = "date_attribution")
    private java.sql.Date date;
    

    // Cs
    
    public Look_sac_heure() {
    }

    public Look_sac_heure(Integer minValue) {
        this.setMinValue(minValue);
        this.setDate(new Date(Calendar.getInstance().getTimeInMillis()));
    }
    
    // g s

    public String getIdLookHeure() {
        return idLookHeure;
    }

    public void setIdLookHeure(String idLookHeure) {
        this.idLookHeure = idLookHeure;
    }

    public Integer getMinValue() {
        return minValue;
    }

    public void setMinValue(Integer minValue) {
        this.minValue = minValue;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
    
}
