/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arena1;

import edu.arena.entities.Equipe;
import edu.arena.entities.User;
import edu.arena.services.EquipeService;
import edu.arena.services.UserService;
import java.sql.SQLException;

/**
 *
 * @author LENOVO
 */
public class Arena1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        
        Equipe e = new Equipe("EST","image.jpg",5,"tunis");
        Equipe e1 = new Equipe("CSS","image.jpg",5,"tunis");
        Equipe e3 = new Equipe("try","try",7,"try");
        EquipeService sp = new EquipeService();
        User p = new User(26,"Nouha","nounou","nour.jpg","@esprit","nour","25478965",5,"admin","oui");
      //  sp.supprimer(13);
       User u = new User (27,"nounou", "nou", "image.jpg", "@", "lol", "2545789", 1, "admin","non");
        UserService us= new UserService ();
           // sp.ajouter(e3);
           // sp.ajouter(e1);
           // us.supprimer(8);
           // us.ajouter(u);
            //us.ajouter2(p);
       // User u2 = new User ("Nouha", "", "u", "@", "lol", "2545789",  "admin");
  //sp.modifier(2, e1);
            us.modifier3(u);
             System.out.println(u);
        // sp.modifier3(e1);
                 // us.modifier2(5,e3);
         //   
    //   System.out.println(us.encrypt("nour"));
     //  System.out.println(us.getRole("nounou"));
   // us.envoyerCodeVerif("boujmiln@gmail.com"); 
 //   us.blockUtilisateur(p);
        try {
            System.out.println(sp.afficher());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
      
        
    }
    

