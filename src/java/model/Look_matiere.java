/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import annoted.ColumnField;
import annoted.TableAnnotation;

/**
 *
 * @author rango
 */
@TableAnnotation(nameTable = "look_matiere", sequence = "look_matiere_seq", prefix = "LO_MAT_")
public class Look_matiere {
    
    @ColumnField(column = "id_look_matiere", primary_key = true, is_increment = true)
    private String id_look_matiere;
    
    @ColumnField(column = "id_matiere_premiere")
    private String idmatiere;
    
    @ColumnField(column = "id_look_sac")
    private String idlook;

    
    // GETTERS AND SETTERS

    public String getId_look_matiere() {
        return id_look_matiere;
    }

    public void setId_look_matiere(String id_look_matiere) {
        this.id_look_matiere = id_look_matiere;
    }

    
    public String getIdmatiere() {
        return idmatiere;
    }

    public void setIdmatiere(String idmatiere) {
        this.idmatiere = idmatiere;
    }

    public String getIdlook() {
        return idlook;
    }

    public void setIdlook(String idlook) {
        this.idlook = idlook;
    }
    
    
}
