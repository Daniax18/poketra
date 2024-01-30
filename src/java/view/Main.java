/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import database.ConnectionBase;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mapping.BddObject;
import model.AchatMatiere;
import model.BeneficeRate;
import model.BuyMatiere;
import model.Employer;
import model.Fabrication;
import model.Facture;
import model.FactureFille;
import model.Look_sac;
import model.MatierePremiere;
import model.Mouvement;
import model.Poketra;
import model.Poketra_matiere;
import model.Poste;
import model.Taille_sac;
import model.Taille_sac_emp;
import model.Type_sac;
import utilities.MyFileUtil;
import utilities.Ordering;

/**
 *
 * @author rango
 */
public class Main {
    
  
    
    
    public static void main(String[] args) throws Exception{
        
     

// ---------------------------------------------------------------------------------------------------

         Connection connection = null;
         ConnectionBase cb = new ConnectionBase();
        try {
            connection = cb.dbConnect();
            Poketra poketra = new Poketra();
            poketra.setIdPoketra("POK_4");
            poketra = BddObject.findById(poketra, connection);
            
            System.out.println("Men " + poketra.canMakeSortie(2, connection));
            
           connection.commit();
        } catch (Exception e) {
            connection.rollback();
            throw new Exception("Error on main. "+e.getLocalizedMessage());
        } finally{
            connection.close();
        }  
    }
}
