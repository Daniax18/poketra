/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import annoted.ColumnField;
import annoted.TableAnnotation;
import java.sql.Date;

/**
 *
 * @author aram
 */
@TableAnnotation(nameTable = "coefficient_poste", sequence = "coefficient_seq", prefix = "CO_")
public class CoefficientPoste {
    @ColumnField(column = "id_coefficient", primary_key = true, is_increment = true)
    private String idCoefficient;
    
    @ColumnField(column = "id_poste")
    private String idPoste;
    
    @ColumnField(column = "ancienete")
    private Double ancienete;
    
    @ColumnField(column = "coefficient")
    private Double coefficient;
    
    @ColumnField(column = "date_coeff")
    private java.sql.Date date;

    
    // getters and setters
    public String getIdCoefficient() {
        return idCoefficient;
    }

    public void setIdCoefficient(String idCoefficient) {
        this.idCoefficient = idCoefficient;
    }

    public String getIdPoste() {
        return idPoste;
    }

    public void setIdPoste(String idPoste) {
        this.idPoste = idPoste;
    }

    public Double getAncienete() {
        return ancienete;
    }

    public void setAncienete(Double ancienete) {
        this.ancienete = ancienete;
    }

    public Double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Double coefficient) {
        this.coefficient = coefficient;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
    
}
