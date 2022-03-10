/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.Services;

import edu.arena.entities.Avis;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import edu.arena.utils.DataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author DeathKnight
 */
public class AvisService implements IService<Avis>{
   Connection connexion;
   Statement stm;
     public AvisService() {
        connexion = DataBase.getInstance().getConnection();

    }
     
    @Override
    public void ajouter(Avis a) throws SQLException {
        String req = "INSERT INTO `avis` (`score`, `commentaire`,`idJeux`,`idUtulisateur`) "
                + "VALUES ( ?, ? , ?, ?) ";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setInt(1, a.getScore());
        ps.setString(2, a.getCommentaire());
        ps.setInt(3, a.getIdJeux());
        ps.setInt(4, a.getIdUtulisateur());
        ps.executeUpdate();
    }

    @Override
    public void update(Avis a) throws SQLException {
        try {
            PreparedStatement pre = connexion.prepareStatement("UPDATE `avis` SET `score`= ?,`commentaire`= ? where `id` = ? ");

            pre.setInt(1, a.getScore());
            pre.setString(2, a.getCommentaire());   
            pre.setInt(3, a.getId());

            pre.executeUpdate();
               
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void delete(int id) throws SQLException {
        PreparedStatement pre = connexion.prepareStatement("Delete from avis where id=? ");
        pre.setInt(1, id);
        pre.executeUpdate();
          
    }

    @Override
    public ObservableList<Avis> afficher() throws SQLException {
        ObservableList<Avis> avis = FXCollections.observableArrayList();
        stm = connexion.createStatement();
        ResultSet rs = stm.executeQuery("select * from avis");
        while (rs.next()) {
            int idAvis =rs.getInt("id");
            int score = rs.getInt("score");
            String commentaire = rs.getString("commentaire");
            int idJeux = rs.getInt("idJeux");
            int idUtulisateur  = rs.getInt("idUtulisateur");
            
            Avis a = new Avis(idAvis,score,commentaire,idJeux,idUtulisateur);
            avis.add(a);
        }
        return avis;
    }
    
    //SELECT avis.id , avis.idUtulisateur, avis.idProduit , avis.score, avis.commentaire , utulisateur.username ,produit.nom FROM avis INNER JOIN utulisateur ON avis.idUtulisateur = utulisateur.id INNER JOIN produit ON produit.id = avis.idProduit;
  public ObservableList<Avis> getAll()throws SQLException{
        ObservableList<Avis> list = FXCollections.observableArrayList();
        stm = connexion.createStatement();
        
        String query ="SELECT avis.id , avis.idUtulisateur, avis.idJeux , avis.score, avis.commentaire , user.nom as username ,jeux.NomJeux FROM avis INNER JOIN user ON avis.idUtulisateur = user.id INNER JOIN jeux ON avis.idJeux = jeux.IdJeux";
        ResultSet rs = stm.executeQuery(query);
                while (rs.next()) {
            int idAvis = rs.getInt("id");
            int idUser = rs.getInt("idUtulisateur");
            int idJeux  = rs.getInt("idJeux");
            
            int score = rs.getInt("score");
            String commentaire = rs.getString("commentaire");
            String username = rs.getString("username");
            
            String NomJeux  = rs.getString("NomJeux");
    
       
            Avis avis = new Avis();
            
            avis.setId(idAvis);
            avis.setIdUser(idUser);
            avis.setIdJeux(idJeux);
            avis.setScore(score);
            avis.setCommentaire(commentaire);
            avis.setNomUtulisateur(username);
            avis.setNomJeux(NomJeux);
    
       
            list.add(avis);
        }
        return list;
    }
    
    public double scoreAvg(int id) throws SQLException{
        //List<Avis> avisProducts = new ArrayList<>();
        //AvisService AvisService = new AvisService();
        //avisProducts = AvisService.afficher();
//        stm = connexion.createStatement();
        PreparedStatement pre = connexion.prepareStatement("select AVG(score) as moyenne from `avis` where `idProduit` = ?");
        pre.setInt(1, id);
        ResultSet rs = pre.executeQuery();
       if(rs.next()){
           return rs.getDouble("moyenne");
       }
    
     return 0;
    }
}