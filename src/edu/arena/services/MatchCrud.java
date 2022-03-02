/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.services;

import edu.arena.entities.Jeux;
import edu.arena.entities.JeuxTournois;
import edu.arena.entities.Match;
import edu.arena.utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author tarek
 */
public class MatchCrud {
             private Connection con;
    private Statement ste;

    public MatchCrud() {
        con = DataBase.getInstance().getConnection();
    }
    
    
    
     
    
    
     public void ajouter(Match m) throws SQLException {
        PreparedStatement pre = con.prepareStatement("INSERT INTO matchs (IdTournois,DateMatch,Reference)VALUES (?,?,?);");
        pre.setInt(1, m.getIdTournois());
        pre.setString(2, m.getDateMatch());
        pre.setString(3,m.getReference());
        pre.executeUpdate();
    }
     
     
     
     
             public List<Integer> rechercheJeuxTournois(int idTournois) {
        {
            List<Integer> arr = new ArrayList<>();

            try {
                PreparedStatement pre = con.prepareStatement("SELECT  t.IdTournois,e.IdEquipe  FROM  equipe e ,tournois t, participation p WHERE p.IdEquipe = e.IdEquipe AND p.IdTournois = t.IdTournois and p.IdTournois=? ");
                pre.setInt(1, idTournois);
                ResultSet rs = pre.executeQuery();

                while (rs.next()) {
                    

                    int idT = rs.getInt(1);
                    int idcla=rs.getInt("IdEquipe");
                    
                    JeuxTournois ce = new JeuxTournois(idTournois, idcla);
                    ce.setIdequipe(idcla);
                    arr.add(idcla);
                }
            } catch (SQLException ex) {
                Logger.getLogger(JeuxTournoisCrud.class.getName()).log(Level.SEVERE, null, ex);
            }

            return arr;
        }
    }
     
     
     
     
     
     
     
             public List<Match> getMatchbyIdTournois(int IdTournois) throws SQLException {
        List<Match> arr = new ArrayList<>();
         try {
        PreparedStatement pre = con.prepareStatement("SELECT IdTournois , DateMatch , idMatch FROM matchs WHERE IdTournois=?; "); //ORDER BY P asc
         pre.setInt(1, IdTournois);
         ResultSet rs = pre.executeQuery();

             while(rs.next()){
                     int idT = rs.getInt(1);
                     int idmatch=rs.getInt("idMatch");
                     String Date=rs.getString("DateMatch");
                     Match m=new Match(idmatch,idT,Date);
                    arr.add(m);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return arr;
    }
             
             
             
                   public List<Match> readAll(){

        List<Match> lu = new ArrayList<>();
         try {
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select idMatch ,IdTournois,DateMatch,Reference from matchs");
        while (rs.next()) {
            int idmatch=rs.getInt("idMatch");
            int idtouronis = rs.getInt("IdTournois");
            String DateMatch = rs.getString("DateMatch");
              String Reference = rs.getString("Reference");
            Match j = new Match(idmatch,idtouronis, DateMatch , Reference);
            lu.add(j);
        }
         } catch (SQLException ex) {
        }
        return lu;
    }  
             
             
             
             
             
             

}
