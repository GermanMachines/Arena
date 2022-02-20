/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import entities.Reclamation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;

/**
 *
 * @author DeathKnight
 */
public class ReclamationService implements IService<Reclamation> {
   Connection connexion;
   Statement stm;
     public ReclamationService() {
        connexion = MyDB.getInstance().getConnexion();
    }
     
    @Override
    public void ajouter(Reclamation o) throws SQLException {
 
       stm = connexion.createStatement();
       String req = "INSERT INTO `reclamation`(`message`, `idUser`, `idCategoryReclamation`) VALUES (? , ? , ?)";
       PreparedStatement pre = connexion.prepareStatement(req);   
       pre.setString(1,o.getMessage());
       pre.setInt(2, o.getUserId());
       pre.setInt(3, o.getCategoryReclamationId());
       pre.executeUpdate();

    }

    @Override
    public void update(Reclamation o) throws SQLException {
         stm = connexion.createStatement();
         PreparedStatement pre = connexion.prepareStatement("update reclamation set message =? where id=? ");
         pre.setString(1, o.getMessage());
         pre.setInt(2, o.getId());
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
            String message = rs.getString("message");
            int idUser = rs.getInt("idUser");
            int idCategoryReclamation  = rs.getInt("idCategoryReclamation");
       
            Reclamation rec = new Reclamation(id,message,idUser,idCategoryReclamation);
            list.add(rec);
        }
        return list;
    }
  }
    

