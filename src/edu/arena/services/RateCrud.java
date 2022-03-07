/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.services;

import edu.arena.entities.rate;
import edu.arena.utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class RateCrud {
         private Connection con;
    private Statement ste;

    public RateCrud() {
        con = DataBase.getInstance().getConnection();

    }
    
    
    
    
     public void ajouter(rate c) throws SQLException {
        PreparedStatement pre = con.prepareStatement("INSERT INTO rate (nombre,id_post)  VALUES (?,?);");
                                                 
        pre.setInt(1, c.getNombre());
        pre.setInt(2, c.getId_post());
        pre.executeUpdate();
    }
}