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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import mapping.BddObject;
import utilities.DatePattern;
import utilities.DateUtil;
import utilities.Ordering;

/**
 *
 * @author aram
 */
@TableAnnotation(nameTable = "facture", sequence = "facture_seq", prefix = "INVOICE_")
public class Facture {
    @ColumnField(column = "id_facture", primary_key = true, is_increment = true)
    private String idFacture;
    
    @ColumnField(column = "id_client")
    private String idClient;
    
    @ColumnField(column = "id_remise")
    private String idRemise;
    
    @ColumnField(column = "date_facture")
    private java.sql.Date date;
    
    @ColumnField(column = "montant_total")
    private Double montantTotal;
    
    @ColumnField(column = "status_facture")
    private Integer status;
    
    private Client client;
    
    private Remise remise;
    
    private List<FactureFille> filles;
    
    private List<Paiement> paiements;
    
    
    // Make the paiement
    public void makePaiement(String montant, String date, Connection connection) throws Exception{
        if(this.getStatus() == 2) return;
        boolean isOpen = false;
        ConnectionBase cb = new ConnectionBase();
        if(connection == null){
            connection = cb.dbConnect();     // If it is null, creating connection
        }else{
            isOpen = true;
        }
        
        try {
           Paiement paiement = new Paiement(this.getIdFacture(), date, montant);
           double[] state = this.paiementAndReste(connection);
           if(state[1] < paiement.getMontant()){
               throw new Exception("Montant get too high by. "+ (state[1] - paiement.getMontant()));
           } else if(state[1] == paiement.getMontant()){
               BddObject.insertInDatabase(paiement, connection);
               this.setStatus(2);
               BddObject.updatingObject(this, connection);
           } else{
               BddObject.insertInDatabase(paiement, connection);
           }
            
           if(isOpen == false) connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
            throw new Exception("Error on making the paiement. "+ e.getMessage());
        } finally{
            if(isOpen == false) connection.close();
        }
    }
    
    
     // TOTAL PAIEMENT RECUS ET RESTE
    // 0 -> TOTAL RECUS
    // 1 -> TOTAL FACTURE - 0 (Reste a payer)
    public double[] paiementAndReste(Connection connection) throws Exception{
        boolean isOpen = false;
        ConnectionBase cb = new ConnectionBase();
        if(connection == null){
            connection = cb.dbConnect();     // If it is null, creating connection
        }else{
            isOpen = true;
        }
        double[] result = new double[2];
        try {
           result[0] = this.totalPaiementRecu(connection);
           result[1] = this.getMontantTotal() - result[0];
           return result;
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
            throw new Exception("Error on getting the total paid and left. "+ e.getMessage());
        } finally{
            if(isOpen == false) connection.close();
        }
    }
    
    
    // TOTAL PAIEMENT RECUS
    public double totalPaiementRecu(Connection connection) throws Exception{
          boolean isOpen = false;
        ConnectionBase cb = new ConnectionBase();
        if(connection == null){
            connection = cb.dbConnect();     // If it is null, creating connection
        }else{
            isOpen = true;
        }
        PreparedStatement pm = null;
        ResultSet resultSet = null;
       
        try {
            String sqlQuery = "SELECT SUM(montant) FROM paiement WHERE id_facture = ?";
            pm = connection.prepareStatement(sqlQuery);
            pm.setString(1, this.getIdFacture());
            resultSet = pm.executeQuery();
            if (resultSet.next()) {
                // Retrieve the sum of prices from the result set
                double totalPrice = resultSet.getDouble(1);

                // Return the result
                return totalPrice;
            }else{
                return 0.0;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error on getting the montant total of paiement. "+ e.getMessage());
        } finally{
            resultSet.close();
            pm.close();
            if(isOpen == false) connection.close();
        }
    }
    
    
    // Valider facture
    public void validateFacture(Connection connection) throws Exception{
         boolean isOpen = false;
        ConnectionBase cb = new ConnectionBase();
        if(connection == null){
            connection = cb.dbConnect();     // If it is null, creating connection
        }else{
            isOpen = true;
        }
      
        try {
           this.setStatus(1);
           BddObject.updatingObject(this, connection);
            
            if(isOpen == false) connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
            throw new Exception("Error on updating the status of facture. "+ e.getMessage());
        } finally{
            if(isOpen == false) connection.close();
        }
    }
    
    // GET TOTAL MONTANT WITH REMISE
    public double getMontantWithRemise(Connection connection) throws Exception{
        boolean isOpen = false;
        ConnectionBase cb = new ConnectionBase();
        if(connection == null){
            connection = cb.dbConnect();     // If it is null, creating connection
        }else{
            isOpen = true;
        }
      
        try {
            double remise = this.getRemise().myValueRemise(connection);
            double withoutRemise = this.getMontantWithoutRemise(connection);
            return withoutRemise - ((withoutRemise * remise)/100);
            
            
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
            throw new Exception("Error on getting the montant with remise of the facture. "+ e.getMessage());
        } finally{
            if(isOpen == false) connection.close();
        }
    }
    
    // GET THE TOTAL ACCOUNT GIVEN
    public double getAccountGiven(Connection connection) throws Exception{
         boolean isOpen = false;
        ConnectionBase cb = new ConnectionBase();
        if(connection == null){
            connection = cb.dbConnect();     // If it is null, creating connection
        }else{
            isOpen = true;
        }
      
        try {
           return this.getMontantWithoutRemise(connection) - this.getMontantWithRemise(connection);
            
            
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
            throw new Exception("Error on getting the montant of remise of the facture. "+ e.getMessage());
        } finally{
            if(isOpen == false) connection.close();
        }
    }
    
    
    // GET TOTAL OF FACTURE WITHOUT THE REMISE
    public double getMontantWithoutRemise(Connection connection) throws Exception{
    
         boolean isOpen = false;
        ConnectionBase cb = new ConnectionBase();
        if(connection == null){
            connection = cb.dbConnect();     // If it is null, creating connection
        }else{
            isOpen = true;
        }
        PreparedStatement pm = null;
        ResultSet resultSet = null;
       
        try {
            String sqlQuery = "SELECT SUM(montant) FROM facture_fille WHERE id_facture = ?";
            pm = connection.prepareStatement(sqlQuery);
            pm.setString(1, this.getIdFacture());
            resultSet = pm.executeQuery();
            if (resultSet.next()) {
                // Retrieve the sum of prices from the result set
                double totalPrice = resultSet.getDouble(1);

                // Return the result
                return totalPrice;
            }else{
                return 0.0;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error on getting the montant without renise of the facture. "+ e.getMessage());
        } finally{
            resultSet.close();
            pm.close();
            if(isOpen == false) connection.close();
        }    
    }
    

    // CS
    public Facture() {
    }

    public Facture(String idClient, String idRemise, String date) throws Exception{
        try {
            this.setIdClient(idClient);
            this.setIdRemise(idRemise);
            this.setDate(date);
            this.setStatus(0);
            this.setMontantTotal(0.0);
        } catch (Exception e) {
            throw new Exception("Error on constructing new invoice. "+e.getMessage());
        }
    }
    
    
    public String myStatus(){
        String[] all = {"Creer", "Non payee", "Payee"};
        return all[this.getStatus()];
    }
    
    // getters and setters

    public String getIdFacture() {
        return idFacture;
    }

    public void setIdFacture(String idFacture) {
        this.idFacture = idFacture;
    }

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public String getIdRemise() {
        return idRemise;
    }

    public void setIdRemise(String idRemise) {
        this.idRemise = idRemise;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public void setDate(String dtnHml) throws Exception{
        try {
            java.sql.Date date = DateUtil.stringToSqlDate(java.sql.Date.class, dtnHml, DatePattern.YYYY_MM_DD);
            this.setDate(date);
        } catch (Exception e) {
            throw new Exception("Error on setting the date of invoice. "+e.getMessage());
        }
    } 

    public Double getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(Double montantTotal) {
        this.montantTotal = montantTotal;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Client getClient() throws Exception{
        if(this.getIdClient() == null) return null;
        try {
            Client cl = new Client();
            cl.setIdClient(this.getIdClient());
            return BddObject.findById(cl, null);
        } catch (Exception e) {
            throw new Exception("Error on getting the client of the invoice. "+e.getMessage());
        }
    }

    public Remise getRemise() throws Exception{
        if(this.getIdRemise()== null) return null;
        try {
            Remise cl = new Remise();
            cl.setIdRemise(this.getIdRemise());
            return BddObject.findById(cl, null);
        } catch (Exception e) {
            throw new Exception("Error on getting the remise of the invoice. "+e.getMessage());
        }
    }

    public List<FactureFille> getFilles() throws Exception{
        try {
            FactureFille cl = new FactureFille();
            cl.setIdFacture(this.getIdFacture());
            return BddObject.find(cl, null);
        } catch (Exception e) {
            throw new Exception("Error on getting the filles of the invoice. "+e.getMessage());
        }
    }

    public void setFilles(List<FactureFille> filles) {
        this.filles = filles;
    }

    public List<Paiement> getPaiements() throws Exception{
        if(this.getIdFacture() == null) return null;
        try {
            Paiement paiement = new Paiement();
            paiement.setIdFacture(this.getIdFacture());
            return BddObject.findByOrder(paiement, "datePaiement", Ordering.DESC, null);
        } catch (Exception e) {
            throw new Exception("Error on getting paiments of factures");
        }
    }

    public void setPaiements(List<Paiement> paiements) {
        this.paiements = paiements;
    }
    
    
    
    
}
