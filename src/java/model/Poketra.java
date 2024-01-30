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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import mapping.BddObject;
import utilities.Ordering;

/**
 *
 * @author rango
 */
@TableAnnotation(nameTable = "poketra", sequence = "poketra_seq", prefix = "POK_")
public class Poketra {
    @ColumnField(column = "id_poketra", primary_key = true, is_increment = true)
    private String idPoketra;
    
    @ColumnField(column = "nom_poketra")
    private String nomPoketra;
    
    @ColumnField(column = "id_type_sac")
    private String idTypeSac;
    
    @ColumnField(column = "id_look_sac")
    private String idLookSac;
    
    @ColumnField(column = "id_taille_sac")
    private String idTailleSac;
    
    private Type_sac my_type_sac;
    
    private Look_sac my_look_sac;
    
    private Taille_sac my_taille_sac;
    
    private List<Poketra_matiere> my_matiere;
    
    
    // IF CAN MAKE MOUVEMENT
    public boolean canMakeSortie(double qty, Connection connection) throws Exception{
        double result = 0;
        boolean isOpen = false;
        ConnectionBase cb = new ConnectionBase();
        if(connection == null){
            connection = cb.dbConnect();     // If it is null, creating connection
        }else{
            isOpen = true;
        }
       
        try {
           if(this.myQuantityActual(connection) < qty){
               return false;
           }
           return true;
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
            throw new Exception("Error on knowing if we can make move. "+ e.getMessage());
        } finally{
            if(isOpen == false) connection.close();
        }
    }
    
    // Get value stock and stock
    // 0 -> QTY
    // 1 -> VALEUR STOCK
    public double[] myStockAndValue(Connection connection) throws Exception{
         double[] result = new double[2];
        boolean isOpen = false;
        ConnectionBase cb = new ConnectionBase();
        if(connection == null){
            connection = cb.dbConnect();     // If it is null, creating connection
        }else{
            isOpen = true;
        }
       
        try {
            result[0] = this.myQuantityActual(connection);
            result[1] = result[0] * this.my_pv(connection);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error on getting the stock and its value. "+ e.getMessage());
        } finally{
            if(isOpen == false) connection.close();
        }
    }
    
    
    // GET MY ACTUAL STOCK
    public double myQuantityActual(Connection connection) throws Exception{
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
            String sqlQuery = "SELECT SUM(qty_entree) - SUM(qty_sortie) as reste FROM mouvement_poketra WHERE id_poketra = ?";
            pm = connection.prepareStatement(sqlQuery);
            pm.setString(1, this.getIdPoketra());
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
            throw new Exception("Error on getting actual qty of the poketra. "+ e.getMessage());
        } finally{
            resultSet.close();
            pm.close();
            if(isOpen == false) connection.close();
        }
    }
    
    
    // EXPLOITE RATION FROM DB
    public double[] mydata(Connection connection) throws Exception{
         double result[] = new double[2];
        boolean isOpen = false;
        ConnectionBase cb = new ConnectionBase();
        if(connection == null){
            connection = cb.dbConnect();     // If it is null, creating connection
        }else{
            isOpen = true;
        }
       
        try {
            result[0] = this.ratioSellingGenre(0, connection);
            result[1] = this.ratioSellingGenre(1, connection);
            
           return result;
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
            throw new Exception("Error on getting my benefice. "+ e.getMessage());
        } finally{
            if(isOpen == false) connection.close();
        }
    
    }
    
    
    // GET MY RATIO SELLING BRUT FROM DB
    public double ratioSellingGenre(Integer genre, Connection connection) throws Exception{
         boolean isOpen = false;
        ConnectionBase cb = new ConnectionBase();
        if(connection == null){
            connection = cb.dbConnect();     // If it is null, creating connection
        }else{
            isOpen = true;
        }
        PreparedStatement pm = null;
        ResultSet resultSet = null;
        HashMap<String, Integer> result = new HashMap<>();
        HashMap<Integer, Double> temp = new HashMap<>();
        try {
            String sqlQuery = "SELECT * FROM v_vente_ratio WHERE id_poketra = ? and genre = ?";
            pm = connection.prepareStatement(sqlQuery);
            pm.setString(1, this.getIdPoketra());
            pm.setInt(2, genre);
            resultSet = pm.executeQuery();
            if (resultSet.next()) {
                // Retrieve the sum of prices from the result set
//                double totalPrice = resultSet.getDouble(1);
                return resultSet.getDouble(3);
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error on getting the ratio of the poketra selling. "+ e.getMessage());
        } finally{
            resultSet.close();
            pm.close();
            if(isOpen == false) connection.close();
        }
        
    }
    
    // get benefice
    public double my_benefice(Connection connection) throws Exception{
        double result = 0;
        boolean isOpen = false;
        ConnectionBase cb = new ConnectionBase();
        if(connection == null){
            connection = cb.dbConnect();     // If it is null, creating connection
        }else{
            isOpen = true;
        }
       
        try {
            result = this.my_pv(connection) - this.totalPriceFabrication(connection);
            
           return result;
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
            throw new Exception("Error on getting my benefice. "+ e.getMessage());
        } finally{
            if(isOpen == false) connection.close();
        }
    }
    
    
    // get my PRIX VENTE par rapport taux de benefice
//    public double  my_pv(Connection connection) throws Exception{
//        double result = 0;
//        boolean isOpen = false;
//        ConnectionBase cb = new ConnectionBase();
//        if(connection == null){
//            connection = cb.dbConnect();     // If it is null, creating connection
//        }else{
//            isOpen = true;
//        }
//       
//        try {
//           BeneficeRate br = new BeneficeRate();
//           br.setIdPoketra(this.getIdPoketra());
//           List<BeneficeRate> temp = BddObject.findByOrder(br, "dateRate", Ordering.DESC, connection);
//           if(temp.size() > 0){
//               double my_pr = this.totalPriceFabrication(connection);
//               result = my_pr + ((my_pr * temp.get(0).getRate()) / 100);
//           }
//            
//           return result;
//        } catch (Exception e) {
//            e.printStackTrace();
//            connection.rollback();
//            throw new Exception("Error on getting my prix de vente. "+ e.getMessage());
//        } finally{
//            if(isOpen == false) connection.close();
//        }
//    }
    
    
    
//    // get my PRIX VENTE
    public double  my_pv(Connection connection) throws Exception{
        double result = 0;
        boolean isOpen = false;
        ConnectionBase cb = new ConnectionBase();
        if(connection == null){
            connection = cb.dbConnect();     // If it is null, creating connection
        }else{
            isOpen = true;
        }
       
        try {
            PrixVentePoketra pv = new PrixVentePoketra();
            pv.setIdPoketra(this.getIdPoketra());
            List<PrixVentePoketra> temp = BddObject.find(pv, connection);
            if(temp.size() > 0){
                return temp.get(0).getPrice();
            }
            
           return result;
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
            throw new Exception("Error on getting my prix de vente. "+ e.getMessage());
        } finally{
            if(isOpen == false) connection.close();
        }
    }
    
    // PRENDRE LE TOTAL PRIX DE FABRICATION
    public double totalPriceFabrication(Connection connection) throws Exception{
        double result = 0;
        boolean isOpen = false;
        ConnectionBase cb = new ConnectionBase();
        if(connection == null){
            connection = cb.dbConnect();     // If it is null, creating connection
        }else{
            isOpen = true;
        }
       
        try {
            result = this.getPrixHomme(connection) + this.myPrixFabrication(connection);
            
           return result;
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
            throw new Exception("Error on getting my total prix revient unit. "+ e.getMessage());
        } finally{
            if(isOpen == false) connection.close();
        }
    }
    
        // GET PRIX HOMME WAGE
    public double getPrixHomme(Connection connection) throws Exception{
        double result = 0;
        boolean isOpen = false;
        ConnectionBase cb = new ConnectionBase();
        if(connection == null){
            connection = cb.dbConnect();     // If it is null, creating connection
        }else{
            isOpen = true;
        }
       
        try {
            List<Taille_sac_emp> poste_need = this.getMy_taille_sac().getEmployeeNeed(connection);
            double volume_horaire = this.getMy_type_sac().getMyVolumeHoraire(connection);
            for(Taille_sac_emp ts : poste_need){
                result += volume_horaire * ts.getMinValue() * ts.getMyPoste().getMyTauxHoraire(connection);
            }
                 
           return result;
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
            throw new Exception("Error on getting my prix homme unit. "+ e.getMessage());
        } finally{
            if(isOpen == false) connection.close();
        }
    }
    

//        // GET PRIX HOMME WAGE
//        public double getPrixHomme(Connection connection) throws Exception{
//            double result = 0;
//            boolean isOpen = false;
//            ConnectionBase cb = new ConnectionBase();
//            if(connection == null){
//                connection = cb.dbConnect();     // If it is null, creating connection
//            }else{
//                isOpen = true;
//            }
//
//            try {
//                double emp_need = this.getMy_taille_sac().getEmpNeed(connection);
//                double hour_need = this.getMy_look_sac().getHeureNeed(connection);
//                double taux = Taux_horaire.getTaux(connection);
//                result = taux * emp_need * hour_need;
//
//               return result;
//            } catch (Exception e) {
//                e.printStackTrace();
//                connection.rollback();
//                throw new Exception("Error on getting my prix homme unit. "+ e.getMessage());
//            } finally{
//                if(isOpen == false) connection.close();
//            }
//        }
    

    // MY PRIX EN TERME DE MATIERE PREMIERE
    public double myPrixFabrication(Connection connection) throws Exception{
        double result = 0;
        boolean isOpen = false;
        ConnectionBase cb = new ConnectionBase();
        if(connection == null){
            connection = cb.dbConnect();     // If it is null, creating connection
        }else{
            isOpen = true;
        }
       
        try {
            for(Poketra_matiere pm : this.getMy_matiere()){
                double my_pu = pm.getMy_mp().myUnitPrice(connection);
                double value_mat = my_pu * pm.getQty();
                result += value_mat;
            }
            
           return result;
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
            throw new Exception("Error on getting my price unit. "+ e.getMessage());
        } finally{
            if(isOpen == false) connection.close();
        }
    }
    
    // Insert a new poketra with its matieres
    public void savePoketraWithMatiere(String[] matieres) throws Exception{
        Connection connection = null;
        try {
            ConnectionBase cb = new ConnectionBase();
            connection = cb.dbConnect();
            String inserted = BddObject.insertInDatabase(this, connection);
            
            for(String s : matieres){
                Poketra_matiere pm = new Poketra_matiere(inserted, s, 0.0);
                BddObject.insertInDatabase(pm, connection);
            }
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            throw new Exception("Error on inserting new Poketra. " + e.getMessage());
        } finally{
            connection.close();
        }
    }
    
    // GET PRICE DE FABRICATION POKETRA BETWEEN 2  PRICE
    public static List<Poketra> all_poketra_between(String prix1, String prix2, Connection connection) throws Exception{
        List<Poketra> result = new ArrayList<>();
        boolean isOpen = false;
        ConnectionBase cb = new ConnectionBase();
        if(connection == null){
            connection = cb.dbConnect();     // If it is null, creating connection
        }else{
            isOpen = true;
        }
       
        try {
            Double v1 = Double.valueOf(prix1);
            Double v2 = Double.valueOf(prix2);
            if(v1 > v2) throw new Exception("Erreur");
            List<Poketra> temp = BddObject.find(new Poketra(), connection);
            
            for(Poketra p : temp){
                double data = p.totalPriceFabrication(connection);
                if(data >= v1 && data <= v2){
                    result.add(p);
                }
            }
            
           return result;
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
            throw new Exception("Error on getting all poketra between "+ prix1 + " and " + prix2 + " . Error: " + e.getMessage());
        } finally{
            if(isOpen == false) connection.close();
        }
    }
    
    // GET PRICE POKETRA BETWEEN 2 DATE
    public static List<Poketra> all_poketra_benefice_between(String prix1, String prix2, Connection connection) throws Exception{
        List<Poketra> result = new ArrayList<>();
        boolean isOpen = false;
        ConnectionBase cb = new ConnectionBase();
        if(connection == null){
            connection = cb.dbConnect();     // If it is null, creating connection
        }else{
            isOpen = true;
        }
       
        try {
            Double v1 = Double.valueOf(prix1);
            Double v2 = Double.valueOf(prix2);
            if(v1 > v2) throw new Exception("Erreur");
            List<Poketra> temp = BddObject.find(new Poketra(), connection);
            
            for(Poketra p : temp){
                double data = p.my_benefice(connection);
                if(data >= v1 && data <= v2){
                    result.add(p);
                }
            }
            
           return result;
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
            throw new Exception("Error on getting all poketra benefice between "+ prix1 + " and " + prix2 + " . Error: " + e.getMessage());
        } finally{
            if(isOpen == false) connection.close();
        }
    }
    
    @Override
    public String toString(){
        try {
            return this.getMy_type_sac().getNom() + " de "+this.getMy_look_sac().getNom_look() + " " + this.getMy_taille_sac().getNom();
        } catch (Exception e) {
            return "Error";
        }
    }
    
    // Constructors

    public Poketra() {
    }

    public Poketra(String nomPoketra, String idTypeSac, String idLookSac, String idTailleSac) {
        this.setNomPoketra(nomPoketra);
        this.setIdTypeSac(idTypeSac);
        this.setIdLookSac(idLookSac);
        this.setIdTailleSac(idTailleSac);
    }
    
    
    
    
    // GETTERS AND SETTERS
    public String getIdPoketra() {
        return idPoketra;
    }

    public void setIdPoketra(String idPoketra) {
        this.idPoketra = idPoketra;
    }

    public String getIdTypeSac() {
        return idTypeSac;
    }

    public void setIdTypeSac(String idTypeSac) {
        this.idTypeSac = idTypeSac;
    }

    public String getIdLookSac() {
        return idLookSac;
    }

    public void setIdLookSac(String idLookSac) {
        this.idLookSac = idLookSac;
    }

    public Type_sac getMy_type_sac() throws Exception{
        if(this.getIdTypeSac()== null) return null;
        try {
            Type_sac ls = new Type_sac();
            ls.setId(this.getIdTypeSac());
            return BddObject.findById(ls, null);
        } catch (Exception e) {
            throw new Exception("Error on getting the Look sac of the poketre. "+e.getMessage());
        }
    }

    public void setMy_type_sac(Type_sac my_type_sac) {
        this.my_type_sac = my_type_sac;
    }

    public Look_sac getMy_look_sac() throws Exception{
        if(this.getIdLookSac() == null) return null;
        try {
            Look_sac ls = new Look_sac();
            ls.setIdlook(this.getIdLookSac());
            return BddObject.findById(ls, null);
        } catch (Exception e) {
            throw new Exception("Error on getting the Look sac of the poketre. "+e.getMessage());
        }
    }

    public void setMy_look_sac(Look_sac my_look_sac) {
        this.my_look_sac = my_look_sac;
    }

    public String getIdTailleSac() {
        return idTailleSac;
    }

    public void setIdTailleSac(String idTailleSac) {
        this.idTailleSac = idTailleSac;
    }

    public Taille_sac getMy_taille_sac() throws Exception{
        if(this.getIdTailleSac()== null) return null;
        try {
            Taille_sac ls = new Taille_sac();
            ls.setId(this.getIdTailleSac());
            return BddObject.findById(ls, null);
        } catch (Exception e) {
            throw new Exception("Error on getting the Taille sac of the poketra. "+e.getMessage());
        }
    }

    public void setMy_taille_sac(Taille_sac my_taille_sac) {
        this.my_taille_sac = my_taille_sac;
    }

    public String getNomPoketra() {
        return nomPoketra;
    }

    public void setNomPoketra(String nomPoketra) {
        this.nomPoketra = nomPoketra;
    }

    public List<Poketra_matiere> getMy_matiere() throws Exception{
        if(this.getIdPoketra()== null) return null;
        try {
            Poketra_matiere pm = new Poketra_matiere();
            pm.setIdPoketra(this.getIdPoketra());
            return BddObject.find(pm, null);
        } catch (Exception e) {
            throw new Exception("Error on getting the materiels of the poketra. "+e.getMessage());
        }
    }

    public void setMy_matiere(List<Poketra_matiere> my_matiere) {
        this.my_matiere = my_matiere;
    }
    
    
    
    
}
