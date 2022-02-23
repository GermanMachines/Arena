/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.services;

import edu.arena.entities.Match;
import edu.arena.entities.MatchEquipe;
import edu.arena.utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author tarek
 */
public class MatchEquipeCrud {
             private Connection con;
    private Statement ste;

    public MatchEquipeCrud() {
        con = DataBase.getInstance().getConnection();
    }
    
         public void ajouter(MatchEquipe m) throws SQLException {
        PreparedStatement pre = con.prepareStatement("INSERT INTO match_eq (idMatch ,IdEquipe , Score ) VALUES (?,?,?);");
        pre.setInt(1, m.getIdMatch());
        pre.setInt(2, m.getIdEquipe());
        pre.setInt(3, m.getScore());

        pre.executeUpdate();
    }
         
         
         
             public boolean Update(int idMatch  ,int IdEquipe  , int Score) {
            try {
            PreparedStatement pre = con.prepareStatement("UPDATE match_eq SET  Score= ? where idMatch = ? And IdEquipe=? ;");
            pre.setInt(1, Score);
            pre.setInt(2, idMatch);
            pre.setInt(3, IdEquipe);

            if (pre.executeUpdate() != 0) {
                System.out.println(" Match Score updated");
                 } else {
                System.out.println("non");
            }
                return true;
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
         
         
         
         
    
}
