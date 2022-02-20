/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import Services.AvisService;
import Services.CategoryReclamationService;
import Services.ProduitService;
import Services.ReclamationService;
import Services.UserService;
import entities.Avis;
import entities.CategoryReclamation;
import entities.Produit;
import entities.Reclamation;
import entities.User;
import java.sql.SQLException;
import utils.MyDB;

/**
 *
 * @author DeathKnight
 */
public class Main {
    public static void main(String[] args) throws SQLException {
 /*      
        User user = new User("abdou");
        User user2 = new User("hamza");

        

        UserService serviceUser = new UserService();
        serviceUser.ajouter(user);
        serviceUser.ajouter(user2);
        
        
        
        Produit produit = new Produit("new product 1");      
        Produit produit2 = new Produit("new product 2"); 
        
        ProduitService produitService = new ProduitService();
        produitService.ajouter(produit);
        produitService.ajouter(produit2);
        
        Avis avis = new Avis(2,"this product is ok",user.getId(),produit.getId());
        Avis avis2 = new Avis(3,"this product is bad",user2.getId(),produit2.getId());
        
        AvisService serviceAvis = new AvisService();
        serviceAvis.ajouter(avis);
        serviceAvis.ajouter(avis2);
        

        
        
       avis.setCommentaire("this product is decent");
       System.out.println(avis.toString());
        serviceAvis.update(avis);
       
        CategoryReclamation categoryRec = new CategoryReclamation("bug");
        CategoryReclamation categoryRec2 = new CategoryReclamation("auth");
        
        System.out.println(categoryRec2.toString());
        
        
        CategoryReclamationService categoryReclamationService = new CategoryReclamationService();
        categoryReclamationService.ajouter(categoryRec);
        categoryReclamationService.ajouter(categoryRec2);
        
        categoryRec.setNom("new name");
        categoryReclamationService.update(categoryRec);
        System.out.println(categoryRec.toString());
                

         
        Reclamation reclamation =
                new Reclamation("something went wrong when tournament started",user.getId(),categoryRec.getId());
        
        Reclamation reclamation2 =
                new Reclamation("didnt get cashprize",user2.getId(),categoryRec2.getId());
        
       
        
        ReclamationService reclamationService = new ReclamationService();
        
        reclamationService.ajouter(reclamation);
        reclamationService.ajouter(reclamation2);
        
         reclamation2.setMessage("nvm !!");
         reclamationService.update(reclamation2);
         reclamationService.delete(reclamation.getId());
        System.out.println(reclamation2.toString());
        
        */
 
        
        User user = new User("abdou");
        User user2 = new User("hamza");

        

        UserService serviceUser = new UserService();
        serviceUser.ajouter(user);
        serviceUser.ajouter(user2);
        
        
        
        Produit produit = new Produit("new product 1");      
        Produit produit2 = new Produit("new product 2"); 
        
        ProduitService produitService = new ProduitService();
        produitService.ajouter(produit);
        produitService.ajouter(produit2);
        
        Avis avis = new Avis(2,"this product is ok",user.getId(),produit.getId());
        Avis avis2 = new Avis(3,"this product is bad",user2.getId(),produit2.getId());
        
        AvisService serviceAvis = new AvisService();
        serviceAvis.ajouter(avis);
        serviceAvis.ajouter(avis2);
        serviceAvis.delete(avis.getId());
        
    }
    
}
