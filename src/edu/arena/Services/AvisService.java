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
        String req = "INSERT INTO `avis` (`score`, `commentaire`,`idProduit`,`idUtulisateur`) "
                + "VALUES ( ?, ? , ?, ?) ";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setInt(1, a.getScore());
        ps.setString(2, a.getCommentaire());
        ps.setInt(3, a.getIdProduit());
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
    public List<Avis> afficher() throws SQLException {
   
        List<Avis> avis = new ArrayList<>();
        stm = connexion.createStatement();
        ResultSet rs = stm.executeQuery("select * from avis");
        while (rs.next()) {
            int idAvis =rs.getInt("id");
            int score = rs.getInt("score");
            String commentaire = rs.getString("commentaire");
            int idProduit = rs.getInt("idProduit");
            int idUtulisateur  = rs.getInt("idUtulisateur ");
            Avis a = new Avis(idAvis,score,commentaire,idProduit,idUtulisateur);
            avis.add(a);
        }
        return avis;
    }
    
}
