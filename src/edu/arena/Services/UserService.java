/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.Services;

import edu.arena.entities.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import edu.arena.utils.DataBase;

/**
 *
 * @author DeathKnight
 */
public class UserService implements IService<User> {
   Connection connexion;
   Statement stm;
     public UserService() throws SQLException {
      connexion = DataBase.getInstance().getConnection();

    }
     
    @Override
    public void ajouter(User u) throws SQLException {
      try{
        stm = connexion.createStatement();
      //  String req = "INSERT INTO utulisateur (`nom`) VALUES ( '"+ u.getNom()+"') ;";
      String req = "INSERT INTO `utulisateur`(`username`) VALUES ('"+u.getNom()+"');";
        stm.executeUpdate(req);
      }catch(SQLException e){
          e.printStackTrace();
      }
       
    }

    @Override
    public void update(User o) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List afficher() throws SQLException {
      /*List<User> presonnes = new ArrayList<>();
        String req = "select * from personne";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            User p = new User(rst.getInt("id"));//or rst.getInt(1)
                    rst.getString("nom"),
                  
            presonnes.add(p);
        }
        return presonnes; } */
          List<User> list = new ArrayList<>();
        stm = connexion.createStatement();
        ResultSet rs = stm.executeQuery("select * from utulisateur");
        while (rs.next()) {
            int id = rs.getInt("id");
            String nom = rs.getString("nom");
            User u = new User(id, nom);
            list.add(u);
        }
        return list;
    }
}
