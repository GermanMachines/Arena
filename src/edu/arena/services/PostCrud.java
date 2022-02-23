/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.services;

import edu.arena.entities.Post;
import edu.arena.utils.DataBase;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PostCrud {
         private Connection con;
    private Statement ste;

    public PostCrud() {
        con = DataBase.getInstance().getConnection();

    }
    
    
    
    
     public void ajouter(Post p) throws SQLException {
        PreparedStatement pre = con.prepareStatement("INSERT INTO post (titre,auteur,img_post,date_post,id_com)VALUES (?,?,?,?,?);");
        pre.setString(1, p.getTitre());
        pre.setString(2, p.getAuteur());
        pre.setString(3, p.getImg_post());
        pre.setDate(4, p.getDate_post());
           pre.setInt(5, p.getId_com());

        
        pre.executeUpdate();
    }

    public boolean Update(String titre,String auteur,String img_post,Date date_post,int id_com,int id) {
            try {
            PreparedStatement pre = con.prepareStatement("update post set titre =? , auteur=? , img_post=? , date_post=? ,id_com=? where id_post=? ;");
            
         
            pre.setString(1, titre);
            pre.setString(2, auteur);   
            pre.setString(3, img_post);
            pre.setDate(4, date_post);
            pre.setInt(5,id_com);

             pre.setInt(6, id);
            if (pre.executeUpdate() != 0) {
                System.out.println(" post updated");
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("id  not found!!!");
        return false;
    }

    public boolean delete(Integer id_post) throws SQLException {

        PreparedStatement pre = con.prepareStatement("Delete from post where id_post=? ;");
        pre.setInt(1, id_post);
        if (pre.executeUpdate() != 0) {
            System.out.println("post Deleted");
            return true;
        }
        System.out.println("id psot not found!!!");
        return false;

    }
    
        public List<Post> readAll() throws SQLException {

        List<Post> lu = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select id_post ,titre,auteur,img_post,date_post ,id_com from post");
        while (rs.next()) {
            int id_post=rs.getInt("id_post");
            String titre = rs.getString("titre");
            String auteur = rs.getString("auteur");
            String img_post = rs.getString("img_post");
            Date date_post = rs.getDate("date_post");
            int id_com=rs.getInt("id_com");
            
            Post p = new Post(id_post ,titre,auteur,img_post,date_post,id_com);
            lu.add(p);
        }
        return lu;
    }

  
        
    
    
}
