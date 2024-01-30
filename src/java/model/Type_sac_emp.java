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
@TableAnnotation(nameTable = "type_sac_emp", sequence = "type_sac_emp_seq", prefix = "T_HO_")
public class Type_sac_emp {
    @ColumnField(column = "id_type_sac_emp", primary_key = true, is_increment = true)
    private String idTypeHoraire;
    
    @ColumnField(column = "id_type_sac")
    private String idTypeSac;
    
    @ColumnField(column = "valeur")
    private Double value;
    
    @ColumnField(column = "date_attribution")
    private java.sql.Date date;
    
    
    private Type_sac myTypeSac;
    
    // CS

    public Type_sac_emp(String idTypeSac, String value) throws Exception{
        try {
            this.setIdTypeSac(idTypeSac);
            this.setValue(value);
            this.setDate(new Date(Calendar.getInstance().getTimeInMillis()));
        } catch (Exception e) {
            throw new Exception("Error on constructing the type_sac_emp");
        }

    }

    public Type_sac_emp() {
    }
    
    
    
    // GETTERS N SETTERS

    public Type_sac getMyTypeSac() throws Exception{
        if(this.getIdTypeSac() == null) return null;
        try {
            Type_sac ts = new Type_sac();
            ts.setId(this.getIdTypeSac());
            return BddObject.findById(ts, null);
        } catch (Exception e) {
            throw new Exception("Type sac not found in horaire");
        }
    }

    public void setMyTypeSac(Type_sac myTypeSac) {
        this.myTypeSac = myTypeSac;
    }
    

    public String getIdTypeHoraire() {
        return idTypeHoraire;
    }

    public void setIdTypeHoraire(String idTypeHoraire) {
        this.idTypeHoraire = idTypeHoraire;
    }

    public String getIdTypeSac() {
        return idTypeSac;
    }

    public void setIdTypeSac(String idTypeSac) {
        this.idTypeSac = idTypeSac;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
    
    public void setValue(String valueHtml) throws Exception{
        try {
            Double myvalue = Double.valueOf(valueHtml);
            this.setValue(myvalue);
        } catch (Exception e) {
            throw new Exception("Error on setting the horaire of type sac");
        }
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
}
