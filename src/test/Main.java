/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import edu.arena.Services.AvisService;
import edu.arena.Services.CategoryReclamationService;
import edu.arena.Services.ProduitService;
import edu.arena.Services.ReclamationService;
import edu.arena.Services.UserService;
import edu.arena.entities.Avis;
import edu.arena.entities.CategoryReclamation;
import edu.arena.entities.Produit;
import edu.arena.entities.Reclamation;
import edu.arena.entities.User;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import edu.arena.utils.DataBase;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 *
 * @author DeathKnight
 */
public class Main {
    public static void main(String[] args) throws SQLException {
    /*
        
        
        User user = new User(3,"abdou ");
        User user2 = new User(4,"hamza ");

        

        UserService serviceUser = new UserService();
        serviceUser.ajouter(user);
        serviceUser.ajouter(user2);
        
        
        
        Produit produit = new Produit(3,"new product 1");      
        Produit produit2 = new Produit(4,"new product 2"); 
        
        ProduitService produitService = new ProduitService();
        produitService.ajouter(produit);
        produitService.ajouter(produit2);
        
        Avis avis = new Avis(1,5,"this product is ok",user.getId(),produit.getId());
        Avis avis2 = new Avis(2,1,"this product is decent",user2.getId(),produit2.getId());
        
        AvisService serviceAvis = new AvisService();
        serviceAvis.ajouter(avis);
        serviceAvis.ajouter(avis2);
        

        
        
       avis.setCommentaire("this product is whatver");
       System.out.println(avis.toString());
        serviceAvis.update(avis);
       
        CategoryReclamation categoryRec = new CategoryReclamation(3,"123");
        CategoryReclamation categoryRec2 = new CategoryReclamation(4,"aaa");
        
        System.out.println(categoryRec2.toString());
        
        
        CategoryReclamationService categoryReclamationService = new CategoryReclamationService();
        categoryReclamationService.ajouter(categoryRec);
        categoryReclamationService.ajouter(categoryRec2);
        
        categoryRec.setNom("changed the name again");
        categoryReclamationService.update(categoryRec);
        System.out.println(categoryRec.toString());
                

         
        Reclamation reclamation =
                new Reclamation(8,"title 3","something went wrong when tournament started 3",user.getId(),categoryRec.getId());
        
        Reclamation reclamation2 =
                new Reclamation(9,"title 4 ","didnt get cashprize 4",user2.getId(),categoryRec2.getId());
        
       
        
        ReclamationService reclamationService = new ReclamationService();
        
        reclamationService.ajouter(reclamation);
        reclamationService.ajouter(reclamation2);
        System.out.println(reclamation.toString());
        System.out.println(reclamation2.toString());
        
        reclamation.setEtat(true);
        reclamationService.update(reclamation);
        
         reclamation2.setMessage("nvm !! :c");
         reclamationService.update(reclamation2);
         reclamationService.delete(reclamation.getId());
        System.out.println(reclamation2.toString());
        
        */
        /*User user = new User(1,"abdou");
        User user2 = new User(2,"hamza");

        

        UserService serviceUser = new UserService();
        serviceUser.ajouter(user);
        serviceUser.ajouter(user2);
        
        
        
        Produit produit = new Produit(1,"new product 1");      
        Produit produit2 = new Produit(2,"new product 2"); 
        
        ProduitService produitService = new ProduitService();
        produitService.ajouter(produit);
        produitService.ajouter(produit2);
        
        Avis avis = new Avis(1,2,"this product is ok",user.getId(),produit.getId());
        Avis avis2 = new Avis(2,3,"this product is bad",user2.getId(),produit2.getId());
        
        AvisService serviceAvis = new AvisService();
        serviceAvis.ajouter(avis);
        serviceAvis.ajouter(avis2);
        serviceAvis.delete(avis.getId());
        */
     
        
        /*ReclamationService rec = new ReclamationService();
         List<Reclamation> list = new ArrayList<>();
               list = rec.afficher();
               list.forEach(r -> {
                              r.setTitre("test");
            try {
                rec.update(r);
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
                                          
               });

    }*/
    /*    AvisService avisService = new AvisService();
        System.out.println(avisService.scoreAvg(42));
        
       ReclamationService reclamationService = new ReclamationService();
       
       List<Reclamation> list1 = new ArrayList<>();
       List<Reclamation> list2 = new ArrayList<>();
       List<Reclamation> list3 = new ArrayList<>();
       
       list1 = reclamationService.recherche("test");
       list2 = reclamationService.recherche("t");
       list3 = reclamationService.recherche("x");
       //list 3 vide
       
       list1.forEach(r -> {
           
           System.out.println(r.toString());
       });
        System.out.println("____________________");
    
        
        list2.forEach(r -> {
           
           System.out.println(r.toString());
       });
        
         System.out.println("____________________");
    
        
        list3.forEach(r -> {
           
           System.out.println(r.toString());
       });
        
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        list3 = new ArrayList<>();
        
        list1 = reclamationService.tri(true);
        list2 = reclamationService.tri(false);
        
        
           list1.forEach(r -> {
           
           System.out.println(r.toString() + "tri = true");
       });
        System.out.println("____________________");
    
        
        list2.forEach(r -> {
           
           System.out.println(r.toString() + "tri = false");
       });
          
        HashMap<String,Integer> list4 = new HashMap<>();
        list4 = reclamationService.stat();
        System.out.println(list4.get("nbTotal"));
        System.out.println(list4.get("nbTrue"));
        System.out.println(list4.get("nbFalse"));
        */


    
       
           
    }
     
}
