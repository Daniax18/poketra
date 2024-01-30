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

@TableAnnotation(nameTable = "taille_sac_emp", sequence = "taille_sac_emp_seq", prefix = "T_EMP_")
public class Taille_sac_emp {
      @ColumnField(column = "id_taille_sac_emp", primary_key = true, is_increment = true)
    private String idTailleEmp;
    
    @ColumnField(column = "valeur")
    private Integer minValue;
    
    @ColumnField(column = "date_attribution")
    private java.sql.Date date;
    
    @ColumnField(column = "id_poste")
    private String idPoste;
    
    private Poste myPoste;
    
    // CS
    public Taille_sac_emp() {
    }
    
    public Taille_sac_emp(String idPoste, String minValue) throws Exception{
        try {
            this.setIdPoste(idPoste);
            this.setMinValue(minValue);
            this.setDate(new Date(Calendar.getInstance().getTimeInMillis()));
        } catch (Exception e) {
            throw new Exception("Error on constructing the taille_sac_emp");
        }        
    }

    public Taille_sac_emp(Integer minValue) {
        this.setMinValue(minValue);
        this.setDate(new Date(Calendar.getInstance().getTimeInMillis()));
    }
    
    // G ANS S
    public String getIdTailleEmp() {
        return idTailleEmp;
    }

    public void setIdTailleEmp(String idTailleEmp) {
        this.idTailleEmp = idTailleEmp;
    }

    public Integer getMinValue() {
        return minValue;
    }

    public void setMinValue(Integer minValue) {
        this.minValue = minValue;
    }
    
    public void setMinValue(String minValueHtml) throws Exception{
        try {
            Integer value = Integer.valueOf(minValueHtml);
            this.setMinValue(value);
        } catch (Exception e) {
            throw new Exception("Error on constructing the minimum value of taille_emp_sec");
        }
        this.minValue = minValue;
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
            throw new Exception("Error on getting the poste of the taille_sac_emp. "+e.getMessage());
        }
    }

    public void setMyPoste(Poste myPoste) {
        this.myPoste = myPoste;
    }
    
    
    
}
