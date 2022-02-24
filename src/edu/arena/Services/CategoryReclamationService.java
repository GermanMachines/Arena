    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.Services;

import edu.arena.Services.IService;
import edu.arena.entities.CategoryReclamation;
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
public class CategoryReclamationService implements IService<CategoryReclamation> {
   Connection connexion;
   Statement stm;
     public CategoryReclamationService() {
        connexion = DataBase.getInstance().getConnection();
    }
    @Override
    public void ajouter(CategoryReclamation o) throws SQLException {
        String req = "INSERT INTO `categoriereclamation`(`nom`) VALUES ( ? ) ";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setString(1, o.getNom());
        ps.executeUpdate();
    }

    @Override
    public void update(CategoryReclamation o) throws SQLException {

         stm = connexion.createStatement();
         PreparedStatement pre = connexion.prepareStatement("update categoriereclamation set nom =? where id=? ");
         pre.setString(1, o.getNom());
         pre.setInt(2, o.getId());
         pre.executeUpdate();
   
    }

    @Override
    public void delete(int id) throws SQLException {
       PreparedStatement pre = connexion.prepareStatement("Delete from categoriereclamation where id=? ");
       pre.setInt(1, id);
       pre.executeUpdate();
            

    }

    @Override
    public List<CategoryReclamation> afficher() throws SQLException {
           List<CategoryReclamation> list = new ArrayList<>();
        stm = connexion.createStatement();
        ResultSet rs = stm.executeQuery("select id,nom from categoryreclamation");
        while (rs.next()) {
            int id = rs.getInt("id");
            String nom = rs.getString("nom");
            CategoryReclamation categoryRec = new CategoryReclamation(id,nom);
            list.add(categoryRec);
        }
        return list;
    }
}
    

