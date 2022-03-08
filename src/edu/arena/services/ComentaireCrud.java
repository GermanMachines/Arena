/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.services;

import edu.arena.entities.Comentaire;
import edu.arena.entities.Post;
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

    public boolean update(Comentaire a){
        Connection cnx =null;
        Statement st = null;
        
        
        String requette = "UPDATE commentaire SET desc_com='"+a.getDesc_com()+"',date_com='"+a.getDate_com()+"' WHERE id_com="+a.getId_com()+"";
                     
        
        
        try {
            cnx = DataBase.getInstance().getConnection();          
            st = cnx.createStatement();
            st.executeUpdate(requette);
            return true;
            
           
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }finally {
    
    if (st != null) {
        try {
            st.close();
        } catch (SQLException e) { /* Ignored */}
    }
    }}
    public boolean delete(Comentaire a){
        Connection cnx =null;
        Statement st = null;
        String requette = "DELETE FROM commentaire WHERE id_com="+a.getId_com()+"";
        try {
     cnx = DataBase.getInstance().getConnection();          
            st = cnx.createStatement();
            st.executeUpdate(requette);
            return true;
            
           
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }finally {
    
    if (st != null) {
        try {
            st.close();
        } catch (SQLException e) { /* Ignored */}
    }
    }
        
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
        public String totalC() throws SQLException {

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
       int nbr =lu.size();
        String nbrpacks = String.valueOf(nbr);
        
       return nbrpacks;
    }   

    public boolean Update(int id_user ,String desc_com , String date_com ,int id) {
              try {
            PreparedStatement pre = con.prepareStatement("update commentaire set id_user =?, desc_com=? , date_com=?   where id_com=? ;");
            
           
            pre.setInt(1, id_user);
            pre.setString(2, desc_com); 
            pre.setString(3, date_com);
          
            pre.setInt(4, id);

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
    public List<Comentaire> postCommentaires(int id_poste) throws SQLException {

        List<Comentaire> lu = new ArrayList<>();
        ste = con.createStatement();
        
        ResultSet rs = ste.executeQuery("SELECT * from commentaire WHERE id_post="+id_poste+";");
        while (rs.next()) {
            
          int id_com = rs.getInt("commentaire.id_com");
          int id_user = rs.getInt("commentaire.id_user");
          
            String desc_com = rs.getString("commentaire.desc_com");
             int id_post = rs.getInt("commentaire.id_post");
            
            

            Comentaire c = new Comentaire(id_com,id_user,desc_com,id_post);
            
            lu.add(c);
        }
        return lu;
    }
    
    
    
    }
    
    

