/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import annoted.ColumnField;
import annoted.TableAnnotation;
import database.ConnectionBase;
import java.sql.Connection;
import mapping.BddObject;

/**
 *
 * @author aram
 */
@TableAnnotation(nameTable = "facture_fille", sequence = "fille_seq", prefix = "FF_")
public class FactureFille {
    @ColumnField(column = "id_fille", primary_key = true, is_increment = true)
    private String idFille;
    
    @ColumnField(column = "id_facture")
    private String idFacture;
    
    @ColumnField(column = "id_poketra")
    private String idPoketra;
    
    @ColumnField(column = "pu_poketra")
    private Double pu;
    
    @ColumnField(column = "qty")
    private Double qty;
    
    @ColumnField(column = "montant")
    private Double montant;

    private Poketra my_poketra;
    
    private Facture motherFacture;
    
    // ENREGISTRER FACTURE FILLE -> DENORMALISATION FACTURE MERE
    public void saveFactureFille(Connection connection) throws Exception{
        
        boolean isOpen = false;
        ConnectionBase cb = new ConnectionBase();
        if(connection == null){
            connection = cb.dbConnect();     // If it is null, creating connection
        }else{
            isOpen = true;
        }
      
        try {
            double my_stock = this.getMy_poketra().myQuantityActual(connection);
            
            if(my_stock > this.getQty()){
                String insert = BddObject.insertInDatabase(this, connection);
                MouvementPoketra mp = new MouvementPoketra(this.getIdPoketra(), this.getMotherFacture().getDate(), 0.0, this.getQty());
                BddObject.insertInDatabase(mp, connection);
                
                Facture facture = this.getMotherFacture();
    //            System.out.println("the f is " + facture.getIdFacture());
                double new_total = facture.getMontantWithRemise(connection);
                facture.setMontantTotal(new_total);
                BddObject.updatingObject(facture, connection);
            } else {
                connection.rollback();
                throw new Exception("Not enough poketra. You need to make " + (this.getQty() - my_stock) + " more.");
            }
           
            if(isOpen == false) connection.commit();
        } catch (Exception e) {
            connection.rollback();
            throw new Exception("Error on inserting facture fille. "+ e.getMessage());
        } finally{
            if(isOpen == false) connection.close();
        }
    }
    
    // Cs
    public FactureFille(String idFacture, String idPoketra, String qty) throws Exception{
        try {
            this.setIdFacture(idFacture);
            this.setIdPoketra(idPoketra);
            this.setQty(qty);
            double my_pu = this.getMy_poketra().my_pv(null);
            this.setPu(my_pu);
            this.setMontant(my_pu * this.getQty());
        } catch (Exception e) {
            throw new Exception("Error on constructing the facture fille. " + e.getMessage());
        }
    }
    
    public FactureFille() {
    }
    
    
    
    
    // GETTERS AMND SETTERS
    public String getIdFille() {
        return idFille;
    }

    public void setIdFille(String idFille) {
        this.idFille = idFille;
    }

    public String getIdFacture() {
        return idFacture;
    }

    public void setIdFacture(String idFacture) {
        this.idFacture = idFacture;
    }

    public String getIdPoketra() {
        return idPoketra;
    }

    public void setIdPoketra(String idPoketra) {
        this.idPoketra = idPoketra;
    }

    public Double getPu() {
        return pu;
    }

    public void setPu(Double pu) {
        this.pu = pu;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }
    
    public void setQty(String qty) throws Exception{
        try {
            Double value = Double.valueOf(qty);
            this.setQty(value);
        } catch (Exception e) {
            throw new Exception("Error on setting the qty of the facture fille. "+e.getMessage());
        }
    }
    

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }
    
    
    public Poketra getMy_poketra() throws Exception{
         if(this.getIdPoketra()== null) return null;
        try {
            Poketra mp = new Poketra();
            mp.setIdPoketra(this.getIdPoketra());
            return BddObject.findById(mp, null);
        } catch (Exception e) {
            throw new Exception("Error on getting the poketra of facture fille. "+e.getMessage());
        }
    }

    public Facture getMotherFacture() throws Exception{
        
        if(this.getIdFacture()== null) return null;
        try {
            Facture mp = new Facture();
            mp.setIdFacture(this.getIdFacture());
            return BddObject.findById(mp, null);
        } catch (Exception e) {
            throw new Exception("Error on getting the mother of facture fille. "+e.getMessage());
        }
    }
    
    
    
}
