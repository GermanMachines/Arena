/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.services;

import edu.arena.entities.Equipe;
import edu.arena.entities.Match;
import edu.arena.entities.MatchEquipe;
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
             
             
 public void randomize(List<MatchEquipe> matches) {
    List<MatchEquipe> randomizedList = new ArrayList<>();
    int numberOfAttempts = 256;

    // tmpSubList is a temporary list that contains all matches
    // (excluding unwanted) after n-th iteration (randomization).
    List<MatchEquipe> tmpSubList = new ArrayList<MatchEquipe>(matches);
    while (matches.size() > 0) {

        // if tmpSubList contains - it means there is no match that can be added.
        // Need to restart randomization algorithm.
        if (tmpSubList.size() == 0) {
            System.out.println("Restarting algorithm.");

            if (--numberOfAttempts == 0) {
                throw new ArithmeticException("Could not find solution.");
            }
            // Need to restart:
            matches.addAll(randomizedList);
            tmpSubList.addAll(randomizedList);
            randomizedList.clear();
        }

        int randomIndex = (int) (tmpSubList.size() * Math.random());

        MatchEquipe match = tmpSubList.remove(randomIndex);
        matches.remove(match); // remove also from the main list;
        randomizedList.add(match);

        int lastTeam1 = match.getIdEquipe();
        int lastTeam2 = match.getIdEquipe();

        tmpSubList.clear();
        matches.stream()
            .forEach( x -> tmpSubList.add(x));
    }
     System.out.println(randomizedList);
    matches.addAll(randomizedList);
}
        
      public List<MatchEquipe> readAll(){

        List<MatchEquipe> lu = new ArrayList<>();
         try {
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select idMatch ,IdEquipe,score from match_eq");
        while (rs.next()) {
            int idmatch=rs.getInt("idMatch");
            int IdEquipe = rs.getInt("IdEquipe");
            int score = rs.getInt("score");
            MatchEquipe j = new MatchEquipe(idmatch,IdEquipe, score);
            lu.add(j);
        }
         } catch (SQLException ex) {
        }
        return lu;
    }  
      
      
        public List<MatchEquipe> readAllbyMatch(int idmatch){

        List<MatchEquipe> lu = new ArrayList<>();
         try {
        PreparedStatement pre = con.prepareStatement("select  e.nom , e.region , mq.idMatch , mq.score  from equipe e , match_eq mq where idMatch=? and e.IdEquipe=mq.IdEquipe;");        
        pre.setInt(1, idmatch);
        ResultSet rs = pre.executeQuery();

        while (rs.next()) {
            int Idmatch=rs.getInt("mq.idMatch");
            String nomequipe=rs.getString("e.nom");
            String region = rs.getString("region");
            int score = rs.getInt("score");
            MatchEquipe meq = new MatchEquipe(Idmatch,nomequipe,region, score);
            lu.add(meq);
        }
         } catch (SQLException ex) {
        }
        return lu;
    }
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
         
    
}
