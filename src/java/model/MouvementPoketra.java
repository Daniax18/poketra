/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import annoted.ColumnField;
import annoted.TableAnnotation;
import java.sql.Date;
import mapping.BddObject;

/**
 *
 * @author aram
 */
@TableAnnotation(nameTable = "mouvement_poketra", sequence = "mouvement_pok_seq", prefix = "MVP_")
public class MouvementPoketra {
    @ColumnField(column = "id_mouvement_poketra", primary_key = true, is_increment = true)
    private String idMouvementPoketra;
    
    @ColumnField(column = "id_poketra")
    private String idPoketra;
    
    @ColumnField(column = "date_mouvement")
    private java.sql.Date date;
    
    @ColumnField(column = "qty_entree")
    private Double entree;
    
    @ColumnField(column = "qty_sortie")
    private Double sortie;
    
    private Poketra my_poketra;

    public MouvementPoketra() {
    }

    public MouvementPoketra(String idPoketra, Date date, Double entree, Double sortie) {
        this.setIdPoketra(idPoketra);
        this.setDate(date);
        this.setEntree(entree);
        this.setSortie(sortie);
    }
    
    
    // getters and setters

    public String getIdMouvementPoketra() {
        return idMouvementPoketra;
    }

    public void setIdMouvementPoketra(String idMouvementPoketra) {
        this.idMouvementPoketra = idMouvementPoketra;
    }

    public String getIdPoketra() {
        return idPoketra;
    }

    public void setIdPoketra(String idPoketra) {
        this.idPoketra = idPoketra;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getEntree() {
        return entree;
    }

    public void setEntree(Double entree) {
        this.entree = entree;
    }

    public Double getSortie() {
        return sortie;
    }

    public void setSortie(Double sortie) {
        this.sortie = sortie;
    }

    public Poketra getMy_poketra() throws Exception{
        if(this.getIdPoketra()== null) return null;
        try {
            Poketra mp = new Poketra();
            mp.setIdPoketra(this.getIdPoketra());
            return BddObject.findById(mp, null);
        } catch (Exception e) {
            throw new Exception("Error on getting the poketra of mouvement poketra. "+e.getMessage());
        }
    }

    public void setMy_poketra(Poketra my_poketra) {
        this.my_poketra = my_poketra;
    }
    
}
