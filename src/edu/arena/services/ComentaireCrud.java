/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.services;

import edu.arena.entities.Comentaire;
import edu.arena.utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ComentaireCrud {
         private Connection con;
    private Statement ste;

    public ComentaireCrud() {
        con = DataBase.getInstance().getConnection();

    }
    
    
    
    
     public void ajouter(Comentaire c) throws SQLException {
        PreparedStatement pre = con.prepareStatement("INSERT INTO commentaire (nom_com,desc_com)VALUES (?,?);");
        pre.setString(1, c.getNom_com());
        pre.setString(2, c.getDesc_com());
       
        pre.executeUpdate();
    }

    public boolean Update(String nom_com,String desc_com,int id) {
            try {
            PreparedStatement pre = con.prepareStatement("update commentaire set nom_com =? , desc_com=? where id_com=? ;");
            
           
            pre.setString(1, nom_com);
            pre.setString(2, desc_com);   
            pre.setInt(3, id);

            if (pre.executeUpdate() != 0) {
                System.out.println(" commantaire updated");
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("id  not found!!!");
        return false;
    }

    public boolean delete(Integer idcode) throws SQLException {

        PreparedStatement pre = con.prepareStatement("Delete from commentaire where id_com=? ;");
        pre.setInt(1, idcode);
        if (pre.executeUpdate() != 0) {
            System.out.println("commentaire Deleted");
            return true;
        }
        System.out.println("id commentaire not found!!!");
        return false;

    }
    
        public List<Comentaire> readAll() throws SQLException {

        List<Comentaire> lu = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select id_com ,nom_com,desc_com  from commentaire");
        while (rs.next()) {
            int id_com=rs.getInt("id_com");
            String nom_com = rs.getString("nom_com");
            String desc_com = rs.getString("desc_com");
           
            Comentaire c = new Comentaire(id_com ,nom_com,desc_com);
            lu.add(c);
        }
        return lu;
    }
        
    
    
}
