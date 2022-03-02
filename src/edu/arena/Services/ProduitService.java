/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.Services;

import edu.arena.Services.IService;
import edu.arena.entities.Produit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import edu.arena.utils.DataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author DeathKnight
 */
public class ProduitService implements IService<Produit> {
       Connection connexion;
       Statement stm;
        public ProduitService() {
        connexion = DataBase.getInstance().getConnection();
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
    public ObservableList<Produit> afficher() throws SQLException {
        ObservableList<Produit> list = FXCollections.observableArrayList();
        stm = connexion.createStatement();
        ResultSet rs = stm.executeQuery("select * from produit");
        while (rs.next()) {
            int id = rs.getInt("id");
            String nom = rs.getString("nom");
            //missing avis part
            Produit prod = new Produit(id,nom);
            list.add(prod);
        }
        return list;
    }
    
}
