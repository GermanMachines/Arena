package edu.arena.services;

import edu.arena.entities.Equipe;
import edu.arena.entities.User;
import edu.arena.services.IUser;
import edu.arena.utils.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.mail.MessagingException;

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
    private int randomCode;

    public int getRandomCode() {
        return randomCode;
    }

    public UserService() {
        connexion = MyDB.getInstance().getConnexion();

    }

    @Override
    public void ajouter(User p) throws SQLException {

        String req = "INSERT INTO `user` (`nom`, `surnom`, `image`, `email` , `mdp` ,  `telephone`,`id_equipe`, `role`, `block`) "
                + "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
        try {
            PreparedStatement ps = connexion.prepareStatement(req);
            ps.setString(1, p.getNom());
            ps.setString(2, p.getSurnom());
            ps.setString(3, p.getImage());
            ps.setString(4, p.getEmail());
            ps.setString(5, p.getMdp());
            ps.setString(6, p.getTelephone());
            ps.setInt(7, p.getId_equipe());
            ps.setString(8, "joueur");
            ps.setString(9, "non");   


            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void ajouter2(User p) throws SQLException {

        String req = "INSERT INTO `user`  (`nom`, `surnom`, `image`, `email` , `mdp` ,  `telephone`, `role`, `block` ) "
                + "VALUES ( ?, ?, ?, ?, ?, ? , ?,?) ";
        try {
            PreparedStatement ps = connexion.prepareStatement(req);
            ps.setString(1, p.getNom());
            ps.setString(2, p.getSurnom());
            ps.setString(3, p.getImage());
            ps.setString(4, p.getEmail());
            ps.setString(5, p.getMdp());
            ps.setString(6, p.getTelephone());
            ps.setString(7, "joueur");
            ps.setString(8, "non");
          

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

        req = "UPDATE `user` SET  `surnom`=? ,`nom`=?,`image`=?,`email`=?,`mdp`=?,`telephone`=?,`id_equipe`=?, `role`=? , `block`=? WHERE id =?";

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
            ps.setString(9, p.getBlock());

            ps.setInt(10, id);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    public void modifier2( User p) throws SQLException {
        //request 
        String req;

        req = "UPDATE `user` SET  `mdp`=?,`telephone`=?,`id_equipe`=?, `role`=?, `block` WHERE email =?";

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
              ps.setString(9, p.getBlock());

            ps.setString(10, p.getEmail());
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
        String req = "select * from user where block = 'non' ";
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
                    rst.getString("role"),
                    rst.getString("block")
            );
            users.add(p);
        }
        return users;

    }
     public String getNom(int id) {
        String email = "";
        try {
            PreparedStatement pre = connexion.prepareStatement("select nom from user where id=?");
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                email = rs.getString(1);
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return email;
    }
    public static String getRole(String nom) {
        String role = "";
         Connection connexion;
    Statement stm;
            connexion = MyDB.getInstance().getConnexion();

        try {
            
            PreparedStatement pre;
            pre = connexion.prepareStatement("select `role` from user where nom=?");
            pre.setString(1, nom);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                role = rs.getString(1);
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return role;
        
        
    }
    public static String getBlock(String nom) {
        String block = "";
         Connection connexion;
    Statement stm;
            connexion = MyDB.getInstance().getConnexion();

        try {
            
            PreparedStatement pre;
            pre = connexion.prepareStatement("select `block` from user where nom=?");
            pre.setString(1, nom);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                block = rs.getString(1);
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return block;
        
        
    }
    
 /*   public boolean updatepassword(int id, String password) {

        try {
            PreparedStatement pre = connexion.prepareStatement("update user set mdp =? where id=? ;");
            String hashed2 = BCrypt.hashpw(password, BCrypt.gensalt(13));
            hashed2 = "$2y$" + hashed2.substring(4);

            pre.setString(1, hashed2);
            pre.setInt(2, id);

            if (pre.executeUpdate() != 0) {
                System.out.println("user's mdp is up to date");
                return true;
            }
            System.out.println("id user not found!!!");
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    
       public int login(User u) {
        int count = 0;

        PreparedStatement preparedStatement;
        try {
            PreparedStatement pre = connexion.prepareStatement("SELECT * FROM user WHERE email = ?");
            pre.setString(1, u.getEmail());
            ResultSet rs = pre.executeQuery();
//            System.out.println(rs.getString("roles"));

            while (rs.next()) {
                String crypted1 = "$2a" + rs.getString("mdp").substring(3);
                if (BCrypt.checkpw(u.getMdp(), crypted1)) {
                    u.setId(rs.getInt("id"));
                    System.out.println("connnectee");
                    return 1;
                }
            }

            System.out.println(" non connnectee");
                 
            return 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return count;
    }
       
          public boolean checkUser(String username, String password) {
        boolean verif = false;

        try {
            PreparedStatement pt = connexion.prepareStatement("SELECT pwd FROM user where email=?");
            pt.setString(1, username);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                String crypted1 = "$2a" + rs.getString("password").substring(3);
                if (BCrypt.checkpw(password, crypted1)) {
                    System.out.println("connecte");
                    return true;
                } else {
                    System.out.println("nononoconnecte");
                }

            }

        } catch (SQLException ex) {
            ex.getMessage();
        }
        return false;
    }

 */
    
    
    public static int CompteExiste(String nom,String mdp)
    {   
        int resultat=0 ;
        try {
            String requete = "SELECT * FROM user WHERE (nom='"+nom+"' AND mdp='"+mdp+"')";
            Statement st;
            st = MyDB.getInstance().getConnexion().createStatement();
            ResultSet rs = st.executeQuery(requete);
            int n = 0;
            if ( rs.next() ) {
                n = rs.getInt(1);
            }
            if (n!=0)
            {
                resultat= n;
            }
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
        }
     return resultat; 
    }
    public static boolean existe(String nomDutilisateur)
    {
        boolean resultat=false;
        try {
            String requete = "SELECT EXISTS(SELECT * FROM user WHERE nom='"+nomDutilisateur+"')";
            Statement st = MyDB.getInstance().getConnexion().createStatement();
            ResultSet rs = st.executeQuery(requete);
            int n = 0;
            if ( rs.next() ) {
                n = rs.getInt(1);
            }
            if (n!=0)
            {
                resultat= true;
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return resultat;
    }
    
   
    

public static String encrypt(String  mdp){
    String result="";
    String alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    int i=0;
    while(i<mdp.length())
    {
        char c=mdp.charAt(i);
        int j=alphabet.indexOf(c);
        if(j!=-1)
        {
            if(i+j>51)
            {
                int k=i+j-52;
                result+= alphabet.charAt(k);
            }
            else
                result+= alphabet.charAt(i+j);
        }
        else
            result+= c;
        i++;
    }
    return  result;
    }
  public void envoyerCodeVerif(String email) {
        Random rand = new Random();
        randomCode = rand.nextInt(999999);
        String subject = "Reseting Code";
        String message = "Your reset code is " + randomCode + "";

        try {
            JavaMailUtil.sendMail(email, subject, message);
        } catch (MessagingException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 public void modifier3( User p) throws SQLException {
        //request 
        String req;

        req = "UPDATE `user` SET  `surnom`=? ,`nom`=?,`image`=?,`email`=?,`mdp`=?,`telephone`=?,`id_equipe`=?, `role`=? , block =? WHERE id =?";

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
            ps.setString(9, p.getBlock());

            ps.setInt(10, p.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

    }}
  public void modifierMotDePasse(User user, String newMdp, String confrimNewMdp) throws SQLException {
       
           System.out.println("nou");
            String t = encrypt(newMdp);
            user.setMdp(t);
           

            this.modifier3(user);
                       System.out.println(user);

        

    }
       
 public boolean blockUtilisateur(User user) {

        String req = "SELECT `block` FROM `user` WHERE id=?";
        try {
          PreparedStatement  ps = connexion.prepareStatement(req);
            ps.setInt(1, user.getId());
            ResultSet us = ps.executeQuery();
            System.out.print("id trouv" );

            if ((us.next()) && (us.getString(1).equals("non"))) {
                String req2 = "UPDATE `user` SET `block`=? WHERE id=?";

                try {
                    ps = connexion.prepareStatement(req2);
                    ps.setString(1, "oui");
                    ps.setInt(2, user.getId());
                    ps.executeUpdate();
                    System.out.println("utilisateur bloqué");

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                return true;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;

    }
     public ObservableList<User> show() throws SQLException {
          ObservableList<User> users = FXCollections.observableArrayList();
        String req = "select * from user where block = 'non'";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            User p;
            p = new User(
                    rst.getInt("id"),//or rst.getInt(1)
                    rst.getString("surnom"),
                    rst.getString("nom"),
                    rst.getString("image"),
                    rst.getString("email"),
                    rst.getString("mdp"),
                    rst.getString("telephone"),
                    rst.getInt("id_equipe"),
                    rst.getString("role"),
                    rst.getString("block")
                    
            );
            users.add(p);
        }
        return users;  
    }
     
      public ObservableList<User> showb() throws SQLException {
          ObservableList<User> users = FXCollections.observableArrayList();
        String req = "select * from user where block = 'oui'";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            User p;
            p = new User(
                    rst.getInt("id"),//or rst.getInt(1)
                    rst.getString("surnom"),
                    rst.getString("nom"),
                    rst.getString("image"),
                    rst.getString("email"),
                    rst.getString("mdp"),
                    rst.getString("telephone"),
                    rst.getInt("id_equipe"),
                    rst.getString("role"),
                    rst.getString("block")
                    
            );
            users.add(p);
        }
        return users;  
    }
     
     
     public User findUsertbymail(String mail) {
        User u = null;
        try {

            PreparedStatement pre = connexion.prepareStatement("Select * from user  WHERE email=? ");
            pre.setString(1, mail);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String surnom = rs.getString("surnom");
                String nom = rs.getString("nom");
                String image = rs.getString("image");
               // String email = rs.getString("email");
                String mdp = rs.getString("mdp");
                String telephone = rs.getString("telephone");
                int id_equipe = rs.getInt("id_equipe");
                String role = rs.getString("role");
                String block = rs.getString("block");
                
                u = new User(id, surnom, nom, image, mail, mdp,telephone , id_equipe, role, block);
            }
            return u;
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return u;
    }
     
     public static boolean mailIsValid(String mail){
        Pattern pattern = Pattern.compile("^[a-zA-z0-9]*@[a-zA-z0-9]*\\.[a-zA-z0-9]*$");
        Matcher matcher = pattern.matcher(mail);
        boolean matchFound = matcher.find();
        return matchFound;
    }
     
     
         public User findbyidcode(int idcode) {
        User u = null;
        try {

            PreparedStatement pre = connexion.prepareStatement("Select * from user  WHERE id=? ");
            pre.setInt(1, idcode);
            ResultSet rs = pre.executeQuery();
           
               while (rs.next()) {
               // int id = rs.getInt("id");
                String surnom = rs.getString("surnom");
                String nom = rs.getString("nom");
                String image = rs.getString("image");
                String email = rs.getString("email");
                String mdp = rs.getString("mdp");
                String telephone = rs.getString("telephone");
                int id_equipe = rs.getInt("id_equipe");
                String role = rs.getString("role");
                String block = rs.getString("block");
                
                u = new User( idcode, surnom, nom, image, email, mdp,telephone , id_equipe, role, block);
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }

        return u;
    }
         
          public String getImage(int id) {
        String image = "";
        try {
            PreparedStatement pre = connexion.prepareStatement("select image from user where id=?");
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                image = rs.getString(1);
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return image;
    }
          
            public boolean updateimage(int id, String image) {

        try {
            PreparedStatement pre = connexion.prepareStatement("update user set image =? where id=? ;");
            pre.setString(1, image);
            pre.setInt(2, id);

            if (pre.executeUpdate() != 0) {
                System.out.println("user's image is up to date");
                return true;
            }
            System.out.println("id user not found!!!");
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public boolean deblockUtilisateur(User J) {
        
        
        String req = "SELECT `block` FROM `user` WHERE id=?";
        try {
          PreparedStatement  ps = connexion.prepareStatement(req);
            ps.setInt(1, J.getId());
            ResultSet us = ps.executeQuery();
            System.out.print("id trouv" );

            if ((us.next()) && (us.getString(1).equals("oui"))) {
                String req2 = "UPDATE `user` SET `block`=? WHERE id=?";

                try {
                    ps = connexion.prepareStatement(req2);
                    ps.setString(1, "non");
                    ps.setInt(2, J.getId());
                    ps.executeUpdate();
                    System.out.println("utilisateur bloqué");

                } catch (SQLException ex) {
                    System.out.println(ex.getMessage()); 
                }
                return true;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
      public int findEquipe(Equipe u) {
        int count = 0;

        PreparedStatement loginPs;
        try {

            String loginQry = "select * from user where id_equipe = ?";
            loginPs = connexion.prepareStatement(loginQry);
            loginPs.setInt(1, u.getIdEquipe());
            ResultSet rs = loginPs.executeQuery();
            while (rs.next()) {
                count++;
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return count;
    }
}   