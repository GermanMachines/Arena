/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.entities;

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
    
    //use this when constructing reclamation
        public Reclamation(int id,String titre,String message, int userId, int categoryReclamationId) {
        this.id = id;
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

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", titre=" + titre + ", message=" + message + ", userId=" + userId + ", categoryReclamationId=" + categoryReclamationId + ", etat=" + etat + ", date=" + date + '}';
    }

   



 
    
}
