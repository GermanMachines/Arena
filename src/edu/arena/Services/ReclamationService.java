/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.Services;


import edu.arena.entities.Reclamation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import edu.arena.utils.DataBase;

/**
 *
 * @author DeathKnight
 */
public class ReclamationService implements IService<Reclamation> {
   Connection connexion;
   Statement stm;
     public ReclamationService() {
        connexion = DataBase.getInstance().getConnection();
    }
     
    @Override
    public void ajouter(Reclamation o) throws SQLException {
 
       stm = connexion.createStatement();
       String req = "INSERT INTO `reclamation`(`titre`, `message`, `etat`, `idUser`, `idCategoryReclamation`) VALUES  ( ? , ? , ? , ? , ?)";
       PreparedStatement pre = connexion.prepareStatement(req); 
       pre.setString(1,o.getTitre());
       pre.setString(2,o.getMessage());
       pre.setBoolean(3, o.getEtat());
       pre.setInt(4, o.getUserId());
       pre.setInt(5, o.getCategoryReclamationId());
       pre.executeUpdate();

    }

    @Override
    public void update(Reclamation o) throws SQLException {
         stm = connexion.createStatement();
         PreparedStatement pre = connexion.prepareStatement("UPDATE `reclamation` set `titre` = ? , `message` = ? ,`etat` = ? where id= ? ");
         pre.setString(1, o.getTitre());
         pre.setString(2, o.getMessage());
         pre.setBoolean(3, o.getEtat());
         pre.setInt(4, o.getId());
         pre.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
       PreparedStatement pre = connexion.prepareStatement("Delete from reclamation where id=? ");
       pre.setInt(1, id);
       pre.executeUpdate();  
    
    }

    @Override
    public List<Reclamation> afficher() throws SQLException {
        List<Reclamation> list = new ArrayList<>();
        stm = connexion.createStatement();
        ResultSet rs = stm.executeQuery("select * from reclamation");
        while (rs.next()) {
            int id = rs.getInt("id");
            String titre = rs.getString("titre");
            String message = rs.getString("message");
            int idUser = rs.getInt("idUser");
            int idCategoryReclamation  = rs.getInt("idCategoryReclamation");
            boolean etat  = rs.getBoolean("etat");
            Date date = rs.getDate("date");
       
            Reclamation rec = new Reclamation(id,titre,message,idUser,idCategoryReclamation,etat,date);
            list.add(rec);
        }
        return list;
    }
    
        public List<Reclamation> tri(boolean isAsc) throws SQLException {
        List<Reclamation> list = new ArrayList<>();
        stm = connexion.createStatement();  

         String ordre = isAsc ? "" : 
                 "desc";
       
        ResultSet rs = stm.executeQuery("select * from reclamation order by `date`" + ordre );
       while (rs.next()) {
            int id = rs.getInt("id");
            String titre = rs.getString("titre");
            String message = rs.getString("message");
            int idUser = rs.getInt("idUser");
            int idCategoryReclamation  = rs.getInt("idCategoryReclamation");
            boolean etat  = rs.getBoolean("etat");
            Date date = rs.getDate("date");
       
            Reclamation rec = new Reclamation(id,titre,message,idUser,idCategoryReclamation,etat,date);
            list.add(rec);
        }
        return list;
        
  }
        
         public List<Reclamation> recherche(String title) throws SQLException {
        List<Reclamation> list = new ArrayList<>();
        stm = connexion.createStatement();  


       
        ResultSet rs = stm.executeQuery("select * from reclamation where title like '%"+title+"%'");
       while (rs.next()) {
            int id = rs.getInt("id");
            String titre = rs.getString("titre");
            String message = rs.getString("message");
            int idUser = rs.getInt("idUser");
            int idCategoryReclamation  = rs.getInt("idCategoryReclamation");
            boolean etat  = rs.getBoolean("etat");
            Date date = rs.getDate("date");
       
            Reclamation rec = new Reclamation(id,titre,message,idUser,idCategoryReclamation,etat,date);
            list.add(rec);
        }
        return list;
        
  }

}
    

