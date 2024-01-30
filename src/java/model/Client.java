/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import annoted.ColumnField;
import annoted.TableAnnotation;
import java.sql.Date;
import utilities.DatePattern;
import utilities.DateUtil;

/**
 *
 * @author aram
 */
@TableAnnotation(nameTable = "client", sequence = "client_seq", prefix = "CLIENT_")
public class Client {
    @ColumnField(column = "id_client", primary_key = true, is_increment = true)
    private String idClient;
    
    @ColumnField(column = "nom_client")
    private String nom;
    
    @ColumnField(column = "prenom_client")
    private String prenom;
    
    @ColumnField(column = "dtn")
    private java.sql.Date dtn;

    @ColumnField(column = "genre")
    private Integer genre;
    
    // CS
    public Client() {
    }

    public Client(String nom, String prenom, String dtn, String genre) throws Exception{
        try {
            this.setNom(nom);
            this.setPrenom(prenom);
            this.setDtn(dtn);
            this.setGenre(Integer.valueOf(genre));
        } catch (Exception e) {
            throw  new Exception("Error on constructing the client. " + e.getMessage());
        }
    }
    

    // GETTERS AND SETTERS
    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDtn() {
        return dtn;
    }

    public void setDtn(Date dtn) {
        this.dtn = dtn;
    }
    
    public void setDtn(String dtnHml) throws Exception{
        try {
            java.sql.Date date = DateUtil.stringToSqlDate(java.sql.Date.class, dtnHml, DatePattern.YYYY_MM_DD);
            this.setDtn(date);
        } catch (Exception e) {
            throw new Exception("Error on setting the dtn of client");
        }
    } 

    public Integer getGenre() {
        return genre;
    }

    public void setGenre(Integer genre) {
        this.genre = genre;
    }
    
    
    
}
