package edu.arena.services;

import edu.arena.entities.User;
import edu.arena.services.IUser;
import edu.arena.utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author LENOVO
 */
public class UserService implements IUser<User> {

    Connection connexion;
    Statement stm;

    public UserService() {
        connexion = DataBase.getInstance().getConnection();

    }

    @Override
    public void ajouter(User p) throws SQLException {

        String req = "INSERT INTO `user` (`nom`, `surnom`, `image`, `email` , `mdp` ,  `telephone`,`id_equipe`, `role`) "
                + "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?) ";
        try {
            PreparedStatement ps = connexion.prepareStatement(req);
            ps.setString(1, p.getNom());
            ps.setString(2, p.getSurnom());
            ps.setString(3, p.getImage());
            ps.setString(4, p.getEmail());
            ps.setString(5, p.getMdp());
            ps.setString(6, p.getTelephone());
            ps.setInt(7, p.getId_equipe());
            ps.setString(8, p.getRole());

            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /* public void ajouter2(User p) throws SQLException {
        
        String req = "INSERT INTO `user` (`nom`, `surnom`, `image`, `email` , `mdp` ,  `telephone`, `role`) "
                + "VALUES ( ?, ?, ?, ?, ?, ?, ?) ";
        try {
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setString(1, p.getNom());
        ps.setString(2, p.getSurnom());
        ps.setString(3, p.getImage());
        ps.setString(4, p.getEmail());
        ps.setString(5, p.getMdp());
        ps.setString(6, p.getTelephone());
        
        ps.setString(8, p.getRole());

        

        ps.executeUpdate();
         } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
     */
    @Override
    public void modifier(int id, User p) throws SQLException {
        //request 
        String req;

        req = "UPDATE `user` SET  `surnom`=? ,`nom`=?,`image`=?,`email`=?,`mdp`=?,`telephone`=?,`id_equipe`=?, `role`=? WHERE id =?";

        try {
            PreparedStatement ps = connexion.prepareStatement(req);
            ps.setString(1, p.getSurnom());
            ps.setString(2, p.getNom());
            ps.setString(3, p.getImage());
            ps.setString(4, p.getEmail());
            ps.setString(5, p.getMdp());
            ps.setString(6, p.getTelephone());
             ps.setInt(7, p.getId_equipe());
            ps.setString(8, p.getRole());
            ps.setInt(9, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void supprimer(int iduser) throws SQLException {
        try {
            PreparedStatement pre = connexion.prepareStatement("Delete from user where id=? ;");
            pre.setInt(1, iduser);
            if (pre.executeUpdate() != 0) {
                System.out.println("user Deleted");

            }

        } catch (SQLException ex) {
            ex.getMessage();
        }
    }
    
   
  /*  @Override
    public List<User> chercher(List<User> initialList, String input) {
        List<User> strList = initialList.stream()
                           .map( User::concat )
                           .filter(pt -> pt.contains(input))
                           .map(pt -> new User(Integer.parseInt(pt.split(".@.")[0]),pt.split(".@.")[1],pt.split(".@.")[2],pt.split(".@.")[3],pt.split(".@.")[4]))
                           .collect( Collectors.toList() );
        
        return strList;
    }*/
    @Override
    public List<User> afficher() throws SQLException {
        List<User> users = new ArrayList<>();
        String req = "select * from user";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            User p;
            p = new User(
                    rst.getInt("id"),//or rst.getInt(1)
                    rst.getString("nom"),
                    rst.getString("surnom"),
                    rst.getString("image"),
                    rst.getString("email"),
                    rst.getString("mdp"),
                    rst.getString("telephone"),
                    rst.getInt("id_equipe"),
                    rst.getString("role")
            );
            users.add(p);
        }
        return users;

    }

}
