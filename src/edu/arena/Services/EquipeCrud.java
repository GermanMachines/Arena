/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.services;

import edu.arena.entities.Equipe;
import edu.arena.entities.Tournois;
import edu.arena.utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;

/**
 *
 * @author tarek
 */
public class EquipeCrud {
    private Connection con;
    private Statement ste;

    public EquipeCrud() {
        con = DataBase.getInstance().getConnection();
    }
    
    
    public List<Integer> ListEquipe() {

        List<Integer> arr = new ArrayList<>();

        try {

            PreparedStatement pre = con.prepareStatement("SELECT IdEquipe from equipe ");
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {

                int idequipe = rs.getInt("IdEquipe");

                arr.add(idequipe);
            }
        } catch (SQLException ex) {
        }

        return arr;

    }

    public List<Equipe> getEquipebyid(int i) throws SQLException {
        List<Equipe> arr = new ArrayList<>();
        ste = con.createStatement();

        try {

            PreparedStatement pre = con.prepareStatement("SELECT IdEquipe , nom , logo , score , region from equipe where IdEquipe=?");
            pre.setInt(1, i);
   
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {

                 String nom = rs.getString("nom");
                 String logo = rs.getString("logo");
                 int score = rs.getInt("score");
                 String region = rs.getString("region");
                
                int idequipe = rs.getInt("IdEquipe");
            Equipe e = new Equipe(idequipe,nom,logo,score,region);

                arr.add(e);
            }
        } catch (SQLException ex) {
        }

        return arr;

    }

    public List<Equipe> getEquipebyidMatch(int IdMatch) throws SQLException {
 List<Equipe> arr = new ArrayList<>();
        ste = con.createStatement();

        try {
  PreparedStatement pre = con.prepareStatement("SELECT e.IdEquipe ,nom , region FROM match_eq m , equipe e WHERE m.IdEquipe=e.IdEquipe and m.idMatch=?");
                pre.setInt(1, IdMatch);
   
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                int IdEquipe = rs.getInt("e.IdEquipe");
                 String nom = rs.getString("nom");
                 String region = rs.getString("region");
                
            Equipe e = new Equipe(IdEquipe,nom,region);

                arr.add(e);
            }
        } catch (SQLException ex) {
        }

        return arr;


    }


    
    
    
}
