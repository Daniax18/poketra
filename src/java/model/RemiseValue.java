/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import annoted.ColumnField;
import annoted.TableAnnotation;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 *
 * @author aram
 */
@TableAnnotation(nameTable = "remise_value", sequence = "remise_value_seq", prefix = "REM_VAL_")
public class RemiseValue {
    @ColumnField(column = "id_remise_value", primary_key = true, is_increment = true)
    private String idRemiseValue;
    
    @ColumnField(column = "id_remise")
    private String idRemise;
    
    @ColumnField(column = "value_remise")
    private Double value;
    
    @ColumnField(column = "date_remise")
    private java.sql.Timestamp date;

    // CS

    public RemiseValue() {
    }

    public RemiseValue(String idRemise, String value) throws Exception{
        try {
            this.setIdRemise(idRemise);
            this.setValue(value);
            Calendar calendar = Calendar.getInstance();
            this.setDate(new Timestamp(calendar.getTimeInMillis()));
        } catch (Exception e) {
            throw new Exception("Error on constructing the remise value." +e.getMessage());
        }
    }
    
    // GETTERS AND SETTERS
    public String getIdRemiseValue() {
        return idRemiseValue;
    }

    public void setIdRemiseValue(String idRemiseValue) {
        this.idRemiseValue = idRemiseValue;
    }

    public String getIdRemise() {
        return idRemise;
    }

    public void setIdRemise(String idRemise) {
        this.idRemise = idRemise;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
    
    public void setValue(String valueHtml) throws Exception{
        try {
            Double my_value = Double.valueOf(valueHtml);
            this.setValue(my_value);
        } catch (Exception e) {
            throw new Exception("Error on setting the value of remise");
        }
    }


    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
    
    
}
