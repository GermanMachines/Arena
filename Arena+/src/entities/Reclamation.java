/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author DeathKnight
 */
public class Reclamation {
    static int nbReclamation = 0;
    private int id;
    private String message;
    private int userId;
    private int categoryReclamationId;
    
    public Reclamation(String message, int userId, int categoryReclamationId) {
        nbReclamation++;
        this.id = nbReclamation;
        this.message = message;
        this.userId = userId;
        this.categoryReclamationId = categoryReclamationId;

    }
    
    public Reclamation(int id, String message, int userId, int categoryReclamationId) {
        this.id = id;
        this.message = message;
        this.userId = userId;
        this.categoryReclamationId = categoryReclamationId;
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

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", message=" + message + ", user=" + userId + ", categoryReclamation=" + categoryReclamationId + '}';
    }
    
}
