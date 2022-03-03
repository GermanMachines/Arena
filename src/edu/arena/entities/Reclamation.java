/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.entities;
//import java.sql.Date
import java.util.Date;

/**
 *
 * @author DeathKnight
 */
public class Reclamation {
    private int id;
    private String titre;
    private String message;
    private int userId;
    private int categoryReclamationId;
    private boolean etat;
    private Date date;
    private CategoryReclamation categoryReclamation;
    private User user;
    private String nomCategory;
    private String nomUser;

    
    public Reclamation(){
        
    }
    
       public Reclamation(String titre,String message, User user, CategoryReclamation cat) {
        this.titre = titre;
        this.message = message;
        this.userId = user.getId();
        this.categoryReclamationId = cat.getId();
        this.etat = false;
        this.date = new Date();
        this.user = user;
        this.categoryReclamation = cat;
        this.nomUser = user.getNom();
        this.nomCategory = categoryReclamation.getNom();
        

    }
    
    //use this when constructing reclamation
        public Reclamation(String titre,String message, int userId, int categoryReclamationId) {
        this.titre = titre;
        this.message = message;
        this.userId = userId;
        this.categoryReclamationId = categoryReclamationId;
        this.etat = false;
        this.date = new Date();

    }
        
    //use this when getting data from db
    public Reclamation(int id,String titre,String message, int userId, int categoryReclamationId, boolean etat, Date date) {
        this.id = id;
        this.titre = titre;
        this.message = message;
        this.userId = userId;
        this.categoryReclamationId = categoryReclamationId;
        this.etat = etat;
        this.date = date;

    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCategoryReclamationId() {
        return categoryReclamationId;
    }

    public void setCategoryReclamationId(int categoryReclamationId) {
        this.categoryReclamationId = categoryReclamationId;
    }

    public boolean getEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Date getDate() {
        return date;
    }


    public boolean isEtat() {
        return etat;
    }

 
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public void setDate(Date d){
        this.date = d;
    }

    public CategoryReclamation getCategoryReclamation() {
        return categoryReclamation;
    }

    public void setCategoryReclamation(CategoryReclamation categoryReclamation) {
        this.categoryReclamation = categoryReclamation;
    }

    public String getNomCategory() {
        return nomCategory;
    }

    public void setNomCategory(String nomCategory) {
        this.nomCategory = nomCategory;
    }

    public String getNomUser() {
        return nomUser;
    }

    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", titre=" + titre + ", message=" + message + ", userId=" + userId + ", categoryReclamationId=" + categoryReclamationId + ", etat=" + etat + ", date=" + date + ", categoryReclamation=" + categoryReclamation + ", user=" + user + ", nomCategory=" + nomCategory + ", nomUser=" + nomUser + '}';
    }

  
  
   
    

   



 
    
}
