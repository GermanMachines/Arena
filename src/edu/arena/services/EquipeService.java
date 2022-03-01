/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.services;

import edu.arena.entities.Equipe;
import edu.arena.utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class EquipeService implements IUser <Equipe> {
 Connection connexion;
    Statement stm;

    public EquipeService() {
        connexion = DataBase.getInstance().getConnection();
    }

    @Override
    public void ajouter(Equipe p) throws SQLException {
       
        String req;
         req = "INSERT INTO `equipe`(`nom`, `logo`, `score`, `region`) "
                 + "VALUES (?,?,?,?)";

       
            PreparedStatement pst =connexion.prepareStatement(req);
            pst.setString(1,p.getNom());
            pst.setString(2,p.getLogo());
            pst.setInt(3,p.getScore());
            pst.setString(4,p.getRegion());
       
                      
            pst.executeUpdate();  
           
        
    }

    @Override
    public void modifier(int idEquipe, Equipe p) throws SQLException {
      String req;

        req = "UPDATE `equipe` SET  `nom`=? ,`logo`=?,`score`=?,`region`=? WHERE idEquipe =?";

        try {
            PreparedStatement ps = connexion.prepareStatement(req);
            ps.setString(1, p.getNom());
           
            ps.setString(2, p.getLogo());
            ps.setInt(3, p.getScore());
            ps.setString(4, p.getRegion());
          
            ps.setInt(5, idEquipe);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    
    
        
    }

    @Override
    public void supprimer(int idequipe) throws SQLException {
          try {
            PreparedStatement pre = connexion.prepareStatement("Delete from equipe where idEquipe=? ;");
            pre.setInt(1, idequipe);
            if (pre.executeUpdate() != 0) {
                System.out.println("equipe Deleted");
                
            }

        } catch (SQLException ex) {
            ex.getMessage();
        }
        System.out.println("id equipe not found!!!");
    }

    @Override
    public List<Equipe> afficher() throws SQLException {
        List<Equipe> equipes = new ArrayList<>();
        String req = "select * from equipe";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Equipe p;
            p = new Equipe(
                    rst.getInt("idEquipe"),//or rst.getInt(1)
                    rst.getString("nom"),
                    rst.getString("logo"),
                    rst.getInt("score"),
                    rst.getString("region")
            );
            equipes.add(p);
        }
        return equipes;  
    }
    
   
    
    
    
    
}
