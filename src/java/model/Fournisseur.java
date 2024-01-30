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
 * @author aram
 */
@TableAnnotation(nameTable = "fournisseur", sequence = "frn_seq", prefix = "FRN_")
public class Fournisseur {
    @ColumnField(column = "id_fournisseur", primary_key = true, is_increment = true)
    private String idFournisseur;
    
    @ColumnField(column = "nom_fournisseur")
    private String nomFournisseur;

    
    // GETTER AND SETTER
    public String getIdFournisseur() {
        return idFournisseur;
    }

    public void setIdFournisseur(String idFournisseur) {
        this.idFournisseur = idFournisseur;
    }

    public String getNomFournisseur() {
        return nomFournisseur;
    }

    public void setNomFournisseur(String nomFournisseur) {
        this.nomFournisseur = nomFournisseur;
    }
    
    
}
