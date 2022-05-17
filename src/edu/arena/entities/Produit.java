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
public class Produit {
    private int id;
    private String nom;
    //check
    private int avisId;

    public Produit(String nom){
        this.nom = nom;
    }
     public Produit(int id, String nom) {
        this.id = id;
        this.nom = nom;   
    }
    public Produit(int id, String nom, int avisId) {
        this.id = id;
        this.nom = nom;
        this.avisId = avisId;
    }

    public Produit() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    public int getAvisId() {
        return avisId;
    }

    public void setAvisId(int avisId) {
        this.avisId = avisId;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", nom=" + nom + ", reclamation=" + avisId + '}';
    }
    
    
}
