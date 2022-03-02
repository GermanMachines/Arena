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
        PreparedStatement pre = con.prepareStatement("INSERT INTO commentaire (id_user,desc_com,date_com,id_post)VALUES (?,?,?,?);");
        pre.setInt(1, c.getId_user());
        pre.setString(2, c.getDesc_com());
           pre.setString(3, c.getDate_com());
               pre.setInt(4, c.getId_post());
        pre.executeUpdate();
    }

    public boolean Update(int id_user ,String desc_com , String date_com ,int id_post,int id) {
            try {
            PreparedStatement pre = con.prepareStatement("update commentaire set id_user =?, desc_com=? , date_com=? , id_post=?  where id_com=? ;");
            
           
            pre.setInt(1, id_user);
            pre.setString(2, desc_com); 
            pre.setString(3, date_com);
            pre.setInt(4, id_post); 
            pre.setInt(5, id);

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
        ResultSet rs = ste.executeQuery("select id_com,id_user,desc_com,date_com,id_post from commentaire");
        while (rs.next()) {
            int  id_com=rs.getInt("id_com");
                int id_user=rs.getInt("id_user");
                 String desc_com = rs.getString("desc_com");
                 String date_com = rs.getString("date_com");
                int id_post=rs.getInt("id_post");
            Comentaire c = new Comentaire(id_com ,id_user,desc_com,date_com,id_post);
            lu.add(c);
        }
        return lu;
    }

    public List<Comentaire> getCombyPost(int id_post) {
     List<Comentaire> arr = new ArrayList<>();
         try {
        PreparedStatement pre = con.prepareStatement("SELECT c.id_com , u.nom , c.desc_com , c.date_com FROM user u , commentaire c , post p WHERE u.id=c.id_user and c.id_post=p.id_post and c.id_post=? ; "); //ORDER BY P asc
         pre.setInt(1, id_post);
         ResultSet rs = pre.executeQuery();

             while(rs.next()){
                     int idC = rs.getInt("c.id_com");
                     
                     String nomuser=rs.getString("u.nom");
                     String Commentaire=rs.getString("c.desc_com");
                     String Datecom=rs.getString("c.date_com");
                     
                     Comentaire m=new Comentaire(idC,nomuser,Commentaire,Datecom);
                    arr.add(m);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return arr;

    }
        
    
    
}
