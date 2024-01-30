/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import annoted.ColumnField;
import annoted.TableAnnotation;
import database.ConnectionBase;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;
import mapping.BddObject;
import utilities.DatePattern;
import utilities.DateUtil;
import utilities.Ordering;

/**
 *
 * @author ITU
 */
@TableAnnotation(nameTable = "Employer", sequence = "Employer_seq", prefix = "EMP")
public class Employer {
    @ColumnField(column = "Id", primary_key = true, is_increment = true)
    private String id;
    
    @ColumnField(column = "Nom")
    private String nom;
    
    @ColumnField(column = "Prenom")
    private String prenom;
    
    @ColumnField(column = "date_naissance")
    private java.sql.Date date_naissance;
    
    @ColumnField(column = "date_embauche")
    private java.sql.Date dateEmbauche;
    
    @ColumnField(column = "genre")
    private String genre;
    
    // GET MY POSTE
    public Poste getMyPoste(Connection connection) throws Exception{
        boolean isOpen = false;
        ConnectionBase cb = new ConnectionBase();
        if(connection == null){
            connection = cb.dbConnect();     // If it is null, creating connection
        }else{
            isOpen = true;
        }       
        try {
          double my_anciennete = this.my_anciennete(connection);
            List<CoefficientPoste> cp = BddObject.findByOrder(new CoefficientPoste(), "ancienete", Ordering.ASC, connection);
            for(int i = 0; i < cp.size(); i++){
                if(cp.get(i).getAncienete() > my_anciennete){
                    Poste poste = new Poste();
                    poste.setIdPoste(cp.get(i - 1).getIdPoste());
                    return BddObject.findById(poste, connection);
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error on getting the poste of employe. "+ e.getMessage());
        } finally{
            if(isOpen == false) connection.close();
        }
    }
    
    // GET MY ANCIENNETE
    public double my_anciennete(Connection connection) throws Exception{
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
            String sqlQuery = "SELECT nombre_de_jours FROM v_anciennete WHERE id = ?";
            pm = connection.prepareStatement(sqlQuery);
            pm.setString(1, this.getId());
            resultSet = pm.executeQuery();
            if (resultSet.next()) {
                // Retrieve the sum of prices from the result set
                double totalPrice = resultSet.getDouble(1);

                // Return the result
                return totalPrice / 365;
            }else{
                return 0.0;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error on getting the anciennete of employe. "+ e.getMessage());
        } finally{
            resultSet.close();
            pm.close();
            if(isOpen == false) connection.close();
        }
    }
    
    // CS

    public Employer(String nom, String prenom, String date_naissance, String dateEmbauche, String genre) throws Exception{
        try {
            this.setNom(nom);
            this.setPrenom(prenom);
            this.setDate_naissance(date_naissance);
            this.setDateEmbauche(dateEmbauche);
            this.setGenre(genre);
        } catch (Exception e) {
            throw new Exception("Error on constructing the new employer. "+e.getMessage());
        }
    }

    public Employer() {
    }
    
    
    
    // getter setter 

    public String getId() {
        return id;
    }

    public void setId(String Id) {
        this.id = Id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String Nom) {
        this.nom = Nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String Prenom) {
        this.prenom = Prenom;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) throws Exception{
        try{           
        LocalDate local_date = date_naissance.toLocalDate();
        int year = local_date.getYear();
        int year_initial = local_date.now().getYear();
        if(year_initial - year < 18){
            throw new Exception("revenez lorsque vous etes majeur");
        }
        else{
            this.date_naissance = date_naissance;
        }
        }catch(Exception e){
            throw new Exception("Erreur : " + e.getMessage());
        }
    }
//    
    public void setDate_naissance(String dateHtml) throws Exception{
        try {
            java.sql.Date date = DateUtil.stringToSqlDate(java.sql.Date.class, dateHtml, DatePattern.YYYY_MM_DD);
            this.setDate_naissance(date);
        } catch (Exception e) {
            throw new Exception("erreur sur la date" + e.getMessage());
        }
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Date getDateEmbauche() {
        return dateEmbauche;
    }

    public void setDateEmbauche(Date dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }
    
    public void setDateEmbauche(String dateHtml) throws Exception{
       try {
            java.sql.Date date = DateUtil.stringToSqlDate(java.sql.Date.class, dateHtml, DatePattern.YYYY_MM_DD);
            this.setDateEmbauche(date);
        } catch (Exception e) {
            throw new Exception("erreur sur la date" + e.getMessage());
        }
    }
    
    
    
}
