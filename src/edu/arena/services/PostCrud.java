/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.services;

import edu.arena.entities.Post;
import edu.arena.utils.DataBase;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.stream.Collectors;

public class PostCrud {
         private Connection con;
    private Statement ste;

    public PostCrud() {
        con = DataBase.getInstance().getConnection();

    }
    
    
    
    
     public void ajouter(Post p) throws SQLException {
        PreparedStatement pre = con.prepareStatement("INSERT INTO post (titre,auteur,img_post,date_post)VALUES (?,?,?,?);");
        pre.setString(1, p.getTitre());
        pre.setString(2, p.getAuteur());
        pre.setString(3, p.getImg_post());
        pre.setString(4, p.getDate_post());


        
        pre.executeUpdate();
    }

       public boolean update(Post a){
        Connection cnx =null;
        Statement st = null;
        
        
        String requette = "UPDATE post SET titre='"+a.getTitre()+"',auteur='"+a.getAuteur()+"',img_post='"+a.getImg_post()+"',date_post='"+a.getDate_post()+"' WHERE id_post="+a.getId_post()+"";
                     
        
        
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

   public boolean delete(Post a){
        Connection cnx =null;
        Statement st = null;
        String requette = "DELETE FROM post WHERE id_post="+a.getId_post()+"";
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
    
        public List<Post> showpost() throws SQLException {

        List<Post> lu = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select id_post ,titre,auteur,img_post,date_post from post");
        while (rs.next()) {
            int id_post=rs.getInt("id_post");
            String titre = rs.getString("titre");
            String auteur = rs.getString("auteur");
            String img_post = rs.getString("img_post");
            String date_post = rs.getString("date_post");
            
            
            Post p = new Post(id_post ,titre,auteur,img_post,date_post);
            lu.add(p);
        }
        return lu;
    }
  public ObservableList<Post> getPosteList() throws SQLException {
           
        ObservableList<Post> commandelist = FXCollections.observableArrayList();
        
         List <Post> id = new ArrayList<>(); 
        Statement stm = con.createStatement();
       ResultSet rs = ste.executeQuery("select id_post ,titre,auteur,img_post,date_post from post");

        //ResultSet rs;
     
        Post post;
        while (rs.next()) {
           post= new Post(rs.getInt("id_post"), rs.getString("titre"), rs.getString("auteur"), rs.getString("img_post"), rs.getString("date_post")); 
            //System.out.println(events);
            commandelist.add(post);

        }
        return commandelist;

    }
  
    
     public List<Post> sortByAuteur() throws SQLException{
        
        List<Post> evenements= showpost();
        List<Post> resultat=evenements.stream().sorted(Comparator.comparing(Post::getAuteur)).collect(Collectors.toList());
        return resultat;
    }
    public List<Post> findbyLieu(String auteur) throws SQLException {
       List<Post> evenements=showpost();
        List<Post> resultat=evenements.stream().filter(evenement->auteur.equals(evenement.getAuteur())).collect(Collectors.toList());
        return resultat;
    }
}
