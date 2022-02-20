/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import entities.Produit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;

/**
 *
 * @author DeathKnight
 */
public class ProduitService implements IService<Produit> {
       Connection connexion;
       Statement stm;
        public ProduitService() {
        connexion = MyDB.getInstance().getConnexion();
    }

    @Override
    public void ajouter(Produit o) throws SQLException {
    try{
  
        String req = "INSERT INTO `produit`(`nom`) VALUES ('"+o.getNom()+"');";
        stm = connexion.createStatement();
        stm.executeUpdate(req);
      }catch(SQLException e){
          e.printStackTrace();
      }
    }

    @Override
    public void update(Produit o) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Produit> afficher() throws SQLException {
          List<Produit> products = new ArrayList<>();
        stm = connexion.createStatement();
        ResultSet rs = stm.executeQuery("select id,nom from categoryreclamation");
        while (rs.next()) {
            int id = rs.getInt("id");
            String nom = rs.getString("nom");
            //missing avis part
            Produit prod = new Produit(id,nom);
            products.add(prod);
        }
        return products;
    }
    
}
