/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.entities;

/**
 *
 * @author DeathKnight
 */
public class CategoryReclamation {  
    private int id;
    private String nom;

    
    public CategoryReclamation(int id ,String nom) {
        this.id = id;
        this.nom = nom;

    }
        
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "CategoryReclamation{" + "id=" + id + ", nom=" + nom + '}';
    }
    
}
