/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.services;

import edu.arena.entities.Jeux;
import edu.arena.utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

/**
 *
 * @author tarek
 */
public class JeuxCrud {
         private Connection con;
    private Statement ste;
    
    



    public JeuxCrud() {
        con = DataBase.getInstance().getConnection();

    }
    
    
    
    
     public void ajouter(Jeux j) throws SQLException {
        PreparedStatement pre = con.prepareStatement("INSERT INTO jeux (NomJeux,ImageJeux)VALUES (?,?);");
        pre.setString(1, j.getNomJeux());
        pre.setString(2, j.getImageJeux());
        pre.executeUpdate();
    }


     
         public boolean Update(int IdJeux ,String NomJeux , String ImageJeux) {
            try {
            PreparedStatement pre = con.prepareStatement("UPDATE jeux SET NomJeux= ? ,ImageJeux = ? where IdJeux= ? ;");
            pre.setString(1, NomJeux);
            pre.setString(2, ImageJeux);
            pre.setInt(3, IdJeux);   


            if (pre.executeUpdate() != 0) {
                System.out.println(" game updated");
                 } else {
                System.out.println("non");
            }
                return true;
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
         
         
 

         
         
         
         
         
     
     
     
     
     
     
     
     

    public boolean delete(Integer idcode) throws SQLException {

        PreparedStatement pre = con.prepareStatement("Delete from jeux where IdJeux=? ;");
        pre.setInt(1, idcode);
        if (pre.executeUpdate() != 0) {
            System.out.println("Jeux Deleted");
            return true;
        }
        System.out.println("id jeux not found!!!");
        return false;

    }
    
        public List<Jeux> readAll(){

        List<Jeux> lu = new ArrayList<>();
         try {
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select IdJeux ,NomJeux,ImageJeux  from jeux");
        while (rs.next()) {
            int IdJeux=rs.getInt("IdJeux");
            String NomJeux = rs.getString("NomJeux");
            String ImageJeux = rs.getString("ImageJeux");
            Jeux j = new Jeux(IdJeux,NomJeux, ImageJeux);
            lu.add(j);
        }
         } catch (SQLException ex) {
        }
        return lu;
    }
        
        public ObservableList<Jeux> readAll2(){

        ObservableList<Jeux> lu = FXCollections.observableArrayList();
         try {
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select IdJeux ,NomJeux,ImageJeux  from jeux");
        while (rs.next()) {
            int IdJeux=rs.getInt("IdJeux");
            String NomJeux = rs.getString("NomJeux");
            String ImageJeux = rs.getString("ImageJeux");
            Jeux j = new Jeux(IdJeux,NomJeux, ImageJeux);
            lu.add(j);
        }
         } catch (SQLException ex) {
        }
        return lu;
    }
        

        
        
        
        
        
        
        
    public List<String> ListJeux() {

        List<String> arr = new ArrayList<>();

        try {

            PreparedStatement pre = con.prepareStatement("SELECT NomJeux from jeux");
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {

                String Nomjeux = rs.getString("NomJeux");

                arr.add(Nomjeux);
            }
        } catch (SQLException ex) {
            
        }

        return arr;

    }
        
    
        

    public String getImageJeux(int idjeux) {
        String q = "";

        String requete4 = "select ImageJeux from jeux where IdJeux=?;";
        PreparedStatement pst;
        try {
            pst = con.prepareStatement(requete4);
            pst.setInt(1, idjeux);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                q = rs.getString(1);
            }
        } catch (SQLException ex) {
        }
        return q;
    
    }
    
    
    
                public int getidJeuxbynom(String i) throws SQLException {
        ste = con.createStatement();
        int idjeux = 0;

        try {

            PreparedStatement pre = con.prepareStatement("SELECT IdJeux  from jeux where NomJeux=?");
            pre.setString(1, i);
   
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {


                
                 idjeux = rs.getInt("IdJeux");

            }
        } catch (SQLException ex) {
        }

        return idjeux;

    }
                
                
        public String getJeuxBynom(int i) throws SQLException {
        ste = con.createStatement();
        String NomJeux = "";

        try {

            PreparedStatement pre = con.prepareStatement("SELECT NomJeux  from jeux where IdJeux=?");
            pre.setInt(1, i);
   
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {


                
                 NomJeux = rs.getString("NomJeux");

            }
        } catch (SQLException ex) {
        }

        return NomJeux;

    }            
        
    
    
}
